package main;

import baseClasses.ErrorJDialog;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.SecurityMan;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import managers.ConfigurationMan;

/**
 * Root class off application creates the main windows
 *
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Screen objects">

    private screen.add.AddCustomer addCustomer;
    private screen.add.AddFlight addFlight;
    private screen.add.AddLuggage addLuggage;
    private screen.add.AddUser addUser;
    private screen.ChangeSettings changeSettings;
    private screen.details.CustomerDetails customerDetails;
    private screen.delete.DeleteCustomer deleteCustomer;
    private screen.delete.DeleteFlight deleteFlight;
    private screen.delete.DeleteLuggage deleteLuggage;
    private screen.Example example;
    private screen.FirstStart firstStart;
    private screen.details.FlightDetails flightDetails;
    private screen.GenerateStatistics generateStatistics;
    private screen.Help help;
    private screen.help.Adding helpAdding;
    private screen.help.Finding helpFinding;
    private screen.help.Linking helpLinking;
    private screen.help.Removing helpRemoving;
    private screen.home.HomeScreenAdministrator homeScreenAdministrator;
    private screen.home.HomeScreenEmployee homeScreenEmployee;
    private screen.home.HomeScreenManager homeScreenManager;
    private screen.LoginScreen loginScreen;
    private screen.details.LuggageDetails luggageDetails;
    private screen.search.SearchCustomer searchCustomer;
    private screen.search.SearchFlight searchFlight;
    private screen.search.SearchLuggage searchLuggage;
    private screen.details.UserDetails userManagement;
    // </editor-fold>

    // Used to determine if components have been initialized
    private boolean componentsInitialized = false;

    private JMenuBar menuBar;

    // keeps a reference to the current active panel
    private SwitchingJPanel currentPanel;
    private SwitchingJPanel previousPanel;

    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;

    // security manager and event interface
    private SecurityMan secman;

    // configuration manager
    private ConfigurationMan conman;

    /**
     *
     */
    public LuggageControl() {
        // parse the refernence of our interface to the security manager class
        // so it can call us.
        secman = new SecurityMan(this);

        // get the monitor dimension of the default monitor
        // this needs to switch to the monitor the application will appear in the future
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        monitorSize = new Dimension(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());

        // Exits the application on closing this JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        firstStart = new screen.FirstStart(this);
        firstStart.setSize(monitorSize);
        firstStart.setVisible(true);

        // ConfigurationMan must be initialized after initComponents
        conman = new ConfigurationMan(this);
        System.out.println(conman.getMysqlDumpLocationWindows(this));

        // start fullscreen
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setPreferredSize(monitorSize);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // circumventing static reference with appropiate EventQueue
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set cross-platform Java L&F (also called "Metal")
                    UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException e) {
                    // handle exception
                } catch (ClassNotFoundException e) {
                    // handle exception
                } catch (InstantiationException e) {
                    // handle exception
                } catch (IllegalAccessException e) {
                    // handle exception
                }

                
                new LuggageControl().setVisible(true);
            }
        });
    }

    /**
     * Creates instances ( Objects ) of all possible screens Increases
     * performance and memory consumption, chosen to favor performance over
     * memory consumption.
     */
    public void initComponents() {
        if (!componentsInitialized) {
            componentsInitialized = true;

            // <editor-fold defaultstate="collapsed" desc="Screen objects initialazations">
            addCustomer = new screen.add.AddCustomer(this);
            addFlight = new screen.add.AddFlight(this);
            addLuggage = new screen.add.AddLuggage(this);
            addUser = new screen.add.AddUser(this);
            changeSettings = new screen.ChangeSettings(this);
            customerDetails = new screen.details.CustomerDetails(this);
            deleteCustomer = new screen.delete.DeleteCustomer(this);
            deleteFlight = new screen.delete.DeleteFlight(this);
            deleteLuggage = new screen.delete.DeleteLuggage(this);
            example = new screen.Example(this);
            flightDetails = new screen.details.FlightDetails(this);
            generateStatistics = new screen.GenerateStatistics(this);
            help = new screen.Help(this);
            helpAdding = new screen.help.Adding(this);
            helpFinding = new screen.help.Finding(this);
            helpLinking = new screen.help.Linking(this);
            helpRemoving = new screen.help.Removing(this);
            homeScreenAdministrator = new screen.home.HomeScreenAdministrator(this);
            homeScreenEmployee = new screen.home.HomeScreenEmployee(this);
            homeScreenManager = new screen.home.HomeScreenManager(this);
            loginScreen = new screen.LoginScreen(this);
            luggageDetails = new screen.details.LuggageDetails(this);
            searchCustomer = new screen.search.SearchCustomer(this);
            searchFlight = new screen.search.SearchFlight(this);
            searchLuggage = new screen.search.SearchLuggage(this);
            userManagement = new screen.details.UserDetails(this);
            // </editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Screen objects configurations">
            addCustomer.setSize(monitorSize);
            addCustomer.setVisible(true);
            addFlight.setSize(monitorSize);
            addFlight.setVisible(true);
            addLuggage.setSize(monitorSize);
            addLuggage.setVisible(true);
            addUser.setSize(monitorSize);
            addUser.setVisible(true);
            changeSettings.setSize(monitorSize);
            changeSettings.setVisible(true);
            customerDetails.setSize(monitorSize);
            customerDetails.setVisible(true);
            deleteCustomer.setSize(monitorSize);
            deleteCustomer.setVisible(true);
            deleteFlight.setSize(monitorSize);
            deleteFlight.setVisible(true);
            deleteLuggage.setSize(monitorSize);
            deleteLuggage.setVisible(true);
            example.setSize(monitorSize);
            example.setVisible(true);
            flightDetails.setSize(monitorSize);
            flightDetails.setVisible(true);
            generateStatistics.setSize(monitorSize);
            generateStatistics.setVisible(true);
            help.setSize(monitorSize);
            help.setVisible(true);
            homeScreenAdministrator.setSize(monitorSize);
            homeScreenAdministrator.setVisible(true);
            homeScreenEmployee.setSize(monitorSize);
            homeScreenEmployee.setVisible(true);
            homeScreenManager.setSize(monitorSize);
            homeScreenManager.setVisible(true);
            loginScreen.setSize(monitorSize);
            loginScreen.setVisible(true);
            luggageDetails.setSize(monitorSize);
            luggageDetails.setVisible(true);
            searchCustomer.setSize(monitorSize);
            searchCustomer.setVisible(true);
            searchFlight.setSize(monitorSize);
            searchFlight.setVisible(true);
            searchLuggage.setSize(monitorSize);
            searchLuggage.setVisible(true);
            userManagement.setSize(monitorSize);
            userManagement.setVisible(true);
            //</editor-fold>

            /* Corendon red menubar
             menuBar = new JMenuBar();
             menuBar.setSize(1920, 50);
             menuBar.setBackground(Styling.CORENDON_RED);
             menuBar.setVisible(true);

             JMenuItem menuItem = new JMenuItem();
             menuItem.setText("Test item");
             menuItem.setForeground(Color.WHITE);
             menuItem.setBackground(Styling.CORENDON_RED);
             menuBar.add(menuItem);

             this.add(menuBar);
             */
            this.currentPanel = loginScreen;
            this.switchJPanel(ScreenNames.LOGINSCREEN);

            //this.currentPanel = firstStart;
            //this.switchJPanel(ScreenNames.FIRST_START);
        }
    }

    /**
     * Switch to the previously displayed panel, This does not work!
     */
    public void switchPreviousPanel() {
        String tempSwitchString = this.previousPanel.getClass().getSimpleName();
        this.switchJPanel(tempSwitchString);
    }

    /**
     * Based on our currentPanel variable we remove the panel so a new one can
     * be added.
     */
    private void removeCurrentJPanel() {

        // this giant if statement needs to be converted to a switch statement
        if (this.currentPanel instanceof screen.add.AddCustomer) {
            this.remove(addCustomer);
        } else if (this.currentPanel instanceof screen.add.AddFlight) {
            this.remove(addFlight);
        } else if (this.currentPanel instanceof screen.add.AddLuggage) {
            this.remove(addLuggage);
        } else if (this.currentPanel instanceof screen.add.AddUser) {
            this.remove(addUser);
        } else if (this.currentPanel instanceof screen.ChangeSettings) {
            this.remove(changeSettings);
        } else if (this.currentPanel instanceof screen.details.CustomerDetails) {
            this.remove(customerDetails);
        } else if (this.currentPanel instanceof screen.delete.DeleteCustomer) {
            this.remove(deleteCustomer);
        } else if (this.currentPanel instanceof screen.delete.DeleteFlight) {
            this.remove(deleteFlight);
        } else if (this.currentPanel instanceof screen.delete.DeleteLuggage) {
            this.remove(deleteLuggage);
        } else if (this.currentPanel instanceof screen.Example) {
            this.remove(example);
        } else if (this.currentPanel instanceof screen.FirstStart) {
            this.remove(firstStart);
        } else if (this.currentPanel instanceof screen.details.FlightDetails) {
            this.remove(flightDetails);
        } else if (this.currentPanel instanceof screen.GenerateStatistics) {
            this.remove(generateStatistics);
        } else if (this.currentPanel instanceof screen.Help) {
            this.remove(help);
        } else if (this.currentPanel instanceof screen.help.Adding) {
            this.remove(helpAdding);
        } else if (this.currentPanel instanceof screen.help.Finding) {
            this.remove(helpFinding);
        } else if (this.currentPanel instanceof screen.help.Linking) {
            this.remove(helpLinking);
        } else if (this.currentPanel instanceof screen.help.Removing) {
            this.remove(helpRemoving);
        } else if (this.currentPanel instanceof screen.home.HomeScreenAdministrator) {
            this.remove(homeScreenAdministrator);
        } else if (this.currentPanel instanceof screen.home.HomeScreenEmployee) {
            this.remove(homeScreenEmployee);
        } else if (this.currentPanel instanceof screen.home.HomeScreenManager) {
            this.remove(homeScreenManager);
        } else if (this.currentPanel instanceof screen.LoginScreen) {
            this.remove(loginScreen);
        } else if (this.currentPanel instanceof screen.details.LuggageDetails) {
            this.remove(luggageDetails);
        } else if (this.currentPanel instanceof screen.search.SearchCustomer) {
            this.remove(searchCustomer);
        } else if (this.currentPanel instanceof screen.search.SearchFlight) {
            this.remove(searchFlight);
        } else if (this.currentPanel instanceof screen.search.SearchLuggage) {
            this.remove(searchLuggage);
        } else if (this.currentPanel instanceof screen.details.UserDetails) {
            this.remove(userManagement);
        }
    }

    /**
     * Go to home screen based on current permissions.
     */
    public void switchHomeScreen() {
        if (secman.getPermissions() == 1) {
            this.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
        } else if (secman.getPermissions() == 2) {
            this.switchJPanel(ScreenNames.HOME_SCREEN_MANAGER);
        } else if (secman.getPermissions() == 3) {
            this.switchJPanel(ScreenNames.HOME_SCREEN_ADMINISTRATOR);
        } else {
            this.switchJPanel(ScreenNames.LOGINSCREEN);
        }
    }

    /**
     * Switch the panel to another panel identified by ScreenNames constants.
     *
     * @param panelName string that identifies the screen use
     * constants.ScreenNames
     */
    public void switchJPanel(String panelName) {
        switch (panelName) {
            case ScreenNames.ADD_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(addCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = addCustomer;
                break;
            case ScreenNames.ADD_FLIGHT:
                this.removeCurrentJPanel();
                this.add(addFlight);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = addFlight;
                break;
            case ScreenNames.ADD_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(addLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = addLuggage;
                break;
            case ScreenNames.ADD_USER:
                this.removeCurrentJPanel();
                this.add(addUser);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = addUser;
                break;
            case ScreenNames.CHANGE_SETTINGS:
                this.removeCurrentJPanel();
                this.add(changeSettings);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = changeSettings;
                break;
            case ScreenNames.CUSTOMER_DETAILS:
                this.removeCurrentJPanel();
                this.add(customerDetails);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = customerDetails;
                break;
            case ScreenNames.DELETE_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(deleteCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = deleteCustomer;
                break;
            case ScreenNames.DELETE_FLIGHT:
                this.removeCurrentJPanel();
                this.add(deleteFlight);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = deleteFlight;
                break;
            case ScreenNames.DELETE_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(deleteLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = deleteLuggage;
                break;
            case ScreenNames.EXAMPLE:
                this.removeCurrentJPanel();
                this.add(example);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = example;
                break;
            case ScreenNames.FIRST_START:
                this.removeCurrentJPanel();
                this.add(firstStart);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = firstStart;
                break;
            case ScreenNames.FLIGHT_DETAILS:
                this.removeCurrentJPanel();
                this.add(flightDetails);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = flightDetails;
                break;
            case ScreenNames.HELP:
                this.removeCurrentJPanel();
                this.add(help);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = help;
                break;
            case ScreenNames.Help.ADDING:
                this.removeCurrentJPanel();
                this.add(helpAdding);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = helpAdding;
                break;
            case ScreenNames.Help.FINDING:
                this.removeCurrentJPanel();
                this.add(helpFinding);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = helpFinding;
                break;
            case ScreenNames.Help.LINKING:
                this.removeCurrentJPanel();
                this.add(helpLinking);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = helpLinking;
                break;
            case ScreenNames.Help.REMOVING:
                this.removeCurrentJPanel();
                this.add(helpRemoving);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = helpRemoving;
                break;
            case ScreenNames.HOME_SCREEN_ADMINISTRATOR:
                this.removeCurrentJPanel();
                this.add(homeScreenAdministrator);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = homeScreenAdministrator;
                break;
            case ScreenNames.HOME_SCREEN_EMPLOYEE:
                this.removeCurrentJPanel();
                this.add(homeScreenEmployee);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = homeScreenEmployee;
                break;
            case ScreenNames.HOME_SCREEN_MANAGER:
                this.removeCurrentJPanel();
                this.add(homeScreenManager);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = homeScreenManager;
                break;
            case ScreenNames.GENERATE_STATISTICS:
                this.removeCurrentJPanel();
                this.add(generateStatistics);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = generateStatistics;
                break;
            case ScreenNames.LOGINSCREEN:
                this.removeCurrentJPanel();
                this.add(loginScreen);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = loginScreen;
                break;
            case ScreenNames.LUGGAGE_DETAILS:
                this.removeCurrentJPanel();
                this.add(luggageDetails);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = luggageDetails;
                break;
            case ScreenNames.SEARCH_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(searchCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = searchCustomer;
                break;
            case ScreenNames.SEARCH_FLIGHT:
                this.removeCurrentJPanel();
                this.add(searchFlight);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = searchFlight;
                break;
            case ScreenNames.SEARCH_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(searchLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = searchLuggage;
                break;
            case ScreenNames.USER_MANAGEMENT:
                this.removeCurrentJPanel();
                this.add(userManagement);
                this.revalidate();
                this.repaint();
                this.previousPanel = this.currentPanel;
                this.currentPanel = userManagement;
                break;
            default:
                new ErrorJDialog(this, true, "Error: screen does not exist", (new Throwable()).getStackTrace());
                break;
        }
    }

    /**
     * Switch a screen.help panel to a specific tab.
     *
     * @param tabName the name of the tab
     * @param panelName the help screen must be of package screen.help
     */
    public void switchTab(String tabName, String panelName) {
        if (panelName == ScreenNames.Help.ADDING) {
            helpAdding.selectTab(tabName);
        } else if (panelName == ScreenNames.Help.FINDING) {
            helpFinding.selectTab(tabName);
        } else if (panelName == ScreenNames.Help.LINKING) {
            helpLinking.selectTab(tabName);
        } else if (panelName == ScreenNames.Help.REMOVING) {
            helpRemoving.selectTab(tabName);
        } else {
            new ErrorJDialog(this, true, "Error: screen does not exist", (new Throwable()).getStackTrace());
        }
    }

    /**
     * Makes sure that when we are switching from panels we do not create loops
     * in the back button, NOT WORKING!
     */
    private void updatePreviousPanel() {
        if (!(this.previousPanel instanceof screen.help.Adding
                || this.previousPanel instanceof screen.help.Finding
                || this.previousPanel instanceof screen.help.Linking
                || this.previousPanel instanceof screen.help.Removing)) {
            if (!(this.currentPanel instanceof screen.Help)) {
                this.previousPanel = this.currentPanel;
            }
        }
    }

    /**
     * Special function to prefill detail panels with information based on a
     * specific ID When supplying a panelName make sure this is a details panel.
     *
     * @param panelName the panelName from the screen constants, this must be a
     * details screen in order to work
     * @param id the specific ID
     */
    public void prefillPanel(String panelName, int id) {
        if (panelName.equals(ScreenNames.CUSTOMER_DETAILS)) {
            customerDetails.loadCustomer(id);
        } else if (panelName.equals(ScreenNames.LUGGAGE_DETAILS)) {
            // does not work!
            luggageDetails.loadLuggage(id);
        }
        else if(panelName.equals(ScreenNames.FLIGHT_DETAILS)) {
            // does not work!
            flightDetails.loadFlight(id);
        }
        else {
            new ErrorJDialog(this, true, "Error: Trying to prefill detail panel which does not exist", (new Throwable()).getStackTrace());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="user management methods">
    /**
     * Parse to login to the security manager and attempt a login sequence.
     *
     * @param username the username as described in the database
     * @param password the password as described in the database
     */
    public boolean loginUser(String username, String password) {
        if (secman.logInUser(username, password)) {
            username = null;
            password = null;
            return true;
        } else {
            username = null;
            password = null;
            return false;
        }
    }

    /**
     * Parse to login to the security manager and attempt a login sequence This
     * method is more secure since it uses a <code>char[]</code> instead of a
     * String which is easier deleted.
     *
     * @param username the username as described in the database
     * @param password array of characters which together are the password as
     * described in the database
     */
    public void loginUser(String username, char[] password) {
//        if(secman.logInUser(username, password)) {
//            
//        }
//        else {
//            new ErrorJDialog("Username or password incorrect", "Your username or password is incorrect.");
//        }
    }

    /**
     * Sets the user afk to true or false and parses this through to the
     * security manager
     *
     * @param userAFK true if the user is afk, false if he is not.
     */
    public void setUserAFK(boolean userAFK) {
        this.secman.setUserAFK(false);
    }

    /**
     * Called when the user is timed-out switches the panel back to the login
     * panel.
     */
    public void userTimeOut() {
        this.switchJPanel(ScreenNames.LOGINSCREEN);
    }
    // </editor-fold>
}
