package screen.help;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Corne Lukken
 */
public class Linking extends SwitchingJPanel {
    
    // static variables used to identify tabs

    /**
     *
     */
        public static final String CUSTOMER_TO_FLIGHTS = "CUSTOMER_TO_FLIGHTS";

    /**
     *
     */
    public static final String FLIGHTS_TO_LUGGAGE = "FLIGHTS_TO_LUGGAGE";

    /**
     *
     */
    public static final String LUGGAGE_TO_CUSTOMER = "LUGGAGE_TO_CUSTOMER";

    /**
     *
     */
    public static final String LUGGAGE_TO_LUGGAGE = "LUGGAGE_TO_LUGGAGE";

    /**
     *
     * @param luggageControl
     */
    public Linking(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
    }
    
    /**
     * Tells the screen to switch to a specific tab
     * Use the jPanel class its static strings to identify the tab.
     * @param tabName string of the tab identifying constants.
     */
    public void selectTab(String tabName) {
        if(tabName.equals(CUSTOMER_TO_FLIGHTS)) {
            linkingTab.setSelectedComponent(panelCustomersFlights);
        }
        else if(tabName.equals(FLIGHTS_TO_LUGGAGE)) {
            linkingTab.setSelectedComponent(panelFlightsLuggage);
        }
        else if(tabName.equals(LUGGAGE_TO_CUSTOMER)) {
            linkingTab.setSelectedComponent(panelLuggageCustomers);
        }
        else if(tabName.equals(LUGGAGE_TO_LUGGAGE)) {
            linkingTab.setSelectedComponent(lostFoundLuggageP);
        }
        else {
            new ErrorJDialog(this.luggageControl, true, "Error: tab does not exist", (new Throwable()).getStackTrace());
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
        panelCustomersFlights = new javax.swing.JPanel();
        panelFlightsLuggage = new javax.swing.JPanel();
        panelLuggageCustomers = new javax.swing.JPanel();
        lostFoundLuggageP = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        screenName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        screenName.setText("Help - linking");
        screenName.setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout panelCustomersFlightsLayout = new javax.swing.GroupLayout(panelCustomersFlights);
        panelCustomersFlights.setLayout(panelCustomersFlightsLayout);
        panelCustomersFlightsLayout.setHorizontalGroup(
            panelCustomersFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1852, Short.MAX_VALUE)
        );
        panelCustomersFlightsLayout.setVerticalGroup(
            panelCustomersFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );

        linkingTab.addTab("Customer to flights", panelCustomersFlights);

        javax.swing.GroupLayout panelFlightsLuggageLayout = new javax.swing.GroupLayout(panelFlightsLuggage);
        panelFlightsLuggage.setLayout(panelFlightsLuggageLayout);
        panelFlightsLuggageLayout.setHorizontalGroup(
            panelFlightsLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelFlightsLuggageLayout.setVerticalGroup(
            panelFlightsLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Flights to luggage", panelFlightsLuggage);

        javax.swing.GroupLayout panelLuggageCustomersLayout = new javax.swing.GroupLayout(panelLuggageCustomers);
        panelLuggageCustomers.setLayout(panelLuggageCustomersLayout);
        panelLuggageCustomersLayout.setHorizontalGroup(
            panelLuggageCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelLuggageCustomersLayout.setVerticalGroup(
            panelLuggageCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Luggage to customers", panelLuggageCustomers);

        javax.swing.GroupLayout lostFoundLuggagePLayout = new javax.swing.GroupLayout(lostFoundLuggageP);
        lostFoundLuggageP.setLayout(lostFoundLuggagePLayout);
        lostFoundLuggagePLayout.setHorizontalGroup(
            lostFoundLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        lostFoundLuggagePLayout.setVerticalGroup(
            lostFoundLuggagePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Lost luggage to found luggage", lostFoundLuggageP);

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackbackToPreviousScreen(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(screenName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(linkingTab, javax.swing.GroupLayout.DEFAULT_SIZE, 1860, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screenName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkingTab)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        linkingTab.getAccessibleContext().setAccessibleName("Linking Luggage");
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackbackToPreviousScreen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackbackToPreviousScreen
        this.userNotAFK();
        luggageControl.switchPreviousPanel();
    }//GEN-LAST:event_buttonBackbackToPreviousScreen


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JTabbedPane linkingTab;
    private javax.swing.JPanel lostFoundLuggageP;
    private javax.swing.JPanel panelCustomersFlights;
    private javax.swing.JPanel panelFlightsLuggage;
    private javax.swing.JPanel panelLuggageCustomers;
    private javax.swing.JLabel screenName;
    // End of variables declaration//GEN-END:variables
}
