package snippits;

import constants.ScreenNames;
import javax.swing.JPanel;
import screen.LoginScreen;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
class SwitchingJPanel extends javax.swing.JPanel {    
    public abstract static class SJPanelEvents {
        public abstract void switchPanel(String screenName); 
    }
}

// first example screen inheriting SwitchingJPanel with abstract event class
class examplePanel extends SwitchingJPanel {
    
    private SJPanelEvents sJPanelEventsInstance;
    
    public examplePanel(SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
    }
}

// second example screen inheriting SwitchingJPanel with abstract event class
class examplePanel2 extends SwitchingJPanel {
    
    private SJPanelEvents sJPanelEventsInstance;
    
    public examplePanel2(SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
        
        // switch screen to add customer
        sJPanelEventsInstance.switchPanel(ScreenNames.ADD_CUSTOMER);
    }
}

// example main function

/**
 *
 * @author geoffrey
 */
public class SwitchingJPanelExample extends javax.swing.JFrame {
    
    private examplePanel exmPanel;
    private examplePanel2 exmPanel2;
    
    // because the all inherent the same base class we can now define an single event interface
    // instead of one for each separate screen.
    SwitchingJPanel.SJPanelEvents sjPanelEventsInstance = new SwitchingJPanel.SJPanelEvents() {
        
        @Override
        public void switchPanel(String screenName) {
            switchJPanel(screenName);
        }
    };

    /**
     *
     */
    public SwitchingJPanelExample() {
        // while the screen classes differ we can still reference the same event instance,
        // since this property is inherited from our SwitchingJPanel class.
        exmPanel = new examplePanel(sjPanelEventsInstance);
        exmPanel2 = new examplePanel2(sjPanelEventsInstance);
    }
    
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new SwitchingJPanelExample();
    }
    
    private void switchJPanel(String screenName) {
        if(screenName.equals(ScreenNames.ADD_CUSTOMER)) {
            
        }
        else if(screenName.equals(ScreenNames.ADD_LUGGAGE)) {
            
        }
        else if(screenName.equals(ScreenNames.CHANGE_SETTINGS)) {
            
        }
        else if(screenName.equals(ScreenNames.HOME_SCREEN_EMPLOYEE)) {
            
        }
        else if(screenName.equals(ScreenNames.LOGINSCREEN)) {
            
        }
        else if(screenName.equals(ScreenNames.SEARCH_CUSTOMER)) {
            
        }
        else if(screenName.equals(ScreenNames.USER_MANAGEMENT)) {
            
        }
        else {
            System.out.println("Trying to switch to screen that does not exist!");
        }
    }
}