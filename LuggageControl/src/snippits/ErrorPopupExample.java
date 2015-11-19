package snippits;

import baseClasses.ErrorJDialog;

/**
 * Run this file to display an ErrorJDialog pop-up
 * @author Corne Lukken
 */
public class ErrorPopupExample {
    
    public ErrorPopupExample() {
        new ErrorJDialog("Error: tab does not exist", (new Throwable()).getStackTrace());
    }
    
    public static void main(String[] args) throws Exception {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ErrorPopupExample();
            }
        });
    }
}