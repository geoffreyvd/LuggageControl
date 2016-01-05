/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.delete;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Gebruiker
 */
public class DeleteLuggage extends SwitchingJPanel {

    private DatabaseMan db = new DatabaseMan();

    /**
     *
     * @param luggageControl
     */
    public DeleteLuggage(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageID);
        PromptSupport.setPrompt("Flight number", textFieldFlightNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightNumber);
        PromptSupport.setPrompt("Location", textFieldLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLocation);
        PromptSupport.setPrompt("Status", textFieldStatus);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldStatus);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelHeader = new javax.swing.JLabel();
        textFieldFlightNumber = new javax.swing.JFormattedTextField();
        textFieldLuggageID = new javax.swing.JFormattedTextField();
        buttonSearchLuggage = new javax.swing.JButton();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableDeleteLuggage = new javax.swing.JTable();
        textFieldStatus = new javax.swing.JFormattedTextField();
        textFieldLocation = new javax.swing.JFormattedTextField();
        buttonUpdate = new javax.swing.JButton();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
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

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeader.setText("Delete luggage");

        textFieldFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFlightNumberActionPerformed(evt);
            }
        });

        textFieldLuggageID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLuggageIDActionPerformed(evt);
            }
        });

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        tableDeleteLuggage.setAutoCreateRowSorter(true);
        tableDeleteLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Flightnumber", "Status", "Location", "Remove"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDeleteLuggage.setPreferredSize(new java.awt.Dimension(1920, 500));
        scrollPaneLuggageTable.setViewportView(tableDeleteLuggage);

        textFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldStatusActionPerformed(evt);
            }
        });

        textFieldLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLocationActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelHeader)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(textFieldLuggageID)
                                            .addComponent(textFieldFlightNumber))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldStatus)
                                            .addComponent(textFieldLocation)))
                                    .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUpdate)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(luggageControl.HOME_SCREEN_ADMINISTRATOR);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
        fillFlightTable();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void fillFlightTable() {
        ArrayList<String> values = new ArrayList<String>();
        String query = "SELECT Luggage.Luggage_id, flight_id, status, location FROM luggage INNER JOIN luggage_flight ON luggage_flight.luggage_id = luggage.luggage_id";
        if (!textFieldFlightNumber.getText().equals("") || !textFieldLocation.getText().equals("")
                || !textFieldLuggageID.getText().equals("") || !textFieldStatus.getText().equals("")) {
            query += " WHERE 1 = 0";
        } else {
            query += " order by luggage_id desc";
        }
        if (!textFieldLuggageID.getText().equals("")) {
            query += " OR luggage.luggage_id = ?";
            values.add(helpers.Filters.filteredString(textFieldLuggageID.getText(), 1, 10));
        }
        if (!textFieldLocation.getText().equals("")) {
            query += " OR Location = ?";
            values.add(helpers.Filters.filteredString(textFieldLocation.getText(), 1, 45));
        }
        if (!textFieldStatus.getText().equals("")) {
            query += " OR Status = ?";
            values.add(helpers.Filters.filteredString(textFieldStatus.getText(), 1, 45));
        }
        if (!textFieldFlightNumber.getText().equals("")) {
            query += " OR flight_id = ?";
            values.add(helpers.Filters.filteredString(textFieldFlightNumber.getText(), 1, 10));
        }

        ResultSet result = new EmptyResultSet();
        try {
            result = db.query(query, values.toArray(new String[values.size()]));
            DefaultTableModel datamodel = (DefaultTableModel) tableDeleteLuggage.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {
                Object[] data = {result.getString("luggage_id"), result.getString("flight_id"), result.getString("status"), result.getString("location"), false};
                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableDeleteLuggage.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, "Error: retrieving flights dataset", (new Throwable()).getStackTrace());
        }
    }

    private void textFieldFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFlightNumberActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldFlightNumberActionPerformed

    private void textFieldLuggageIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLuggageIDActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldLuggageIDActionPerformed

    private void textFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldStatusActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldStatusActionPerformed

    private void textFieldLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLocationActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldLocationActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        fillFlightTable();
    }//GEN-LAST:event_formAncestorAdded

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        DefaultTableModel datamodel = (DefaultTableModel) tableDeleteLuggage.getModel();
        String query = "DELETE FROM luggage WHERE 1=0";
        String query1 = "DELETE FROM luggage_flight WHERE 1=0";
        String query2 = "DELETE FROM customer_luggage WHERE 1=0";
        String query3 = "DELETE FROM luggage_lost_found WHERE 1=0";
        String query4 = query3;
        ArrayList<String> data = new ArrayList();
        ArrayList<String> types = new ArrayList();
        boolean[] idRemove = new boolean[datamodel.getRowCount()];

        for (int i = datamodel.getRowCount() - 1; i >= 0; i--) {

            // if this entry equals true - true to remove
            if ((boolean) datamodel.getValueAt(i, (datamodel.getColumnCount() - 1))) {
                query += " OR luggage_id = ?";
                query1 += " OR luggage_id = ?";
                query2 += " OR luggage_id = ?";
                query3 += " OR luggage_lost_id = ?";
                query4 += " OR luggage_found_id = ?";
                data.add((String) datamodel.getValueAt(i, 0));
                types.add(db.PS_TYPE_INT);
            }
        }

        String[] values = data.toArray(new String[data.size()]);
        String[] types2 = types.toArray(new String[types.size()]);

        try {
            db.queryManipulation(query4, values, types2);
            db.queryManipulation(query3, values, types2);
            db.queryManipulation(query2, values, types2);
            db.queryManipulation(query1, values, types2);
            db.queryManipulation(query, values, types2);
            fillFlightTable();
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, "Critical error: my god what have you done!", e.getStackTrace(), true);
        }        
    }//GEN-LAST:event_buttonUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JTable tableDeleteLuggage;
    private javax.swing.JFormattedTextField textFieldFlightNumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldLuggageID;
    private javax.swing.JFormattedTextField textFieldStatus;
    // End of variables declaration//GEN-END:variables
}
