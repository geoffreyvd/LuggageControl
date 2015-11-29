/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

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
 * Searches through the database with the given data for luggage
 *
 * @author Konrad
 */
public class SearchLuggage extends SwitchingJPanel {

    private DatabaseMan db = new DatabaseMan();
    private SecurityMan sc;

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
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

        labelSearchLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchLuggage.setText("Search luggage");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Not returend" }));

        tableLuggageSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Flight number", "Owner ID", "Status", "Location"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonHelpLinking, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelSearchLuggage, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textFieldOwnerID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
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
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonHelpLinking))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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
        textFieldFlightNumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldLuggageID.setText("");
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

    private void fillSearchLuggageTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT * FROM luggage";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldLuggageID.getText().equals("") || !textFieldFlightNumber.getText().equals("")
                || !textFieldOwnerID.getText().equals("") || !textFieldLocation.getText().equals("")
                || !comboBoxLuggageStatus.getSelectedItem().toString().equals("Status")) {
            query += " WHERE 1=0 ";
        }

        try {
            if (!textFieldLuggageID.getText().equals("")) {
                query += "OR luggage_id = ? ";
                values.add(sc.filteredString(textFieldLuggageID.getText()));
            }

            if (!textFieldLocation.getText().equals("")) {
                query += "OR surname = ? ";
                values.add(sc.filteredString(textFieldLocation.getText()));
            }

            if (!comboBoxLuggageStatus.getSelectedItem().toString().equals("")) {
                query += "OR email = ? ";
                values.add(sc.filteredString(comboBoxLuggageStatus.getSelectedItem().toString()));
            }

            // If you get a mysql error saying: not unique table/alias look here 
            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
            // You need to create a mysql alias if you select multiple times from the same table!
            if (!textFieldFlightNumber.getText().equals("")) {
                query += "UNION SELECT luggage.luggage_id, location, status ";
                query += "FROM `luggage_flight` INNER JOIN `luggage` ON `luggage`.`luggage_id` ";
                query += "WHERE `luggage_flight`.`flight_id` = ? AND `luggage`.`luggage_id` = `luggage_flight`.`lugagge_id`";
                values.add(sc.filteredString(textFieldFlightNumber.getText()));
            }
            if (!textFieldOwnerID.getText().equals("")) {
                query += "UNION SELECT luggage.luggage_id, location, status ";
                query += "FROM `customer_luggage` INNER JOIN `luggage` ON `luggage`.`luggage_id` ";
                query += "WHERE `customer_luggage`.`customer_id` = ? AND `luggage`.`luggage_id` = `customer_luggage`.`lugagge_id`";
                values.add(sc.filteredString(textFieldFlightNumber.getText()));
            }

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableLuggageSearch.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("luggage_id"),
                    result.getString("flightnumber"),
                    result.getString("customer_id"),
                    result.getString("status"),
                    result.getString("location")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableLuggageSearch.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }


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
