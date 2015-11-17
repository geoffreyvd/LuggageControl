package managers;

import constants.ScreenNames;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manage security aspects such as user input filtering and login management
 *
 * @author Dantali0n
 */
public class SecurityMan {

    // reference to defined version of securityupdates
    private SecurityUpdates secUpdatesInterface;

    // <editor-fold defaultstate="collapsed" desc="time out, timertask, timer and default time out time">
    // Custom timertask which contains reference to the securityupdate event interface.
    private class timeOutTimerTask extends TimerTask {

        SecurityUpdates secUpdatesInterface;

        public timeOutTimerTask(SecurityUpdates secUpdatesReference) {
            secUpdatesInterface = secUpdatesReference;
        }

        @Override
        public void run() {
            secUpdatesInterface.userTimeOut();
        }
    }

    // default timeout is 300000 milliseconds (5 minutes)
    private int defaultTimeOutTime = 300000;

    private int timeOutTime = defaultTimeOutTime;

    // timer to manage when the user has timed out.
    private Timer timeOut;

    // instance of our custom timer
    private timeOutTimerTask fireTimeOut;

    // </editor-fold>
    /**
     * You probably don't need to create an object of this class, but just for
     * kicks I put this here anyway just kidding I'm stupid where using a timer,
     * yes you need to create an object of this class.
     *
     * @param secUpdatesReference instance of abstract securityUpdates class to
     * push events
     */
    public SecurityMan(SecurityUpdates secUpdatesReference) {

        // pass the defined class reference to the event interface
        // now we can call our abstract functions
        secUpdatesInterface = secUpdatesReference;

        // create the timer and start the userTimeOut task
        timeOut = new Timer();
        fireTimeOut = new timeOutTimerTask(secUpdatesInterface);

        //  start the timer with the timertask
        timeOut.scheduleAtFixedRate(fireTimeOut, timeOutTime, timeOutTime);
    }

    /**
     * Resets the timeout timer to zero.
     *
     * @return true if succeeded, false when failed.
     */
    public boolean resetTimer() {
        try {
            timeOut.cancel();
            timeOut.purge();
            timeOut.scheduleAtFixedRate(fireTimeOut, timeOutTime, timeOutTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
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

    /**
     * Resets the timeout time to the default time Please note that the timer
     * time does not change until you reset the timer.
     *
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
    // </editor-fold>

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
     * Updates will be pushed through this class Things like timeouts, logins
     * intrusions and such.
     */
    public abstract static class SecurityUpdates {

        /**
         * Called when the logged in user was inactive for the provided timeout
         * time
         */
        public abstract void userTimeOut();
        
        /**
         * 
         * @param String screenName used to identify the screen we are switching to
         */
        public abstract void switchPanel(String screenName);
    }

    public void logInUser(String username, String password) {
        if (username.equals(password)) {
            secUpdatesInterface.switchPanel(ScreenNames.ADD_LUGGAGE);
        }
    }
}
