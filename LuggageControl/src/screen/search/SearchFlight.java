package screen.search;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;
import screen.base.BaseSearch;

/**
 *
 * @author Admin
 */
public class SearchFlight extends BaseSearch {

    private DatabaseMan db = new DatabaseMan();

    /**
     * Creates new form SearchFlight
     *
     * @param luggageControl
     */
    public SearchFlight(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Origin", textFieldOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOrigin);
        PromptSupport.setPrompt("Destination", textFieldDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDestination);
        PromptSupport.setPrompt("Departure time", textFieldDepartureTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDepartureTime);
        PromptSupport.setPrompt("Arrival time", textFieldArrivalTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldArrivalTime);
    }

    /**
     * Clears all textfields
     */
    public void clearSearchLuggage() {
        textFieldFlightnumber.setText("");
        textFieldOrigin.setText("");
        textFieldDestination.setText("");
        textFieldDepartureTime.setText("");
        textFieldArrivalTime.setText("");
    }

    /**
     * Go to the flight details screen based on the selected flight id This
     * method is best used in conjunction with the fillTableFlight.
     *
     * @param flightId The specific database luggage id from the luggage table
     */
    public void switchFlightDetails(int flightId) {
        this.luggageControl.prefillPanel(this.luggageControl.FLIGHT_DETAILS, flightId);
        this.luggageControl.switchJPanel(this.luggageControl.FLIGHT_DETAILS);
    }

    /**
     * Searches through the database for the luggage withe filters from the
     * textfields or for every luggage when none of the fields are filled in
     */
    private void fillSearchLuggageTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT flight_id, origin, destination, departure, arrival FROM flight ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFlightnumber.getText().equals("") || !textFieldOrigin.getText().equals("")
                || !textFieldDestination.getText().equals("") || !textFieldDepartureTime.getText().equals("")
                || !textFieldArrivalTime.getText().equals("")) {
            if (comboBoxSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxSearchType.getSelectedItem().toString().equals("Exclusive")
                    || comboBoxSearchType.getSelectedItem().toString().equals("Loose")) {
                query += "WHERE 1=0 ";
            }
        }

        try {
            //mid
            if (!textFieldFlightnumber.getText().equals("")) {
                query += checkComboBox("flight_id", textFieldFlightnumber, values);
            }

            if (!textFieldOrigin.getText().equals("")) {
                query += checkComboBox("origin", textFieldOrigin, values);
            }

            if (!textFieldDestination.getText().equals("")) {
                query += checkComboBox("destination", textFieldDestination, values);
            }

            if (!textFieldDepartureTime.getText().equals("")) {
                query += checkComboBox("departure", textFieldDepartureTime, values);
            }

            if (!textFieldArrivalTime.getText().equals("")) {
                query += checkComboBox("arrival", textFieldArrivalTime, values);
            }

//            // If you get a mysql error saying: not unique table/alias look here 
//            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
//            // You need to create a mysql alias if you select multiple times from the same table!
//            query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, content, status ";
//            query += "FROM `luggage_flight` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
//            if (!textFieldFlightNumber.getText().equals("")) {
//                query += "`luggage_flight`.`flight_id` = ? AND ";
//                values.add(helpers.Filters.filteredString(textFieldFlightNumber.getText()));
//            }
//            query += "`luggage`.`luggage_id` = `luggage_flight`.`luggage_id`";
//            
//            query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, content, status ";
//            query += "FROM `customer_luggage` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
//            if (!textFieldOwnerID.getText().equals("")) {
//                query += "`customer_luggage`.`customer_id` = ? AND ";
//                values.add(helpers.Filters.filteredString(textFieldFlightNumber.getText()));
//            }
//            query += "`luggage`.`luggage_id` = `customer_luggage`.`luggage_id`";
            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableFlightSearch.getModel();
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
            tableFlightSearch.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }

    /**
     * 
     * @param kolomNaam
     * @param textField
     * @param values
     * @return 
     */
    private String checkComboBox(String kolomNaam, JFormattedTextField textField,  ArrayList<String> values) {
        if (comboBoxSearchType.getSelectedItem().toString().equals("Inclusive")) {
            values.add(helpers.Filters.filteredString(textField.getText()));
            return " AND " + kolomNaam + " = ? ";
        }else if(comboBoxSearchType.getSelectedItem().toString().equals("Loose")){
            values.add(helpers.Filters.filteredString("%" + textField.getText() + "%"));
            return " OR " + kolomNaam + " LIKE ? ";
        }else {
            values.add(helpers.Filters.filteredString(textField.getText()));
            return " OR " + kolomNaam + " = ? ";
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
        labelSearchFlight = new javax.swing.JLabel();
        buttonSearch = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonHelpLinking = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFlightSearch = new javax.swing.JTable();
        textFieldArrivalTime = new javax.swing.JFormattedTextField();
        textFieldDestination = new javax.swing.JFormattedTextField();
        textFieldOrigin = new javax.swing.JFormattedTextField();
        textFieldDepartureTime = new javax.swing.JFormattedTextField();
        comboBoxSearchType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelSearchFlight.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchFlight.setText("Search flight");

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonHelpLinking.setText("Help");
        buttonHelpLinking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpLinkingbuttonHelpActionPerformed(evt);
            }
        });

        tableFlightSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flightnumber", "Origin", "Destination", "Departure", "Arival"
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
        tableFlightSearch.getTableHeader().setReorderingAllowed(false);
        tableFlightSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableFlightSearchKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableFlightSearch);

        comboBoxSearchType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inclusive", "Exclusive", "Loose" }));

        jLabel1.setText("Search type:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSearchFlight)
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(comboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonHelpLinking, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldDepartureTime, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldArrivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSearchFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(buttonHelpLinking))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldArrivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch)
                    .addComponent(buttonCancel))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.fillSearchLuggageTable();
        this.userNotAFK();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        clearSearchLuggage();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonHelpLinkingbuttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpLinkingbuttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpLinkingbuttonHelpActionPerformed

    private void tableFlightSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFlightSearchKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            JTable tempTable = new JTable();
            try {
                tempTable = (JTable) evt.getComponent();
                // look at this one liner
                switchFlightDetails(Integer.parseInt((String) tempTable.getValueAt(tempTable.getSelectedRow(), 0)));
            } catch (Exception e) {
                new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            }

            tableFlightSearch.clearSelection();
        }
    }//GEN-LAST:event_tableFlightSearchKeyPressed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        fillSearchLuggageTable();
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelpLinking;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox comboBoxSearchType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSearchFlight;
    private javax.swing.JTable tableFlightSearch;
    private javax.swing.JFormattedTextField textFieldArrivalTime;
    private javax.swing.JFormattedTextField textFieldDepartureTime;
    private javax.swing.JFormattedTextField textFieldDestination;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldOrigin;
    // End of variables declaration//GEN-END:variables
}
