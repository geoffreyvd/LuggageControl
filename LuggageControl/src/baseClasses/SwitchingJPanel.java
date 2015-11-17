package baseClasses;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
public class SwitchingJPanel extends javax.swing.JPanel{    
    public abstract static class SJPanelEvents {
        public abstract void switchPanel(String screenName); 
        public abstract void loginUser(String username, String password);
    }
}
