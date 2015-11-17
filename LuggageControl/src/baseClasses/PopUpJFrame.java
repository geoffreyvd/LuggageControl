package baseClasses;

/**
 * Pop up frame base class
 * @author Corne Lukken
 */
public class PopUpJFrame extends javax.swing.Popup {
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpJFrame();
            }
        });
    }
}
