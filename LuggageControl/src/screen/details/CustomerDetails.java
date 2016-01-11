package screen.details;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import org.jdesktop.swingx.prompt.PromptSupport;
import screen.base.BaseDetails;
import screen.base.SearchPanes;

/**
 *
 * @author Admin
 */
public class CustomerDetails extends BaseDetails {
    
    private int currentCustomerId = 0; 

    public CustomerDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Link customer with flightnumber", textFieldAddFlight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAddFlight);
        PromptSupport.setPrompt("Link customer with luggage", textFieldAddLuggage);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAddLuggage);
        PromptSupport.setPrompt("Cellphone", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Postalcode", textFieldPostcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPostcode);
        PromptSupport.setPrompt("Adress", textFieldAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAdress);
        
        searchPanel.removeSearchTab(SearchPanes.SEARCH_CUSTOMER);
        searchPanel.removeSearchTab(SearchPanes.SEARCH_USER);
    }
    
    @Override
    public void updatePanelInformation() {
        loadCustomer(currentCustomerId);
    }
    
    @Override
    public void updatePanelInformation(int customerId) {
        loadCustomer(customerId);
    }
    
    @Override
    public void clearPanelInformation() {
        this.clearCustomerData();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Customer data manipulation & screen update methods">
    /**
     * Clears all data so we can present new result sets to the user.
     */
    private void clearCustomerData() {
        comboBoxFlight.removeAllItems();
        comboBoxLuggage.removeAllItems();
        comboBoxGender.setSelectedIndex(0);
        labelNameDisplay.setText("");
        labelOwnerIdDisplay.setText("");
        labelSurnameDisplay.setText("");
        labelBirthdayDisplay.setText("");
        textFieldAddFlight.setText("");
        textFieldAddLuggage.setText("");
        textFieldAdress.setText("");
        textFieldPostcode.setText("");
        textFieldCellphone.setText("");
        textFieldEmail.setText("");
    }
    
    /**
     * Fill the flight combo box based on the current customer id
     * Retrieves the customer id from private variable
     */
    private void fillCustomerFlights() {
        this.fillCustomerFlights(currentCustomerId);
    }
    
    /**
     * Fill the flight combo box based on the current customer id
     * @param customerId specific customer id to use as reference
     */
    private void fillCustomerFlights(int customerId) {
        comboBoxFlight.removeAllItems();
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
     * Fill the luggage combo box based on the current customer id
     * Retrieves the customer id from private variable
     */
    private void fillCustomerLuggage() {
        this.fillCustomerFlights(currentCustomerId);
    }
    
    /**
     * Fill the luggage combo box based on the current customer id
     * @param customerId specific customer id to use as reference
     */
    private void fillCustomerLuggage(int customerId) {
        comboBoxLuggage.removeAllItems();
        try {
            String query = "SELECT `luggage`.luggage_id ";
            query += "FROM luggage INNER JOIN customer_luggage WHERE customer_id = ? AND `customer_luggage`.luggage_id = `luggage`.luggage_id;";
            ResultSet result = db.query(query, new String[]{String.valueOf(customerId)});
            while(result.next()) {
                comboBoxLuggage.addItem(result.getString("luggage_id"));
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
    private void loadCustomer(int customerID) {
        this.clearCustomerData();
        try {
            ResultSet result = db.query("SELECT * FROM customer WHERE customer_id = ?", new String[]{customerID + ""});
            // get the first row result
            while(result.next()) {
                labelNameDisplay.setText(result.getString("firstname"));
                labelSurnameDisplay.setText(result.getString("surname"));
                labelBirthdayDisplay.setText(result.getString("birthday"));
                labelOwnerIdDisplay.setText(result.getString("customer_id"));
                
                textFieldAdress.setText(result.getString("adress"));
                textFieldCellphone.setText(result.getString("cellphone"));
                textFieldEmail.setText(result.getString("email"));
                textFieldPostcode.setText(result.getString("postcode"));
                
                currentCustomerId = Integer.parseInt(result.getString("customer_id"));
                
                this.fillCustomerFlights(currentCustomerId);
                this.fillCustomerLuggage(currentCustomerId);
                this.setCustomerGender(result.getString("gender"));
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
    private void removeCustomerFlightLink(int flight_id) {
        try {
            db.queryManipulation("DELETE FROM customer_flight WHERE customer_id = ? AND flight_id = ?", 
            new String[]{currentCustomerId+"", flight_id+""}, new String[]{"Int", "Int"});
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * Remove link between customer and luggage
     * @param flight_id the id of the flight present in customer_flight
     */
    private void removeCustomerLuggageLink(int luggage_id) {
        try {
            db.queryManipulation("DELETE FROM customer_luggage WHERE customer_id = ? AND luggage_id = ?", 
            new String[]{currentCustomerId+"", luggage_id+""}, new String[]{"Int", "Int"});
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
        if(!textFieldAdress.getText().equals("") || !textFieldCellphone.getText().equals("") || 
            !textFieldEmail.getText().equals("") || !textFieldPostcode.getText().equals("")) {
            query += " SET ";
        }
        
        try {
            
            // check if our email is not taken yet
            if(db.queryOneResult("SELECT `email` FROM user WHERE email = ?", new String[]{textFieldEmail.getText()}).equals("")) {
                // email has 2 validation steps
            }
            else {
                labelStatus.setText("Email adress already taken!");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // check if our email is still a actual email adress
            if(!helpers.Filters.filteredEmail(textFieldEmail.getText()).equals("")) {
                query += " email = ?,";
                values.add(textFieldEmail.getText());
                types.add("String");
            }
            else {
                labelStatus.setText("Email adress is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // check if our cellphone number is still a actual cellphone number
            if(!helpers.Filters.filteredCellphone(textFieldCellphone.getText()).equals("")) {
                query += " cellphone = ?,";
                values.add(textFieldCellphone.getText());
                types.add("String");
            }
            else {
                labelStatus.setText("Cellphone contains illegal charaters, can only contain numbers");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // Validate gender
            if(comboBoxGender.getSelectedItem().toString().equals("Female") || 
                comboBoxGender.getSelectedItem().toString().equals("Male") || 
                comboBoxGender.getSelectedItem().toString().equals("Androgenous")
            ) {
                query += " gender = ?,";
                values.add(comboBoxGender.getSelectedItem().toString());
                types.add("String");
            }
            else {
                labelStatus.setText("You used memory manipulation to edit this combobox, close but no cigar.");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate postcode placeholder
            if(!textFieldPostcode.getText().equals("")) {
                query += " postcode = ?,";
                values.add(textFieldPostcode.getText());
                types.add("String");
            }
            else {
                labelStatus.setText("Postcode not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }
            
            // validate adress placeholder
            if(!textFieldAdress.getText().equals("")) {
                query += " adress = ?,";
                values.add(textFieldAdress.getText());
                types.add("String");
            }
            else {
                labelStatus.setText("Adress not valid");
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
                        values.add(helpers.Filters.filteredInt(textFieldAddFlight.getText(), 1, Integer.MAX_VALUE));
                        // int + "" is my favorite java conversion hack, its so dirty lol
                        values.add(helpers.Filters.filteredInt(currentCustomerId + "", 1, Integer.MAX_VALUE));
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
                db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
                values.clear();
                types.clear();
            }
            
            // check if our customer is not already linked to this flight
            // this is the uglieest if statement I ever made.
            if(!textFieldAddLuggage.getText().equals("")) {
                if(db.queryOneResult("SELECT `luggage_id` FROM customer_luggage WHERE luggage_id = ? AND customer_id = ?", 
                        new String[]{
                            textFieldAddLuggage.getText(),
                            currentCustomerId + ""
                        }
                    ).equals("")
                ) {
                    // test if our flight id exists
                    if(!db.queryOneResult("SELECT `luggage_id` FROM luggage WHERE luggage_id = ?", 
                        new String[]{
                            textFieldAddLuggage.getText()
                        }
                    ).equals("")) {
                        query = "INSERT INTO customer_luggage (luggage_id, customer_id) VALUES (?, ?)";
                        values.add(helpers.Filters.filteredInt(textFieldAddLuggage.getText(), 1, Integer.MAX_VALUE));
                        // int + "" is my favorite java conversion hack, its so dirty lol
                        values.add(helpers.Filters.filteredInt(currentCustomerId + "", 1, Integer.MAX_VALUE));
                        types.add("Int");
                        types.add("Int");
                    }
                    else {
                        labelStatus.setText("luggage does not exist");
                        this.resetLabel(5000, labelStatus);
                        return;
                    }
                }
                else {
                    labelStatus.setText("Customer has already been linked to this luggage");
                    this.resetLabel(5000, labelStatus);
                    return;
                }
                db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
                values.clear();
                types.clear();
            }             
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
        // if all went well wse tell the user
        labelStatus.setText("");
        
        loadCustomer(currentCustomerId);
    }
    
    /**
     * Select the gender from the comboBox
     * @param gender the gender string from the database
     */
    private void setCustomerGender(String gender) {
        if(gender.equals("Female")) {
            comboBoxGender.setSelectedIndex(0);
        }
        else if(gender.equals("Male")) {
            comboBoxGender.setSelectedIndex(1);
        }
        else if(gender.equals("Androgenous")) {
            comboBoxGender.setSelectedIndex(2);
        }
        else {
            new ErrorJDialog(this.luggageControl, true, "Error: Gender does not exist", (new Throwable()).getStackTrace());
        }
    }
    //</editor-fold>
    
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
        labelOwnerIdDisplay = new javax.swing.JLabel();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldAddFlight = new javax.swing.JFormattedTextField();
        comboBoxFlight = new javax.swing.JComboBox();
        buttonUpdateCustomer = new javax.swing.JButton();
        buttonCancelChanges = new javax.swing.JButton();
        separatorScreenDefider = new javax.swing.JSeparator();
        labelHeaderSearch = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        comboBoxGender = new javax.swing.JComboBox();
        labeBirthday = new javax.swing.JLabel();
        textFieldPostcode = new javax.swing.JFormattedTextField();
        textFieldAdress = new javax.swing.JFormattedTextField();
        labelOwnerId = new javax.swing.JLabel();
        labelBirthdayDisplay = new javax.swing.JLabel();
        comboBoxLuggage = new javax.swing.JComboBox();
        buttonRemoveLuggage = new javax.swing.JButton();
        textFieldAddLuggage = new javax.swing.JFormattedTextField();
        labelOwnerId1 = new javax.swing.JLabel();
        labelOwnerId2 = new javax.swing.JLabel();
        searchPanel = new screen.base.SearchPanes(luggageControl, db);

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

        labelOwnerIdDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerIdDisplay.setText(" XXXXXXXXX");

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldAddFlight.setMaximumSize(new java.awt.Dimension(150, 150));

        comboBoxFlight.setToolTipText("Links to flights");
        comboBoxFlight.setMaximumSize(new java.awt.Dimension(150, 150));

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

        labelHeaderSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeaderSearch.setText("Search");

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

        comboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Female", "Male", "Androgenous" }));
        comboBoxGender.setToolTipText("Gender");
        comboBoxGender.setMaximumSize(new java.awt.Dimension(150, 150));

        labeBirthday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labeBirthday.setText("Birthday:");

        textFieldPostcode.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldAdress.setMaximumSize(new java.awt.Dimension(150, 150));

        labelOwnerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerId.setText("Customer id:");

        labelBirthdayDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelBirthdayDisplay.setText(" XXXXXXXXX");

        comboBoxLuggage.setToolTipText("Links to flights");
        comboBoxLuggage.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonRemoveLuggage.setText("Remove");
        buttonRemoveLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveLuggage(evt);
            }
        });

        textFieldAddLuggage.setMaximumSize(new java.awt.Dimension(150, 150));

        labelOwnerId1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerId1.setText("Flights:");

        labelOwnerId2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOwnerId2.setText("Luggage:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(labeBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                                            .addComponent(labelOwnerId))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelOwnerIdDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelBirthdayDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelSurnameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelOwnerId1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textFieldAddFlight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxFlight, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textFieldAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldPostcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(comboBoxLuggage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textFieldAddLuggage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRemoveLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonCancelChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelOwnerId2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(separatorScreenDefider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeaderSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorScreenDefider)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHeaderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonHelp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName)
                            .addComponent(labelNameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSurname)
                            .addComponent(labelSurnameDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labeBirthday)
                            .addComponent(labelBirthdayDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOwnerId, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOwnerIdDisplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelOwnerId1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(comboBoxFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelOwnerId2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRemoveLuggage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldAddFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldAddLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 13, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCancelChanges)
                            .addComponent(buttonUpdateCustomer)
                            .addComponent(buttonBack))
                        .addGap(38, 38, 38))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backToSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToSearchCustomer
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.SEARCH_CUSTOMER);
    }//GEN-LAST:event_backToSearchCustomer

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    /**
     * Cancel updates all fields with the values that where in the database
     * @param evt button event not used
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        loadCustomer(currentCustomerId);
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * Update query to update the current user information and add additional information.
     * @param evt event details, not used
     */
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
        updateCustomer();
        textFieldAddFlight.setText("");
        textFieldAddLuggage.setText("");
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonRemoveLuggage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveLuggage
        this.userNotAFK();
        try {
            removeCustomerLuggageLink(Integer.parseInt(comboBoxLuggage.getSelectedItem().toString()));
        }
        catch(NullPointerException e) {
            labelStatus.setText("Cannot delete unexisting reference");
            this.resetLabel(5000, labelStatus);
            return;
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        loadCustomer(currentCustomerId);
    }//GEN-LAST:event_buttonRemoveLuggage


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancelChanges;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonRemoveLuggage;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JComboBox comboBoxFlight;
    private javax.swing.JComboBox comboBoxGender;
    private javax.swing.JComboBox comboBoxLuggage;
    private javax.swing.JLabel labeBirthday;
    private javax.swing.JLabel labelBirthdayDisplay;
    private javax.swing.JLabel labelHeaderLeftSide;
    private javax.swing.JLabel labelHeaderSearch;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNameDisplay;
    private javax.swing.JLabel labelOwnerId;
    private javax.swing.JLabel labelOwnerId1;
    private javax.swing.JLabel labelOwnerId2;
    private javax.swing.JLabel labelOwnerIdDisplay;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelSurname;
    private javax.swing.JLabel labelSurnameDisplay;
    private screen.base.SearchPanes searchPanel;
    private javax.swing.JSeparator separatorScreenDefider;
    private javax.swing.JFormattedTextField textFieldAddFlight;
    private javax.swing.JFormattedTextField textFieldAddLuggage;
    private javax.swing.JFormattedTextField textFieldAdress;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldPostcode;
    // End of variables declaration//GEN-END:variables
}
