/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.home;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author root
 */
public class HomeScreenEmployee extends SwitchingJPanel {

    private SecurityMan sc;
    DatabaseMan db = new DatabaseMan();

    public HomeScreenEmployee(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flight number", textFieldFlightNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightNumber);
        this.buildTable("SELECT * FROM luggagecontroldata.luggage");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAddCustomer = new javax.swing.JButton();
        buttonAddLuggage = new javax.swing.JButton();
        buttonSearchCustomer = new javax.swing.JButton();
        buttonSearchLuggage = new javax.swing.JButton();
        labelHeaderRightSide = new javax.swing.JLabel();
        labelHeaderLeftSide = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton();
        buttonChangeSettings = new javax.swing.JButton();
        textFieldFlightNumber = new javax.swing.JTextField();
        scrollPaneQuickSearchTable = new javax.swing.JScrollPane();
        tableLuggage = new javax.swing.JTable();
        buttonAddFlight = new javax.swing.JButton();

        buttonAddCustomer.setText("Add customer");
        buttonAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerActionPerformed(evt);
            }
        });

        buttonAddLuggage.setText("Add luggage");
        buttonAddLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddLuggageActionPerformed(evt);
            }
        });

        buttonSearchCustomer.setText("Search customer");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerActionPerformed(evt);
            }
        });

        buttonSearchLuggage.setText("Search luggage");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchLuggageActionPerformed(evt);
            }
        });

        labelHeaderRightSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderRightSide.setText("Quick search");

        labelHeaderLeftSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderLeftSide.setText("Home");

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonChangeSettings.setText("Change settings");
        buttonChangeSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeSettingsActionPerformed(evt);
            }
        });

        textFieldFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFlightNumberActionPerformed(evt);
            }
        });

        tableLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Luggage ID", "Origin", "Location", "Flightnumber"
            }
        ));
        scrollPaneQuickSearchTable.setViewportView(tableLuggage);

        buttonAddFlight.setText("Add flight");
        buttonAddFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddFlightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(buttonAddLuggage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearchCustomer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAddCustomer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAddFlight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelHeaderLeftSide))
                .addGap(204, 204, 204)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPaneQuickSearchTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelHeaderRightSide)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonChangeSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addGap(18, 18, 18)
                        .addComponent(buttonChangeSettings))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelHeaderRightSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCustomer)
                    .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneQuickSearchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAddLuggage)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAddFlight)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearchCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearchLuggage)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonChangeSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeSettingsActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.CHANGE_SETTINGS);
    }//GEN-LAST:event_buttonChangeSettingsActionPerformed

    private void buttonSearchLuggageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchLuggageActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_LUGGAGE);
    }//GEN-LAST:event_buttonSearchLuggageActionPerformed

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_CUSTOMER);
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonAddLuggageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddLuggageActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.ADD_LUGGAGE);
    }//GEN-LAST:event_buttonAddLuggageActionPerformed

    private void buttonAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.ADD_CUSTOMER);
    }//GEN-LAST:event_buttonAddCustomerActionPerformed

    private void buttonAddFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddFlightActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.ADD_FLIGHT);
    }//GEN-LAST:event_buttonAddFlightActionPerformed

    private void textFieldFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFlightNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFlightNumberActionPerformed

    private void buildTable(String query) {
        String[] values = { //sc.filteredString(textFieldQuickSearchFlightNumber.getText())
        };

        query += " limit 4;";

        ResultSet result;
        try {
            result = db.query(query, values);
            DefaultTableModel datamodel = (DefaultTableModel) tableLuggage.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("luggage_id"),
                    result.getString("location"),
                    result.getString("location"),
                    result.getString("status")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableLuggage.setModel(datamodel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new ErrorJDialog(this.luggageControl, true, "Error: retrieving customer dataset", (new Throwable()).getStackTrace());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddCustomer;
    private javax.swing.JButton buttonAddFlight;
    private javax.swing.JButton buttonAddLuggage;
    private javax.swing.JButton buttonChangeSettings;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JLabel labelHeaderLeftSide;
    private javax.swing.JLabel labelHeaderRightSide;
    private javax.swing.JScrollPane scrollPaneQuickSearchTable;
    private javax.swing.JTable tableLuggage;
    private javax.swing.JTextField textFieldFlightNumber;
    // End of variables declaration//GEN-END:variables
}
