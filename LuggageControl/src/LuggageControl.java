import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.DatabaseMan;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;

/**
 * Root class off application creates the main windows
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    
    private screen.AddCustomer addCustomer;
    private screen.AddLuggage addLuggage;
    private screen.ChangeSettings changeSettings;
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.LoginScreen loginScreen;
    private screen.SearchCustomer searchCustomer;
    private screen.SearchLuggage searchLuggage;
    private screen.UserManagement userManagement;
    
    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;
    
    // event to switch from panel
    private SwitchingJPanel.SJPanelEvents sjPanelEventsInstance = new SwitchingJPanel.SJPanelEvents() {
        
        @Override
        public void switchPanel(String screenName) {
            // put method to switch jpanel here
        }
    };
    
    /**
     * 
     */
    public LuggageControl() {
        
        // get the monitor dimension of the default monitor
        // this needs to switch to the monitor the application will appear in the future
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        monitorSize = new Dimension(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
        
        initComponents();
        
        this.setSize(monitorSize);
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
        addCustomer = new screen.AddCustomer(sjPanelEventsInstance);
        addLuggage = new screen.AddLuggage(sjPanelEventsInstance);
        changeSettings = new screen.ChangeSettings(sjPanelEventsInstance);
        homeScreenEmployee = new screen.HomeScreenEmployee(sjPanelEventsInstance);
        loginScreen = new screen.LoginScreen(sjPanelEventsInstance);
        searchCustomer = new screen.SearchCustomer(sjPanelEventsInstance);
        searchLuggage = new screen.SearchLuggage(sjPanelEventsInstance);
        userManagement = new screen.UserManagement(sjPanelEventsInstance);
        
        addCustomer.setSize(monitorSize);
        addCustomer.setVisible(true);
        
        addLuggage.setSize(monitorSize);
        addLuggage.setVisible(true);
        
        changeSettings.setSize(monitorSize);
        changeSettings.setVisible(true);
        
        homeScreenEmployee.setSize(monitorSize);
        homeScreenEmployee.setVisible(true);
        
        loginScreen.setSize(monitorSize);
        loginScreen.setVisible(true);
        
        searchCustomer.setSize(monitorSize);
        searchCustomer.setVisible(true);
        
        searchLuggage.setSize(monitorSize);
        searchLuggage.setVisible(true);
        
        userManagement.setSize(monitorSize);
        userManagement.setVisible(true);
        
        this.switchJPanel(ScreenNames.LOGINSCREEN);
        
        /*
         DatabaseMan DB1 = new DatabaseMan();
         DB1.Query("select * from wsdatabase.cijfer");
         */
    }
    
    /**
     * This is required since the removeAll method breaks the JFrame
     * Current implementation is silly,
     * we need a way to find out what the current screen is
     */
    private void removeAllJPanels() {
        this.remove(addCustomer);
        this.remove(addLuggage);
        this.remove(changeSettings);
        this.remove(homeScreenEmployee);
        this.remove(loginScreen);
        this.remove(searchCustomer);
        this.remove(searchLuggage);
        this.remove(userManagement);
    }
    
    private void switchJPanel(String panelName) {
        switch (panelName) {
            case ScreenNames.ADD_CUSTOMER:
                this.removeAllJPanels();
                this.add(addCustomer);
                break;
            case ScreenNames.ADD_LUGGAGE:
                this.removeAllJPanels();
                this.add(addLuggage);
                break;
            case ScreenNames.CHANGE_SETTINGS:
                this.removeAllJPanels();
                this.add(changeSettings);
                break;
            case ScreenNames.HOME_SCREEN_EMPLOYEE:
                this.removeAllJPanels();
                this.add(homeScreenEmployee);
                break;
            case ScreenNames.LOGINSCREEN:
                this.removeAllJPanels();
                this.add(loginScreen);
                break;
            case ScreenNames.SEARCH_CUSTOMER:
                this.removeAllJPanels();
                this.add(searchCustomer);
                break;
            case ScreenNames.SEARCH_LUGGAGE:
                this.removeAllJPanels();
                this.add(searchLuggage);
                break;
            case ScreenNames.USER_MANAGEMENT:
                this.removeAllJPanels();
                this.add(userManagement);
                break;
            default:
                System.out.println("Trying to switch to screen that does not exist!");
                break;
        }
    }
}
