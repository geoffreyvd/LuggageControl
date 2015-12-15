/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.add;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import main.LuggageControl;
import managers.DatabaseMan;
import org.apache.commons.codec.binary.Base64;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Konrad
 */
public class AddLuggage extends SwitchingJPanel {

    private String imageBase64 = "";
    private DatabaseMan db = new DatabaseMan();

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public AddLuggage(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Location", textFieldLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLocation);
        PromptSupport.setPrompt("OwnerID (optional)", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Weight (gram)", textFieldWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldWeight);
        PromptSupport.setPrompt("Color", textFieldColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldColor);
        PromptSupport.setPrompt("Size", textFieldSize);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSize);

    }

    /**
     *
     */
    public void clearFields() {
        textFieldFlightnumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldWeight.setText("");
        textFieldColor.setText("");
        textFieldSize.setText("");
        textPaneContent.setText("");
        pic.setIcon(null);
    }

    /**
     * encodes the image into a base64 string
     *
     * @param imageByteArray
     * @return base64 string
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * check input
     */
    public boolean checkInput() {

        // validate location placeholder
        if (textFieldLocation.getText().equals("")) {
            labelStatus.setText("Location not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if flightnumber is valid and exists
        if (textFieldFlightnumber.getText().equals("")) {
            labelStatus.setText("Flightnumber is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        else if (db.queryOneResult("SELECT `flight_id` FROM flight WHERE flight_id = ?", new String[]{textFieldFlightnumber.getText()}).equals("")) {
            labelStatus.setText("Flightnumber doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if customer exists
        if (!(textFieldOwnerID.getText().equals("")) && db.queryOneResult("SELECT `customer_id` FROM customer WHERE customer_id = ?", new String[]{textFieldOwnerID.getText()}).equals("")) {
            labelStatus.setText("Customer doesn't exist");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // Validate luggage status
        if (!(comboBoxLuggageStatus.getSelectedItem().toString().equals("Lost")
                || comboBoxLuggageStatus.getSelectedItem().toString().equals("Found")
                || comboBoxLuggageStatus.getSelectedItem().toString().equals("Returned"))) {
            labelStatus.setText("Status is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // validate color placeholder
        if (textFieldColor.getText().equals("")) {
            labelStatus.setText("Color is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // validate size placeholder
        if (textFieldSize.getText().equals("")) {
            labelStatus.setText("Size is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }

        // check if luggage weight is a number
        if (helpers.Filters.filteredCellphone(textFieldWeight.getText()).equals("")) {
            labelStatus.setText("Weight is not valid");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        
        // check if content is filled in
        if (textPaneContent.getText().equals("")) {
            labelStatus.setText("Content is empty");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        
        // check if an image is selected
        if (imageBase64.equals("")) {
            labelStatus.setText("Image is not selected");
            this.resetLabel(5000, labelStatus);
            return false;
        }
        return true;
    }

    /**
     * add luggage
     */
    public void addLuggage() {
        if (checkInput()) {
            String queryInsertLuggage = "INSERT INTO `luggagecontroldata`.`luggage`"
                    + "(`location`, `color`, `weight`, `size`, `status`, `content`, `image`)  "
                    + "VALUES(?,?,?,?,?,?,?)";
            String queryInsertFlight = "INSERT INTO `luggagecontroldata`.`luggage_flight`"
                    + "(`flight_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String queryInsertCustomer = "INSERT INTO `luggagecontroldata`.`customer_luggage`"
                    + "(`customer_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String querySearchLuggage = "SELECT luggage_id FROM luggage WHERE "
                    + "location = ? AND color = ? AND weight = ? AND size = ? "
                    + "AND status = ? AND content = ? AND image = ?;";

            String[] luggageID = {};

            String[] values = new String[7];
            String[] types = new String[7];

            String[] values2 = new String[2];
            String[] types2 = new String[2];

            String[] values3 = new String[2];
            String[] types3 = new String[2];

            values[0] = textFieldLocation.getText();
            values[1] = textFieldColor.getText();
            values[2] = textFieldWeight.getText();
            values[3] = textFieldSize.getText();
            values[4] = comboBoxLuggageStatus.getSelectedItem().toString();
            values[5] = textPaneContent.getText();
            values[6] = imageBase64;

            types[0] = "String";
            types[1] = "String";
            types[2] = "Int";
            types[3] = "String";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";

            try {
                db.queryManipulation(queryInsertLuggage, values, types);
                values2[0] = textFieldFlightnumber.getText();
                values2[1] = db.queryOneResult(querySearchLuggage, values);
                types2[0] = "Int";
                types2[1] = "Int";
                db.queryManipulation(queryInsertFlight, values2, types2);
                if (!textFieldOwnerID.getText().equals("")) {
                    values3[0] = textFieldOwnerID.getText();
                    values3[1] = db.queryOneResult(querySearchLuggage, values);
                    types3[0] = "Int";
                    types3[1] = "Int";
                    db.queryManipulation(queryInsertCustomer, values3, types3);
                }
            } catch (SQLException e) {
                System.out.println("get rekt");
            }
            this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);

            this.userNotAFK();
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
        labelAddLuggage = new javax.swing.JLabel();
        textFieldLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldColor = new javax.swing.JFormattedTextField();
        textFieldSize = new javax.swing.JFormattedTextField();
        textFieldWeight = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneContent = new javax.swing.JTextPane();
        buttonUploadImage = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        butonCancel = new javax.swing.JButton();
        pic = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();

        labelAddLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddLuggage.setText("Add luggage");

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Returned" }));

        jScrollPane1.setViewportView(textPaneContent);

        buttonUploadImage.setText("Upload image");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadImagebuttonUploadsImageActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        butonCancel.setText("Cancel");
        butonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelbuttonCancelActionPerformed(evt);
            }
        });

        pic.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pic.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(textFieldSize, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelAddLuggage)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(labelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxLuggageStatus, 0, 267, Short.MAX_VALUE)
                                    .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldOwnerID)
                                    .addComponent(textFieldColor, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
                            .addComponent(textFieldLocation))
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAddLuggage)
                    .addComponent(buttonHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUploadImage)
                    .addComponent(buttonConfirm)
                    .addComponent(butonCancel)
                    .addComponent(buttonBack))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {butonCancel, buttonBack, buttonConfirm, buttonHelp});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonUploadImagebuttonUploadsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadImagebuttonUploadsImageActionPerformed
        this.userNotAFK();
        helpers.ImageMaker.setPath();
        ImageIcon imageIcon = new ImageIcon(helpers.ImageMaker.getPath());

        pic.setIcon(helpers.ImageMaker.resizeImage(pic.getWidth(), pic.getHeight(), helpers.ImageMaker.getPath()));

        imageBase64 = helpers.ImageMaker.base64Encode(helpers.ImageMaker.getPath());

    }//GEN-LAST:event_buttonUploadImagebuttonUploadsImageActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        this.userNotAFK();
        addLuggage();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        clearFields();
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        clearFields();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddLuggage;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel pic;
    private javax.swing.JFormattedTextField textFieldColor;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    private javax.swing.JFormattedTextField textFieldSize;
    private javax.swing.JFormattedTextField textFieldWeight;
    private javax.swing.JTextPane textPaneContent;
    // End of variables declaration//GEN-END:variables
}
