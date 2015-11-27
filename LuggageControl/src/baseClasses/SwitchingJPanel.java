package baseClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JLabel;
import main.LuggageControl;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
public class SwitchingJPanel extends javax.swing.JPanel{    
    
    protected LuggageControl luggageControl;
    
    private ArrayList<LabelThread> resetLabels = new ArrayList<LabelThread>();
    
    // Special class to attend to resetting labels
    // makes sure 2 threads cannot wait to change the same label
    class LabelThread extends Thread {
        
        private JLabel label;
        private int time;
        private AtomicBoolean canceled = new AtomicBoolean(false);
        
        public LabelThread(String name, int time, JLabel label) {
            this.setName(name);
            this.label = label;
            this.time = time;
        }
        
        @Override
        public void run() {
            try {
                sleep(time);
                if(!canceled.get()) {
                    this.label.setText("");
                }
            } catch (InterruptedException ex) {
            }
        }
        
        public void setCanceled(boolean cancel) {
            canceled.set(cancel);
        }

        public  JLabel getLabel() {
            return label;
        } 
    }
    
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
    protected synchronized void resetLabel(final int time, final JLabel label) {
        
        // my very first iterator
        // turns out you can't access a arraylist while you are looping through it with for / while etc
        // ConcurrentModificationException
        for(Iterator<LabelThread> labelIter = resetLabels.iterator(); labelIter.hasNext(); ) {
            LabelThread resetLabel = labelIter.next();
            if(resetLabel.getLabel() == label) {
                resetLabel.setCanceled(true);
                // resetLabels.remove(resetLabel);
            }
            if(!resetLabel.isAlive()) {
                resetLabels.remove(resetLabel);
            }
        }
        
        LabelThread resetText = new LabelThread("resetText-" + label.getClass().getSimpleName(), time, label);
        
        resetLabels.add(resetText);
        resetText.start();
    }
}
