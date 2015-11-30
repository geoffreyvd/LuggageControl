/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

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
 * This JPanel changes the data from the corresponding luggage into the database
 * @author Admin
 */
public class LuggageDetails extends SwitchingJPanel {

    private int currentLuggageId = 0; 
    
    private DatabaseMan db = new DatabaseMan();
    private SecurityMan sc;
    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public LuggageDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Location", textFieldUpdateLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateLocation);
        PromptSupport.setPrompt("OwnerID", textFieldUpdateOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateOwnerID);
        PromptSupport.setPrompt("Size", textFieldUpdateSize);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateSize);
        PromptSupport.setPrompt("Color", textFieldUpdateColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateColor);
        PromptSupport.setPrompt("Weight", textFieldUpdateWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateWeight);
        PromptSupport.setPrompt("Content", textFieldUpdateContent);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateContent);
        PromptSupport.setPrompt("Name", textFieldSearchName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSearchName);
        
    }
    
    /**
     * Clears all textfields
     */
    public void clearLuggage() {
        textFieldUpdateLocation.setText("");
        textFieldUpdateOwnerID.setText("");
        textFieldUpdateColor.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldUpdateWeight.setText("");
        textFieldUpdateSize.setText("");
        textFieldUpdateContent.setText("");
        textFieldSearchName.setText("");
    }
    
    /**
     * Prepares the screen with data based on the supplied customer id
     * @param luggageID the lggage id get this from the database
     */
    public void loadLuggage(int luggageID) {
        try {
            ResultSet resultLuggage = db.query("SELECT * FROM luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultLuggage.next()) {
                labelDisplayOrigin.setText(resultLuggage.getString("origin"));
                labelDisplayDestination.setText(resultLuggage.getString("destination"));
                textFieldUpdateLocation.setText(resultLuggage.getString("location"));
                textFieldUpdateColor.setText(resultLuggage.getString("color"));
                textFieldUpdateWeight.setText(resultLuggage.getString("weight"));
                textFieldUpdateSize.setText(resultLuggage.getString("size"));
                
                currentLuggageId = Integer.parseInt(resultLuggage.getString("luggage_id"));
            }
            ResultSet resultFlight = db.query("SELECT flight_id FROM luggage_flight WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultFlight.next()) {
                labelDisplayFlightnumber.setText(resultFlight.getString("flight_id"));
            }
            ResultSet resultOwner = db.query("SELECT customer_id FROM customer_luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultFlight.next()) {
                textFieldUpdateOwnerID.setText(resultFlight.getString("flight_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
//    private void updateLuggage() {
//        String query = "UPDATE luggage";
//        ArrayList<String> values = new ArrayList<String>();
//        ArrayList<String> types = new ArrayList<String>();
//        
//        // If Some text fields are not empty we add the SET clause
//        if(!textFieldUpdateLocation.getText().equals("") || !textFieldUpdateColor.getText().equals("") ||
//            !textFieldUpdateWeight.getText().equals("")|| !textFieldUpdateSize.getText().equals("") ||
//            !textFieldUpdateContent.getText().equals("")){
//            query += " SET ";
//        }
//        
//        try {
//            
//            // check if our email is not taken yet
//            if(db.queryOneResult("SELECT `email` FROM user WHERE email = ?", new String[]{textFieldEmail.getText()}).equals("")) {
//                query += "email = ?,";
//                values.add(sc.filteredString(textFieldEmail.getText()));
//                types.add("String");
//            }
//            else {
//                labelStatus.setText("Email adress already taken!");
//                this.resetLabel(5000, labelStatus);
//                return;
//            }
//            
//            // check if our cellphone number is still a actual cellphone number
//            if(!SecurityMan.filteredString(textFieldCellphone.getText(), new char[]{'0','1','2','3','4','5','6','7','8','9'}, true).equals("")) {
//                query += " cellphone = ?,";
//                values.add(sc.filteredString(textFieldCellphone.getText()));
//                types.add("String");
//            }
//            else {
//                labelStatus.setText("Invalid characters in cellphone, can only contain numbers");
//                this.resetLabel(5000, labelStatus);
//                return;
//            }
//            
//            // Validate gender
//            if(comboBoxGender.getSelectedItem().toString().equals("Female") || 
//                comboBoxGender.getSelectedItem().toString().equals("Male") || 
//                comboBoxGender.getSelectedItem().toString().equals("Androgenous")
//            ) {
//                query += " gender = ?,";
//                values.add(sc.filteredString(comboBoxGender.getSelectedItem().toString()));
//                types.add("String");
//            }
//            else {
//                labelStatus.setText("You used memory manipulation to edit this combobox, close but no cigar.");
//                this.resetLabel(5000, labelStatus);
//                return;
//            }
//            
//            // validate postcode placeholder
//            if(!textFieldPostcode.getText().equals("")) {
//                query += " postcode = ?,";
//                values.add(sc.filteredString(textFieldPostcode.getText()));
//                types.add("String");
//            }
//            else {
//                labelStatus.setText("Invalid characters in cellphone, can only contain numbers");
//                this.resetLabel(5000, labelStatus);
//                return;
//            }
//            
//            // validate adress placeholder
//            if(!textFieldAdress.getText().equals("")) {
//                query += " adress = ?,";
//                values.add(sc.filteredString(textFieldAdress.getText()));
//                types.add("String");
//            }
//            else {
//                labelStatus.setText("Invalid characters in cellphone, can only contain numbers");
//                this.resetLabel(5000, labelStatus);
//                return;
//            }
//            
//            // remove the last , from the string
//            query = query.substring(0, query.length()-1);
//            
//            // add the where clause to only update current user
//            query += " WHERE customer_id = ?";
//            values.add(currentCustomerId + "");
//            types.add("Int");
//            
//            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
//            values.clear();
//            types.clear();
//            
//            // check if our customer is not already linked to this flight
//            // this is the uglieest if statement I ever made.
//            if(!textFieldAddFlight.getText().equals("")) {
//                if(db.queryOneResult("SELECT `flight_id` FROM customer_flight WHERE flight_id = ? AND customer_id = ?", 
//                        new String[]{
//                            textFieldAddFlight.getText(),
//                            currentCustomerId + ""
//                        }
//                    ).equals("")
//                ) {
//                    // test if our flight id exists
//                    if(!db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", 
//                        new String[]{
//                            textFieldAddFlight.getText()
//                        }
//                    ).equals("")) {
//                        query = "INSERT INTO customer_flight (flight_id, customer_id) VALUES (?, ?)";
//                        values.add(sc.filteredInt(textFieldAddFlight.getText(), 1, Integer.MAX_VALUE));
//                        // int + "" is my favorite java conversion hack, its so dirty lol
//                        values.add(sc.filteredInt(currentCustomerId + "", 1, Integer.MAX_VALUE));
//                        types.add("Int");
//                        types.add("Int");
//                    }
//                    else {
//                        labelStatus.setText("flight does not exist");
//                        this.resetLabel(5000, labelStatus);
//                        return;
//                    }
//                }
//                else {
//                    labelStatus.setText("Customer has already been linked to this flight");
//                    this.resetLabel(5000, labelStatus);
//                    return;
//                }
//            }
//            else {
//                return;
//            }
//            
//            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
//                        
//        }
//        catch(Exception e) {
//            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
//        }
//        
//        loadLuggage(currentLuggageId);
//    }
    
    /**
     * Prepares the comboBox with the data from the database
     * @param status the status from the luggage tkaen from the database
     */
    private void setLuggageStatus(String status) {
        if(status.equals("Lost")) {
            comboBoxLuggageStatus.setSelectedIndex(0);
        }
        else if(status.equals("Found")) {
            comboBoxLuggageStatus.setSelectedIndex(1);
        }
        else if(status.equals("Returned")) {
            comboBoxLuggageStatus.setSelectedIndex(2);
        }
        else {
            new ErrorJDialog(this.luggageControl, true, "Error: State of the luggage does not exist", (new Throwable()).getStackTrace());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLuggageDetails = new javax.swing.JLabel();
        labelFlightnumber = new javax.swing.JLabel();
        labelDisplayFlightnumber = new javax.swing.JLabel();
        labelOrigin = new javax.swing.JLabel();
        labelDisplayOrigin = new javax.swing.JLabel();
        labelDestination = new javax.swing.JLabel();
        labelDisplayDestination = new javax.swing.JLabel();
        textFieldUpdateLocation = new javax.swing.JFormattedTextField();
        textFieldUpdateOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldUpdateColor = new javax.swing.JFormattedTextField();
        textFieldUpdateWeight = new javax.swing.JFormattedTextField();
        textFieldUpdateSize = new javax.swing.JFormattedTextField();
        textFieldUpdateContent = new javax.swing.JFormattedTextField();
        separatorCenter = new javax.swing.JSeparator();
        labelSearch = new javax.swing.JLabel();
        textFieldSearchName = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomerSearch = new javax.swing.JTable();
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();

        labelLuggageDetails.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelLuggageDetails.setText("Luggage details");

        labelFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFlightnumber.setText("Flightnumber: ");

        labelDisplayFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayFlightnumber.setText(" XXXXXXXXX");

        labelOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOrigin.setText("Origin: ");

        labelDisplayOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayOrigin.setText(" XXXXXXXXXX");

        labelDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDestination.setText("Destination: ");

        labelDisplayDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayDestination.setText(" XXXXXXXXX");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lost", "Found", "Returned" }));

        labelSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearch.setText("Customer Search");

        tableCustomerSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Customer ID", "Name", "Last flight"
            }
        ));
        jScrollPane1.setViewportView(tableCustomerSearch);

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateSize))
                    .addComponent(textFieldUpdateOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelOrigin)
                        .addGap(0, 0, 0)
                        .addComponent(labelDisplayOrigin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelFlightnumber)
                        .addGap(0, 0, 0)
                        .addComponent(labelDisplayFlightnumber))
                    .addComponent(labelLuggageDetails)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDestination)
                        .addGap(0, 0, 0)
                        .addComponent(labelDisplayDestination))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textFieldSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorCenter)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLuggageDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFlightnumber)
                            .addComponent(labelDisplayFlightnumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOrigin)
                            .addComponent(labelDisplayOrigin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDestination)
                            .addComponent(labelDisplayDestination))
                        .addGap(20, 20, 20)
                        .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldUpdateContent, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonCancel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textFieldSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonHelp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBack)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearch)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    /**
     * sets the user as not afk and changes to panel home screen
     * @param evt 
     */
    private void butonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelActionPerformed
        this.userNotAFK();
        clearLuggage();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_butonCancelActionPerformed

    /**
     * searches through the database with the given variables
     * @param evt 
     */
    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonSearchActionPerformed

    /**
     * sets the user as not afk and changes to panel searchLuggage
     * @param evt 
     */
    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearLuggage();
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_LUGGAGE);
    }//GEN-LAST:event_buttonBackActionPerformed

    /**
     * sets the user as not afk and changes to panel help_linking
     * @param evt 
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.LINKING);
    }//GEN-LAST:event_buttonHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDestination;
    private javax.swing.JLabel labelDisplayDestination;
    private javax.swing.JLabel labelDisplayFlightnumber;
    private javax.swing.JLabel labelDisplayOrigin;
    private javax.swing.JLabel labelFlightnumber;
    private javax.swing.JLabel labelLuggageDetails;
    private javax.swing.JLabel labelOrigin;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JTable tableCustomerSearch;
    private javax.swing.JFormattedTextField textFieldSearchName;
    private javax.swing.JFormattedTextField textFieldUpdateColor;
    private javax.swing.JFormattedTextField textFieldUpdateContent;
    private javax.swing.JFormattedTextField textFieldUpdateLocation;
    private javax.swing.JFormattedTextField textFieldUpdateOwnerID;
    private javax.swing.JFormattedTextField textFieldUpdateSize;
    private javax.swing.JFormattedTextField textFieldUpdateWeight;
    // End of variables declaration//GEN-END:variables
}
