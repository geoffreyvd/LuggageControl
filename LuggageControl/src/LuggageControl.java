
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.DatabaseMan;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;

/**
 * Root class off application creates the main windows
 *
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    private screen.AddCustomer addCustomer;
    private screen.AddLuggage addLuggage;
    private screen.ChangeSettings changeSettings;
    private screen.CustomerDetails customerDetails;    
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.HomeScreenManager homeScreenManager;
    private screen.GenerateStatistics generateStatistics;
    private screen.LoginScreen loginScreen;
    private screen.LuggageDetails luggageDetails;
    private screen.SearchCustomer searchCustomer;
    private screen.SearchLuggage searchLuggage;
    private screen.UserManagement userManagement;

    private SwitchingJPanel currentPanel;

    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;

    // event to switch from panel
    private SwitchingJPanel.SJPanelEvents sjPanelEventsInstance = new SwitchingJPanel.SJPanelEvents() {

        @Override
        public void switchPanel(String screenName) {
            switchJPanel(screenName);
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
     * Creates instances ( Objects ) of all possible screens Increases
     * performance and memory consumption, chosen to favor performance over
     * memory consumption.
     */
    private void initComponents() {
        addCustomer = new screen.AddCustomer(sjPanelEventsInstance);
        addLuggage = new screen.AddLuggage(sjPanelEventsInstance);
        changeSettings = new screen.ChangeSettings(sjPanelEventsInstance);
        customerDetails = new screen.CustomerDetails(sjPanelEventsInstance);
        homeScreenEmployee = new screen.HomeScreenEmployee(sjPanelEventsInstance);
        homeScreenManager = new screen.HomeScreenManager(sjPanelEventsInstance);
        generateStatistics = new screen.GenerateStatistics(sjPanelEventsInstance);
        loginScreen = new screen.LoginScreen(sjPanelEventsInstance);
        luggageDetails = new screen.LuggageDetails(sjPanelEventsInstance);
        searchCustomer = new screen.SearchCustomer(sjPanelEventsInstance);
        searchLuggage = new screen.SearchLuggage(sjPanelEventsInstance);
        userManagement = new screen.UserManagement(sjPanelEventsInstance);
        
        addCustomer.setSize(monitorSize);
        addCustomer.setVisible(true);

        addLuggage.setSize(monitorSize);
        addLuggage.setVisible(true);

        changeSettings.setSize(monitorSize);
        changeSettings.setVisible(true);
        
        customerDetails.setSize(monitorSize);
        customerDetails.setVisible(true);

        homeScreenEmployee.setSize(monitorSize);
        homeScreenEmployee.setVisible(true);
        
        homeScreenManager.setSize(monitorSize);
        homeScreenManager.setVisible(true);
        
        generateStatistics.setSize(monitorSize);
        generateStatistics.setVisible(true);

        loginScreen.setSize(monitorSize);
        loginScreen.setVisible(true);
        
        luggageDetails.setSize(monitorSize);
        luggageDetails.setVisible(true);

        searchCustomer.setSize(monitorSize);
        searchCustomer.setVisible(true);

        searchLuggage.setSize(monitorSize);
        searchLuggage.setVisible(true);

        userManagement.setSize(monitorSize);
        userManagement.setVisible(true);

        this.currentPanel = loginScreen;
        this.switchJPanel(ScreenNames.LOGINSCREEN);
    }

    /**
     * This is required since the removeAll method breaks the JFrame
     * Current implementation is silly,
     * we need a way to find out what the current screen is
     */
    private void removeCurrentJPanel() {
        if(this.currentPanel instanceof screen.AddCustomer) {
            this.remove(addCustomer);
        }
        else if(this.currentPanel instanceof screen.AddLuggage) {
            this.remove(addLuggage);
        }
        else if(this.currentPanel instanceof screen.ChangeSettings) {
            this.remove(changeSettings);
        }
        else if(this.currentPanel instanceof screen.ChangeSettings) {
            this.remove(customerDetails);
        }
        else if(this.currentPanel instanceof screen.HomeScreenEmployee) {
            this.remove(homeScreenEmployee);
        }
        else if(this.currentPanel instanceof screen.HomeScreenEmployee) {
            this.remove(homeScreenManager);
        }
        else if(this.currentPanel instanceof screen.LoginScreen) {
            this.remove(generateStatistics);
        }
        else if(this.currentPanel instanceof screen.LoginScreen) {
            this.remove(loginScreen);
        }
        else if(this.currentPanel instanceof screen.LoginScreen) {
            this.remove(luggageDetails);
        }
        else if(this.currentPanel instanceof screen.SearchCustomer) {
            this.remove(searchCustomer);
        }
        else if(this.currentPanel instanceof screen.SearchLuggage) {
            this.remove(searchLuggage);
        }
        else if(this.currentPanel instanceof screen.UserManagement) {
            this.remove(userManagement);
        } 
    }
    
    /**
     * 
     * @param panelName string that identifies the screen use constants.ScreenNames
     */
    private void switchJPanel(String panelName) {
        switch (panelName) {
            case ScreenNames.ADD_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(addCustomer);
                this.revalidate();
                this.repaint();
                this.currentPanel = addCustomer;
                break;
            case ScreenNames.ADD_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(addLuggage);
                this.revalidate();
                this.repaint();
                this.currentPanel = addLuggage;
                break;
            case ScreenNames.CHANGE_SETTINGS:
                this.removeCurrentJPanel();
                this.add(changeSettings);
                this.revalidate();
                this.repaint();
                this.currentPanel = changeSettings;
                break;
            case ScreenNames.CUSTOMER_DETAILS:
                this.removeCurrentJPanel();
                this.add(customerDetails);
                this.revalidate();
                this.repaint();
                this.currentPanel = customerDetails;
                break;
            case ScreenNames.HOME_SCREEN_EMPLOYEE:
                this.removeCurrentJPanel();
                this.add(homeScreenEmployee);
                this.revalidate();
                this.repaint();
                this.currentPanel = homeScreenEmployee;
                break;
            case ScreenNames.HOME_SCREEN_MANAGER:
                this.removeCurrentJPanel();
                this.add(homeScreenManager);
                this.revalidate();
                this.repaint();
                this.currentPanel = homeScreenManager;
                break;
            case ScreenNames.GENERATE_STATISTICS:
                this.removeCurrentJPanel();
                this.add(generateStatistics);
                this.revalidate();
                this.repaint();
                this.currentPanel = generateStatistics;
                break;
            case ScreenNames.LOGINSCREEN:
                this.removeCurrentJPanel();
                this.add(loginScreen);
                this.revalidate();
                this.repaint();
                this.currentPanel = loginScreen;
                break;
            case ScreenNames.LUGGAGE_DETAILS:
                this.removeCurrentJPanel();
                this.add(luggageDetails);
                this.revalidate();
                this.repaint();
                this.currentPanel = luggageDetails;
                break;
            case ScreenNames.SEARCH_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(searchCustomer);
                this.revalidate();
                this.repaint();
                this.currentPanel = searchCustomer;
                break;
            case ScreenNames.SEARCH_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(searchLuggage);
                this.revalidate();
                this.repaint();
                this.currentPanel = searchLuggage;
                break;
            case ScreenNames.USER_MANAGEMENT:
                this.removeCurrentJPanel();
                this.add(userManagement);
                this.revalidate();
                this.repaint();
                this.currentPanel = userManagement;
                break;
            default:
                System.out.println("Trying to switch to screen that does not exist!");
                break;
        }
    }
}
