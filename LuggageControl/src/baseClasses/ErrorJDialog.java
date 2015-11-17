package baseClasses;

import javax.swing.JTextPane;

/**
 * Pop up to display errors to users
 * @author Corne Lukken
 */
public class ErrorJDialog extends PopUpJFDialog{
    
    /**
     * Default constructor without arguments displays empty error dialog.
     */
    public ErrorJDialog() {
        this.setTitle("Error");
    }
    
    /**
     * Display a error dialog with a given title and some text.
     * @param title determines the dialog (window) title.
     * @param text text displayed within a textPane to the user.
     */
    public ErrorJDialog(String title, String text) {
        this.setTitle(title);
        
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setText(text);
        this.add(textPane);
    }
    
    /**
     *  Display a error with a dialog and present the user with a stracktrace.
     * @param title Title of the dialog
     * @param strackTrace array of calls which led to the point the error occured.
     */
    public ErrorJDialog(String title, StackTraceElement[] strackTrace) {
        this.setTitle(title);
        
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        String text = ""; 
        for(StackTraceElement stackElement: strackTrace) {
            text += stackElement.toString() + "\r\n";
        }
        textPane.setText(text);
        this.add(textPane);
    }
}
