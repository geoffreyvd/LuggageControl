package baseClasses;

/**
 * Pop up to display errors to users
 * @author Corne Lukken
 */
public class ErrorJFrame extends PopUpJFrame{
        
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ErrorJFrame();
            }
        });
    }
}
