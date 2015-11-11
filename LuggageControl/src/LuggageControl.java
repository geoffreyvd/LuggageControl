import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JPanel;

/**
 * Root class off application creates the main windows
 * @author Team3 Fys
 */
public class LuggageControl extends javax.swing.JFrame {
    
    // this is an test with a array approach for screens,
    // not worth it if you ask me.
    private javax.swing.JPanel[] screens;
    
    private GraphicsDevice graphicsDevice;
    private Dimension monitorSize;
    
    /**
     * 
     */
    public LuggageControl() {
        screens = new JPanel[8];
        
        // get the monitor dimension of the default monitor
        // this needs to switch to the monitor the application will appear in the future
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        monitorSize = new Dimension(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
        
        // stop the application when the window is closed
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        initComponents();
        
        this.setSize(monitorSize);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // circumventing static reference
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LuggageControl();
            }
        });
    }
    
    /**
     * Creates instances ( Objects ) of all possible screens
     * Increases performance and memory consumption, chosen to favor performance over memory consumption.
     */
    private void initComponents() {
        screens[1] = new screen.AddCustomer();
        screens[2] = new screen.HomeScreenEmployee();
        screens[3] = new screen.LoginScreen();
        screens[4] = new screen.UserManagement();
        
        screens[1].setSize(monitorSize);
        screens[1].setVisible(false);
        
        screens[2].setSize(monitorSize);
        screens[2].setVisible(false);
        
        screens[3].setSize(monitorSize);
        // set the loginscreen as the default screen
        screens[3].setVisible(true);
        
        screens[4].setSize(monitorSize);
        screens[4].setVisible(false);
        
        this.add(screens[1]);
        this.add(screens[2]);
        this.add(screens[3]);
        this.add(screens[4]);
        
        // If we use an array we need to cast each screen to its own class before we can use it
        // I personaly find that this makes the use of arrays redundant and with such this approach should be avoided.
        screen.LoginScreen tempPanel = (screen.LoginScreen)screens[3];
        tempPanel.ParseLogin();
    }
    
}
