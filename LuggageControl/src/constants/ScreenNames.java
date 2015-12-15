package constants;

// A team FYI about this class, someone is gonna complain about this and make us move it to LuggageControl,
// This is because a rule of the ITOPIA conventions is that you may not create empty classes for the sole purpose of holding static strings.
// Not really a big problem just a heads up.

/**
 * Class to store string variables of each JPanel screen name,
 * this can then be used to easily tell methods about which JPanel screen their talking,
 * Some developers find this ugly but java itself uses this all the time
 * just take a look into the Swing library.
 * @deprecated Dont use mofo!
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

    /**
     *
     */
    public static final String ADD_CUSTOMER = "AddCustomer";

    /**
     *
     */
    public static final String ADD_FLIGHT = "AddFlight";

    /**
     *
     */
    public static final String ADD_LUGGAGE = "AddLuggage";

    /**
     *
     */
    public static final String ADD_USER = "AddUser";

    /**
     *
     */
    public static final String CHANGE_SETTINGS = "ChangeSettings";

    /**
     *
     */
    public static final String CUSTOMER_DETAILS = "CustomerDetails";

    /**
     *
     */
    public static final String DELETE_CUSTOMER = "DeleteCustomer";

    /**
     *
     */
    public static final String DELETE_FLIGHT = "DeleteFlight";

    /**
     *
     */
    public static final String DELETE_LUGGAGE = "DeleteLuggage";

    /**
     *
     */
    public static final String EXAMPLE = "Example";

    /**
     *
     */
    public static final String FIRST_START = "FirstStart";

    /**
     *
     */
    public static final String FLIGHT_DETAILS = "FlightDetails";

    /**
     *
     */
    public static final String GENERATE_STATISTICS =  "GenerateStatistics";

    /**
     *
     */
    public static final String HELP = "Help";

    /**
     *
     */
    public static final String HOME_SCREEN_ADMINISTRATOR = "HomeScreenAdministrator";

    /**
     *
     */
    public static final String HOME_SCREEN_EMPLOYEE = "HomeScreenEmployee";

    /**
     *
     */
    public static final String HOME_SCREEN_MANAGER = "HomeScreenManager"; 

    /**
     *
     */
    public static final String LOGINSCREEN = "LoginScreen";

    /**
     *
     */
    public static final String LUGGAGE_DETAILS = "LuggageDetails";

    /**
     *
     */
    public static final String SEARCH_CUSTOMER = "SearchCustomer";

    /**
     *
     */
    public static final String SEARCH_FLIGHT = "SearchFlight";

    /**
     *
     */
    public static final String SEARCH_LUGGAGE = "SearchLuggage";

    /**
     *
     */
    public static final String USER_MANAGEMENT = "UserManagement";
    
    /**
     *
     */
    public class Help {

            /**
             *
             */
            public static final String ADDING = "Adding";

            /**
             *
             */
            public static final String FINDING = "Finding";

            /**
             *
             */
            public static final String LINKING = "Linking";

            /**
             *
             */
            public static final String REMOVING = "Removing";
    }
}
