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
       
        // lets test calling our reference shall we?
        secUpdatesInterface.userTimeOut();
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
