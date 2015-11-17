import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.SecurityMan;
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
    private screen.CustomerDetails customerDetails;    
    private screen.Help help;
    private screen.help.Adding helpAdding;
    private screen.help.Finding helpFinding;
    private screen.help.Linking helpLinking;
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
    
    // security manager and event interface
    private SecurityMan secman;
    SecurityMan.SecurityUpdates secManUpdates = new SecurityMan.SecurityUpdates() {
        
        @Override
        public void userTimeOut() {
            System.out.println("Calling! SecurityMan do you copy?");
        }
    };

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
        // parse the refernence of our interface to the security manager class
        // so it can call us.
        secman = new SecurityMan(secManUpdates);
        
        // get the monitor dimension of the default monitor
        // this needs to switch to the monitor the application will appear in the future
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        monitorSize = new Dimension(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
        
        // Exits the application on closing this JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        initComponents();

        this.setSize(monitorSize);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // circumventing static reference with appropiate EventQueue
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LuggageControl().setVisible(true);
            }
        });
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
        help = new screen.Help(sjPanelEventsInstance);
        helpAdding = new screen.help.Adding(sjPanelEventsInstance);
        helpFinding = new screen.help.Finding(sjPanelEventsInstance);
        helpLinking = new screen.help.Linking(sjPanelEventsInstance);
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
        
        help.setSize(monitorSize);
        help.setVisible(true);

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

        this.currentPanel = helpLinking;
        this.switchJPanel(ScreenNames.Help.LINKING);
        
        // testing if I can switch to a specific tab
        // and yes that works
        helpLinking.selectTab(helpLinking.FLIGHTS_TO_LUGGAGE);
    }

    /**
     * Based on our currentPanel variable we remove the panel so a new one can be added.
     */
    private void removeCurrentJPanel() {
        
        // this giant if statement needs to be converted to a switch statement
        if(this.currentPanel instanceof screen.AddCustomer) {
            this.remove(addCustomer);
        }
        else if(this.currentPanel instanceof screen.AddLuggage) {
            this.remove(addLuggage);
        }
        else if(this.currentPanel instanceof screen.ChangeSettings) {
            this.remove(changeSettings);
        }
        else if(this.currentPanel instanceof screen.CustomerDetails) {
            this.remove(customerDetails);
        }
        else if(this.currentPanel instanceof screen.Help) {
            this.remove(help);
        }
        else if(this.currentPanel instanceof screen.help.Adding) {
            this.remove(helpAdding);
        }
        else if(this.currentPanel instanceof screen.help.Finding) {
            this.remove(helpFinding);
        }
        else if(this.currentPanel instanceof screen.help.Linking) {
            this.remove(helpLinking);
        }
        else if(this.currentPanel instanceof screen.HomeScreenEmployee) {
            this.remove(homeScreenEmployee);
        }
        else if(this.currentPanel instanceof screen.HomeScreenManager) {
            this.remove(homeScreenManager);
        }
        else if(this.currentPanel instanceof screen.GenerateStatistics) {
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
     * Switch the panel to another panel identified by ScreenNames constants.
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
            case ScreenNames.HELP:
                this.removeCurrentJPanel();
                this.add(help);
                this.revalidate();
                this.repaint();
                this.currentPanel = help;
                break;
            case ScreenNames.Help.ADDING:
                this.removeCurrentJPanel();
                this.add(helpAdding);
                this.revalidate();
                this.repaint();
                this.currentPanel = helpAdding;
                break;
            case ScreenNames.Help.FINDING:
                this.removeCurrentJPanel();
                this.add(helpFinding);
                this.revalidate();
                this.repaint();
                this.currentPanel = helpFinding;
                break;
            case ScreenNames.Help.LINKING:
                this.removeCurrentJPanel();
                this.add(helpLinking);
                this.revalidate();
                this.repaint();
                this.currentPanel = helpLinking;
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
