package baseClasses;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import main.LuggageControl;
import managers.DatabaseMan;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
public abstract class SwitchingJPanel extends javax.swing.JPanel{    
    
    /**
     * Inherit a reference to the main Frame of the application
     */
    protected LuggageControl luggageControl;
    
    /**
     * DatabaseMan object to access the database.
     */
    protected DatabaseMan db = new DatabaseMan();
    
    /**
     * ArrayList to hold current active threads to reset labels 
     */
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

        public JLabel getLabel() {
            return label;
        } 
    }
    
    /**
     *
     * @param luggageControl
     */
    public SwitchingJPanel(LuggageControl luggageControl) {
        this.luggageControl = luggageControl;
    }
    
    /**
     * method to update the panel with new database information, called when luggagecontrol switches to this panel.
     */
    public void updatePanelInformation() {
        System.err.println("You should really override this function!");
    };
    
    /**
     * Update the panel with a specific id
     * @param id the id of the panel datatype
     */
    public void updatePanelInformation(int id) {
        System.err.println("You should really override this function!");
    }
    
    /**
     * method to clear the panel and remove all information
     */
    public void clearPanelInformation() {
        for(int i = 0 ; i < this.getComponentCount(); i++) {
            java.awt.Component component = this.getComponent(i);
            if(component instanceof javax.swing.JLabel) {
                javax.swing.JLabel label = (javax.swing.JLabel)component;
                label.setText("");
            }
            else if(component instanceof javax.swing.JComboBox) {
                javax.swing.JComboBox comboBox = (javax.swing.JComboBox)component;
                comboBox.setSelectedIndex(0);
            }
        }
        System.err.println("You should really override this function!");
    }
    
    protected String selectLabelImage(JLabel label) {
        String tempCurrentImagePath = helpers.ImageMaker.getImagePath();
        ImageIcon imageIcon = new ImageIcon(tempCurrentImagePath);
        System.out.println(imageIcon);
        if(imageIcon.getIconHeight() > imageIcon.getIconWidth()) {
            label.setIcon(helpers.ImageMaker.resizeImage(-1, label.getHeight(), tempCurrentImagePath));
        }
        else if(imageIcon.getIconHeight() < imageIcon.getIconWidth()) {
            label.setIcon(helpers.ImageMaker.resizeImage(label.getWidth(), -1, tempCurrentImagePath));
        }
        else {
            label.setIcon(helpers.ImageMaker.resizeImage(label.getWidth(), label.getHeight(), tempCurrentImagePath));
        }
        
        label.setHorizontalAlignment(label.CENTER);
        label.setVerticalAlignment(label.CENTER);
        return helpers.ImageMaker.encodeImage(tempCurrentImagePath);
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
        try {
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
        catch(ConcurrentModificationException e) {
            System.err.println("Multiple modifcations of array list at the same time see issue: #75");
            System.err.println("https://github.com/geoffreyvd/LuggageControl/issues/75");
        }
    }
}
