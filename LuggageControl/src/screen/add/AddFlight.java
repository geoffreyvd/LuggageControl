package screen.add;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;

import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * This JPanel adds a flight into the database
 *
 * @author Konrad
 */
public class AddFlight extends SwitchingJPanel {
    
    private DatabaseMan db = new DatabaseMan();
    private SecurityMan sc;

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public AddFlight(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageID);
        PromptSupport.setPrompt("Owner ID", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Origin", textFieldOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOrigin);
        PromptSupport.setPrompt("Destination", textFieldDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDestination);
        PromptSupport.setPrompt("Departure time (YYYY-MM-DD HH:MM:SS)", textFieldDepartureTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDepartureTime);
        PromptSupport.setPrompt("Arrival time (YYYY-MM-DD HH:MM:SS)", textFieldArrivalTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldArrivalTime);
        PromptSupport.setPrompt("First Name", textFieldFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFirstName);
        PromptSupport.setPrompt("Sur Name", textFieldSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurName);
        PromptSupport.setPrompt("Cellphone number", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageId);
        PromptSupport.setPrompt("Luggage Location", textFieldLugLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLugLocation);
    }
    public boolean checkInput(){
        // check if customer id exists
        if (!(textFieldOwnerID.getText().equals("")) 
                && db.queryOneResult("SELECT `customer_id` FROM customer WHERE customer_id = ?", new String[]{textFieldOwnerID.getText()}).equals("")) {
            labelStatus.setText("Customer doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if luggage id exists
        if (!(textFieldLuggageID.getText().equals("")) && 
                db.queryOneResult("SELECT `luggage_id` FROM luggage WHERE luggage_id = ?", new String[]{textFieldLuggageID.getText()}).equals("")) {
            labelStatus.setText("Luggage doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if flightnumber isnt taken
        if (!(textFieldFlightnumber.getText().equals("")) 
                && !db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", new String[]{textFieldFlightnumber.getText()}).equals("")) {
            labelStatus.setText("Flightnumber does already exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if departure time is in right datetime format
        if(helpers.Filters.filteredDateTime(textFieldDepartureTime.getText()).equals("")){
            labelStatus.setText("Not a correct entry for Departure time!");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if arrival time is in right datetime format
        if(helpers.Filters.filteredDateTime(textFieldArrivalTime.getText()).equals("")){
            labelStatus.setText("Not a correct entry for arrival time!");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate origin placeholder
        if (textFieldOrigin.getText().equals("")) {
            labelStatus.setText("Origin is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate origin placeholder
        if (textFieldDestination.getText().equals("")) {
            labelStatus.setText("Destination is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        return true;  
    }
    private void addFlight(){
        
        if (checkInput()) {

            String queryInsertFlight = "INSERT INTO `luggagecontroldata`.`flight`"
                    + "(`flight_id`, `origin`, `destination`, `departure`, `arrival`) "
                    + "VALUES(?,?,?,?,?)";
            String queryInsertLuggage = "INSERT INTO `luggagecontroldata`.`luggage_flight`"
                    + "(`flight_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String queryInsertCustomer = "INSERT INTO `luggagecontroldata`.`customer_flight`"
                    + "(`customer_id`, `flight_id`)  "
                    + "VALUES(?,?)";

            String[] values = new String[5];
            String[] types = new String[5];
            
            String[] values2 = new String[2];
            String[] types2 = new String[2];

            String[] values3 = new String[2];
            String[] types3 = new String[2];

            values[0] = textFieldFlightnumber.getText();
            values[1] = textFieldOrigin.getText();
            values[2] = textFieldDestination.getText();
            values[3] = textFieldDepartureTime.getText();
            values[4] = textFieldArrivalTime.getText();
            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "String";
            types[4] = "String";
            
            values2[0] = textFieldFlightnumber.getText();
            values2[1] = textFieldLuggageID.getText();
            types2[0] = "String";
            types2[1] = "String";
            
            values3[0] = textFieldOwnerID.getText();
            values3[1] = textFieldFlightnumber.getText();
            types3[0] = "String";
            types3[1] = "String";

            try {
                db.queryManipulation(queryInsertFlight, values, types);
                if(!("".equals(textFieldLuggageID.getText()))){
                db.queryManipulation(queryInsertLuggage, values2, types2);
                } 
                if(!("".equals(textFieldOwnerID.getText()))){
                db.queryManipulation(queryInsertCustomer, values3, types3);
                }
                
            } catch (Exception e) {
            }
            this.clearFields();
            this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
        }
    }
    @Override
    public void updatePanelInformation() {
        System.err.println("Add panel not capabable of prefilling data");
    }
    
    @Override
    public void updatePanelInformation(int customerId) {
        this.updatePanelInformation();
    }
    
    @Override
    public void clearPanelInformation() {
        this.clearFields();
    }
    
    private void clearFields(){
        textFieldFlightnumber.setText("");
        textFieldDestination.setText("");
        textFieldOrigin.setText("");
        textFieldDepartureTime.setText("");
        textFieldArrivalTime.setText("");
        labelStatus.setText("");
        textFieldLuggageID.setText("");
        textFieldOwnerID.setText("");
    }
    private void searchCustomer() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT * FROM customer ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFirstName.getText().equals("") || !textFieldSurName.getText().equals("") ||
            !textFieldCellphone.getText().equals("") || !textFieldEmail.getText().equals("")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldFirstName.getText().equals("")) {
                query += "OR firstname = ? ";
                values.add(helpers.Filters.filteredString(textFieldFirstName.getText()));
            }

            if (!textFieldSurName.getText().equals("")) {
                query += "OR surname = ? ";
                values.add(helpers.Filters.filteredString(textFieldSurName.getText()));
            }
            
            if (!textFieldCellphone.getText().equals("")) {
                query += "OR cellphone = ? ";
                values.add(helpers.Filters.filteredString(textFieldCellphone.getText()));
            }

            if (!textFieldEmail.getText().equals("")) {
                query += "OR email = ? ";
                values.add(helpers.Filters.filteredString(textFieldEmail.getText()));
            }

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableSearchCustomer.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("customer_id"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    result.getString("email"),
                    result.getString("cellphone"),
                    result.getString("birthday"),
                    result.getString("gender"),
                    result.getString("adress"),
                    result.getString("postcode")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableSearchCustomer.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    private void searchLuggage() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT luggage_id, location, color, weight, size, description, status FROM luggage ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldLugLocation.getText().equals("") || !textFieldLuggageId.getText().equals("") ||
            !comboBoxLugStatus.getSelectedItem().toString().equals("Status")) {
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
            
            if (!comboBoxLugStatus.getSelectedItem().toString().equals("Status")) {
                query += "OR status = ? ";
                values.add(helpers.Filters.filteredString(comboBoxLugStatus.getSelectedItem().toString()));
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelAddCustomer = new javax.swing.JLabel();
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        textFieldOrigin = new javax.swing.JFormattedTextField();
        textFieldDestination = new javax.swing.JFormattedTextField();
        textFieldDepartureTime = new javax.swing.JFormattedTextField();
        textFieldArrivalTime = new javax.swing.JFormattedTextField();
        buttonHelp = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        butonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelQuickSearch = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        textFieldLuggageID = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        tabPaneSearch = new javax.swing.JTabbedPane();
        panelSearchCustomer = new javax.swing.JPanel();
        buttonSearchCustomer = new javax.swing.JButton();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();
        textFieldFirstName = new javax.swing.JFormattedTextField();
        textFieldSurName = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        panelSearchLuggage = new javax.swing.JPanel();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage = new javax.swing.JTable();
        comboBoxLugStatus = new javax.swing.JComboBox();
        textFieldLugLocation = new javax.swing.JFormattedTextField();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        buttonSearchLuggage = new javax.swing.JButton();
        scrollPaneContent = new javax.swing.JScrollPane();
        textPaneContent = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        labelAddCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddCustomer.setText("Add flight");

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        butonCancel.setText("Cancel");
        butonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelbuttonCancelActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        labelQuickSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelQuickSearch.setText("Quick search");

        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerActionPerformed(evt);
            }
        });

        tableSearchCustomer.setAutoCreateRowSorter(true);
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
        tableSearchCustomer.getTableHeader().setReorderingAllowed(false);
        scrollPaneTable.setViewportView(tableSearchCustomer);

        textFieldFirstName.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldSurName.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(6, 20));

        javax.swing.GroupLayout panelSearchCustomerLayout = new javax.swing.GroupLayout(panelSearchCustomer);
        panelSearchCustomer.setLayout(panelSearchCustomerLayout);
        panelSearchCustomerLayout.setHorizontalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                                .addComponent(textFieldSurName, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(buttonSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchCustomerLayout.setVerticalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldSurName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchCustomer)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("Customer", panelSearchCustomer);

        tableLugSearchLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Location", "Color", "Weight", "Size", "Status"
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

        jLabel1.setText("Content:");

        javax.swing.GroupLayout panelSearchLuggageLayout = new javax.swing.GroupLayout(panelSearchLuggage);
        panelSearchLuggage.setLayout(panelSearchLuggageLayout);
        panelSearchLuggageLayout.setHorizontalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneContent, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(comboBoxLugStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLugLocation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneContent, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addContainerGap())
        );

        tabPaneSearch.addTab("Luggage", panelSearchLuggage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldOrigin, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldDepartureTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldArrivalTime)
                            .addComponent(textFieldLuggageID)
                            .addComponent(textFieldDestination)))
                    .addComponent(textFieldFlightnumber)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelAddCustomer)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelQuickSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabPaneSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAddCustomer)
                    .addComponent(buttonHelp)
                    .addComponent(labelQuickSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldArrivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirm)
                            .addComponent(butonCancel)
                            .addComponent(buttonBack)))
                    .addComponent(tabPaneSearch))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP_ADDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        this.addFlight();
        this.userNotAFK();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        this.clearFields();
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearFields();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
        this.userNotAFK();
        this.searchCustomer();
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonSearchLuggage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchLuggage
        this.userNotAFK();
        this.searchLuggage();

    }//GEN-LAST:event_buttonSearchLuggage


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JComboBox comboBoxLugStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddCustomer;
    private javax.swing.JLabel labelQuickSearch;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchLuggage;
    private javax.swing.JScrollPane scrollPaneContent;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTabbedPane tabPaneSearch;
    private javax.swing.JTable tableLugSearchLuggage;
    private javax.swing.JTable tableSearchCustomer;
    private javax.swing.JFormattedTextField textFieldArrivalTime;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldDepartureTime;
    private javax.swing.JFormattedTextField textFieldDestination;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstName;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLugLocation;
    private javax.swing.JFormattedTextField textFieldLuggageID;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    private javax.swing.JFormattedTextField textFieldOrigin;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    private javax.swing.JFormattedTextField textFieldSurName;
    private javax.swing.JTextPane textPaneContent;
    // End of variables declaration//GEN-END:variables
}
