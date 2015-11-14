import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import managers.DatabaseMan;
import baseClasses.SwitchingJPanel;

/**
 * Root class off application creates the main windows
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    
    private screen.LoginScreen loginScreen;
    private screen.AddCustomer addCustomer;
    private screen.HomeScreenEmployee homeScreenEmployee;
    private screen.UserManagement userManagement;
    
    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;
    
    // event to switch from panel
    SwitchingJPanel.SJPanelEvents sjPanelEventsInstance = new SwitchingJPanel.SJPanelEvents() {
        
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
        loginScreen = new screen.LoginScreen(sjPanelEventsInstance);
        addCustomer = new screen.AddCustomer(sjPanelEventsInstance);
        homeScreenEmployee = new screen.HomeScreenEmployee(sjPanelEventsInstance);
        userManagement = new screen.UserManagement(sjPanelEventsInstance);
        
        loginScreen.setSize(monitorSize);
        loginScreen.setVisible(true);
        
        this.add(loginScreen);
        /*
         DatabaseMan DB1 = new DatabaseMan();
         DB1.Query("select * from wsdatabase.cijfer");
         */
    }
}
