/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import main.LuggageControl;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Searches through the client database with the given data.
 * @author Konrad
 */
public class SearchCustomer extends SwitchingJPanel {

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public SearchCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("First name", textFieldFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFirstName);
        PromptSupport.setPrompt("Last name", textFieldLastName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLastName);
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Cellphone number", textFieldCellphoneNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphoneNumber);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSearchCustomer = new javax.swing.JLabel();
        textFieldFirstName = new javax.swing.JFormattedTextField();
        textFieldLastName = new javax.swing.JFormattedTextField();
        textFieldCellphoneNumber = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        buttonSearch = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();

        labelSearchCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchCustomer.setText("Search Customer");

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        tableSearchCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableSearchCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSearchCustomer)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearchCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch)
                    .addComponent(buttonCancel))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Searches through the client database with the given data.
     * @param evt
     */
    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonSearchActionPerformed

    /**
     * sets the user as not afk, resets all the textfields and changes to panel home screen
     * @param evt 
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldCellphoneNumber.setText("");
        textFieldEmail.setText("");
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * sets the user as not afk and changes to panel help_finding
     * @param evt 
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.FINDING);
    }//GEN-LAST:event_buttonHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelSearchCustomer;
    private javax.swing.JTable tableSearchCustomer;
    private javax.swing.JFormattedTextField textFieldCellphoneNumber;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstName;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLastName;
    // End of variables declaration//GEN-END:variables
}
