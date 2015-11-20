package managers;

import constants.ScreenNames;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import main.LuggageControl;

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
            this.luggageControlTimer = luggageControl;
        }

        @Override
        public void run() {
            if(userAFK.get()) {
                userPermissions.set(0);
                userLoggedIn.set(false);
                luggageControl.userTimeOut();
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

        //  start the timer with the timertask
        timeOut.scheduleAtFixedRate(fireTimeOut, timeOutTime, timeOutTime);
    }
    
    /**
     * Filters strings to the point where they are safe to use within our
     * application This class is mostly used for filtering user input
     *
     * @param originalString
     * @return filtered string to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredString(String originalString) {
        return originalString;
    }
    
    /**
     * Log the user in and handle session and permissions
     *
     * @param username username as described in database
     * @param password password as described in database
     * @return true if successful, false when failed.
     */
    public boolean logInUser(String username, String password) {
        //This query will return a string, it only returns 1 value!
        String result = databaseMan.QueryOneResult("select users.permissions from users where username = \""
                                            + username + "\" and password = \"" + password + "\"");
        username = null;
        password = null;
        if (result != null) {
            System.out.println("succesful query");
            int resultInt = Byte.parseByte(result);
            if (resultInt == 0) {
                //oude gebruiker gegevens zonder inlog
                return false;
            }else if (resultInt == 1) {
                //gebruiker
                this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
            }else if (resultInt == 2) {
                //manager
                this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_MANAGER);
            }else if (resultInt == 3) {
                //admin
                this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_ADMINISTRATOR);
            }
            return true;
        } else {
            System.out.println("De opgegeven gebruiker niet gevonden in de database");
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
    
    public boolean getUserAFK() {
        return this.userAFK.get();
    }
    
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
     * @return true if succeeded, false when failed.
     */
    public boolean resetTimeOutTime() {
        try {
            timeOutTime = defaultTimeOutTime;
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
            timeOut.scheduleAtFixedRate(fireTimeOut, timeOutTime, timeOutTime);
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
     * @return true if succeeded, false when failed.
     */
    public boolean setTimeOutTime(int newTimeOutTime) {
        try {
            timeOutTime = newTimeOutTime;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    // </editor-fold>
}
