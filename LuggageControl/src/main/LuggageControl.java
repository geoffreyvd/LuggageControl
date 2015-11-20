package main;

import baseClasses.ErrorJDialog;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.SecurityMan;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import constants.Styling;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Root class off application creates the main windows
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    // <editor-fold defaultstate="collapsed" desc="Screen objects">
    private screen.AddCustomer addCustomer;
    private screen.AddLuggage addLuggage;
    private screen.AddUser addUser;
    private screen.ChangeSettings changeSettings;
    private screen.CustomerDetails customerDetails;   
    private screen.DeleteCustomer deleteCustomer;
    private screen.DeleteLuggage deleteLuggage;
    private screen.Example example;
    private screen.GenerateStatistics generateStatistics;
    private screen.Help help;
    private screen.help.Adding helpAdding;
    private screen.help.Finding helpFinding;
    private screen.help.Linking helpLinking;
    private screen.HomeScreenAdministrator homeScreenAdministrator;
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.HomeScreenManager homeScreenManager;
    private screen.LoginScreen loginScreen;
    private screen.LuggageDetails luggageDetails;
    private screen.SearchCustomer searchCustomer;
    private screen.SearchLuggage searchLuggage;
    private screen.UserManagement userManagement;
    // </editor-fold>

    private JMenuBar menuBar;
    
    // keeps a reference to the current active panel
    private SwitchingJPanel currentPanel;
    private SwitchingJPanel previousPanel;

    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;
    
    // security manager and event interface
    private SecurityMan secman;
    
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

        initComponents();

        // start fullscreen
        this.setExtendedState(MAXIMIZED_BOTH);
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
        
        // <editor-fold defaultstate="collapsed" desc="Screen objects">
        addCustomer = new screen.AddCustomer(this);
        addLuggage = new screen.AddLuggage(this);
        addUser = new screen.AddUser(this);
        changeSettings = new screen.ChangeSettings(this);
        customerDetails = new screen.CustomerDetails(this);
        deleteCustomer = new screen.DeleteCustomer(this);
        deleteLuggage = new screen.DeleteLuggage(this);
        example = new screen.Example(this);
        generateStatistics = new screen.GenerateStatistics(this);
        help = new screen.Help(this);
        helpAdding = new screen.help.Adding(this);
        helpFinding = new screen.help.Finding(this);
        helpLinking = new screen.help.Linking(this);
        homeScreenAdministrator = new screen.HomeScreenAdministrator(this);
        homeScreenEmployee = new screen.HomeScreenEmployee(this);
        homeScreenManager = new screen.HomeScreenManager(this);
        loginScreen = new screen.LoginScreen(this);
        luggageDetails = new screen.LuggageDetails(this);
        searchCustomer = new screen.SearchCustomer(this);
        searchLuggage = new screen.SearchLuggage(this);
        userManagement = new screen.UserManagement(this);
        // </editor-fold>
        
        addCustomer.setSize(monitorSize);
        addCustomer.setVisible(true);
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
        deleteLuggage.setSize(monitorSize);
        deleteLuggage.setVisible(true);
        example.setSize(monitorSize);
        example.setVisible(true);
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
        searchLuggage.setSize(monitorSize);
        searchLuggage.setVisible(true);
        userManagement.setSize(monitorSize);
        userManagement.setVisible(true);
        
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
        // testing if I can switch to a specific tab
        // and yes that works
        // helpLinking.selectTab("Not available");
    }
    
    /**
     * Switch to the previously displayed panel.
     */
    public void switchPreviousPanel() {
        String tempSwitchString = this.previousPanel.getClass().getSimpleName();
        this.switchJPanel(tempSwitchString);
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
        else if(this.currentPanel instanceof screen.AddUser) {
            this.remove(addUser);
        }
        else if(this.currentPanel instanceof screen.ChangeSettings) {
            this.remove(changeSettings);
        }
        else if(this.currentPanel instanceof screen.CustomerDetails) {
            this.remove(customerDetails);
        }
        else if(this.currentPanel instanceof screen.DeleteCustomer) {
            this.remove(deleteCustomer);
        }
        else if(this.currentPanel instanceof screen.DeleteLuggage) {
            this.remove(deleteLuggage);
        }    
        else if(this.currentPanel instanceof screen.Example) {
            this.remove(example);  
        }
        else if(this.currentPanel instanceof screen.GenerateStatistics) {
            this.remove(generateStatistics);
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
        else if(this.currentPanel instanceof screen.HomeScreenAdministrator) {
            this.remove(homeScreenAdministrator);
        }
        else if(this.currentPanel instanceof screen.HomeScreenEmployee) {
            this.remove(homeScreenEmployee);
        }
        else if(this.currentPanel instanceof screen.HomeScreenManager) {
            this.remove(homeScreenManager);
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
    public void switchJPanel(String panelName) {
        switch (panelName) {
            case ScreenNames.ADD_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(addCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = addCustomer;
                break;
            case ScreenNames.ADD_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(addLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = addLuggage;
                break;
            case ScreenNames.ADD_USER:
                this.removeCurrentJPanel();
                this.add(addUser);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = addUser;
                break;
            case ScreenNames.CHANGE_SETTINGS:
                this.removeCurrentJPanel();
                this.add(changeSettings);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = changeSettings;
                break;
            case ScreenNames.CUSTOMER_DETAILS:
                this.removeCurrentJPanel();
                this.add(customerDetails);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = customerDetails;
                break;
            case ScreenNames.DELETE_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(deleteCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = deleteCustomer;
                break;
            case ScreenNames.DELETE_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(deleteLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = deleteLuggage;
                break;
            case ScreenNames.HELP:
                this.removeCurrentJPanel();
                this.add(help);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
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
            case ScreenNames.HOME_SCREEN_ADMINISTRATOR:
                this.removeCurrentJPanel();
                this.add(homeScreenAdministrator);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = homeScreenAdministrator;
                break;
            case ScreenNames.HOME_SCREEN_EMPLOYEE:
                this.removeCurrentJPanel();
                this.add(homeScreenEmployee);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = homeScreenEmployee;
                break;
            case ScreenNames.HOME_SCREEN_MANAGER:
                this.removeCurrentJPanel();
                this.add(homeScreenManager);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = homeScreenManager;
                break;
            case ScreenNames.GENERATE_STATISTICS:
                this.removeCurrentJPanel();
                this.add(generateStatistics);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = generateStatistics;
                break;
            case ScreenNames.LOGINSCREEN:
                this.removeCurrentJPanel();
                this.add(loginScreen);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = loginScreen;
                break;
            case ScreenNames.LUGGAGE_DETAILS:
                this.removeCurrentJPanel();
                this.add(luggageDetails);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = luggageDetails;
                break;
            case ScreenNames.SEARCH_CUSTOMER:
                this.removeCurrentJPanel();
                this.add(searchCustomer);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = searchCustomer;
                break;
            case ScreenNames.SEARCH_LUGGAGE:
                this.removeCurrentJPanel();
                this.add(searchLuggage);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = searchLuggage;
                break;
            case ScreenNames.USER_MANAGEMENT:
                this.removeCurrentJPanel();
                this.add(userManagement);
                this.revalidate();
                this.repaint();
                this.previousPanel = currentPanel;
                this.currentPanel = userManagement;
                break;
            default:
                new ErrorJDialog("Error: screen does not exist", (new Throwable()).getStackTrace());
                break;
        }
    }
    
    /**
     * Switch a screen.help panel to a specific tab.
     * @param tabName the name of the tab
     * @param helpScreen the help screen must be of package screen.help
     */
    public void switchTab(String tabName, String helpScreen) {
        if(helpScreen == ScreenNames.Help.ADDING) {
            helpAdding.selectTab(tabName);
        }
        else if(helpScreen == ScreenNames.Help.FINDING) {
            helpFinding.selectTab(tabName);
        }
        else if(helpScreen == ScreenNames.Help.LINKING) {
            helpLinking.selectTab(tabName);
        }
        else {
            new ErrorJDialog("Error: screen does not exist", (new Throwable()).getStackTrace());
        }
    }
    // </editor-fold>
    
    /**
     * Parse to login to the security manager and attempt a login sequence.
     * @param username the username as described in the database
     * @param password the password as described in the database
     */
    public boolean loginUser(String username, String password) {
        if(secman.logInUser(username, password)) {
            username = null;
            password = null;
            return true;
        }
        else {
            username = null;
            password = null;
            return false;
        }
    }
    
    /**
     * Parse to login to the security manager and attempt a login sequence
     * This method is more secure since it uses a char array instead of a String which is easier deleted.
     * @param username the username as described in the database
     * @param password array of characters which together are the password as described in the database
     */
    public void loginUser(String username, char[] password) {
//        if(secman.logInUser(username, password)) {
//            
//        }
//        else {
//            new ErrorJDialog("Username or password incorrect", "Your username or password is incorrect.");
//        }
    }
    
    public void setUserAFK(boolean userAFK) {
        System.out.println("User moved!");
        this.secman.setUserAFK(false);
    }
    
    /**
     * Called when the user is timed-out switches the panel back to the login panel.
     */
    public void userTimeOut() {
        this.switchJPanel(ScreenNames.LOGINSCREEN);
    }
}
