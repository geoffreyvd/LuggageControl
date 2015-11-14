package snippits;

import javax.swing.JPanel;
import screen.LoginScreen;

/**
 * Extension JPanel with event to switch screens
 * @author Corne Lukken
 */
public class ScreenSwitchingExample extends javax.swing.JPanel {    
    public abstract static class SJPanelEvents {
        public abstract void switchPanel(); 
    }
}

// first example screen inheriting SwitchingJPanel with abstract event class
class examplePanel extends ScreenSwitchingExample {
    
    private SJPanelEvents sJPanelEventsInstance;
    
    public examplePanel(SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
    }
}

// second example screen inheriting SwitchingJPanel with abstract event class
class examplePanel2 extends ScreenSwitchingExample {
    
    private SJPanelEvents sJPanelEventsInstance;
    
    public examplePanel2(SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
        
        // switch screen to add customer
        sJPanelEventsInstance.switchPanel();
    }
}

// example main function
class exampleMain extends javax.swing.JFrame {
    
    private examplePanel exmPanel;
    private examplePanel2 exmPanel2;
    
    // because the all inherent the same base class we can now define an single event interface
    // instead of one for each separate screen.
    ScreenSwitchingExample.SJPanelEvents sjPanelEventsInstance = new ScreenSwitchingExample.SJPanelEvents() {
        
        @Override
        public void switchPanel() {
            switchJPanel();
        }
    };

    public exampleMain() {
        // while the screen classes differ we can still reference the same event instance,
        // since this property is inherited from our SwitchingJPanel class.
        exmPanel = new examplePanel(sjPanelEventsInstance);
        exmPanel2 = new examplePanel2(sjPanelEventsInstance);
    }
    
    private void switchJPanel() {
    }
}