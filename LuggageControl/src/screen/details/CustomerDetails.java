package screen.details;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public CustomerDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Link flight with flightnumber", textFieldAddFlight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAddFlight);
        PromptSupport.setPrompt("Link flight with luggage", textFieldAddLuggage);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAddLuggage);
        PromptSupport.setPrompt("Cellphone", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Postalcode", textFieldPostcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPostcode);
        PromptSupport.setPrompt("Adress", textFieldAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAdress);
        
        
        PromptSupport.setPrompt("Location", textFieldLugLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLugLocation);
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageId);

        PromptSupport.setPrompt("Flight ID", textFieldFlightId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightId);
        PromptSupport.setPrompt("Origin", textFieldFliOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFliOrigin);
        PromptSupport.setPrompt("Destination", textFieldFliDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFliDestination);
        PromptSupport.setPrompt("Departure", textFieldFliDeparture);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFliDeparture);
        PromptSupport.setPrompt("Arrival", textFieldFliArrival);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFliArrival);
    }
    
//    @Override
//    protected void updatePanel() {
//        loadCustomer(currentCustomerId);
//    }
    
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
        textFieldLugLocation.setText("");
        textFieldLuggageId.setText("");
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
        DatabaseMan db = new DatabaseMan();
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
    public void loadCustomer(int customerID) {
        this.clearCustomerData();
        try {
            DatabaseMan db = new DatabaseMan();
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
        
        // if all went well we tell the user
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
     * Fill the luggage table
     */
    private void searchLuggage() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT luggage_id, location, color, weight, size, content, status FROM luggage ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldLugLocation.getText().equals("") || !textFieldLuggageId.getText().equals("") ||
            !textPaneContent.getText().equals("")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldLuggageId.getText().equals("")) {
                query += "OR luggage_id = ? ";
                values.add(helpers.Filters.filteredString(textFieldLuggageId.getText()));
            }

            if (!textFieldLugLocation.getText().equals("")) {
                query += "OR location = ? ";
                values.add(helpers.Filters.filteredString(textFieldLugLocation.getText()));
            }

            if (!textPaneContent.getText().equals("")) {
                query += "OR content LIKE ? ";
                values.add("%" + helpers.Filters.filteredString(textPaneContent.getText()) + "%");
            }   

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableLugSearchLuggage.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("luggage_id"),
                    result.getString("location"),
                    result.getString("color"),
                    result.getString("weight"),
                    result.getString("size"),
                    result.getString("content"),
                    result.getString("status")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableLugSearchLuggage.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * fill the flight table
     */
    private void searchFlight() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT flight_id, origin, destination, departure, arrival FROM flight ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFlightId.getText().equals("") || !textFieldFliOrigin.getText().equals("") ||
            !textFieldFliDestination.getText().equals("") || !textFieldFliDeparture.getText().equals("") || 
            !textFieldFliArrival.getText().equals("")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldFlightId.getText().equals("")) {
                query += "OR flight_id = ? ";
                values.add(helpers.Filters.filteredString(textFieldFlightId.getText()));
            }

            if (!textFieldFliOrigin.getText().equals("")) {
                query += "OR origin = ? ";
                values.add(helpers.Filters.filteredString(textFieldFliOrigin.getText()));
            }

            if (!textFieldFliDestination.getText().equals("")) {
                query += "OR destination = ? ";
                values.add(helpers.Filters.filteredString(textFieldFliDestination.getText()));
            }
            if (!textFieldFliDeparture.getText().equals("")) {
                query += "OR departure = ? ";
                values.add(helpers.Filters.filteredString(textFieldFliDeparture.getText()));
            }
            if (!textFieldFliArrival.getText().equals("")) {
                query += "OR arrival = ? ";
                values.add(helpers.Filters.filteredString(textFieldFliArrival.getText()));
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
        buttonRemoveFlightNumber = new javax.swing.JButton();
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
        tabPaneSearch = new javax.swing.JTabbedPane();
        panelSearchLuggage = new javax.swing.JPanel();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage = new javax.swing.JTable();
        comboBoxLugStatus = new javax.swing.JComboBox();
        textFieldLugLocation = new javax.swing.JFormattedTextField();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        buttonSearchLuggage = new javax.swing.JButton();
        scrollPaneContent = new javax.swing.JScrollPane();
        textPaneContent = new javax.swing.JTextPane();
        panelSearchFlight = new javax.swing.JPanel();
        scrollPaneFlightTable = new javax.swing.JScrollPane();
        tableLugSearchFlight = new javax.swing.JTable();
        textFieldFliOrigin = new javax.swing.JFormattedTextField();
        textFieldFlightId = new javax.swing.JFormattedTextField();
        buttonSearchFlight = new javax.swing.JButton();
        textFieldFliDestination = new javax.swing.JFormattedTextField();
        textFieldFliArrival = new javax.swing.JFormattedTextField();
        textFieldFliDeparture = new javax.swing.JFormattedTextField();
        comboBoxLuggage = new javax.swing.JComboBox();
        buttonRemoveLuggage = new javax.swing.JButton();
        textFieldAddLuggage = new javax.swing.JFormattedTextField();
        labelOwnerId1 = new javax.swing.JLabel();
        labelOwnerId2 = new javax.swing.JLabel();

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

        buttonRemoveFlightNumber.setText("Remove");
        buttonRemoveFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveFlight(evt);
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

        tableLugSearchLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Content", "Color", "Size", "Status", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchLuggage.setFocusable(false);
        tableLugSearchLuggage.getTableHeader().setReorderingAllowed(false);
        scrollPaneLuggageTable.setViewportView(tableLugSearchLuggage);
        tableLugSearchLuggage.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxLugStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Not returned" }));
        comboBoxLugStatus.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLugLocation.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchLuggage(evt);
            }
        });

        scrollPaneContent.setViewportView(textPaneContent);

        javax.swing.GroupLayout panelSearchLuggageLayout = new javax.swing.GroupLayout(panelSearchLuggage);
        panelSearchLuggage.setLayout(panelSearchLuggageLayout);
        panelSearchLuggageLayout.setHorizontalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneContent)
                    .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(comboBoxLugStatus, 0, 422, Short.MAX_VALUE)
                    .addComponent(textFieldLugLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchLuggageLayout.setVerticalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldLuggageId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldLugLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxLugStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneContent, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("Luggage", panelSearchLuggage);

        tableLugSearchFlight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight ID", "Origin", "Destination", "Departure", "Arrival"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tableLugSearchFlight.setFocusable(false);
        tableLugSearchFlight.setRowSelectionAllowed(false);
        tableLugSearchFlight.getTableHeader().setReorderingAllowed(false);
        scrollPaneFlightTable.setViewportView(tableLugSearchFlight);
        tableLugSearchFlight.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        textFieldFliOrigin.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchFlight.setText("Search");
        buttonSearchFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchFlight(evt);
            }
        });

        textFieldFliDestination.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFliArrival.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFliDeparture.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchFlightLayout = new javax.swing.GroupLayout(panelSearchFlight);
        panelSearchFlight.setLayout(panelSearchFlightLayout);
        panelSearchFlightLayout.setHorizontalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textFieldFliOrigin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFlightId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFliDestination, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addComponent(buttonSearchFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                        .addComponent(textFieldFliDeparture, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFliArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchFlightLayout.setVerticalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFliOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFliDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFliArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFliDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearchFlight)
                .addGap(6, 6, 6))
        );

        tabPaneSearch.addTab("Flight", panelSearchFlight);

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
                                .addComponent(labelHeaderLeftSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                            .addComponent(labelSurnameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelOwnerId1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonRemoveFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonRemoveLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabPaneSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
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
                        .addGap(26, 26, 26)
                        .addComponent(tabPaneSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
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
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRemoveFlightNumber))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCancelChanges)
                            .addComponent(buttonUpdateCustomer)
                            .addComponent(buttonBack))
                        .addGap(38, 38, 38))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backToSearchCustomer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToSearchCustomer
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.SEARCH_CUSTOMER);
    }//GEN-LAST:event_backToSearchCustomer

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonSearchLuggage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchLuggage
        this.userNotAFK();
        this.searchLuggage();
    }//GEN-LAST:event_buttonSearchLuggage

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

    private void buttonRemoveFlight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveFlight
        this.userNotAFK();
        try {
            removeCustomerFlightLink(Integer.parseInt(comboBoxFlight.getSelectedItem().toString()));
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
    }//GEN-LAST:event_buttonRemoveFlight

    private void buttonSearchFlight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchFlight
        this.userNotAFK();
        this.searchFlight();
    }//GEN-LAST:event_buttonSearchFlight

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
    private javax.swing.JButton buttonRemoveFlightNumber;
    private javax.swing.JButton buttonRemoveLuggage;
    private javax.swing.JButton buttonSearchFlight;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JComboBox comboBoxFlight;
    private javax.swing.JComboBox comboBoxGender;
    private javax.swing.JComboBox comboBoxLugStatus;
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
    private javax.swing.JPanel panelSearchFlight;
    private javax.swing.JPanel panelSearchLuggage;
    private javax.swing.JScrollPane scrollPaneContent;
    private javax.swing.JScrollPane scrollPaneFlightTable;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JSeparator separatorScreenDefider;
    private javax.swing.JTabbedPane tabPaneSearch;
    private javax.swing.JTable tableLugSearchFlight;
    private javax.swing.JTable tableLugSearchLuggage;
    private javax.swing.JFormattedTextField textFieldAddFlight;
    private javax.swing.JFormattedTextField textFieldAddLuggage;
    private javax.swing.JFormattedTextField textFieldAdress;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFliArrival;
    private javax.swing.JFormattedTextField textFieldFliDeparture;
    private javax.swing.JFormattedTextField textFieldFliDestination;
    private javax.swing.JFormattedTextField textFieldFliOrigin;
    private javax.swing.JFormattedTextField textFieldFlightId;
    private javax.swing.JFormattedTextField textFieldLugLocation;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    private javax.swing.JFormattedTextField textFieldPostcode;
    private javax.swing.JTextPane textPaneContent;
    // End of variables declaration//GEN-END:variables
}
