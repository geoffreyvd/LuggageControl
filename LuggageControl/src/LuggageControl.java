
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.LoginScreen loginScreen;
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
        else if(this.currentPanel instanceof screen.HomeScreenEmployee) {
            this.remove(homeScreenEmployee);
        }
        else if(this.currentPanel instanceof screen.LoginScreen) {
            this.remove(loginScreen);
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
            case ScreenNames.HOME_SCREEN_EMPLOYEE:
                this.removeCurrentJPanel();
                this.add(homeScreenEmployee);
                this.revalidate();
                this.repaint();
                this.currentPanel = homeScreenEmployee;
                break;
            case ScreenNames.LOGINSCREEN:
                this.removeCurrentJPanel();
                this.add(loginScreen);
                this.revalidate();
                this.repaint();
                this.currentPanel = loginScreen;
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
