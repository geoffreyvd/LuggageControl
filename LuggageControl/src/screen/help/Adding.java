package screen.help;

import baseClasses.ErrorJFrame;
import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Corne Lukken
 */
public class Adding extends SwitchingJPanel {
    
    // static variables used to identify tabs
    public static final String CUSTOMER_TO_FLIGHTS = "CUSTOMER_TO_FLIGHTS";
    public static final String CUSTOMER_TO_LUGGAGE = "CUSTOMER_TO_LUGGAGE";
    public static final String FLIGHTS_TO_LUGGAGE = "FLIGHTS_TO_LUGGAGE";
    public static final String LUGGAGE_TO_LUGGAGE = "LUGGAGE_TO_LUGGAGE";

    public Adding(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
    }
    
    /**
     * Tells the screen to switch to a specific tab
     * Use the jPanel class its static strings to identify the tab.
     * @param String string of the tab identifying constants.
     */
    public void selectTab(String tabName) {
        if(tabName.equals(CUSTOMER_TO_FLIGHTS)) {
            linkingTab.setSelectedComponent(customerFlightsP);
        }
        else if(tabName.equals(CUSTOMER_TO_LUGGAGE)) {
            linkingTab.setSelectedComponent(customerLuggageP);
        }
        else if(tabName.equals(FLIGHTS_TO_LUGGAGE)) {
            linkingTab.setSelectedComponent(flightLuggageP);
        }
        else if(tabName.equals(LUGGAGE_TO_LUGGAGE)) {
            linkingTab.setSelectedComponent(lostFoundLuggageP);
        }
        else {
            // does not display errors yet.
            new ErrorJFrame();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screenName = new javax.swing.JLabel();
        linkingTab = new javax.swing.JTabbedPane();
        customerFlightsP = new javax.swing.JPanel();
        customerLuggageP = new javax.swing.JPanel();
        flightLuggageP = new javax.swing.JPanel();
        lostFoundLuggageP = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        screenName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        screenName.setText("Help - adding");
        screenName.setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout customerFlightsPLayout = new javax.swing.GroupLayout(customerFlightsP);
        customerFlightsP.setLayout(customerFlightsPLayout);
        customerFlightsPLayout.setHorizontalGroup(
            customerFlightsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        customerFlightsPLayout.setVerticalGroup(
            customerFlightsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );

        linkingTab.addTab("Customer to flights", customerFlightsP);

        javax.swing.GroupLayout customerLuggagePLayout = new javax.swing.GroupLayout(customerLuggageP);
        customerLuggageP.setLayout(customerLuggagePLayout);
        customerLuggagePLayout.setHorizontalGroup(
            customerLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        customerLuggagePLayout.setVerticalGroup(
            customerLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );

        linkingTab.addTab("Customer to luggage", customerLuggageP);

        javax.swing.GroupLayout flightLuggagePLayout = new javax.swing.GroupLayout(flightLuggageP);
        flightLuggageP.setLayout(flightLuggagePLayout);
        flightLuggagePLayout.setHorizontalGroup(
            flightLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        flightLuggagePLayout.setVerticalGroup(
            flightLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );

        linkingTab.addTab("Flights to luggage", flightLuggageP);

        javax.swing.GroupLayout lostFoundLuggagePLayout = new javax.swing.GroupLayout(lostFoundLuggageP);
        lostFoundLuggageP.setLayout(lostFoundLuggagePLayout);
        lostFoundLuggagePLayout.setHorizontalGroup(
            lostFoundLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        lostFoundLuggagePLayout.setVerticalGroup(
            lostFoundLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );

        linkingTab.addTab("Lost luggage to found luggage", lostFoundLuggageP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screenName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(linkingTab))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(screenName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkingTab, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        linkingTab.getAccessibleContext().setAccessibleName("Linking Luggage");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel customerFlightsP;
    private javax.swing.JPanel customerLuggageP;
    private javax.swing.JPanel flightLuggageP;
    private javax.swing.JTabbedPane linkingTab;
    private javax.swing.JPanel lostFoundLuggageP;
    private javax.swing.JLabel screenName;
    // End of variables declaration//GEN-END:variables
}
