/**
 * Root class off application creates the main windows
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    
    private screen.LoginScreen loginScreen;
    private screen.AddCustomer addCustomer;
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.UserManagement userManagement;
    
    /**
     * 
     */
    public LuggageControl() {
        initComponents();
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // circumventing static reference
        new LuggageControl();
    }
    
    /**
     * Creates instances ( Objects ) of all possible screens
     * Increases performance and memory consumption, chosen to favor performance over memory consumption.
     */
    private void initComponents() {
        loginScreen = new screen.LoginScreen();
        addCustomer = new screen.AddCustomer();
        homeScreenEmployee = new screen.HomeScreenEmployee();
        userManagement = new screen.UserManagement();
        
        loginScreen.setVisible(true);
        
        this.add(loginScreen);
    }
    
}
