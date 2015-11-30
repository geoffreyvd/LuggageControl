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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Searches through the client database with the given data.
 * @author Konrad
 */
public class SearchCustomer extends SwitchingJPanel {
    
    private DatabaseMan db = new DatabaseMan();

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public SearchCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("First name", textFieldFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFirstName);
        PromptSupport.setPrompt("Last name", textFieldSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurName);
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Cellphone number", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
    }
    
    private void clearCustomerTable() {
        DefaultTableModel datamodel = (DefaultTableModel)tableCustomer.getModel();
        for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
            datamodel.removeRow(i);
        }
    }
    
    /**
     * Fill our table with data based on the user provided dataset,
     * currently filtering is not really present
     * but atleast the UNION and INNER JOIN work so i am quite happy ~Corne Lukken.
     */
    public void fillCustomerTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT * FROM customer";
        ArrayList<String> values = new ArrayList<String>();
        
        // If Some text fields are not empty we add the WHERE clause
        if(!textFieldFirstName.getText().equals("") || !textFieldSurName.getText().equals("") || 
        !textFieldEmail.getText().equals("") || !textFieldCellphone.getText().equals("") ||
        !textFieldFlightnumber.getText().equals("")) {
            query += " WHERE 1=0 ";
        }
        
        try {
            if(!textFieldFirstName.getText().equals("")) {
                query += "OR firstname = ? ";
                values.add(helpers.Filters.filteredString(textFieldFirstName.getText()));
            }
            
            if(!textFieldSurName.getText().equals("")) {
                query += "OR surname = ? ";
                values.add(helpers.Filters.filteredString(textFieldSurName.getText()));
            }
            
            if(!textFieldEmail.getText().equals("")) {
                query += "OR email = ? ";
                values.add(helpers.Filters.filteredString(textFieldEmail.getText()));
            }
            
            if(!helpers.Filters.filteredCellphone(textFieldCellphone.getText()).equals("")) {
                query += "OR cellphone = ? ";
                values.add(textFieldCellphone.getText());
            }
            
            
            // If you get a mysql error saying: not unique table/alias look here 
            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
            // You need to create a mysql alias if you select multiple times from the same table!
            if(!textFieldFlightnumber.getText().equals("")) {
                query += "UNION SELECT customer.customer_id, firstname, surname, email, cellphone, birthday, gender, adress, postcode ";
                query += "FROM `customer_flight` INNER JOIN `customer` ON `customer`.`customer_id` ";
                query += "WHERE `customer_flight`.`flight_id` = ? AND `customer`.`customer_id` = `customer_flight`.`customer_id`";
                values.add(helpers.Filters.filteredString(textFieldFlightnumber.getText()));
            }
            
            result = db.query(query + ";", values.toArray(new String[values.size()]));
                        
            DefaultTableModel datamodel = (DefaultTableModel)tableCustomer.getModel();
            this.clearCustomerTable();
            
            while(result.next()) {

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
            tableCustomer.setModel(datamodel);
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * Go to the customer details screen based on the selected customer id
     * This method is best used in conjunction with the fillTableCustomers.
     * @param customerId The specific database customer id from the customer table
     */
    public void switchCustomerDetails(int customerId) {
        this.luggageControl.prefillPanel(ScreenNames.CUSTOMER_DETAILS, customerId);
        this.luggageControl.switchJPanel(ScreenNames.CUSTOMER_DETAILS);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSearchCustomer = new javax.swing.JLabel();
        textFieldFirstName = new javax.swing.JFormattedTextField();
        textFieldSurName = new javax.swing.JFormattedTextField();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        buttonSearch = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelSearchCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearchCustomer.setText("Search Customer");

        textFieldFirstName.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldSurName.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(6, 20));

        textFieldFlightnumber.setMaximumSize(new java.awt.Dimension(6, 20));
        textFieldFlightnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldFlightnumberKeyPressed(evt);
            }
        });

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

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        tableCustomer.setAutoCreateRowSorter(true);
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "customer id", "firstname", "surname", "email", "cellphone", "birthday", "gender", "adress", "postcode"
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
        tableCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerMouseClicked(evt);
            }
        });
        tableCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCustomerKeyPressed(evt);
            }
        });
        scrollPaneTable.setViewportView(tableCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSearchCustomer)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldSurName, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(117, 117, 117)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearchCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldSurName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch)
                    .addComponent(buttonCancel))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Searches through the client database with the given data.
     * @param evt
     */
    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
        this.fillCustomerTable();
    }//GEN-LAST:event_buttonSearchActionPerformed

    /**
     * sets the user as not afk, resets all the textfields and changes to panel home screen
     * @param evt 
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldFirstName.setText("");
        textFieldSurName.setText("");
        textFieldCellphone.setText("");
        textFieldEmail.setText("");
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * sets the user as not afk and changes to panel help_finding
     * @param evt 
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.FINDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void textFieldFlightnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldFlightnumberKeyPressed
        this.userNotAFK();
        // identify the pressed key
        if(evt.getKeyCode() == evt.VK_ENTER) {
            this.fillCustomerTable();
        }
    }//GEN-LAST:event_textFieldFlightnumberKeyPressed

    private void tableCustomerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCustomerKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER) {
            JTable tempTable = new JTable();
            try {
                tempTable = (JTable)evt.getComponent();
            }
            catch(Exception e) {
                new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            }
            // look at this one liner
            switchCustomerDetails(Integer.parseInt((String)tempTable.getValueAt(tempTable.getSelectedRow(), 0)));
            
            this.clearCustomerTable();
        }
    }//GEN-LAST:event_tableCustomerKeyPressed

    private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCustomerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableCustomerMouseClicked

    /**
     * This event fires when the screen is displayed use this to update when the screen is drawn.
     * @param evt 
     */
    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        this.fillCustomerTable();
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel labelSearchCustomer;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstName;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldSurName;
    // End of variables declaration//GEN-END:variables
}
