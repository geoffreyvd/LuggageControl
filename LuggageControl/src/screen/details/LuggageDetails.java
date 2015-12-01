/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.details;

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
        PromptSupport.setPrompt("Firstname", textFieldSearchFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSearchFirstname);
        PromptSupport.setPrompt("Surname", textFieldSearchSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSearchSurname);
        PromptSupport.setPrompt("Birthday", textFieldSearchBirthday);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSearchBirthday);
        
    }
    
    /**
     * Clears all textfields
     */
    public void clearLuggage() {
        textFieldUpdateLocation.setText("");
        textFieldUpdateOwnerID.setText("");
        textFieldUpdateColor.setText("");
        comboBoxUpdateStatus.setSelectedIndex(0);
        textFieldUpdateWeight.setText("");
        textFieldUpdateSize.setText("");
        textPaneUpdateContent.setText("");
        textFieldSearchFirstname.setText("");
    }
    
    /**
     * Prepares the screen with data based on the supplied customer id
     * @param luggageID the lggage id get this from the database
     */
    public void loadLuggage(int luggageID) {
        try {
            ResultSet resultLuggage = db.query("SELECT * FROM luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while(resultLuggage.next()) {
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
            
            ResultSet resultFlightDetails = db.query("SELECT origin, destination FROM flight "
                    + "INNER JOIN luggage_flight ON `luggage_flight`.`flight_id` "
                    + "WHERE luggage_id = ? AND `luggage_flight`.`flight_id` = `flight`.`flight_id`", new String[]{luggageID + ""});
            while(resultFlightDetails.next()) {
                labelDisplayOrigin.setText(resultLuggage.getString("origin"));
                labelDisplayDestination.setText(resultLuggage.getString("destination"));
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
                query += "email = ?,";
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
                query += " gender = ?,";
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
                query += " size = ?,";
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
    
    /**
     * Prepares the comboBox with the data from the database
     * @param status the status from the luggage tkaen from the database
     */
    private void setLuggageStatus(String status) {
        if(status.equals("Lost")) {
            comboBoxUpdateStatus.setSelectedIndex(0);
        }
        else if(status.equals("Found")) {
            comboBoxUpdateStatus.setSelectedIndex(1);
        }
        else if(status.equals("Returned")) {
            comboBoxUpdateStatus.setSelectedIndex(2);
        }
        else {
            new ErrorJDialog(this.luggageControl, true, "Error: State of the luggage does not exist", (new Throwable()).getStackTrace());
        }
    }
    
    /**
     * Searches through the database for the customer with filters from 
     * the textfields or for every luggage when none of the fields are filled in
     */
    private void fillSearchLuggageTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT customer_id, firstname, surname, birthday FROM customer ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldSearchFirstname.getText().equals("") || 
            !textFieldSearchSurname.getText().equals("") ||
            !textFieldSearchBirthday.getText().equals("")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldSearchFirstname.getText().equals("")) {
                query += "OR firstname = ? ";
                values.add(helpers.Filters.filteredString(textFieldSearchFirstname.getText()));
            }

            if (!textFieldSearchSurname.getText().equals("")) {
                query += "OR surname = ? ";
                values.add(helpers.Filters.filteredString(textFieldSearchSurname.getText()));
            }

            if (!textFieldSearchBirthday.getText().equals("")) {
                query += "OR birthday = ? ";
                values.add(helpers.Filters.filteredString(textFieldSearchBirthday.getText()));
            }
            
            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableCustomerSearch.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("customer_id"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    result.getString("birthday")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableCustomerSearch.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
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
        comboBoxUpdateStatus = new javax.swing.JComboBox();
        textFieldUpdateColor = new javax.swing.JFormattedTextField();
        textFieldUpdateWeight = new javax.swing.JFormattedTextField();
        textFieldUpdateSize = new javax.swing.JFormattedTextField();
        separatorCenter = new javax.swing.JSeparator();
        labelSearch = new javax.swing.JLabel();
        textFieldSearchFirstname = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomerSearch = new javax.swing.JTable();
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneUpdateContent = new javax.swing.JTextPane();
        labelStatus = new javax.swing.JLabel();
        textFieldSearchSurname = new javax.swing.JFormattedTextField();
        textFieldSearchBirthday = new javax.swing.JFormattedTextField();

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

        labelSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearch.setText("Customer Search");

        tableCustomerSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Firstname", "Surname", "Birthday"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustomerSearch.setColumnSelectionAllowed(true);
        tableCustomerSearch.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableCustomerSearch);
        tableCustomerSearch.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        jScrollPane2.setViewportView(textPaneUpdateContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldUpdateOwnerID, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(textFieldUpdateLocation)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboBoxUpdateStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldUpdateSize)))
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLuggageDetails)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(labelDisplayFlightnumber)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textFieldSearchFirstname)
                    .addComponent(textFieldSearchBirthday)
                    .addComponent(textFieldSearchSurname))
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
                        .addGap(35, 35, 35)
                        .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonCancel))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonHelp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBack)))
                        .addGap(18, 18, 18)
                        .addComponent(textFieldSearchFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(textFieldSearchSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldSearchBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(buttonSearch)
                        .addGap(32, 32, 32))))
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
        fillSearchLuggageTable();
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
    private javax.swing.JComboBox comboBoxUpdateStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDestination;
    private javax.swing.JLabel labelDisplayDestination;
    private javax.swing.JLabel labelDisplayFlightnumber;
    private javax.swing.JLabel labelDisplayOrigin;
    private javax.swing.JLabel labelFlightnumber;
    private javax.swing.JLabel labelLuggageDetails;
    private javax.swing.JLabel labelOrigin;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JTable tableCustomerSearch;
    private javax.swing.JFormattedTextField textFieldSearchBirthday;
    private javax.swing.JFormattedTextField textFieldSearchFirstname;
    private javax.swing.JFormattedTextField textFieldSearchSurname;
    private javax.swing.JFormattedTextField textFieldUpdateColor;
    private javax.swing.JFormattedTextField textFieldUpdateLocation;
    private javax.swing.JFormattedTextField textFieldUpdateOwnerID;
    private javax.swing.JFormattedTextField textFieldUpdateSize;
    private javax.swing.JFormattedTextField textFieldUpdateWeight;
    private javax.swing.JTextPane textPaneUpdateContent;
    // End of variables declaration//GEN-END:variables
}
