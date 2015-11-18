package baseClasses;

import constants.Styling;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

/**
 * Pop up frame base class
 * @author Corne Lukken
 */
public class PopUpJFDialog extends javax.swing.JDialog {
    
    // Default popup dimensions
    private Dimension popUpDimension = new Dimension(400, 300);
    
    // Graphics envrioment used to determine aspects of screen and 3D capabilities.
    protected GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    /**
     * Default popup, Displays immediately in center of screen.
     */
    public PopUpJFDialog() {
        this.setTitle("Pop-up");
        this.setAlwaysOnTop(true);
        this.setMinimumSize(Styling.MINIMUM_DIMENSION);
        this.setSize(popUpDimension);
        this.centerFrame();
        this.setVisible(true);
    }
    
    /**
     * Relocate the JDialog to the center of all monitors.
     */
    protected void centerFrame() {
        this.setLocation(graphics.getCenterPoint().x - (this.getSize().width / 2), graphics.getCenterPoint().y - (this.getSize().height / 2));
    }
    
    /**
     * Halt all other program operation until window is closed.
     */
    protected void haltProgram() {
        this.setVisible(false);
        this.setModalityType(DEFAULT_MODALITY_TYPE);
        this.setVisible(true);
    }
}
