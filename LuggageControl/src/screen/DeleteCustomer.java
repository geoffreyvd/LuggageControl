/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Gebruiker
 */
public class DeleteCustomer extends SwitchingJPanel {

    private SecurityMan sc;
    DatabaseMan db = new DatabaseMan();

    public DeleteCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Customer Name", textFieldCustomerName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerName);
        PromptSupport.setPrompt("Customer ID", textFieldCustomerId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerId);
        PromptSupport.setPrompt("Customer ID", textFieldDeleteCustomerId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDeleteCustomerId);
        buildTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelHeader = new javax.swing.JLabel();
        textFieldCustomerName = new javax.swing.JFormattedTextField();
        textFieldCustomerId = new javax.swing.JFormattedTextField();
        buttonSearch = new javax.swing.JButton();
        scrollPaneDeleteCustomer = new javax.swing.JScrollPane();
        tableDeleteCustomer = new javax.swing.JTable();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        textFieldDeleteCustomerId = new javax.swing.JTextField();

        setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHeader.setText("Delete Customer");

        textFieldCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCustomerNameActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        tableDeleteCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Customer ID", "Customer first name", "Customer surname", "Customer email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPaneDeleteCustomer.setViewportView(tableDeleteCustomer);

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

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        textFieldDeleteCustomerId.setPreferredSize(new java.awt.Dimension(6, 20));
        textFieldDeleteCustomerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldDeleteCustomerIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneDeleteCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCustomerName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(161, 161, 161)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textFieldDeleteCustomerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldDeleteCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonSearch)
                            .addComponent(buttonDelete)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack)))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneDeleteCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCustomerNameActionPerformed
        this.userNotAFK();

    }//GEN-LAST:event_textFieldCustomerNameActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_ADMINISTRATOR);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.REMOVING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
        
        buildTable();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textFieldDeleteCustomerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldDeleteCustomerIdActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_textFieldDeleteCustomerIdActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        this.userNotAFK();
        if (!textFieldDeleteCustomerId.getText().equals("")) {
            String query = "DELETE FROM `luggagecontroldata`.`customer_flight` WHERE `customer_id`= ?; ";
            String[] values = {
                sc.filteredString(textFieldDeleteCustomerId.getText())
            };
            String[] types = {
                "String"
            };
            
            try {
                db.queryManipulation(query, values, types);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
            query = "DELETE FROM `luggagecontroldata`.`customer` WHERE `customer_id`= ?;";
            try {
                db.queryManipulation(query, values, types);
                textFieldDeleteCustomerId.setText("");
                buildTable();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buildTable() {
        ArrayList<String> values = new ArrayList<String>();
        String query = "SELECT * FROM luggagecontroldata.customer";
        if (!textFieldCustomerId.getText().equals("") || !textFieldCustomerName.getText().equals("")) {
            query += " WHERE 1 = 0";
        }else{
            query += " order by customer_id desc";
        }
        if (!textFieldCustomerId.getText().equals("")) {
            query += " OR customer_id = ?";
            values.add(sc.filteredString(textFieldCustomerId.getText()));
        }
        if (!textFieldCustomerName.getText().equals("")) {
            query += " OR firstname = ?";
            values.add(sc.filteredString(textFieldCustomerName.getText()));
        }

        query += " limit 4;";
        
        ResultSet result;
        try {
            result = db.query(query, values.toArray(new String[values.size()]));
            DefaultTableModel datamodel = (DefaultTableModel) tableDeleteCustomer.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("customer_id"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    result.getString("email")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableDeleteCustomer.setModel(datamodel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new ErrorJDialog(this.luggageControl, true, "Error: retrieving customer dataset", (new Throwable()).getStackTrace());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JScrollPane scrollPaneDeleteCustomer;
    private javax.swing.JTable tableDeleteCustomer;
    private javax.swing.JFormattedTextField textFieldCustomerId;
    private javax.swing.JFormattedTextField textFieldCustomerName;
    private javax.swing.JTextField textFieldDeleteCustomerId;
    // End of variables declaration//GEN-END:variables

}
