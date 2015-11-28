/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Admin
 */
public class CustomerDetails extends SwitchingJPanel {
    
    private int currentCustomerId = 0; 
    
    private DatabaseMan db = new DatabaseMan();
    private SecurityMan sc;

    public CustomerDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flightnumber", textFieldAddFlight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAddFlight);
        PromptSupport.setPrompt("Cellphone", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Flightnumber", textFieldFlight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlight);
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageId);
    }
    
//    @Override
//    protected void updatePanel() {
//        loadCustomer(currentCustomerId);
//    }
    
    /**
     * Clears all data so we can present new result sets to the user.
     */
    private void clearData() {
        comboBoxFlight.removeAllItems();
        comboBoxStatus.removeAllItems();
        labelNameDisplay.setText("");
        labelOwnerIdDisplay.setText("");
        labelSurnameDisplay.setText("");
        textFieldAddFlight.setText("");
        textFieldCellphone.setText("");
        textFieldEmail.setText("");
        textFieldFlight.setText("");
        textFieldLuggageId.setText("");
    }
    
    /**
     * Fill the flight combo box based on the current customer id
     * Retrieves the customer id from private variable
     */
    public void fillCustomerFlights() {
        this.fillCustomerFlights(currentCustomerId);
    }
    
    /**
     * Fill the flight combo box based on the current customer id
     * @param flightId specific customer id to use as reference
     */
    public void fillCustomerFlights(int customerId) {
        comboBoxFlight.removeAllItems();
        DatabaseMan db = new DatabaseMan();
        try {
            String query = "SELECT `flight`.flight_id, origin, destination, departure, arrival ";
            query += "FROM flight INNER JOIN customer_flight WHERE customer_id = ? AND `customer_flight`.flight_id = `flight`.flight_id;";
            ResultSet result = db.query(query, new String[]{String.valueOf(customerId)});
            while(result.next()) {
                comboBoxFlight.addItem(result.getString("flight_id"));
            }
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }

    /**
     * Prepares the screen with data based on the supplied customer id
     * @param customerID the customer id get this from the database
     */
    public void loadCustomer(int customerID) {
        this.clearData();
        try {
            DatabaseMan db = new DatabaseMan();
            ResultSet result = db.query("SELECT * FROM customer WHERE customer_id = ?", new String[]{customerID + ""});
            // get the first row result
            while(result.next()) {
                labelNameDisplay.setText(result.getString("firstname"));
                labelSurnameDisplay.setText(result.getString("surname"));
                textFieldCellphone.setText(result.getString("cellphone"));
                textFieldEmail.setText(result.getString("email"));
                labelOwnerIdDisplay.setText(result.getString("customer_id"));
                currentCustomerId = Integer.parseInt(result.getString("customer_id"));
                this.fillCustomerFlights(currentCustomerId);
            }
        } 
        catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * Remove link between customer and flight
     * @param flight_id the id of the flight present in customer_flight
     */
    private void removeLink(int flight_id) {
        try {
            db.queryManipulation("DELETE FROM customer_flight WHERE customer_id = ? AND flight_id = ?", 
            new String[]{currentCustomerId+"", flight_id+""}, new String[]{"Int", "Int"});
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * Update the customer information based on the current information.
     */
    private void updateCustomer() {
        String query = "UPDATE customer";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        
        // If Some text fields are not empty we add the WHERE clause
        if(!textFieldEmail.getText().equals("") || !textFieldCellphone.getText().equals("")) {
            query += " SET ";
        }
        
        try {
            
            // check if our email is not taken yet
            if(db.queryOneResult("SELECT `email` FROM user WHERE email = ?", new String[]{textFieldEmail.getText()}).equals("")) {
                query += "email = ?,";
                values.add(sc.filteredString(textFieldEmail.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Email adress already taken!");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // check if our cellphone number is still a actual cellphone number
            if(!SecurityMan.filteredString(textFieldCellphone.getText(), new char[]{'0','1','2','3','4','5','6','7','8','9'}, true).equals("")) {
                query += " cellphone = ?,";
                values.add(sc.filteredString(textFieldCellphone.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Invalid characters in cellphone, can only contain numbers");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // remove the last , from the string
            query = query.substring(0, query.length()-1);
            
            // add the where clause to only update current user
            query += " WHERE customer_id = ?";
            values.add(currentCustomerId + "");
            types.add("Int");
            
            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
            values.clear();
            types.clear();
            
            // check if our customer is not already linked to this flight
            // this is the uglieest if statement I ever made.
            if(!textFieldAddFlight.getText().equals("")) {
                if(db.queryOneResult("SELECT `flight_id` FROM customer_flight WHERE flight_id = ? AND customer_id = ?", 
                        new String[]{
                            textFieldAddFlight.getText(),
                            currentCustomerId + ""
                        }
                    ).equals("")
                ) {
                    // test if our flight id exists
                    if(!db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", 
                        new String[]{
                            textFieldAddFlight.getText()
                        }
                    ).equals("")) {
                        query = "INSERT INTO customer_flight (flight_id, customer_id) VALUES (?, ?)";
                        values.add(sc.filteredInt(textFieldAddFlight.getText(), 1, Integer.MAX_VALUE));
                        // int + "" is my favorite java conversion hack, its so dirty lol
                        values.add(sc.filteredInt(currentCustomerId + "", 1, Integer.MAX_VALUE));
                        types.add("Int");
                        types.add("Int");
                    }
                    else {
                        labelStatus.setText("flight does not exist");
                        this.resetLabel(5000, labelStatus);
                        return;
                    }
                }
                else {
                    labelStatus.setText("Customer has already been linked to this flight");
                    this.resetLabel(5000, labelStatus);
                    return;
                }
            }
            else {
                return;
            }
            
            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
                        
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
        loadCustomer(currentCustomerId);
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
        labelSurname = new javax.swing.JLabel();
        labelSurnameDisplay = new javax.swing.JLabel();
        labelOwnerId = new javax.swing.JLabel();
        labelOwnerIdDisplay = new javax.swing.JLabel();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldAddFlight = new javax.swing.JFormattedTextField();
        comboBoxFlight = new javax.swing.JComboBox();
        buttonRemoveFlightNumber = new javax.swing.JButton();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonCancelChanges = new javax.swing.JButton();
        separatorScreenDefider = new javax.swing.JSeparator();
        labelHeaderRightSide = new javax.swing.JLabel();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        textFieldFlight = new javax.swing.JFormattedTextField();
        comboBoxStatus = new javax.swing.JComboBox();
        scrollPaneSearchTable = new javax.swing.JScrollPane();
        tableSearch = new javax.swing.JTable();
        buttonSearchLuggage = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();

        labelHeaderLeftSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderLeftSide.setText("Customer details");

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setText("Name: ");

        labelNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNameDisplay.setText(" XXXXXXXXX");

        labelSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelSurname.setText("Surname: ");

        labelSurnameDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelSurnameDisplay.setText(" XXXXXXXXX");

        labelOwnerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerId.setText("Customer id");

        labelOwnerIdDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerIdDisplay.setText(" XXXXXXXXX");

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldCellphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCellphoneActionPerformed(evt);
            }
        });

        textFieldEmail.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldAddFlight.setMaximumSize(new java.awt.Dimension(150, 150));

        comboBoxFlight.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonRemoveFlightNumber.setText("Remove");
        buttonRemoveFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        buttonUpdateCustomer.setText("Update");
        buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonCancelChanges.setText("Cancel");
        buttonCancelChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        labelHeaderRightSide.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderRightSide.setText("Luggage search");

        textFieldLuggageId.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldLuggageId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLuggageIdActionPerformed(evt);
            }
        });

        textFieldFlight.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFlightActionPerformed(evt);
            }
        });

        comboBoxStatus.setMaximumSize(new java.awt.Dimension(150, 150));

        tableSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Flightnumber", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPaneSearchTable.setViewportView(tableSearch);

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(buttonRemoveFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonCancelChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelName)
                                                .addGap(0, 0, 0)
                                                .addComponent(labelNameDisplay))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelOwnerId)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelOwnerIdDisplay))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelSurname)
                                                .addGap(0, 0, 0)
                                                .addComponent(labelSurnameDisplay)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldAddFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldCellphone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxFlight, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(104, 104, 104)))
                        .addComponent(separatorScreenDefider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeaderRightSide, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scrollPaneSearchTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(comboBoxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(labelSurname)
                            .addComponent(labelSurnameDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOwnerId)
                            .addComponent(labelOwnerIdDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRemoveFlightNumber))
                        .addGap(11, 11, 11)
                        .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldAddFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(textFieldFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPaneSearchTable, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearchLuggage)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldCellphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCellphoneActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldCellphoneActionPerformed

    private void textFieldLuggageIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLuggageIdActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldLuggageIdActionPerformed

    private void textFieldFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFlightActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldFlightActionPerformed

    private void backToSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToSearchCustomer
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_CUSTOMER);
    }//GEN-LAST:event_backToSearchCustomer

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonSearchActionPerformed

    /**
     * Cancel updates all fields with the values that where in the database
     * @param evt button event not used
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        updateCustomer();
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * Update query to update the current user information and add additional information.
     * @param evt event details, not used
     */
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
        updateCustomer();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        this.userNotAFK();
        try {
            removeLink(Integer.parseInt(comboBoxFlight.getSelectedItem().toString()));
        }
        catch(NullPointerException e) {
            labelStatus.setText("Cannot delete unexisting reference");
            this.resetLabel(5000, labelStatus);
            return;
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        updateCustomer();
    }//GEN-LAST:event_buttonRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancelChanges;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonRemoveFlightNumber;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JComboBox comboBoxFlight;
    private javax.swing.JComboBox comboBoxStatus;
    private javax.swing.JLabel labelHeaderLeftSide;
    private javax.swing.JLabel labelHeaderRightSide;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNameDisplay;
    private javax.swing.JLabel labelOwnerId;
    private javax.swing.JLabel labelOwnerIdDisplay;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelSurname;
    private javax.swing.JLabel labelSurnameDisplay;
    private javax.swing.JScrollPane scrollPaneSearchTable;
    private javax.swing.JSeparator separatorScreenDefider;
    private javax.swing.JTable tableSearch;
    private javax.swing.JFormattedTextField textFieldAddFlight;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFlight;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    // End of variables declaration//GEN-END:variables
}
