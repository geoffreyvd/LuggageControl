/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.details;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
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

/**
 * This JPanel changes the data from the corresponding luggage into the database
 * @author Admin
 */
public class LuggageDetails extends BaseDetails {

    private int currentLuggageId = 0; 
    
    private String luggageImage = "";

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
        PromptSupport.setPrompt("Firstname", textFieldCustomerId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerId);
        PromptSupport.setPrompt("Surname", textFieldCustomerSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerSurname);
        
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
        textFieldCustomerId.setText("");
        textFieldCustomerSurname.setText("");
        comboBoxCustomerGender.setSelectedIndex(0);
        textFieldFlightId.setText("");
        textFieldFlightOrigin.setText("");
        textFieldFlightDestination.setText("");
        textFieldFlightDeparture.setText("");
        textFieldFlightArrival.setText("");
        
        DefaultTableModel datamodel = (DefaultTableModel) tableLugSearchFlight.getModel();
        for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
            datamodel.removeRow(i);
        }
        
        datamodel = (DefaultTableModel) tableSearchCustomer.getModel();
        for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
            datamodel.removeRow(i);
        }
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
    
    private void searchFlight() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT flight_id, origin, destination, departure, arrival FROM flight ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFlightId.getText().equals("") || !textFieldFlightOrigin.getText().equals("") ||
            !textFieldFlightDestination.getText().equals("") || !textFieldFlightDeparture.getText().equals("") || 
            !textFieldFlightArrival.getText().equals("")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldFlightId.getText().equals("")) {
                query += "OR flight_id = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightId.getText()));
            }

            if (!textFieldFlightOrigin.getText().equals("")) {
                query += "OR origin = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightOrigin.getText()));
            }

            if (!textFieldFlightDestination.getText().equals("")) {
                query += "OR destination = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightDestination.getText()));
            }
            if (!textFieldFlightDeparture.getText().equals("")) {
                query += "OR departure = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightDeparture.getText()));
            }
            if (!textFieldFlightArrival.getText().equals("")) {
                query += "OR arrival = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightArrival.getText()));
            }            

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableLugSearchFlight.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("flight_id"),
                    result.getString("origin"),
                    result.getString("destination"),
                    result.getString("departure"),
                    result.getString("arrival")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableLugSearchFlight.setModel(datamodel);
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
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneUpdateContent = new javax.swing.JTextPane();
        labelStatus = new javax.swing.JLabel();
        tabPaneSearch = new javax.swing.JTabbedPane();
        panelUploadImage = new javax.swing.JPanel();
        labelUploadImage = new javax.swing.JLabel();
        buttonUploadImage = new javax.swing.JButton();
        panelSearchCustomer = new javax.swing.JPanel();
        scrollPaneCustomerTable = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();
        comboBoxCustomerGender = new javax.swing.JComboBox();
        textFieldCustomerSurname = new javax.swing.JFormattedTextField();
        textFieldCustomerId = new javax.swing.JFormattedTextField();
        buttonSearchCustomer = new javax.swing.JButton();
        textFieldCustomerFirstname = new javax.swing.JFormattedTextField();
        textFieldCustomerEmail = new javax.swing.JFormattedTextField();
        textFieldCustomerCellphone = new javax.swing.JFormattedTextField();
        textFieldCustomerBirthday = new javax.swing.JFormattedTextField();
        textFieldCustomerPostcode = new javax.swing.JFormattedTextField();
        textFieldCustomerAdress = new javax.swing.JFormattedTextField();
        panelSearchFlight = new javax.swing.JPanel();
        scrollPaneFlightTable = new javax.swing.JScrollPane();
        tableLugSearchFlight = new javax.swing.JTable();
        textFieldFlightOrigin = new javax.swing.JFormattedTextField();
        textFieldFlightId = new javax.swing.JFormattedTextField();
        buttonSearchFlight = new javax.swing.JButton();
        textFieldFlightDestination = new javax.swing.JFormattedTextField();
        textFieldFlightArrival = new javax.swing.JFormattedTextField();
        textFieldFlightDeparture = new javax.swing.JFormattedTextField();
        panelSearchLuggage = new javax.swing.JPanel();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage = new javax.swing.JTable();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldLuggageLocation = new javax.swing.JFormattedTextField();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        buttonSearchLuggage = new javax.swing.JButton();
        scrollPaneContent = new javax.swing.JScrollPane();
        textPaneLuggageDescription = new javax.swing.JTextPane();
        comboBoxLuggageSize = new javax.swing.JComboBox();
        textFieldLuggageWeight = new javax.swing.JFormattedTextField();
        textFieldLuggageColor = new javax.swing.JFormattedTextField();
        labelLuggageDescription = new javax.swing.JLabel();
        panelSearchUser = new javax.swing.JPanel();
        scrollPaneUserTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage1 = new javax.swing.JTable();
        comboBoxUserGender = new javax.swing.JComboBox();
        textFieldUserName = new javax.swing.JFormattedTextField();
        textFieldUserId = new javax.swing.JFormattedTextField();
        buttonSearchUser = new javax.swing.JButton();
        textFieldUserFirstName = new javax.swing.JFormattedTextField();
        textFieldUserSurName = new javax.swing.JFormattedTextField();
        textFieldUserCellphone = new javax.swing.JFormattedTextField();
        textFieldUserBirthday = new javax.swing.JFormattedTextField();
        textFieldUserNationality = new javax.swing.JFormattedTextField();
        textFieldUserAdress = new javax.swing.JFormattedTextField();
        textFieldUserPostcode = new javax.swing.JFormattedTextField();
        labelDescription = new javax.swing.JLabel();

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

        labelUploadImage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUploadImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelUploadImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonUploadImage.setText("Upload image");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadImagebuttonUploadsImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUploadImageLayout = new javax.swing.GroupLayout(panelUploadImage);
        panelUploadImage.setLayout(panelUploadImageLayout);
        panelUploadImageLayout.setHorizontalGroup(
            panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(labelUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUploadImageLayout.setVerticalGroup(
            panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUploadImage)
                .addContainerGap())
        );

        tabPaneSearch.addTab("Upload image", panelUploadImage);

        tableSearchCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Firstname", "Surname", "Email", "Cellphone", "Birthday", "Gender", "Adress", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchCustomer.setColumnSelectionAllowed(true);
        tableSearchCustomer.setFocusable(false);
        tableSearchCustomer.getTableHeader().setReorderingAllowed(false);
        scrollPaneCustomerTable.setViewportView(tableSearchCustomer);
        tableSearchCustomer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxCustomerGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "gender", "male", "female", "androgenous" }));
        comboBoxCustomerGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerSurname.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomer(evt);
            }
        });

        textFieldCustomerFirstname.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerEmail.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerCellphone.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerBirthday.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerPostcode.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerAdress.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchCustomerLayout = new javax.swing.GroupLayout(panelSearchCustomer);
        panelSearchCustomer.setLayout(panelSearchCustomerLayout);
        panelSearchCustomerLayout.setHorizontalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneCustomerTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(buttonSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCustomerFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCustomerCellphone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerPostcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxCustomerGender, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchCustomerLayout.setVerticalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCustomerGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneCustomerTable, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchCustomer)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("Customer", panelSearchCustomer);

        tableLugSearchFlight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight ID", "Origin", "Destination", "Departure", "Arrival"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchFlight.setColumnSelectionAllowed(true);
        tableLugSearchFlight.setFocusable(false);
        tableLugSearchFlight.setRowSelectionAllowed(false);
        tableLugSearchFlight.getTableHeader().setReorderingAllowed(false);
        scrollPaneFlightTable.setViewportView(tableLugSearchFlight);
        tableLugSearchFlight.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        textFieldFlightOrigin.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchFlight.setText("Search");
        buttonSearchFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchFlight(evt);
            }
        });

        textFieldFlightDestination.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightArrival.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightDeparture.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchFlightLayout = new javax.swing.GroupLayout(panelSearchFlight);
        panelSearchFlight.setLayout(panelSearchFlightLayout);
        panelSearchFlightLayout.setHorizontalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldFlightId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(276, 276, 276))
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchFlightLayout.setVerticalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearchFlight)
                .addGap(6, 6, 6))
        );

        tabPaneSearch.addTab("Flight", panelSearchFlight);

        tableLugSearchLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Location", "Color", "Wieght", "Size", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchLuggage.setColumnSelectionAllowed(true);
        tableLugSearchLuggage.setFocusable(false);
        tableLugSearchLuggage.getTableHeader().setReorderingAllowed(false);
        scrollPaneLuggageTable.setViewportView(tableLugSearchLuggage);
        tableLugSearchLuggage.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Not returned" }));
        comboBoxLuggageStatus.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageLocation.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchLuggage(evt);
            }
        });

        scrollPaneContent.setViewportView(textPaneLuggageDescription);

        comboBoxLuggageSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Size", "Small", "Medium", "Large" }));
        comboBoxLuggageSize.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageWeight.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageColor.setMaximumSize(new java.awt.Dimension(150, 150));

        labelLuggageDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLuggageDescription.setText("Destination: ");

        javax.swing.GroupLayout panelSearchLuggageLayout = new javax.swing.GroupLayout(panelSearchLuggage);
        panelSearchLuggage.setLayout(panelSearchLuggageLayout);
        panelSearchLuggageLayout.setHorizontalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneContent, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldLuggageLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(comboBoxLuggageStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxLuggageSize, 0, 359, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(textFieldLuggageColor, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLuggageDescription))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchLuggageLayout.setVerticalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLuggageLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxLuggageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLuggageWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLuggageColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelLuggageDescription)
                .addGap(0, 0, 0)
                .addComponent(scrollPaneContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("Luggage", panelSearchLuggage);

        tableLugSearchLuggage1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Firstname", "Email", "Cellphone", "Birthday", "Gender", "Nationality", "Adress", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchLuggage1.setColumnSelectionAllowed(true);
        tableLugSearchLuggage1.setFocusable(false);
        tableLugSearchLuggage1.getTableHeader().setReorderingAllowed(false);
        scrollPaneUserTable.setViewportView(tableLugSearchLuggage1);
        tableLugSearchLuggage1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxUserGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender", "Male", "Female", "Androgenous" }));
        comboBoxUserGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserId.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserIdActionPerformed(evt);
            }
        });

        buttonSearchUser.setText("Search");
        buttonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchUser(evt);
            }
        });

        textFieldUserFirstName.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldUserFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserFirstNameActionPerformed(evt);
            }
        });

        textFieldUserSurName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserCellphone.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldUserCellphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserCellphoneActionPerformed(evt);
            }
        });

        textFieldUserBirthday.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserNationality.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldUserNationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserNationalityActionPerformed(evt);
            }
        });

        textFieldUserAdress.setMaximumSize(new java.awt.Dimension(150, 150));
        textFieldUserAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserAdressActionPerformed(evt);
            }
        });

        textFieldUserPostcode.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchUserLayout = new javax.swing.GroupLayout(panelSearchUser);
        panelSearchUser.setLayout(panelSearchUserLayout);
        panelSearchUserLayout.setHorizontalGroup(
            panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserSurName, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(buttonSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldUserNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldUserAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUserPostcode, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(comboBoxUserGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchUserLayout.setVerticalGroup(
            panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserSurName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchUser)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("User", panelSearchUser);

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
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(tabPaneSearch)))
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
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
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
                        .addGap(24, 24, 24)
                        .addComponent(tabPaneSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
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

    private void buttonSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomer
        this.userNotAFK();
        this.searchCustomer();
    }//GEN-LAST:event_buttonSearchCustomer

    private void buttonSearchFlight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchFlight
        this.userNotAFK();
        this.searchFlight();
    }//GEN-LAST:event_buttonSearchFlight

    private void buttonUploadImagebuttonUploadsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadImagebuttonUploadsImageActionPerformed
        this.userNotAFK();
        luggageImage = selectLabelImage(labelUploadImage);
    }//GEN-LAST:event_buttonUploadImagebuttonUploadsImageActionPerformed

    private void buttonSearchLuggage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchLuggage
        this.userNotAFK();
        // this.searchLuggage();
    }//GEN-LAST:event_buttonSearchLuggage

    private void buttonSearchUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchUser
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchUser

    private void textFieldUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserIdActionPerformed

    private void textFieldUserFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserFirstNameActionPerformed

    private void textFieldUserCellphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserCellphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserCellphoneActionPerformed

    private void textFieldUserNationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserNationalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserNationalityActionPerformed

    private void textFieldUserAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserAdressActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSearchFlight;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonSearchUser;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxCustomerGender;
    private javax.swing.JComboBox comboBoxLuggageSize;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JComboBox comboBoxUpdateStatus;
    private javax.swing.JComboBox comboBoxUserGender;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelDestination;
    private javax.swing.JLabel labelDisplayDestination;
    private javax.swing.JLabel labelDisplayFlightnumber;
    private javax.swing.JLabel labelDisplayOrigin;
    private javax.swing.JLabel labelFlightnumber;
    private javax.swing.JLabel labelLuggageDescription;
    private javax.swing.JLabel labelLuggageDetails;
    private javax.swing.JLabel labelOrigin;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelUploadImage;
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchFlight;
    private javax.swing.JPanel panelSearchLuggage;
    private javax.swing.JPanel panelSearchUser;
    private javax.swing.JPanel panelUploadImage;
    private javax.swing.JScrollPane scrollPaneContent;
    private javax.swing.JScrollPane scrollPaneCustomerTable;
    private javax.swing.JScrollPane scrollPaneFlightTable;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JScrollPane scrollPaneUserTable;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JTabbedPane tabPaneSearch;
    private javax.swing.JTable tableLugSearchFlight;
    private javax.swing.JTable tableLugSearchLuggage;
    private javax.swing.JTable tableLugSearchLuggage1;
    private javax.swing.JTable tableSearchCustomer;
    private javax.swing.JFormattedTextField textFieldCustomerAdress;
    private javax.swing.JFormattedTextField textFieldCustomerBirthday;
    private javax.swing.JFormattedTextField textFieldCustomerCellphone;
    private javax.swing.JFormattedTextField textFieldCustomerEmail;
    private javax.swing.JFormattedTextField textFieldCustomerFirstname;
    private javax.swing.JFormattedTextField textFieldCustomerId;
    private javax.swing.JFormattedTextField textFieldCustomerPostcode;
    private javax.swing.JFormattedTextField textFieldCustomerSurname;
    private javax.swing.JFormattedTextField textFieldFlightArrival;
    private javax.swing.JFormattedTextField textFieldFlightDeparture;
    private javax.swing.JFormattedTextField textFieldFlightDestination;
    private javax.swing.JFormattedTextField textFieldFlightId;
    private javax.swing.JFormattedTextField textFieldFlightOrigin;
    private javax.swing.JFormattedTextField textFieldLuggageColor;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    private javax.swing.JFormattedTextField textFieldLuggageLocation;
    private javax.swing.JFormattedTextField textFieldLuggageWeight;
    private javax.swing.JFormattedTextField textFieldUpdateColor;
    private javax.swing.JFormattedTextField textFieldUpdateLocation;
    private javax.swing.JFormattedTextField textFieldUpdateOwnerID;
    private javax.swing.JFormattedTextField textFieldUpdateSize;
    private javax.swing.JFormattedTextField textFieldUpdateWeight;
    private javax.swing.JFormattedTextField textFieldUserAdress;
    private javax.swing.JFormattedTextField textFieldUserBirthday;
    private javax.swing.JFormattedTextField textFieldUserCellphone;
    private javax.swing.JFormattedTextField textFieldUserFirstName;
    private javax.swing.JFormattedTextField textFieldUserId;
    private javax.swing.JFormattedTextField textFieldUserName;
    private javax.swing.JFormattedTextField textFieldUserNationality;
    private javax.swing.JFormattedTextField textFieldUserPostcode;
    private javax.swing.JFormattedTextField textFieldUserSurName;
    private javax.swing.JTextPane textPaneLuggageDescription;
    private javax.swing.JTextPane textPaneUpdateContent;
    // End of variables declaration//GEN-END:variables
}
