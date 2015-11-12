package managers;

import java.util.Timer;

/**
 * Manage security aspects such as user input filtering and login management
 * @author Dantali0n
 */
public class SecurityMan {
    
    private SecurityUpdates secUpdatesInterface;
    
    // default timeout is 300 seconds (5 minutes)
    int timeOutTime = 300;
    
    // timer to manage when the user has timed out.
    Timer timeOut;
    
    /**
     * You probably don't need to create an object of this class,
     * but just for kicks I put this here anyway
     * just kidding I'm stupid where using a timer, 
     * yes you need to create an object of this class.
     */
    public SecurityMan(SecurityUpdates secUpdatesReference) {
        // pass the defined class reference tot the binding interface
        // now we can call our abstract functions
        secUpdatesInterface = secUpdatesReference;
        
        timeOut = new Timer();
        timeOut.scheduleAtFixedRate(null, null, timeOutTime);
    }
    
    /**
     * Resets the timeout timer to zero.
     * @return true if succeeded, false on failed 
     */
    public boolean resetTimer() {
        try{
            timeOut.cancel();
            timeOut.purge();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean setTimeOutTime(int newTimeOutTime) {
        try {
            timeOutTime = newTimeOutTime;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Filters strings to the point where they are safe to use within our application
     * This class is mostly used for filtering user input
     * @param originalString
     * @return filtered string to prevent SQL injections, cross-site scripting and other exploits
     */
    public static String filteredString(String originalString) {
        return originalString;
    }
    
    /**
     * Updates will be pushed through this class
     * Things like timeouts, logins intrusions and such.
     */
    public abstract static class SecurityUpdates {
        
        /**
         * Called when the logged in user was inactive for the provided timeout time
         */
        public abstract void userTimeOut(); 
    }
}
