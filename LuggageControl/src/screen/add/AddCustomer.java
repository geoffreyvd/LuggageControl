package screen.add;

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
 * @author root
 */
public class AddCustomer extends SwitchingJPanel {

    private SecurityMan sc;
    DatabaseMan db = new DatabaseMan();

    public AddCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Name", textFieldName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldName);
        PromptSupport.setPrompt("Sur name", textFieldSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurName);
        PromptSupport.setPrompt("E-Mail", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("Cellphone number", textFieldCellphoneNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphoneNumber);
        PromptSupport.setPrompt("Birthday (YYYY-MM-DD)", textFieldBirthday);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldBirthday);
        PromptSupport.setPrompt("Gender", textFieldGender);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldGender);
        PromptSupport.setPrompt("Adress", textFieldAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAdress);
        PromptSupport.setPrompt("Postalcode", textFieldPostcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPostcode);
        PromptSupport.setPrompt("Flight number", textFieldQuickSearchFlightNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldQuickSearchFlightNumber);
        this.buildTable("SELECT * FROM luggagecontroldata.luggage");
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
        textFieldName = new javax.swing.JTextField();
        textFieldSurName = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        textFieldCellphoneNumber = new javax.swing.JTextField();
        textFieldBirthday = new javax.swing.JTextField();
        textFieldGender = new javax.swing.JTextField();
        textFieldAdress = new javax.swing.JTextField();
        textFieldPostcode = new javax.swing.JTextField();
        labelQuickSearch = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        table = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        textFieldQuickSearchFlightNumber = new javax.swing.JTextField();
        butonCancel = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        labelAddCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddCustomer.setText("Add customer");

        labelQuickSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelQuickSearch.setText("Quick search");

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

        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Origin", "Location", "Flightnumber"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustomer.setFocusable(false);
        tableCustomer.setRowSelectionAllowed(false);
        table.setViewportView(tableCustomer);

        textFieldQuickSearchFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldQuickSearchFlightNumberActionPerformed(evt);
            }
        });
        textFieldQuickSearchFlightNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldQuickSearchFlightNumberKeyPressed(evt);
            }
        });

        butonCancel.setText("Cancel");
        butonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelbuttonCancelActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAddCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldSurName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldEmail)
                            .addComponent(textFieldBirthday)
                            .addComponent(textFieldGender)
                            .addComponent(textFieldAdress)
                            .addComponent(textFieldPostcode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldName))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelQuickSearch)
                                .addGap(0, 43, Short.MAX_VALUE))
                            .addComponent(textFieldQuickSearchFlightNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAddCustomer)
                            .addComponent(labelQuickSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldQuickSearchFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldSurName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirm)
                            .addComponent(butonCancel)
                            .addComponent(buttonBack))
                        .addGap(30, 30, 30))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        textFieldName.setText("");
        textFieldSurName.setText("");
        textFieldEmail.setText("");
        textFieldCellphoneNumber.setText("");
        textFieldBirthday.setText("");
        textFieldGender.setText("");
        textFieldAdress.setText("");
        textFieldPostcode.setText("");
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        if (!("".equals(textFieldName.getText()) || "".equals(textFieldSurName.getText())
                || "".equals(textFieldEmail.getText()) || "".equals(textFieldCellphoneNumber.getText())
                || "".equals(textFieldBirthday.getText()) || "".equals(textFieldGender.getText())
                || "".equals(textFieldAdress.getText()) || "".equals(textFieldPostcode.getText()))) {

            String query = "INSERT INTO `luggagecontroldata`.`customer`"
                    + "(`firstname`, `surname`, `email`, `cellphone`, `birthday`, `gender`, `adress`, `postcode`)"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            String[] values = new String[8];
            String[] types = new String[8];

            values[0] = textFieldName.getText();
            values[1] = textFieldSurName.getText();
            values[2] = textFieldEmail.getText();
            values[3] = textFieldCellphoneNumber.getText();
            values[4] = textFieldBirthday.getText();
            values[5] = textFieldGender.getText();
            values[6] = textFieldAdress.getText();
            values[7] = textFieldPostcode.getText();

            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "Int";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";
            types[7] = "String";

            try {
                db.queryManipulation(query, values, types);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new ErrorJDialog(this.luggageControl, true, "Error: retrieving inserting customer", (new Throwable()).getStackTrace());
            }
            System.out.println("work");
        } else {
            System.out.println("not work");
        }

        this.userNotAFK();
        textFieldName.setText("");
        textFieldSurName.setText("");
        textFieldEmail.setText("");
        textFieldCellphoneNumber.setText("");
        textFieldBirthday.setText("");
        textFieldGender.setText("");
        textFieldAdress.setText("");
        textFieldPostcode.setText("");
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buildTable(String query) {
        String[] values = {
            //sc.filteredString(textFieldQuickSearchFlightNumber.getText())
        };

        query += " limit 4;";

        ResultSet result;
        try {
            result = db.query(query, values);
            DefaultTableModel datamodel = (DefaultTableModel) tableCustomer.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("luggage_id"),
                    result.getString("location"),
                    result.getString("location"),
                    result.getString("status")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableCustomer.setModel(datamodel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void textFieldQuickSearchFlightNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldQuickSearchFlightNumberKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (!textFieldQuickSearchFlightNumber.getText().equals("")) {
                this.buildTable("SELECT * FROM luggagecontroldata.luggage");
            }
        }
    }//GEN-LAST:event_textFieldQuickSearchFlightNumberKeyPressed

    private void textFieldQuickSearchFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldQuickSearchFlightNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldQuickSearchFlightNumberActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddCustomer;
    private javax.swing.JLabel labelQuickSearch;
    private javax.swing.JScrollPane table;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField textFieldAdress;
    private javax.swing.JTextField textFieldBirthday;
    private javax.swing.JTextField textFieldCellphoneNumber;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldGender;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldPostcode;
    private javax.swing.JTextField textFieldQuickSearchFlightNumber;
    private javax.swing.JTextField textFieldSurName;
    // End of variables declaration//GEN-END:variables
}
