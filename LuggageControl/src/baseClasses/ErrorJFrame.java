package baseClasses;

import java.awt.Dimension;
import javax.swing.JTextPane;

/**
 * Pop up to display errors to users
 * @author Corne Lukken
 */
public class ErrorJFrame extends PopUpJFrame{
    
    public ErrorJFrame() {
        this.setVisible(true);
    }
        
    public ErrorJFrame(String title, Exception error) {
        this.setTitle(title);
        JTextPane text = new JTextPane();
        text.setText(error.getStackTrace() + "");
        this.add(text);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setLocationCenter();
    }
}
