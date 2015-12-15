package screen.search;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Searches through the database with the given data for luggage
 *
 * @author Konrad
 */
public class SearchLuggage extends SwitchingJPanel {

    private DatabaseMan db = new DatabaseMan();

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     * @param luggageControl
     */
    public SearchLuggage(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("LuggageID", textFieldLuggageID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageID);
        PromptSupport.setPrompt("Flightnumber", textFieldFlightNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightNumber);
        PromptSupport.setPrompt("OwnerID", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Location", textFieldLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLocation);
    }

    /**
     * Clears all textfields
     */
    public void clearSearchLuggage() {
        textFieldFlightNumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldLuggageID.setText("");
    }
    
    /**
     * Go to the luggage details screen based on the selected lugggage id
     * This method is best used in conjunction with the fillTableLuggage.
     * @param luggageId The specific database luggage id from the luggage table
     */
    public void switchLuggageDetails(int luggageId) {
        this.luggageControl.prefillPanel(ScreenNames.LUGGAGE_DETAILS, luggageId);
        this.luggageControl.switchJPanel(ScreenNames.LUGGAGE_DETAILS);
    }
    
    /**
     * Searches through the database for the luggage withe filters from 
     * the textfields or for every luggage when none of the fields are filled in
     */
    private void fillSearchLuggageTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT luggage_id, location, color, weight, size, content, status FROM luggage ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldLuggageID.getText().equals("") || !textFieldFlightNumber.getText().equals("") ||
            !textFieldOwnerID.getText().equals("") || !textFieldLocation.getText().equals("") || 
            !comboBoxLuggageStatus.getSelectedItem().toString().equals("Status")) {
            query += "WHERE 1=0 ";
        }

        try {
            if (!textFieldLuggageID.getText().equals("")) {
                query += "OR luggage_id = ? ";
                values.add(helpers.Filters.filteredString(textFieldLuggageID.getText()));
            }

            if (!textFieldLocation.getText().equals("")) {
                query += "OR location = ? ";
                values.add(helpers.Filters.filteredString(textFieldLocation.getText()));
            }

            if (!comboBoxLuggageStatus.getSelectedItem().toString().equals("Status")) {
                query += "OR status = ? ";
                values.add(helpers.Filters.filteredString(comboBoxLuggageStatus.getSelectedItem().toString()));
            }

            // If you get a mysql error saying: not unique table/alias look here 
            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
            // You need to create a mysql alias if you select multiple times from the same table!
            
            
            query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, content, status ";
            query += "FROM `luggage_flight` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
            if (!textFieldFlightNumber.getText().equals("")) {
                query += "`luggage_flight`.`flight_id` = ? AND ";
                values.add(helpers.Filters.filteredString(textFieldFlightNumber.getText()));
            }
            query += "`luggage`.`luggage_id` = `luggage_flight`.`luggage_id`";
            
            query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, content, status ";
            query += "FROM `customer_luggage` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
            if (!textFieldOwnerID.getText().equals("")) {
                query += "`customer_luggage`.`customer_id` = ? AND ";
                values.add(helpers.Filters.filteredString(textFieldFlightNumber.getText()));
            }
            query += "`luggage`.`luggage_id` = `customer_luggage`.`luggage_id`";
                    
            

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableLuggageSearch.getModel();
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
            tableLuggageSearch.setModel(datamodel);
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

        labelSearchLuggage = new javax.swing.JLabel();
        textFieldLuggageID = new javax.swing.JFormattedTextField();
        textFieldFlightNumber = new javax.swing.JFormattedTextField();
        textFieldLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLuggageSearch = new javax.swing.JTable();
        buttonHelpLinking = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelSearchLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchLuggage.setText("Search luggage");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Not returend" }));

        tableLuggageSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Location", "Color", "Weight", "Size", "Content", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tableLuggageSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableLuggageSearchKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableLuggageSearch);

        buttonHelpLinking.setText("Help");
        buttonHelpLinking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

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
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonHelpLinking, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelSearchLuggage)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textFieldOwnerID)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                            .addComponent(textFieldFlightNumber))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboBoxLuggageStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textFieldLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
                                .addGap(269, 269, 269)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonHelpLinking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch)
                    .addComponent(buttonCancel))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * sets the user as not afk and changes to panel help_fingding
     *
     * @param evt
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.FINDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    /**
     * sets the user as not afk, resets all the textfields and combobox, and
     * changes to panel home screen
     *
     * @param evt
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK(); 
        clearSearchLuggage();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * Searches through the database with the given data for luggage
     *
     * @param evt
     */
    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.fillSearchLuggageTable();
        this.userNotAFK();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void tableLuggageSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLuggageSearchKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER) {
            JTable tempTable = new JTable();
            try {
                tempTable = (JTable)evt.getComponent();
                // look at this one liner
                switchLuggageDetails(Integer.parseInt((String) tempTable.getValueAt(tempTable.getSelectedRow(), 0))); 
            }
            catch(Exception e) {
                new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            }
            
            
            tableLuggageSearch.clearSelection();
        }
    }//GEN-LAST:event_tableLuggageSearchKeyPressed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        this.fillSearchLuggageTable();
    }//GEN-LAST:event_formAncestorAdded

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelpLinking;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSearchLuggage;
    private javax.swing.JTable tableLuggageSearch;
    private javax.swing.JFormattedTextField textFieldFlightNumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldLuggageID;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    // End of variables declaration//GEN-END:variables
}

//        ResultSet result = new EmptyResultSet();
//        
//        String[] textFieldsLuggage = new String[3];
//        
//        textFieldsLuggage[0] = textFieldLuggageID.getText();
//        textFieldsLuggage[1] = (String)comboBoxLuggageStatus.getSelectedItem();
//        textFieldsLuggage[2] = textFieldLocation.getText();
//        
//        String query = "SELECT * FROM luggagecontroldata.luggage ";
//        String[] values = new String[3];
//        boolean check = true;
//        
//        for(int i = 0; i<textFieldsLuggage.length; i++) {
//            if(!(textFieldsLuggage[i].equals(""))) {
//                if(check) {
//                    query += "WHERE ";
//                    check = false;
//                }
//                values[i] = sc.filteredInt(textFieldsLuggage[i], 1, Integer.MAX_VALUE);
//                query += textFieldsLuggage[i]+" = ?";
//            }
//        }
//        query += ";";
//        
//        try {
//              
//            result = db.query(query, values);
//            
//            DefaultTableModel datamodel = (DefaultTableModel)tableLuggageSearch.getModel();
//            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
//                datamodel.removeRow(i);
//            }
//            while(result.next()) {
//                System.out.println(result.getString("flightnumber"));
//                Object[] data = {result.getString("luggage_id"), result.getString("flightnumber"), result.getString("client_id"), result.getString("status"), result.getString("location")};
//                datamodel.addRow(data);
//            }
//            tableLuggageSearch.setModel(datamodel);
//        }
//        catch(Exception e) {
//            
//        }
        
//        String[] textFieldsLuggage = new String[3];
//
//        textFieldsLuggage[0] = textFieldLuggageID.getText();
//        textFieldsLuggage[1] = (String) comboBoxLuggageStatus.getSelectedItem();
//        textFieldsLuggage[2] = textFieldLocation.getText();
//
//        String query = "SELECT * FROM luggagecontroldata.luggage ";
//        String queryInnerJoin = "SELECT `luggage_id` "
//                + "FROM `luggage_flight`"
//                + "INNER JOIN `luggage`"
//                + "ON luggage_flight.luggage_id=luggage.luggage_id"
//                + "ORDER BY luggage.luggage_id";
//        String[] values = new String[3];
//        boolean check = true;
//
//        for (int i = 0; i < textFieldsLuggage.length; i++) {
//            if (!(textFieldsLuggage[i].equals(""))) {
//                if (check) {
//                    query += "WHERE ";
//                    check = false;
//                }
//                values[i] = sc.filteredInt(textFieldsLuggage[i], 1, Integer.MAX_VALUE);
//                query += textFieldsLuggage[i] + " = ?";
//            }
//        }
//        query += ";";
//
//        try {
//
//            result = db.query(query, values);
//
//            DefaultTableModel datamodel = (DefaultTableModel) tableLuggageSearch.getModel();
//            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
//                datamodel.removeRow(i);
//            }
//            while (result.next()) {
//                System.out.println(result.getString("flightnumber"));
//                Object[] data = {result.getString("luggage_id"), result.getString("flightnumber"), result.getString("client_id"), result.getString("status"), result.getString("location"), false};
//                datamodel.addRow(data);
//            }
//            tableLuggageSearch.setModel(datamodel);
//        } catch (Exception e) {
//
//        }
//    }
