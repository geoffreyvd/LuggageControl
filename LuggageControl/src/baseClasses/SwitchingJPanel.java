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
    
    /**
     * Make luggagecontrol aware that the user is not afk and has moved.
     */
    protected void userNotAFK() {
        luggageControl.setUserAFK(false);
    }
}
