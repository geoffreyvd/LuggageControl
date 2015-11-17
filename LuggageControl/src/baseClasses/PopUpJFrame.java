package baseClasses;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

/**
 * Pop up frame base class
 * @author Corne Lukken
 */
public class PopUpJFrame extends javax.swing.JFrame {
    
    // Default popup dimensions
    private Dimension popUpDimension = new Dimension(400, 300);
    
    protected GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    public PopUpJFrame() {
        this.setTitle("Pop-up");
        this.setAlwaysOnTop(true);
        this.setSize(popUpDimension);
        this.centerFrame();
        this.setVisible(true);
    }
    
    protected void centerFrame() {
        this.setLocation(graphics.getCenterPoint().x - (this.getSize().width / 2), graphics.getCenterPoint().y - (this.getSize().height / 2));
    }
}
