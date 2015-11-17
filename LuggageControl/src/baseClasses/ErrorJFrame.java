package baseClasses;

import javax.swing.JTextPane;

/**
 * Pop up to display errors to users
 * @author Corne Lukken
 */
public class ErrorJFrame extends PopUpJFrame{
        
    public ErrorJFrame() {
        
    }
    
    public ErrorJFrame(String title, String text) {
        this.setTitle(title);
        
        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        this.add(textPane);
    }
    
    public ErrorJFrame(String title, StackTraceElement[] strackTrace) {
        this.setTitle(title);
        
        JTextPane textPane = new JTextPane();
        String text = ""; 
        for(StackTraceElement stackElement: strackTrace) {
            text += stackElement.toString() + "\r\n";
        }
        textPane.setText(text);
        this.add(textPane);
    }
}
