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
 *
 * @author root
 */
public class AddCustomer extends SwitchingJPanel {

    private SecurityMan sc;
    DatabaseMan db = new DatabaseMan();

    /**
     *
     * @param luggageControl
     */
    public AddCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Name", textFieldName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldName);
        PromptSupport.setPrompt("Sur name", textFieldSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurName);
        PromptSupport.setPrompt("Email", textFieldEmail);
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
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("LuggageID", textFieldLuggageID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageID);
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
     * @return
     */
    private boolean checkInput() {
        // Check if luggage id exists
        if (!(textFieldLuggageID.getText().equals("")) && 
                db.queryOneResult("SELECT `luggage_id` FROM luggage WHERE luggage_id = ?", new String[]{textFieldLuggageID.getText()}).equals("")) {
            labelStatus.setText("Luggage doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // Check if flight number exists
        if (!(textFieldFlightnumber.getText().equals("")) && 
                db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", new String[]{textFieldFlightnumber.getText()}).equals("")) {
            labelStatus.setText("Flightnumber doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate name placeholder
        if (textFieldName.getText().equals("")){
            labelStatus.setText("First name is empty");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
        // validate sur name placeholder
        if (textFieldSurName.getText().equals("")){
            labelStatus.setText("Surname is empty");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
        // check if email is in the right format
        if (helpers.Filters.filteredEmail(textFieldEmail.getText()).equals("")){
            labelStatus.setText("Email not valid");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
        // check if cellphone is a number
        if (helpers.Filters.filteredCellphone(textFieldCellphoneNumber.getText()).equals("")){
            labelStatus.setText("Cellphone not valid");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
        // check if birthday is a valid date
        if (helpers.Filters.filteredDate(textFieldBirthday.getText(), "").equals("")){
            labelStatus.setText("Birthday not valid, birthday should be like YYYY-MM-DD");
            this.resetLabel(5000, labelStatus);
            return false; 
        }
        // validate gender placeholder
       if (textFieldGender.getText().equals("")){
            labelStatus.setText("Gender is empty");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
       // validate adress placeholder
       if (textFieldAdress.getText().equals("")){
            labelStatus.setText("Adress is empty");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
       // check if postcode is in the right format
       if (helpers.Filters.filteredPostcode(textFieldPostcode.getText()).equals("")){
            labelStatus.setText("Postcode not valid");
            this.resetLabel(5000, labelStatus);
            return false;    
        }
       return true;
    }
    private void addCustomer(){
        if (checkInput()) {

            String queryInsertCustomer = "INSERT INTO `luggagecontroldata`.`customer`"
                    + "(`firstname`, `surname`, `email`, `cellphone`, `birthday`, `gender`, `adress`, `postcode`)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            String queryInsertLuggage = "INSERT INTO `luggagecontroldata`.`customer_luggage`"
                    + "(`customer_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String queryInsertFlight = "INSERT INTO `luggagecontroldata`.`luggage_flight`"
                    + "(`customer_id`,`flight_id`)  "
                    + "VALUES(?,?)";
            String querySearchCustomer = "SELECT customer_id FROM customer WHERE "
                    + "firstname = ? AND surname = ? AND email = ? AND cellphone = ? "
                    + "AND birthday = ? AND gender = ? AND adress = ? AND postcode = ?;";
            
            
            String[] values = new String[8];
            String[] types = new String[8];
            
            String[] values2 = new String[2];
            String[] types2 = new String[2];
            
            String[] values3 = new String[2];
            String[] types3 = new String[2];

            values[0] = textFieldName.getText();
            values[1] = textFieldSurName.getText();
            values[2] = textFieldEmail.getText();
            values[3] = textFieldCellphoneNumber.getText();
            values[4] = textFieldBirthday.getText();
            values[5] = textFieldGender.getText();
            values[6] = textFieldAdress.getText();
            values[7] = textFieldPostcode.getText();
            
            values2[0] = db.queryOneResult(querySearchCustomer, values);
            values2[1] = textFieldLuggageID.getText();
            
            values3[0] = db.queryOneResult(querySearchCustomer, values);
            values3[1] = textFieldFlightnumber.getText();
            
            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "Int";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";
            types[7] = "String";
            
            types2[0] = "Int";
            types2[1] = "Int";
            
            types3[0] = "Int";
            types3[1] = "Int";

            try {
                db.queryManipulation(queryInsertCustomer, values, types);
                if(!textFieldFlightnumber.getText().equals("")){
                    db.queryManipulation(queryInsertFlight, values3, types3);
                }
                if (!textFieldLuggageID.getText().equals("")) {   
                    db.queryManipulation(queryInsertLuggage, values2, types2);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new ErrorJDialog(this.luggageControl, true, "Error: retrieving inserting customer", (new Throwable()).getStackTrace());
            }
            this.clearFields();
            this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
            
        } 
    }

    /**
     *
     */
    public void clearFields() {
        textFieldName.setText("");
        textFieldSurName.setText("");
        textFieldEmail.setText("");
        textFieldCellphoneNumber.setText("");
        textFieldBirthday.setText("");
        textFieldGender.setText("");
        textFieldAdress.setText("");
        textFieldPostcode.setText("");
        labelStatus.setText("");
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
        butonCancel = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        textFieldLuggageID = new javax.swing.JFormattedTextField();
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        labelStatus = new javax.swing.JLabel();
        searchPanes1 = new screen.base.SearchPanes(luggageControl, db);

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

        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAddCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(textFieldSurName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldEmail)
                    .addComponent(textFieldBirthday)
                    .addComponent(textFieldGender)
                    .addComponent(textFieldAdress)
                    .addComponent(textFieldPostcode, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldName)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldLuggageID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldFlightnumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 171, Short.MAX_VALUE))
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelQuickSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchPanes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAddCustomer)
                        .addComponent(labelQuickSearch))
                    .addComponent(buttonHelp))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldLuggageID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(textFieldPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirm)
                            .addComponent(butonCancel)
                            .addComponent(buttonBack))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchPanes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearFields();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP_ADDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        this.clearFields();
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        this.addCustomer();
        this.userNotAFK();
    }//GEN-LAST:event_buttonConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddCustomer;
    private javax.swing.JLabel labelQuickSearch;
    private javax.swing.JLabel labelStatus;
    private screen.base.SearchPanes searchPanes1;
    private javax.swing.JTextField textFieldAdress;
    private javax.swing.JTextField textFieldBirthday;
    private javax.swing.JTextField textFieldCellphoneNumber;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JTextField textFieldGender;
    private javax.swing.JFormattedTextField textFieldLuggageID;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldPostcode;
    private javax.swing.JTextField textFieldSurName;
    // End of variables declaration//GEN-END:variables
}
