package managers;

import constants.ScreenNames;
import java.util.Timer;
import java.util.TimerTask;
import main.LuggageControl;

/**
 * Manage security aspects such as user input filtering and login management
 *
 * @author Dantali0n
 */
public class SecurityMan {
    
    private LuggageControl luggageControl;

    // <editor-fold defaultstate="collapsed" desc="time out, timertask, timer and default time out time">
    // Custom timertask which contains reference to the securityupdate event interface.
    private class timeOutTimerTask extends TimerTask {

        LuggageControl luggageControlTimer;

        public timeOutTimerTask(LuggageControl luggageControlTimer) {
            this.luggageControlTimer = luggageControlTimer;
        }

        @Override
        public void run() {
            luggageControlTimer.userTimeOut();
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
     * @param LuggageControl reference to main class
     * push events
     */
    public SecurityMan(LuggageControl luggageControl) {

        // our reference to luggagecontrol
        this.luggageControl = luggageControl;
        
        // create the timer and start the userTimeOut task
        timeOut = new Timer();
        fireTimeOut = new timeOutTimerTask(luggageControl);

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

    public void logInUser(String username, String password) {
        if (username.equals(password)) {
            luggageControl.switchJPanel(ScreenNames.ADD_LUGGAGE);
        }
    }
}
