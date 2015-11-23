package screen.help;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Corne Lukken
 */
public class Removing extends SwitchingJPanel {
    
    // static variables used to identify tabs
    public static final String REMOVE_ENTRIES = "REMOVE_ENTRIES";
    public static final String REMOVE_LINKS = "REMOVE_LINKS";

    public Removing(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
    }
    
    /**
     * Tells the screen to switch to a specific tab
     * Use the jPanel class its static strings to identify the tab.
     * @param tabName string of the tab identifying constants.
     */
    public void selectTab(String tabName) {
        if(tabName.equals(REMOVE_ENTRIES)) {
            linkingTab.setSelectedComponent(panelRemoveEntries);
        }
        else if(tabName.equals(REMOVE_LINKS)) {
            linkingTab.setSelectedComponent(panelRemoveLinks);
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
        panelRemoveEntries = new javax.swing.JPanel();
        panelRemoveLinks = new javax.swing.JPanel();
        buttonBack = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        screenName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        screenName.setText("Help - removing");
        screenName.setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout panelRemoveEntriesLayout = new javax.swing.GroupLayout(panelRemoveEntries);
        panelRemoveEntries.setLayout(panelRemoveEntriesLayout);
        panelRemoveEntriesLayout.setHorizontalGroup(
            panelRemoveEntriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1852, Short.MAX_VALUE)
        );
        panelRemoveEntriesLayout.setVerticalGroup(
            panelRemoveEntriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );

        linkingTab.addTab("remove entries", panelRemoveEntries);

        javax.swing.GroupLayout panelRemoveLinksLayout = new javax.swing.GroupLayout(panelRemoveLinks);
        panelRemoveLinks.setLayout(panelRemoveLinksLayout);
        panelRemoveLinksLayout.setHorizontalGroup(
            panelRemoveLinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1855, Short.MAX_VALUE)
        );
        panelRemoveLinksLayout.setVerticalGroup(
            panelRemoveLinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        linkingTab.addTab("remove links", panelRemoveLinks);

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
                    .addComponent(linkingTab))
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
    private javax.swing.JPanel panelRemoveEntries;
    private javax.swing.JPanel panelRemoveLinks;
    private javax.swing.JLabel screenName;
    // End of variables declaration//GEN-END:variables
}