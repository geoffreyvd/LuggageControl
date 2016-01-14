package screen.details;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import org.jdesktop.swingx.prompt.PromptSupport;
import screen.base.BaseDetails;

/**
 * User details screen for the administrator to manage users
 *
 * @author Team 3 FYS
 */
public class UserDetails extends BaseDetails {

    private int currentUserId = 0;

    public UserDetails(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Adress", textFieldAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAdress);
        PromptSupport.setPrompt("Cellphone", textFieldCellphoneNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphoneNumber);
        PromptSupport.setPrompt("City", textFieldCity);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCity);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Zipcode", textFieldZipcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldZipcode);
        PromptSupport.setPrompt("First name", formattedTextFieldSearchFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, formattedTextFieldSearchFirstname);
        PromptSupport.setPrompt("Last name", formattedTextFieldSearchLastname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, formattedTextFieldSearchLastname);

    }

    public void loadUser(int UserID) {
        try {
            ResultSet resultFlight = db.query("SELECT * FROM user WHERE user_id = ?", new String[]{UserID + ""});
            while (resultFlight.next()) {
                labelUserIDDisplay.setText(resultFlight.getString("user_id"));
                labelNameDisplay.setText(resultFlight.getString("firstname"));

                textFieldCellphoneNumber.setText(resultFlight.getString("cellphone"));
                textFieldAdress.setText(resultFlight.getString("adress"));
                textFieldZipcode.setText(resultFlight.getString("zipcode"));
                textFieldCity.setText(resultFlight.getString("city"));
                textFieldEmail.setText(resultFlight.getString("email"));

                if (resultFlight.getString("permission").equals("1")) {
                    comboBoxProfession.setSelectedIndex(1);
                } else if (resultFlight.getString("permission").equals("2")) {
                    comboBoxProfession.setSelectedIndex(2);
                } else if (resultFlight.getString("permission").equals("3")) {
                    comboBoxProfession.setSelectedIndex(3);
                } else {
                    new ErrorJDialog(this.luggageControl, true, "Error: Gender does not exist", (new Throwable()).getStackTrace());
                }

                currentUserId = Integer.parseInt(resultFlight.getString("user_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillSearchUserTable() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT `user`.`user_id`, username, email, firstname, surname, cellphone, `birthday`, nationality, adress, postcode, gender FROM user ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!formattedTextFieldSearchFirstname.getText().equals("")
                || !formattedTextFieldSearchLastname.getText().equals("")) {
            if (comboBoxSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxSearchType.getSelectedItem().toString().equals("Exclusive")
                    || comboBoxSearchType.getSelectedItem().toString().equals("Loose")) {
                query += "WHERE 1=0 ";
            }
        }

        try {
            if (!formattedTextFieldSearchFirstname.getText().equals("")) {
                query += checkComboBox("adress", formattedTextFieldSearchFirstname, values);
            }

            if (!formattedTextFieldSearchLastname.getText().equals("")) {
                query += checkComboBox("birthday", formattedTextFieldSearchLastname, values);
            }

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableUser.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("user_id"),
                    result.getString("username"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    result.getString("email"),
                    result.getString("cellphone"),
                    result.getString("birthday"),
                    result.getString("gender"),
                    result.getString("nationality"),
                    result.getString("adress"),
                    result.getString("postcode")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableUser.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }

    private String checkComboBox(String columnName, JFormattedTextField textField, ArrayList<String> values) {
        if (comboBoxSearchType.getSelectedItem().toString().equals("Inclusive")) {
            values.add(helpers.Filters.filteredString(textField.getText()));
            return " AND " + columnName + " = ? ";
        } else if (comboBoxSearchType.getSelectedItem().toString().equals("Loose")) {
            values.add(helpers.Filters.filteredString("%" + textField.getText() + "%"));
            return " OR " + columnName + " LIKE ? ";
        } else {
            values.add(helpers.Filters.filteredString(textField.getText()));
            return " OR " + columnName + " = ? ";
        }
    }

    private void deleteUser() {
        try {
            db.queryManipulation("DELETE FROM user WHERE user_id = ?", 
            new String[]{currentUserId+""}, new String[]{"Int"});
        }
        catch(Exception e) {
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

        labelUserDetails = new javax.swing.JLabel();
        LabelUserId = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldCellphoneNumber = new javax.swing.JFormattedTextField();
        comboBoxProfession = new javax.swing.JComboBox();
        textFieldAdress = new javax.swing.JFormattedTextField();
        textFieldCity = new javax.swing.JFormattedTextField();
        textFieldZipcode = new javax.swing.JFormattedTextField();
        labelUserSearch = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        labelName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        buttonSearch = new javax.swing.JButton();
        formattedTextFieldSearchFirstname = new javax.swing.JFormattedTextField();
        formattedTextFieldSearchLastname = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        comboBoxSearchType = new javax.swing.JComboBox();
        labelUserIDDisplay = new javax.swing.JLabel();
        labelNameDisplay = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelUserDetails.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelUserDetails.setText("User details");

        LabelUserId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LabelUserId.setText("User ID:  ");

        comboBoxProfession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proffesion", "Employee", "Manager", "Administrator" }));

        labelUserSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelUserSearch.setText("Search");

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelbutonCancelActionPerformed(evt);
            }
        });

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

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setText("Name: ");

        tableUser.setAutoCreateRowSorter(true);
        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "user id", "Username", "firstname", "surname", "email", "cellphone", "birthday", "gender", "Nationality", "adress", "postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUser.getTableHeader().setReorderingAllowed(false);
        tableUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableUserKeyPressed(evt);
            }
        });
        scrollPaneTable.setViewportView(tableUser);

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jLabel1.setText("Search type:");

        comboBoxSearchType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inclusive", "Exclusive", "Loose" }));

        labelUserIDDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelUserIDDisplay.setText("XXXXXXXXXX");

        labelNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNameDisplay.setText("XXXXXXXXXX");

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxProfession, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldCity)
                    .addComponent(textFieldEmail)
                    .addComponent(textFieldCellphoneNumber)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldZipcode, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                    .addComponent(labelUserDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LabelUserId, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelUserIDDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(formattedTextFieldSearchFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formattedTextFieldSearchLastname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelUserSearch)
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(comboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(scrollPaneTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelUserDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelUserIDDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNameDisplay))
                        .addGap(36, 36, 36)
                        .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldZipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(textFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonCancel)
                            .addComponent(buttonDelete)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonHelp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBack))
                            .addComponent(labelUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(formattedTextFieldSearchFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formattedTextFieldSearchLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearch)))
                .addGap(30, 30, 30))
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonCancelbutonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelbutonCancelActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_ADMINISTRATOR);
    }//GEN-LAST:event_buttonCancelbutonCancelActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_ADMINISTRATOR);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        fillSearchUserTable();
    }//GEN-LAST:event_formAncestorAdded

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        this.userNotAFK();
        this.fillSearchUserTable();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        this.userNotAFK();
        try {
            deleteUser();
            
        }
        catch(NullPointerException e) {
            labelStatus.setText("Cannot delete unexisting reference");
            this.resetLabel(5000, labelStatus);
            return;
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void tableUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableUserKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            JTable tempTable = new JTable();
            try {
                tempTable = (JTable) evt.getComponent();
                loadUser(Integer.parseInt((String) tempTable.getValueAt(tempTable.getSelectedRow(), 0)));
            } catch (Exception e) {
                new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            }
        }
    }//GEN-LAST:event_tableUserKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelUserId;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboBoxProfession;
    private javax.swing.JComboBox comboBoxSearchType;
    private javax.swing.JFormattedTextField formattedTextFieldSearchFirstname;
    private javax.swing.JFormattedTextField formattedTextFieldSearchLastname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelNameDisplay;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelUserDetails;
    private javax.swing.JLabel labelUserIDDisplay;
    private javax.swing.JLabel labelUserSearch;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tableUser;
    private javax.swing.JFormattedTextField textFieldAdress;
    private javax.swing.JFormattedTextField textFieldCellphoneNumber;
    private javax.swing.JFormattedTextField textFieldCity;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldZipcode;
    // End of variables declaration//GEN-END:variables
}
