package constants;

// A team FYI about this class, someone is gonna complain about this and make us move it to LuggageControl,
// This is because a rule of the ITOPIA conventions is that you may not create empty classes for the sole purpose of holding static strings.
// Not really a big problem just a heads up.

/**
 * Class to store string variables of each JPanel screen name,
 * this can then be used to easily tell methods about which JPanel screen their talking,
 * Some developers find this ugly but java itself uses this all the time
 * just take a look into the Swing library.
 * @author Corne Lukken
 */
public class ScreenNames {
    
    //<editor-fold defaultstate="collapsed" desc="Funny attempt wanted to leave this in"> 
    //    private final class ScreenIdentifier {
    //        private final String underlyingString;
    //
    //        public ScreenIdentifier(String underlyingString) {
    //            this.underlyingString = underlyingString;
    //        }        
    //    }
    //    
    //    public final ScreenIdentifier[] screens = { 
    //        new ScreenIdentifier("ADD_CUSTOMER"), 
    //        new ScreenIdentifier("ADD_LUGGAGE"), 
    //        new ScreenIdentifier("CHANGE_SETTINGS"), 
    //        new ScreenIdentifier("LOGINSCREEN"),
    //        new ScreenIdentifier("HOME_SCREEN_EMPLOYEE"),
    //        new ScreenIdentifier("SEARCH_CUSTOMER"),
    //        new ScreenIdentifier("SEARCH_LUGGAGE"),
    //        new ScreenIdentifier("USER_MANAGEMENT")
    //    };
    //</editor-fold>
    
    // this might seem silly but:
    // it makes a unique identifieng string available to each and every class in the program
    // that will make the ScreenMan aware of the screen you're referencing to.
    public static final String ADD_CUSTOMER = "ADD_CUSTOMER";
    public static final String ADD_LUGGAGE = "ADD_LUGGAGE";
    public static final String CHANGE_SETTINGS = "CHANGE_SETTINGS";
    public static final String CUSTOMER_DETAILS = "CUSTOMER_DETAILS";
    public static final String DELETE_CUSTOMER = "DELETE_CUSTOMER";
    public static final String DELETE_LUGGAGE = "DELETE_LUGGAGE";
    public static final String HELP = "HELP";
    public static final String HOME_SCREEN_ADMINISTRATOR = "HOME_SCREEN_ADMINISTRATOR";
    public static final String HOME_SCREEN_EMPLOYEE = "HOME_SCREEN_EMPLOYEE";
    public static final String HOME_SCREEN_MANAGER = "HOME_SCREEN_MANAGER"; 
    public static final String GENERATE_STATISTICS =  "GENERATE_STATISTICS";
    public static final String LOGINSCREEN = "LOGINSCREEN";
    public static final String LUGGAGE_DETAILS = "LUGGAGE_DETAILS";
    public static final String SEARCH_CUSTOMER = "SEARCH_CUSTOMER";
    public static final String SEARCH_LUGGAGE = "SEARCH_LUGGAGE";
    public static final String USER_MANAGEMENT = "USER_MANAGEMENT";
    
    public class Help {
        public static final String LINKING = "LINKING";
        public static final String ADDING = "ADDING";
        public static final String FINDING = "FINDING";
    }
}
