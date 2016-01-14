package managers;

import baseClasses.ErrorJDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.LuggageControl;
import org.apache.commons.codec.binary.Base64;

/**
 * Manage security aspects such as user input filtering and login management
 *
 * @author Dantali0n
 */
public class SecurityMan { 

    // reference to defined version of securityupdates
    private LuggageControl luggageControl;
    
    // used to manage a connection to the database
    private DatabaseMan databaseMan;
    
    private AtomicInteger userPermissions = new AtomicInteger(0);
    
    private AtomicBoolean userLoggedIn = new AtomicBoolean(false);

    // <editor-fold defaultstate="collapsed" desc="time out, timertask, timer and default time out time">
    // Custom timertask which contains reference to the securityupdate event interface.
    private class timeOutTimerTask extends TimerTask {

        LuggageControl luggageControlTimer;

        public timeOutTimerTask(LuggageControl luggageControl) {
        }

        @Override
        public void run() {
            if(userAFK.get()) {
                userPermissions.set(0);
                userLoggedIn.set(false);
                luggageControl.userTimeOut();
                this.cancel();
            }
            else {
                userAFK.set(true);
            }
        }
    }

    // default timeout is 300000 milliseconds (5 minutes)
    private int defaultTimeOutTime = 300000;

    private int timeOutTime = defaultTimeOutTime;
    
    private AtomicBoolean userAFK = new AtomicBoolean(true);

    // timer to manage when the user has timed out.
    private Timer timeOut;

    // instance of our custom timer
    private timeOutTimerTask fireTimeOut;

    // </editor-fold>
    
    /**
     * Manage logging in automatic time outs and other security aspects
     *
     * @param luggageControl reference to main class push events
     */
    public SecurityMan(LuggageControl luggageControl) {

        // pass the defined class reference to the event interface
        // now we can call our abstract functions
        this.luggageControl = luggageControl;
        
        // our constant connection to the database
        this.databaseMan = new DatabaseMan();

        // create the timer and start the userTimeOut task
        timeOut = new Timer("userTimeOutTimer");
        fireTimeOut = new timeOutTimerTask(this.luggageControl);
    }
    
    /**
     *
     * @param password
     * @param saltString
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static String encodePassword(String password, String saltString) throws NoSuchAlgorithmException, Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        byte[] salt = saltString.getBytes();
        byte[] passarray = new byte[password.getBytes().length + salt.length];

        System.arraycopy(password.getBytes(), 0, passarray, 0, password.getBytes().length);
        System.arraycopy(salt, 0, passarray, password.getBytes().length, salt.length);

        password = new String(Base64.encodeBase64(md.digest(passarray)));
        
        return password;
    }
    
    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static String createSalt() throws NoSuchAlgorithmException, Exception {
        SecureRandom number;
        number = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[12];
        number.nextBytes(salt);
        return new String(Base64.encodeBase64(salt));
    }
    
    /**
     * Log the user in and handle session and permissions
     *
     * @param username username as described in database
     * @param password password as described in database
     * @return true if successful, false when failed.
     */
    public boolean logInUser(String username, String password) {
        
        // get the salt, try catch in case our user does not exist
        try {
            String salt = databaseMan.queryOneResult("SELECT salt FROM user WHERE username = ?", new String[]{username});
            password = this.encodePassword(password, salt);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        String[] values1 = new String[2];
        values1[0] = username;
        values1[1] = password;
        String query = "SELECT permission FROM user WHERE username = ? AND password = ?";
        
        //This query will return a string, it only returns 1 value!
        String result = databaseMan.queryOneResult(query, values1);
        
        username = null;
        password = null;
        if (!result.equals("")) {
            int resultInt = Byte.parseByte(result);
            if (resultInt == 0) {
                //oude gebruiker gegevens zonder inlog
                return false;
            }else if (resultInt == 1) {
                //gebruiker
                this.userPermissions.set(1);
                this.luggageControl.switchJPanel(luggageControl.HOME_SCREEN_EMPLOYEE);
            }else if (resultInt == 2) {
                //manager
                this.userPermissions.set(2);
                this.luggageControl.switchJPanel(luggageControl.HOME_SCREEN_MANAGER);
            }else if (resultInt == 3) {
                //admin
                this.userPermissions.set(3);
                this.luggageControl.switchJPanel(luggageControl.HOME_SCREEN_ADMINISTRATOR);
            }
            // set the user permission and start the time out timer.
            this.resetTimer();
            this.userLoggedIn.set(true);
            return true;
        } else {
            System.out.println("User was not found in the database");
            return false;
        }
    }
    
    /**
     * 
     * @return user permissions level 
     */
    public int getPermissions() {
        return userPermissions.get();
    }
    
    /**
     * 
     * @return true if the user is logged in, false if the user is not.
     */
    public boolean getLoggedIn() {
        return userLoggedIn.get();
    }
    
    /**
     * 
     * @return true if the user is afk, false if not
     */
    public boolean getUserAFK() {
        return this.userAFK.get();
    }
    
    /**
     * Sets the user afk this is used to trigger the user timeout
     * @param useAFK true if the user is afk false if he's not
     */
    public void setUserAFK(boolean useAFK) {
        this.userAFK.set(useAFK);
    }

    // <editor-fold defaultstate="collapsed" desc="set, get, reset timeout time">
    /**
     * Gets the timeout time and returns it Please note that the timer is not
     * guaranteed to run at this interval
     *
     * @return timeout time in milliseconds
     */
    public int getTimeOutTime() {
        return timeOutTime;
    }
    
    /**
     * Resets the timeout time to the default time Please note that the timer
     * time does not change until you reset the timer.
     * @param resetTimer
     * @return true if succeeded, false when failed.
     */
    public boolean resetTimeOutTime(boolean resetTimer) {
        try {
            timeOutTime = defaultTimeOutTime;
            if(resetTimer) {
                this.resetTimer();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Resets the timeout timer to zero.
     * @return true if succeeded, false when failed.
     */
    public boolean resetTimer() {
        try {
            timeOut.cancel();
            timeOut.purge();
            timeOut = null;
            timeOut = new Timer("userTimeOutTimer");
            fireTimeOut = new timeOutTimerTask(luggageControl);
            this.startTimer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Sets the timeout time to the new specified time Please note that the
     * timer time does not change until you reset the timer.
     *
     * @param newTimeOutTime
     * @param resetTimer
     * @return true if succeeded, false when failed.
     */
    public boolean setTimeOutTime(int newTimeOutTime, boolean resetTimer) {
        try {
            timeOutTime = newTimeOutTime;
            if(resetTimer) {
                this.resetTimer();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     *
     */
    public void startTimer() {
        //  start the timer with the timertask
        timeOut.scheduleAtFixedRate(fireTimeOut, timeOutTime, timeOutTime);
    }
    // </editor-fold>
}
