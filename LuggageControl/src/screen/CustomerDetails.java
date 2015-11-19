/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Admin
 */
public class CustomerDetails extends SwitchingJPanel {

    public CustomerDetails(LuggageControl luggageControl) {
        super(luggageControl);
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

        labelHeaderLeftSide = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelNameDisplay = new javax.swing.JLabel();
        labelLastName = new javax.swing.JLabel();
        labelLastNameDisplay = new javax.swing.JLabel();
        labelOwnerId = new javax.swing.JLabel();
        labelOwnerIdDisplay = new javax.swing.JLabel();
        textFieldCellphoneNumber = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldAddFlightNumber = new javax.swing.JFormattedTextField();
        comboBoxFlightNumbersList = new javax.swing.JComboBox();
        buttonRemoveFlightNumber = new javax.swing.JButton();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonCancelChanges = new javax.swing.JButton();
        separatorScreenDefider = new javax.swing.JSeparator();
        labelHeaderRightSide = new javax.swing.JLabel();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        textFieldFlightNumber = new javax.swing.JFormattedTextField();
        comboBoxStatusList = new javax.swing.JComboBox();
        scrollPaneSearchTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonSearchLuggage = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        labelHeaderLeftSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderLeftSide.setText("Customer details");

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setText("Name: ");

        labelNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNameDisplay.setText(" XXXXXXXXX");

        labelLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLastName.setText("Last name: ");

        labelLastNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLastNameDisplay.setText(" XXXXXXXXX");

        labelOwnerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerId.setText("Owner ID: ");

        labelOwnerIdDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerIdDisplay.setText(" XXXXXXXXX");

        textFieldCellphoneNumber.setText("Cellphone number");
        textFieldCellphoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCellphoneNumberActionPerformed(evt);
            }
        });

        textFieldEmail.setText("Email");

        textFieldAddFlightNumber.setText("Add flightnumber");

        comboBoxFlightNumbersList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Flightnumbers", "Item 2", "Item 3", "Item 4" }));

        buttonRemoveFlightNumber.setText("Remove");
        buttonRemoveFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFlightNumber(evt);
            }
        });

        buttonUpdateCustomer.setText("Update");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updates(evt);
            }
        });

        buttonCancelChanges.setText("Cancel");
        buttonCancelChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceles(evt);
            }
        });

        labelHeaderRightSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderRightSide.setText("Luggage search");

        textFieldLuggageId.setText("Lugagge ID");
        textFieldLuggageId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLuggageIdActionPerformed(evt);
            }
        });

        textFieldFlightNumber.setText("Flightnumber");
        textFieldFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFlightNumberActionPerformed(evt);
            }
        });

        comboBoxStatusList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Luggage ID", "Flightnumber", "Status"
            }
        ));
        scrollPaneSearchTable.setViewportView(jTable1);

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searches(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goesToHelpAdding(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToSearchCustomer(evt);
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
                        .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxFlightNumbersList, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldAddFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelName)
                        .addGap(0, 0, 0)
                        .addComponent(labelNameDisplay))
                    .addComponent(labelHeaderLeftSide)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelOwnerId)
                        .addGap(0, 0, 0)
                        .addComponent(labelOwnerIdDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLastName)
                        .addGap(0, 0, 0)
                        .addComponent(labelLastNameDisplay)))
                .addGap(30, 30, 30)
                .addComponent(separatorScreenDefider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxStatusList, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelHeaderRightSide)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(scrollPaneSearchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorScreenDefider)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelName)
                            .addComponent(labelNameDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLastName)
                            .addComponent(labelLastNameDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOwnerId)
                            .addComponent(labelOwnerIdDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxFlightNumbersList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRemoveFlightNumber))
                        .addGap(11, 11, 11)
                        .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldAddFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdateCustomer)
                            .addComponent(buttonCancelChanges)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelHeaderRightSide, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonHelp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonBack)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldLuggageId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxStatusList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPaneSearchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearchLuggage)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldCellphoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCellphoneNumberActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldCellphoneNumberActionPerformed

    private void removeFlightNumber(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFlightNumber
        this.userNotAFK();
    }//GEN-LAST:event_removeFlightNumber

    private void updates(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updates
        this.userNotAFK();
    }//GEN-LAST:event_updates

    private void canceles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceles
        this.userNotAFK();
    }//GEN-LAST:event_canceles

    private void textFieldLuggageIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLuggageIdActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldLuggageIdActionPerformed

    private void textFieldFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFlightNumberActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldFlightNumberActionPerformed

    private void searches(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searches
        this.userNotAFK();
    }//GEN-LAST:event_searches

    private void goesToHelpAdding(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goesToHelpAdding
        this.userNotAFK();
    }//GEN-LAST:event_goesToHelpAdding

    private void backToSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToSearchCustomer
        this.userNotAFK();
    }//GEN-LAST:event_backToSearchCustomer


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancelChanges;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonRemoveFlightNumber;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JComboBox comboBoxFlightNumbersList;
    private javax.swing.JComboBox comboBoxStatusList;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelHeaderLeftSide;
    private javax.swing.JLabel labelHeaderRightSide;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelLastNameDisplay;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNameDisplay;
    private javax.swing.JLabel labelOwnerId;
    private javax.swing.JLabel labelOwnerIdDisplay;
    private javax.swing.JScrollPane scrollPaneSearchTable;
    private javax.swing.JSeparator separatorScreenDefider;
    private javax.swing.JFormattedTextField textFieldAddFlightNumber;
    private javax.swing.JFormattedTextField textFieldCellphoneNumber;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFlightNumber;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    // End of variables declaration//GEN-END:variables
}
