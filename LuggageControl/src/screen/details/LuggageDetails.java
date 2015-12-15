/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.details;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;
import screen.base.BaseDetails;
import screen.base.SearchPanes;

/**
 * This JPanel changes the data from the corresponding luggage into the database
 * @author Admin
 */
public class LuggageDetails extends BaseDetails {

    private int currentLuggageId = 0; 
    
    private String luggageImage = "";

    private SecurityMan sc;
    
    private SearchPanes searchPanes = new SearchPanes();
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
        
//        SearchPanes henk = new SearchPanes();
//        LayoutManager ly = searchPanel.getLayout();
//        henk.setVisible(true);
//        ly.addLayoutComponent("SearchPanes", henk);
//        searchPanel.add(henk);
//        searchPanel.setLayout(ly);
//        searchPanel.revalidate();
//        searchPanel.repaint();
    }
    
    @Override
    public void updatePanelInformation() {
        this.loadLuggage(currentLuggageId);
    }
    
    @Override
    public void updatePanelInformation(int luggageId) {
        this.loadLuggage(luggageId);
    }
    
    @Override
    public void clearPanelInformation() {
        this.clearLuggage();
    }
    
    /**
     * Clears all textfields
     */
    private void clearLuggage() {
        textFieldUpdateLocation.setText("");
        textFieldUpdateOwnerID.setText("");
        textFieldUpdateColor.setText("");
        comboBoxUpdateStatus.setSelectedIndex(0);
        textFieldUpdateWeight.setText("");
        textFieldUpdateSize.setText("");
        textPaneUpdateContent.setText("");
    }
    
    
    /**
     * Prepares the screen with data based on the supplied customer id
     * @param luggageID the luggage id get this from the database
     */
    private void loadLuggage(int luggageID) {
        try {
            ResultSet resultLuggage = db.query("SELECT * FROM luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultLuggage.next()) {
                textFieldUpdateLocation.setText(resultLuggage.getString("location"));
                textFieldUpdateColor.setText(resultLuggage.getString("color"));
                textFieldUpdateWeight.setText(resultLuggage.getString("weight"));
                textFieldUpdateSize.setText(resultLuggage.getString("size"));
                textPaneUpdateContent.setText(resultLuggage.getString("content"));
                
                currentLuggageId = Integer.parseInt(resultLuggage.getString("luggage_id"));
            }
            
            ResultSet resultFlight = db.query("SELECT flight_id FROM luggage_flight WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultFlight.next()) {
                labelDisplayFlightnumber.setText(resultFlight.getString("flight_id"));
            }
            
            ResultSet resultFlightDetails = db.query("SELECT origin, destination FROM flight "
                    + "INNER JOIN luggage_flight ON `luggage_flight`.`flight_id` "
                    + "WHERE luggage_id = ? AND `luggage_flight`.`flight_id` = `flight`.`flight_id`", new String[]{luggageID + ""});
            while(resultFlightDetails.next()) {
                labelDisplayOrigin.setText(resultFlightDetails.getString("origin"));
                labelDisplayDestination.setText(resultFlightDetails.getString("destination"));
            }
            
            ResultSet resultOwner = db.query("SELECT customer_id FROM customer_luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultFlight.next()) {
                textFieldUpdateOwnerID.setText(resultFlight.getString("customer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
    private void updateLuggage() {
        String query = "UPDATE luggage";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        
        // If Some text fields are not empty we add the SET clause
        if(!textFieldUpdateLocation.getText().equals("") || !textFieldUpdateColor.getText().equals("") ||
            !textFieldUpdateWeight.getText().equals("")|| !textFieldUpdateSize.getText().equals("") ||
            !textPaneUpdateContent.getText().equals("")){
            query += " SET ";
        }
        
        try {
            
            // check if our email is not taken yet
            if(!textFieldUpdateLocation.getText().equals("")) {
                query += "location = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateLocation.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Email adress already taken!");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // Validate the status
            if(comboBoxUpdateStatus.getSelectedItem().toString().equals("Lost") || 
                comboBoxUpdateStatus.getSelectedItem().toString().equals("Found") || 
                comboBoxUpdateStatus.getSelectedItem().toString().equals("Returned")
            ) {
                query += " status = ?,";
                values.add(helpers.Filters.filteredString(comboBoxUpdateStatus.getSelectedItem().toString()));
                types.add("String");
            }
            else {
                labelStatus.setText("You used memory manipulation to edit this combobox, close but no cigar.");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate postcode placeholder
            if(!textFieldUpdateColor.getText().equals("")) {
                query += " color = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateColor.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Color is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate weight placeholder
            if(!textFieldUpdateWeight.getText().equals("")) {
                query += " weight = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateWeight.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Weight is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate size placeholder
            if(!textFieldUpdateSize.getText().equals("")) {
                query += " size = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateSize.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Size is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate size placeholder
            if(!textPaneUpdateContent.getText().equals("")) {
                query += " content = ?,";
                values.add(helpers.Filters.filteredString(textPaneUpdateContent.getText()));
                types.add("String");
            }
            else {
                labelStatus.setText("Size is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // remove the last , from the string
            query = query.substring(0, query.length()-1); //????
            
            // add the where clause to only update current luggage
            query += " WHERE luggage_id = ?";
            values.add(currentLuggageId + "");
            types.add("Int");
            
            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
            values.clear();
            types.clear();
          
            
            // check if our customer is not already linked to this flight
            // this is the uglieest if statement I ever made.
            if(!textFieldUpdateOwnerID.getText().equals("")) {
                if(db.queryOneResult("SELECT `customer_id` FROM customer WHERE customer_id = ?", 
                        new String[]{textFieldUpdateOwnerID.getText()})
                    .equals(textFieldUpdateOwnerID.getText())) {
                    
                    query = "INSERT INTO customer_luggage (customer_id, luggage_id) VALUES (?, ?)";
                    values.add(helpers.Filters.filteredInt(textFieldUpdateOwnerID.getText(), 1, Integer.MAX_VALUE));
                    values.add(helpers.Filters.filteredInt(currentLuggageId + "", 1, Integer.MAX_VALUE));
                    types.add("Int"); 
                    types.add("Int");
                }
                else {
                        labelStatus.setText("Customer does not exist");
                        this.resetLabel(5000, labelStatus);
                        return;
                    }
            }
            else if(!helpers.Filters.filteredString(textFieldUpdateOwnerID.getText(), new char[]{'0','1','2','3','4','5','6','7','8','9'}, true).equals("")) {
                    labelStatus.setText("Customer id must be numbers only");
                    this.resetLabel(5000, labelStatus);
                    return;
            }
         
            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
                        
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
        loadLuggage(currentLuggageId);
    }
    
    private void searchCustomer() {
        
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
        comboBoxUpdateStatus = new javax.swing.JComboBox();
        textFieldUpdateColor = new javax.swing.JFormattedTextField();
        textFieldUpdateWeight = new javax.swing.JFormattedTextField();
        textFieldUpdateSize = new javax.swing.JFormattedTextField();
        separatorCenter = new javax.swing.JSeparator();
        labelSearch = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneUpdateContent = new javax.swing.JTextPane();
        labelStatus = new javax.swing.JLabel();
        labelDescription = new javax.swing.JLabel();
        searchPanes1 = new screen.base.SearchPanes();

        labelLuggageDetails.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelLuggageDetails.setText("Luggage details");

        labelFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFlightnumber.setText("Flightnumber: ");

        labelDisplayFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayFlightnumber.setText(" XXXXXXXXX");

        labelOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOrigin.setText("Origin: ");

        labelDisplayOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayOrigin.setText(" XXXXXXXXX");

        labelDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDestination.setText("Destination: ");

        labelDisplayDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayDestination.setText(" XXXXXXXXX");

        comboBoxUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lost", "Found", "Returned" }));

        textFieldUpdateSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUpdateSizeActionPerformed(evt);
            }
        });

        labelSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearch.setText("Search");

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

        jScrollPane2.setViewportView(textPaneUpdateContent);

        labelDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDescription.setText("Description:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldUpdateOwnerID)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxUpdateStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldUpdateColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldUpdateSize)
                                .addGap(2, 2, 2))
                            .addComponent(textFieldUpdateWeight)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLuggageDetails)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDestination)
                                    .addComponent(labelFlightnumber)
                                    .addComponent(labelOrigin))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelDisplayDestination)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelDisplayOrigin)
                                        .addComponent(labelDisplayFlightnumber))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDescription))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(searchPanes1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorCenter)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescription)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonCancel))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonHelp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBack)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchPanes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
        updateLuggage();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    /**
     * sets the user as not afk and changes to panel home screen
     * @param evt 
     */
    private void butonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelActionPerformed
        this.userNotAFK();
        updateLuggage();
    }//GEN-LAST:event_butonCancelActionPerformed

    /**
     * sets the user as not afk and changes to panel searchLuggage
     * @param evt 
     */
    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearLuggage();
        this.luggageControl.switchJPanel(this.luggageControl.SEARCH_LUGGAGE);
    }//GEN-LAST:event_buttonBackActionPerformed

    /**
     * sets the user as not afk and changes to panel help_linking
     * @param evt 
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP_LINKING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void textFieldUpdateSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUpdateSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUpdateSizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboBoxUpdateStatus;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelDestination;
    private javax.swing.JLabel labelDisplayDestination;
    private javax.swing.JLabel labelDisplayFlightnumber;
    private javax.swing.JLabel labelDisplayOrigin;
    private javax.swing.JLabel labelFlightnumber;
    private javax.swing.JLabel labelLuggageDetails;
    private javax.swing.JLabel labelOrigin;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JLabel labelStatus;
    private screen.base.SearchPanes searchPanes1;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JFormattedTextField textFieldUpdateColor;
    private javax.swing.JFormattedTextField textFieldUpdateLocation;
    private javax.swing.JFormattedTextField textFieldUpdateOwnerID;
    private javax.swing.JFormattedTextField textFieldUpdateSize;
    private javax.swing.JFormattedTextField textFieldUpdateWeight;
    private javax.swing.JTextPane textPaneUpdateContent;
    // End of variables declaration//GEN-END:variables
}
