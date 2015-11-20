package baseClasses;

import javax.swing.JLabel;
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
    
    /**
     * Method to reset given label after a specific period of time
     * @param time
     * @param label 
     */
    protected void resetLabel(final int time, final JLabel label) {
        Thread resetText = new Thread("resetText-" + label.getClass().getSimpleName()) {
                        
            @Override
            public void run() {
                try {
                    sleep(time);
                    label.setText("");
                } catch (InterruptedException ex) {
                }
                
            }
        };
        resetText.start();
    }
}
