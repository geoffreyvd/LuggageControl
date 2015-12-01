package screen.add;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import main.LuggageControl;
import managers.DatabaseMan;
import org.apache.commons.codec.binary.Base64;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * This JPanel adds a luggage into the database
 *
 * @author Konrad
 */
public class AddLuggage extends SwitchingJPanel {

    private String imageBase64;

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
        PromptSupport.setPrompt("OwnerID", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Weight", textFieldWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldWeight);
        PromptSupport.setPrompt("Color", textFieldColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldColor);
        PromptSupport.setPrompt("Size", textFieldSize);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSize);
        PromptSupport.setPrompt("Content", textFieldContent);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldContent);

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
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        textFieldLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldColor = new javax.swing.JFormattedTextField();
        textFieldSize = new javax.swing.JFormattedTextField();
        textFieldWeight = new javax.swing.JFormattedTextField();
        textFieldContent = new javax.swing.JFormattedTextField();
        labelAddLuggage = new javax.swing.JLabel();
        buttonUploadImage = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        butonCancel = new javax.swing.JButton();
        pic = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonBack = new javax.swing.JButton();

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Returned" }));

        labelAddLuggage.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddLuggage.setText("Add luggage");

        buttonUploadImage.setText("Upload image");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadsImageActionPerformed(evt);
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
                buttonCancelActionPerformed(evt);
            }
        });

        pic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAddLuggage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldColor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldLocation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldContent, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(textFieldFlightnumber)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldOwnerID))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(textFieldWeight)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFieldSize)))
                        .addGap(30, 30, 30)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonUploadImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAddLuggage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(textFieldContent, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13)
                        .addComponent(buttonUploadImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonConfirm)
                            .addComponent(butonCancel))))
                .addGap(30, 30, 30))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * pops up a new window which makes the user choose an image and uploads it,
     * then it loads the image onto panels in the label
     *
     * @param evt
     */
    private void buttonUploadsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadsImageActionPerformed
        this.userNotAFK();
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath());
            pic.setIcon(image);

            File file = new File(selectedFile.getAbsolutePath());
            try {
                // Reading a Image file from file system
                FileInputStream imageInFile = new FileInputStream(file);
                byte imageData[] = new byte[(int) file.length()];
                imageInFile.read(imageData);

                // Converting Image byte array into Base64 String
                String imageDataString = encodeImage(imageData);
                imageInFile.close();
                imageBase64 = imageDataString;
                System.out.println(imageDataString);

                System.out.println("Image Successfully Manipulated!");
            } catch (FileNotFoundException e) {
                System.out.println("Image not found" + e);
            } catch (IOException ioe) {
                System.out.println("Exception while reading the Image " + ioe);
            }
        }


    }//GEN-LAST:event_buttonUploadsImageActionPerformed

    /**
     * puts all the strings from the texgtfields into the database
     *
     * @param evt
     */
    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        DatabaseMan db = new DatabaseMan();
        if (!("".equals(textFieldFlightnumber.getText()) || "".equals(textFieldLocation.getText())
                || "".equals(textFieldOwnerID.getText()) || "Status".equals(comboBoxLuggageStatus.getSelectedItem().toString())
                || "".equals(textFieldWeight.getText()) || "".equals(textFieldColor.getText())
                || "".equals(textFieldSize.getText()) || "".equals(textFieldContent.getText()))) {
            
            System.out.println(comboBoxLuggageStatus.getSelectedItem().toString());
            String queryInsertLuggage = "INSERT INTO `luggagecontroldata`.`luggage`"
                    + "(`location`, `color`, `weight`, `size`, `status`, `content`, `image`)  "
                    + "VALUES(?,?,?,?,?,?,?)";
            String queryInsertFlight = "INSERT INTO `luggagecontroldata`.`luggage_flight`"
                    + "(`flight_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String queryInsertCustomer = "INSERT INTO `luggagecontroldata`.`customer_luggage`"
                    + "(`customer_id`, `luggage_id`)  "
                    + "VALUES(?,?)";
            String querySearchLuggage = "SELECT MAX(luggage_id) FROM luggage";

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
            values[5] = textFieldContent.getText();
            values[6] = imageBase64;

            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "String";
            types[4] = "String";
            types[5] = "String";
            types[6] = "String";

            try {
                db.queryManipulation(queryInsertLuggage, values, types);
                System.out.println("hey");
                values2[0] = textFieldFlightnumber.getText();
                values2[1] = db.queryOneResult(querySearchLuggage, luggageID);
                types2[0] = "Int";
                types2[1] = "Int";
                System.out.println("hey2");
                values3[0] = textFieldOwnerID.getText();
                System.out.println("hey2");
                values3[1] = db.queryOneResult(querySearchLuggage, luggageID);
                System.out.println("yoyo");
                types3[0] = "Int";
                types3[1] = "Int";
                System.out.println("yo");
                db.queryManipulation(queryInsertFlight, values2, types2);
                System.out.println("hey3");
                db.queryManipulation(queryInsertCustomer, values3, types3);
            } catch (Exception e) {
                System.out.println("hey3");
            }
            textFieldFlightnumber.setText("");
            textFieldLocation.setText("");
            textFieldOwnerID.setText("");
            comboBoxLuggageStatus.setSelectedIndex(0);
            textFieldWeight.setText("");
            textFieldColor.setText("");
            textFieldSize.setText("");
            textFieldContent.setText("");
            pic.setIcon(null);
            this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
            System.out.println("work");
        } else {
            System.out.println("not work");
        }

        this.userNotAFK();

    }//GEN-LAST:event_buttonConfirmActionPerformed

    /**
     * sets the user as not afk, resets all the text fields and combobox, and
     * changes to panel home screen
     *
     * @param evt
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldWeight.setText("");
        textFieldColor.setText("");
        textFieldSize.setText("");
        textFieldContent.setText("");
        pic.setIcon(null);
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * sets the user as not afk and changes to panel help_adding
     *
     * @param evt
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.ADDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldLocation.setText("");
        textFieldOwnerID.setText("");
        comboBoxLuggageStatus.setSelectedIndex(0);
        textFieldWeight.setText("");
        textFieldColor.setText("");
        textFieldSize.setText("");
        textFieldContent.setText("");
        pic.setIcon(null);
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    /**
     * encodes the image into a base64 string
     *
     * @param imageByteArray
     * @return base64 string
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelAddLuggage;
    private javax.swing.JLabel pic;
    private javax.swing.JFormattedTextField textFieldColor;
    private javax.swing.JFormattedTextField textFieldContent;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldLocation;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    private javax.swing.JFormattedTextField textFieldSize;
    private javax.swing.JFormattedTextField textFieldWeight;
    // End of variables declaration//GEN-END:variables
}
