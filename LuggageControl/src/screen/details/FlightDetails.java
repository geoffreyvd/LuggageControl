/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.details;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Admin
 */
public class FlightDetails extends SwitchingJPanel {

    private int currentFlightId = 0; 
    
    private DatabaseMan db = new DatabaseMan();
    private SecurityMan sc;
    
    /**
     * Creates new form FlightDetails
     */
    public FlightDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Origin", textFieldUpdateOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateOrigin);
        PromptSupport.setPrompt("Destination", textFieldUpdateDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateDestination);
        PromptSupport.setPrompt("Departure", textFieldUpdateDepartureDate);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateDepartureDate);
        PromptSupport.setPrompt("Arrival", textFieldUpdateArrivaDate);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateArrivaDate);
    }

    /**
     * clear all textFields
     */
    public void clearLuggage() {
        textFieldUpdateArrivaDate.setText("");
        textFieldUpdateDepartureDate.setText("");
        textFieldUpdateDestination.setText("");
        textFieldUpdateOrigin.setText("");
    }
    
     /**
     * Prepares the screen with data based on the supplied customer id
     * @param luggageID the lggage id get this from the database
     */
    public void loadFlight(int FlightID) {
        try {
            ResultSet resultFlight = db.query("SELECT * FROM flight WHERE flight_id = ?", new String[]{FlightID + ""});
            while(resultFlight.next()) {
                textFieldUpdateOrigin.setText(resultFlight.getString("origin"));
                textFieldUpdateDestination.setText(resultFlight.getString("destination"));
                textFieldUpdateDepartureDate.setText(resultFlight.getString("departure"));
                textFieldUpdateArrivaDate.setText(resultFlight.getString("arrival"));
                
                currentFlightId = Integer.parseInt(resultFlight.getString("flight_id"));
                labelOwnerIdDisplay.setText(resultFlight.getString("flight_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
    private void updateFlight() {
        String query = "UPDATE luggage";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        
        // If Some text fields are not empty we add the SET clause
        if(!textFieldUpdateOrigin.getText().equals("") || !textFieldUpdateDestination.getText().equals("") ||
            !textFieldUpdateDepartureDate.getText().equals("")|| !textFieldUpdateArrivaDate.getText().equals("")){
            query += " SET ";
        }
        
        try {
            
            // 
            if(!textFieldUpdateOrigin.getText().equals("")) {
                query += "origin = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateOrigin.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Origin is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // 
            if(!textFieldUpdateDestination.getText().equals("")) {
                query += "destination = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateDestination.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Destination is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate postcode placeholder
            if(!textFieldUpdateDepartureDate.getText().equals("")) {
                query += " departure = ?,";
                values.add(helpers.Filters.filteredDateTime(textFieldUpdateDepartureDate.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Departure is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate postcode placeholder
            if(!textFieldUpdateArrivaDate.getText().equals("")) {
                query += " departure = ?,";
                values.add(helpers.Filters.filteredDateTime(textFieldUpdateArrivaDate.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Departure is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
                        
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
        loadFlight(currentFlightId);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDisplayFLightnumber = new javax.swing.JLabel();
        labelHeaderLeftSide = new javax.swing.JLabel();
        labelOwnerIdDisplay = new javax.swing.JLabel();
        textFieldUpdateOrigin = new javax.swing.JTextField();
        textFieldUpdateDestination = new javax.swing.JTextField();
        textFieldUpdateDepartureDate = new javax.swing.JTextField();
        textFieldUpdateArrivaDate = new javax.swing.JTextField();
        labelStatus = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonCancelChanges = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelHeaderSearch = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        labelDisplayFLightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayFLightnumber.setText("Flightnumber:  ");

        labelHeaderLeftSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderLeftSide.setText("Customer details");

        labelOwnerIdDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerIdDisplay.setText(" XXXXXXXXX");

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackbackToSearchCustomer(evt);
            }
        });

        buttonUpdateCustomer.setText("Update");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateCustomerbuttonUpdateActionPerformed(evt);
            }
        });

        buttonCancelChanges.setText("Cancel");
        buttonCancelChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelChangesbuttonCancelActionPerformed(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        labelHeaderSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderSearch.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHeaderLeftSide)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDisplayFLightnumber)
                        .addGap(0, 0, 0)
                        .addComponent(labelOwnerIdDisplay))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textFieldUpdateOrigin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(textFieldUpdateDestination, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldUpdateDepartureDate, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldUpdateArrivaDate, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonCancelChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeaderSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHelp)
                    .addComponent(labelHeaderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDisplayFLightnumber)
                            .addComponent(labelOwnerIdDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateDepartureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateArrivaDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCancelChanges)
                            .addComponent(buttonUpdateCustomer)
                            .addComponent(buttonBack))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackbackToSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackbackToSearchCustomer
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_CUSTOMER);
    }//GEN-LAST:event_buttonBackbackToSearchCustomer

    private void buttonUpdateCustomerbuttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateCustomerbuttonUpdateActionPerformed
        this.userNotAFK();
        updateFlight();
    }//GEN-LAST:event_buttonUpdateCustomerbuttonUpdateActionPerformed

    private void buttonCancelChangesbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelChangesbuttonCancelActionPerformed
        this.userNotAFK();
        loadFlight(currentFlightId);
    }//GEN-LAST:event_buttonCancelChangesbuttonCancelActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancelChanges;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDisplayFLightnumber;
    private javax.swing.JLabel labelHeaderLeftSide;
    private javax.swing.JLabel labelHeaderSearch;
    private javax.swing.JLabel labelOwnerIdDisplay;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JTextField textFieldUpdateArrivaDate;
    private javax.swing.JTextField textFieldUpdateDepartureDate;
    private javax.swing.JTextField textFieldUpdateDestination;
    private javax.swing.JTextField textFieldUpdateOrigin;
    // End of variables declaration//GEN-END:variables
}
