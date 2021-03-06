/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.add;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Konrad
 */
public class AddLuggage extends SwitchingJPanel {

    private String imageBase64 = "";
    private DatabaseMan db = new DatabaseMan();

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     *
     * @param luggageControl
     */
    public AddLuggage(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flight number", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Location", textFieldLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLocation);
        PromptSupport.setPrompt("OwnerID (optional)", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Weight (gram)", textFieldWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldWeight);
        PromptSupport.setPrompt("Color", textFieldColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldColor);
        PromptSupport.setPrompt("Flight number", textFieldFlightId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightId);
        PromptSupport.setPrompt("Flight origin", textFieldFlightOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightOrigin);
        PromptSupport.setPrompt("Flight destination", textFieldFlightDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightDestination);
        PromptSupport.setPrompt("Flight arrival", textFieldFlightArrival);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightArrival);
        PromptSupport.setPrompt("Flight departure", textFieldFlightDeparture);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightDeparture);
        PromptSupport.setPrompt("First name", textFieldFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFirstName);
        PromptSupport.setPrompt("Sur name", textFieldSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurName);
        PromptSupport.setPrompt("Cellphone number", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
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

    /**
     *
     */
    public void clearFields() {
        textFieldFlightnumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldWeight.setText("");
        textFieldColor.setText("");
        comboBoxLuggageSize.setSelectedIndex(0);
        textPaneContent.setText("");
        labelStatus.setText("");
        pic.setIcon(null);
    }

    /**
     * check input
     *
     * @return
     */
    public boolean checkInput() {

        // validate location placeholder
        if (textFieldLocation.getText().equals("")) {
            labelStatus.setText("Location is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if flightnumber exists
        if (!(textFieldFlightnumber.getText().equals(""))
                && db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", new String[]{textFieldFlightnumber.getText()}).equals("")) {
            labelStatus.setText("Flightnumber doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if customer exists
        if (!(textFieldOwnerID.getText().equals(""))
                && db.queryOneResult("SELECT `customer_id` FROM customer WHERE customer_id = ?", new String[]{textFieldOwnerID.getText()}).equals("")) {
            labelStatus.setText("Customer doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // Validate luggage status
        if (!(comboBoxLuggageStatus.getSelectedItem().toString().equals("Lost")
                || comboBoxLuggageStatus.getSelectedItem().toString().equals("Found")
                || comboBoxLuggageStatus.getSelectedItem().toString().equals("Returned"))) {
            labelStatus.setText("Status is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // validate color placeholder
        if (textFieldColor.getText().equals("")) {
            labelStatus.setText("Color is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        if (!(comboBoxLuggageSize.getSelectedItem().toString().equals("Small")
                || comboBoxLuggageSize.getSelectedItem().toString().equals("Medium")
                || comboBoxLuggageSize.getSelectedItem().toString().equals("Large"))) {
            labelStatus.setText("Size is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if luggage weight is a number
        if (helpers.Filters.filteredCellphone(textFieldWeight.getText()).equals("")) {
            labelStatus.setText("Weight is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if content is filled in
        if (textPaneContent.getText().equals("")) {
            labelStatus.setText("Description is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if an image is selected
        if (imageBase64.equals("") && comboBoxLuggageStatus.getSelectedItem().toString().equals("Found")) {
            labelStatus.setText("Image is not selected");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        return true;
    }

    /**
     * add luggage
     */
    public void addLuggage() {
        if (checkInput()) {
            String queryInsertLuggage = "INSERT INTO `luggagecontroldata`.`luggage`"
                    + "(`location`, `color`, `weight`, `size`, `status`, `description`, `image`)  "
                    + "VALUES(?,?,?,?,?,?,?)";
            String queryInsertFlight = "INSERT INTO `luggagecontroldata`.`luggage_flight`"
                    + "(`flight_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String queryInsertCustomer = "INSERT INTO `luggagecontroldata`.`customer_luggage`"
                    + "(`customer_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String querySearchLuggage = "SELECT luggage_id FROM luggage WHERE "
                    + "location = ? AND color = ? AND weight = ? AND size = ? "
                    + "AND status = ? AND description = ? AND image = ?;";
            
            String querySearchLuggageSimilar = "SELECT * FROM luggage WHERE status = ? "
                    + "AND (location LIKE ? OR color LIKE ? OR weight LIKE ? OR size = ? "
                    + "OR description LIKE ?);";

            String[] values = new String[7];
            String[] types = new String[7];

            String[] values2 = new String[2];
            String[] types2 = new String[2];

            String[] values3 = new String[2];
            String[] types3 = new String[2];

            values[0] = textFieldLocation.getText();
            values[1] = textFieldColor.getText();
            values[2] = textFieldWeight.getText();
            values[3] = comboBoxLuggageSize.getSelectedItem().toString();
            values[4] = comboBoxLuggageStatus.getSelectedItem().toString();
            values[5] = textPaneContent.getText();
            values[6] = imageBase64;

            types[0] = "String";
            types[1] = "String";
            types[2] = "Int";
            types[3] = "String";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";

            types2[0] = "Int";
            types2[1] = "Int";

            types3[0] = "Int";
            types3[1] = "Int";

            try {
                db.queryManipulation(queryInsertLuggage, values, types);
                if (!textFieldFlightnumber.getText().equals("")) {
                    values2[0] = textFieldFlightnumber.getText();
                    values2[1] = db.queryOneResult(querySearchLuggage, values);
                    db.queryManipulation(queryInsertFlight, values2, types2);
                }
                if (!textFieldOwnerID.getText().equals("")) {
                    values3[0] = textFieldOwnerID.getText();
                    values3[1] = db.queryOneResult(querySearchLuggage, values);
                    db.queryManipulation(queryInsertCustomer, values3, types3);
                }
            } catch (SQLException e) {
                
            }
            
            String valuesPrefill[] = new String[6];
            
            if(values[4].equals("Lost")) {
                valuesPrefill[0] = "Found";
            }
            else {
                valuesPrefill[0] = "Lost";
            }
            
            valuesPrefill[1] = textFieldLocation.getText();
            valuesPrefill[2] = textFieldColor.getText();
            valuesPrefill[3] = textFieldWeight.getText();
            valuesPrefill[4] = comboBoxLuggageSize.getSelectedItem().toString();
            valuesPrefill[5] = textPaneContent.getText();
            
            this.luggageControl.switchJPanel(this.luggageControl.SEARCH_LUGGAGE);
            ResultSet result = db.query(querySearchLuggageSimilar, valuesPrefill);
            this.luggageControl.prefillSearchLuggage(result);
            
            this.clearFields();
        }
    }

    private void searchCustomer() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT * FROM customer ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFirstName.getText().equals("") || !textFieldSurName.getText().equals("")
                || !textFieldCellphone.getText().equals("") || !textFieldEmail.getText().equals("")) {
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

    private void searchFlight() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT flight_id, origin, destination, departure, arrival FROM flight ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFlightId.getText().equals("") || !textFieldFlightOrigin.getText().equals("")
                || !textFieldFlightDestination.getText().equals("") || !textFieldFlightDeparture.getText().equals("")
                || !textFieldFlightArrival.getText().equals("")) {
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

        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        labelAddLuggage = new javax.swing.JLabel();
        textFieldLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldColor = new javax.swing.JFormattedTextField();
        textFieldWeight = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneContent = new javax.swing.JTextPane();
        buttonConfirm = new javax.swing.JButton();
        butonCancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        tabPaneSearch = new javax.swing.JTabbedPane();
        panelUploadImage = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        buttonUploadImage = new javax.swing.JButton();
        panelSearchFlight = new javax.swing.JPanel();
        scrollPaneFlightTable = new javax.swing.JScrollPane();
        tableLugSearchFlight = new javax.swing.JTable();
        textFieldFlightOrigin = new javax.swing.JFormattedTextField();
        textFieldFlightId = new javax.swing.JFormattedTextField();
        buttonSearchFlight = new javax.swing.JButton();
        textFieldFlightDestination = new javax.swing.JFormattedTextField();
        textFieldFlightArrival = new javax.swing.JFormattedTextField();
        textFieldFlightDeparture = new javax.swing.JFormattedTextField();
        panelSearchCustomer = new javax.swing.JPanel();
        buttonSearchCustomer = new javax.swing.JButton();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();
        textFieldFirstName = new javax.swing.JFormattedTextField();
        textFieldSurName = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        comboBoxLuggageSize = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        labelAddLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddLuggage.setText("Add luggage");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found" }));
        comboBoxLuggageStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLuggageStatusActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(textPaneContent);

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

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        pic.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pic.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUploadImageLayout.setVerticalGroup(
            panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUploadImage)
                .addContainerGap())
        );

        tabPaneSearch.addTab("Upload image", panelUploadImage);

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
                    .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFlightId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addComponent(buttonSearchFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                        .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchFlightLayout.setVerticalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchFlight)
                .addGap(6, 6, 6))
        );

        tabPaneSearch.addTab("Flight", panelSearchFlight);

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
                    .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                                .addComponent(textFieldSurName, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
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
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchCustomer)
                .addGap(12, 12, 12))
        );

        tabPaneSearch.addTab("Customer", panelSearchCustomer);

        comboBoxLuggageSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Size", "Small", "Medium", "Large" }));
        comboBoxLuggageSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comboBoxLuggageSizeMousePressed(evt);
            }
        });
        comboBoxLuggageSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLuggageSizeActionPerformed(evt);
            }
        });

        jLabel1.setText("Description:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(comboBoxLuggageSize, 0, 244, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textFieldWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelAddLuggage)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(labelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboBoxLuggageStatus, 0, 244, Short.MAX_VALUE)
                                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                            .addComponent(textFieldColor, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                                    .addComponent(textFieldLocation))
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabPaneSearch))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAddLuggage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxLuggageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirm)
                            .addComponent(butonCancel)
                            .addComponent(buttonBack)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabPaneSearch)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        this.userNotAFK();
        addLuggage();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        clearFields();
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        clearFields();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerActionPerformed
        this.userNotAFK();
        this.searchCustomer();
    }//GEN-LAST:event_buttonSearchCustomerActionPerformed

    private void buttonUploadImagebuttonUploadsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadImagebuttonUploadsImageActionPerformed
        this.userNotAFK();
        imageBase64 = selectLabelImage(pic);
    }//GEN-LAST:event_buttonUploadImagebuttonUploadsImageActionPerformed

    private void buttonSearchFlight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchFlight
        this.userNotAFK();
        this.searchFlight();
    }//GEN-LAST:event_buttonSearchFlight

    private void comboBoxLuggageSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLuggageSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxLuggageSizeActionPerformed

    private void comboBoxLuggageSizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboBoxLuggageSizeMousePressed
        // TODO add your handling code here:        
    }//GEN-LAST:event_comboBoxLuggageSizeMousePressed

    private void comboBoxLuggageStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLuggageStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxLuggageStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSearchFlight;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxLuggageSize;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddLuggage;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchFlight;
    private javax.swing.JPanel panelUploadImage;
    private javax.swing.JLabel pic;
    private javax.swing.JScrollPane scrollPaneFlightTable;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTabbedPane tabPaneSearch;
    private javax.swing.JTable tableLugSearchFlight;
    private javax.swing.JTable tableSearchCustomer;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldColor;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstName;
    private javax.swing.JFormattedTextField textFieldFlightArrival;
    private javax.swing.JFormattedTextField textFieldFlightDeparture;
    private javax.swing.JFormattedTextField textFieldFlightDestination;
    private javax.swing.JFormattedTextField textFieldFlightId;
    private javax.swing.JFormattedTextField textFieldFlightOrigin;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    private javax.swing.JFormattedTextField textFieldSurName;
    private javax.swing.JFormattedTextField textFieldWeight;
    private javax.swing.JTextPane textPaneContent;
    // End of variables declaration//GEN-END:variables
}
