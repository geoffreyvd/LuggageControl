package baseClasses;

import main.LuggageControl;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
public class SwitchingJPanel extends javax.swing.JPanel{    
    
    protected LuggageControl luggageControl;
    
    public SwitchingJPanel(LuggageControl luggageControl) {
        this.luggageControl = luggageControl;
    }
}
