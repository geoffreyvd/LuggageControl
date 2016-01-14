package screen.add;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.LuggageControl;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Admin
 */
public class AddUser extends SwitchingJPanel {
    private String imageBase64 = "";
    private SecurityMan secman;

    /**
     *
     * @param luggageControl
     */
    public AddUser(LuggageControl luggageControl) {
        super(luggageControl);
        
        initComponents();
        
        PromptSupport.setPrompt("Adress", textFieldAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldAdress);
        PromptSupport.setPrompt("Birthday(yyyy-mm-dd)", textFieldBirthday);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldBirthday);
        PromptSupport.setPrompt("Cellphone", textFieldCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCellphone);
        PromptSupport.setPrompt("Email", textFieldEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldEmail);
        PromptSupport.setPrompt("First name", textFieldFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFirstname);
        PromptSupport.setPrompt("Sur name", textFieldSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurname);
        PromptSupport.setPrompt("Nationality", textFieldNationality);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldNationality);
        PromptSupport.setPrompt("Postalcode", textFieldPostalcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPostalcode);
        PromptSupport.setPrompt("Username", textFieldUsername);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUsername);
        PromptSupport.setPrompt("Password", textFieldPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPassword);
        PromptSupport.setPrompt("City", textFieldCity);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCity);
        
        this.secman = new SecurityMan(luggageControl);
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
    private boolean checkInput(){
        // validate first name placeholder
        if (textFieldFirstname.getText().equals("")) {
            labelStatus.setText("First name is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate sur name placeholder
        if (textFieldSurname.getText().equals("")) {
            labelStatus.setText("Sur name is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // vallidate gender placeholder
        if (comboBoxGender.getSelectedItem().toString().equals("Gender")) {
            labelStatus.setText("You must enter a gender");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if birthday is a valid date
        if (helpers.Filters.filteredDate(textFieldBirthday.getText(), "yyyy-MM-dd").equals("")){
            labelStatus.setText("Birthday not valid, birthday should be like YYYY-MM-DD");
            this.resetLabel(5000, labelStatus);
            return false; 
        }
        // check if email is in the right format
        if (helpers.Filters.filteredEmail(textFieldEmail.getText()).equals("")) {
            labelStatus.setText("Email not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if cellphone is a number
        if (helpers.Filters.filteredCellphone(textFieldCellphone.getText()).equals("")) {
            labelStatus.setText("Cellphone number is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate nationality placeholder
        if (textFieldNationality.getText().equals("")) {
            labelStatus.setText("Nationality is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate city placeholder
        if (textFieldCity.getText().equals("")) {
            labelStatus.setText("City is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate adress placeholder
        if (textFieldAdress.getText().equals("")) {
            labelStatus.setText("Adress is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate postcode placeholder
        if (textFieldPostalcode.getText().equals("")) {
            labelStatus.setText("Postcode is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate profession placeholder
        if (comboBoxProfession.getSelectedIndex() == 0) {
            labelStatus.setText("You must enter a permission");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate username placeholder
        if (textFieldUsername.getText().equals("")) {
            labelStatus.setText("Username is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // validate password placeholder
        if (textFieldPassword.getText().equals("")) {
            labelStatus.setText("Password is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        // check if image is selected
        if (imageBase64.equals("")) {
            labelStatus.setText("Image is not selected");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        return true;
    }
    private void addCustomer() {
        if (checkInput()){
            
            String salt = "";
            String password = "";
            
            try {
                salt = secman.createSalt();
                password = secman.encodePassword(textFieldPassword.getText(), salt);
            } catch (Exception e) {
                new ErrorJDialog(this.luggageControl, true, "Required algorithm does not exists", e.getStackTrace());
                return;
            }
            
            String queryInsertUser = "INSERT INTO `luggagecontroldata`.`user`"
                   + "(`username`,`password`,`salt`,`email`,`firstname`,`surname`,`cellphone`,`birthday`,`gender`,`nationality`,`adress`,`city`,`postcode`,`image`,`permission`)"
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            String[] values =  new String[15];
            String[] types = new String[15];
            
            values[0] = textFieldUsername.getText();
            values[1] = password;
            values[2] = salt;
            values[3] = textFieldEmail.getText();
            values[4] = textFieldFirstname.getText();
            values[5] = textFieldSurname.getText();
            values[6] = textFieldCellphone.getText();
            values[7] = textFieldBirthday.getText();
            values[8] = comboBoxGender.getSelectedItem().toString();
            values[9] = textFieldNationality.getText();
            values[10] = textFieldAdress.getText();
            values[11] = textFieldCity.getText();
            values[12] = textFieldPostalcode.getText();
            values[13] = imageBase64;
            values[14] = comboBoxProfession.getSelectedIndex() + "";
            
            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "String";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";
            types[7] = "String";
            types[8] = "String";
            types[9] = "String";
            types[10] = "String";
            types[11] = "String";
            types[12] = "String";
            types[13] = "String";
            types[14] = "Int";

            try {
                db.queryManipulation(queryInsertUser,values, types);
            } catch (SQLException e) {
                new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            }
            this.clearFields();
            this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_ADMINISTRATOR);
        }
    }
    private void clearFields(){
        textFieldUsername.setText("");
        textFieldPassword.setText(""); 
        textFieldFirstname.setText(""); 
        textFieldSurname.setText(""); 
        textFieldEmail.setText("");
        textFieldCellphone.setText("");
        textFieldBirthday.setText("");
        textFieldCity.setText("");
        comboBoxGender.setSelectedIndex(0);
        textFieldNationality.setText("");
        textFieldAdress.setText("");
        textFieldPostalcode.setText("");
        comboBoxProfession.setSelectedIndex(0);
        pic.setIcon(null);
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
        comboBoxGender = new javax.swing.JComboBox();
        textFieldFirstname = new javax.swing.JFormattedTextField();
        textFieldBirthday = new javax.swing.JFormattedTextField();
        textFieldSurname = new javax.swing.JFormattedTextField();
        labelAddUser = new javax.swing.JLabel();
        buttonUploadImage = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        textFieldNationality = new javax.swing.JFormattedTextField();
        textFieldAdress = new javax.swing.JFormattedTextField();
        textFieldPostalcode = new javax.swing.JFormattedTextField();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        pic = new javax.swing.JLabel();
        comboBoxProfession = new javax.swing.JComboBox();
        textFieldUsername = new javax.swing.JFormattedTextField();
        textFieldPassword = new javax.swing.JFormattedTextField();
        labelStatus = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        textFieldCity = new javax.swing.JFormattedTextField();

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

        comboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender", "Female", "Male", "Androgenous" }));
        comboBoxGender.setToolTipText("Gender");
        comboBoxGender.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldFirstname.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldBirthday.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldSurname.setMaximumSize(new java.awt.Dimension(120, 120));

        labelAddUser.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddUser.setText("Add user");

        buttonUploadImage.setText("Upload image");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadImageActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        textFieldNationality.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldAdress.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldPostalcode.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(250, 250));

        pic.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboBoxProfession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Profession", "Employee", "Manager", "Administrator" }));
        comboBoxProfession.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldUsername.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldPassword.setMaximumSize(new java.awt.Dimension(120, 120));

        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        textFieldEmail.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldCity.setMaximumSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                            .addComponent(comboBoxProfession, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textFieldAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldPostalcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelAddUser))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonBack, buttonCancel, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonUploadImage))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAddUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFirstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldPostalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonBack)
                            .addComponent(buttonCancel)
                            .addComponent(buttonConfirm))))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonBack, buttonCancel, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearFields();
        this.luggageControl.switchJPanel(this.luggageControl.HOME_SCREEN_ADMINISTRATOR);
        
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadImageActionPerformed
        this.userNotAFK();
        imageBase64 = selectLabelImage(pic);
    }//GEN-LAST:event_buttonUploadImageActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        this.addCustomer();
        this.userNotAFK();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.clearFields();
        this.userNotAFK();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP_ADDING);
    }//GEN-LAST:event_buttonHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxGender;
    private javax.swing.JComboBox comboBoxProfession;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddUser;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel pic;
    private javax.swing.JFormattedTextField textFieldAdress;
    private javax.swing.JFormattedTextField textFieldBirthday;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldCity;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstname;
    private javax.swing.JFormattedTextField textFieldNationality;
    private javax.swing.JFormattedTextField textFieldPassword;
    private javax.swing.JFormattedTextField textFieldPostalcode;
    private javax.swing.JFormattedTextField textFieldSurname;
    private javax.swing.JFormattedTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables

    
}
