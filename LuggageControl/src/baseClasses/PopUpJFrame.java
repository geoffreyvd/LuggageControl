package baseClasses;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

/**
 * Pop up frame base class
 * @author Corne Lukken
 */
public class PopUpJFrame extends javax.swing.JFrame {
    public PopUpJFrame() {
        this.setAlwaysOnTop(true);
        this.setTitle("Pop-up");        
    }
    
    protected void setLocationCenter() {
        Point tempPoint = new Point(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
        this.setLocation(tempPoint.x - (this.getSize().width / 2), tempPoint.y - (this.getSize().height / 2 ));
    }
}
