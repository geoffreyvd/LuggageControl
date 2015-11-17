/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;

/**
 *
 * @author Admin
 */
public class SearchLuggage extends SwitchingJPanel {
    
    private SwitchingJPanel.SJPanelEvents sJPanelEventsInstance;

    /**
     * Creates new form SearchLuggage
     */
    public SearchLuggage(SwitchingJPanel.SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSearchLuggage = new javax.swing.JLabel();
        textFieldLuggageID = new javax.swing.JFormattedTextField();
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        textFieldDestinaiton = new javax.swing.JFormattedTextField();
        textFieldOrigin = new javax.swing.JFormattedTextField();
        textFieldLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldDescription = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLuggageSearch = new javax.swing.JTable();
        buttonBack = new javax.swing.JButton();
        buttonHelpLinking = new javax.swing.JButton();

        labelSearchLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchLuggage.setText("Search luggage");

        textFieldLuggageID.setText("Luggage ID");

        textFieldFlightnumber.setText("Flightnumber");

        textFieldDestinaiton.setText("Destination");

        textFieldOrigin.setText("Origin");

        textFieldLocation.setText("Location");

        textFieldOwnerID.setText("Owner ID");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Item 2", "Item 3", "Item 4" }));
        comboBoxLuggageStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxStatusActionPerformed(evt);
            }
        });

        textFieldDescription.setText("Description");

        tableLuggageSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Luggage ID", "Flight number", "Owner ID", "Status", "Location", "Origin", "Destination"
            }
        ));
        jScrollPane1.setViewportView(tableLuggageSearch);

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonHelpLinking.setText("Help");
        buttonHelpLinking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(textFieldDestinaiton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(textFieldOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelSearchLuggage, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonHelpLinking, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelpLinking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldDestinaiton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textFieldDescription))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        sJPanelEventsInstance.switchPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        sJPanelEventsInstance.switchPanel(ScreenNames.Help.FINDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void comboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonHelpLinking;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSearchLuggage;
    private javax.swing.JTable tableLuggageSearch;
    private javax.swing.JFormattedTextField textFieldDescription;
    private javax.swing.JFormattedTextField textFieldDestinaiton;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldLuggageID;
    private javax.swing.JFormattedTextField textFieldOrigin;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    // End of variables declaration//GEN-END:variables
}
