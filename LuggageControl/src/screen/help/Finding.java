package screen.help;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Corne Lukken
 */
public class Finding extends SwitchingJPanel {
    
    // static variables used to identify tabs
    public static final String FIND_CUSTOMER = "FIND_CUSTOMER";
    public static final String FIND_FLIGHTS = "FIND_FLIGHTS";
    public static final String FIND_LUGGAGE = "FIND_LUGGAGE";
    public static final String FIND_LINKS = "FIND_LINKS";

    public Finding(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
    }
    
    /**
     * Tells the screen to switch to a specific tab
     * Use the jPanel class its static strings to identify the tab.
     * @param tabName string of the tab identifying constants.
     */
    public void selectTab(String tabName) {
        if(tabName.equals(FIND_CUSTOMER)) {
            linkingTab.setSelectedComponent(panelFindCustomer);
        }
        else if(tabName.equals(FIND_FLIGHTS)) {
            linkingTab.setSelectedComponent(panelFindFlights);
        }
        else if(tabName.equals(FIND_LUGGAGE)) {
            linkingTab.setSelectedComponent(panelFindLuggage);
        }
        else if(tabName.equals(FIND_LINKS)) {
            linkingTab.setSelectedComponent(panelFindLinks);
        }
        else {
            // does not display errors yet.
            new ErrorJDialog("Error: tab does not exist", (new Throwable()).getStackTrace());
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
        panelFindCustomer = new javax.swing.JPanel();
        panelFindFlights = new javax.swing.JPanel();
        panelFindLuggage = new javax.swing.JPanel();
        panelFindLinks = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        screenName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        screenName.setText("Help - finding");
        screenName.setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout panelFindCustomerLayout = new javax.swing.GroupLayout(panelFindCustomer);
        panelFindCustomer.setLayout(panelFindCustomerLayout);
        panelFindCustomerLayout.setHorizontalGroup(
            panelFindCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1852, Short.MAX_VALUE)
        );
        panelFindCustomerLayout.setVerticalGroup(
            panelFindCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );

        linkingTab.addTab("Find customers", panelFindCustomer);

        javax.swing.GroupLayout panelFindFlightsLayout = new javax.swing.GroupLayout(panelFindFlights);
        panelFindFlights.setLayout(panelFindFlightsLayout);
        panelFindFlightsLayout.setHorizontalGroup(
            panelFindFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelFindFlightsLayout.setVerticalGroup(
            panelFindFlightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Find flights", panelFindFlights);

        javax.swing.GroupLayout panelFindLuggageLayout = new javax.swing.GroupLayout(panelFindLuggage);
        panelFindLuggage.setLayout(panelFindLuggageLayout);
        panelFindLuggageLayout.setHorizontalGroup(
            panelFindLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelFindLuggageLayout.setVerticalGroup(
            panelFindLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Find luggage", panelFindLuggage);

        javax.swing.GroupLayout panelFindLinksLayout = new javax.swing.GroupLayout(panelFindLinks);
        panelFindLinks.setLayout(panelFindLinksLayout);
        panelFindLinksLayout.setHorizontalGroup(
            panelFindLinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelFindLinksLayout.setVerticalGroup(
            panelFindLinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("Find links", panelFindLinks);

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
    private javax.swing.JPanel panelFindCustomer;
    private javax.swing.JPanel panelFindFlights;
    private javax.swing.JPanel panelFindLinks;
    private javax.swing.JPanel panelFindLuggage;
    private javax.swing.JLabel screenName;
    // End of variables declaration//GEN-END:variables
}
