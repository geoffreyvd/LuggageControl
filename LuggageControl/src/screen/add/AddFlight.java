package screen.add;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import main.LuggageControl;
import managers.DatabaseMan;

import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * This JPanel adds a flight into the database
 *
 * @author Konrad
 */
public class AddFlight extends SwitchingJPanel {

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public AddFlight(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flightnumber", textFieldFlightnumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightnumber);
        PromptSupport.setPrompt("Origin", textFieldOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOrigin);
        PromptSupport.setPrompt("Destination", textFieldDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDestination);
        PromptSupport.setPrompt("Departure time", textFieldDepartureTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldDepartureTime);
        PromptSupport.setPrompt("Arival time", textFieldArivalTime);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldArivalTime);

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
        textFieldFlightnumber = new javax.swing.JFormattedTextField();
        textFieldOrigin = new javax.swing.JFormattedTextField();
        textFieldDestination = new javax.swing.JFormattedTextField();
        textFieldDepartureTime = new javax.swing.JFormattedTextField();
        textFieldArivalTime = new javax.swing.JFormattedTextField();
        buttonHelp = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        butonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();

        labelAddCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddCustomer.setText("Add flight");

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
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

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldDestination)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAddCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldOrigin)
                            .addComponent(textFieldFlightnumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldDepartureTime)
                            .addComponent(textFieldArivalTime))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldArivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm)
                    .addComponent(butonCancel))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * sets the user as not afk and changes to panel help_adding
     *
     * @param evt
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.ADDING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    /**
     * puts all the strings from the texgtfields into the database
     *
     * @param evt
     */
    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        DatabaseMan db = new DatabaseMan();
        if (!("".equals(textFieldFlightnumber.getText()) || "".equals(textFieldArivalTime.getText())
                || "".equals(textFieldDepartureTime.getText()) || "".equals(textFieldDestination.getText())
                || "".equals(textFieldOrigin.getText()))) {

            String query = "INSERT INTO `luggagecontroldata`.`flights`"
                    + "(`flight_id`, `origin`, `destination`, `departure`, `arrival`) "
                    + "VALUES(?,?,?,?,?)";

            String[] values = new String[5];
            String[] types = new String[5];

            values[0] = textFieldFlightnumber.getText();
            values[1] = textFieldOrigin.getText();
            values[2] = textFieldDestination.getText();
            values[3] = textFieldDepartureTime.getText();
            values[4] = textFieldArivalTime.getText();

            types[0] = "String";
            types[1] = "String";
            types[2] = "String";
            types[3] = "String";
            types[4] = "String";

            try {

                db.queryManipulation(query, values, types);

            } catch (Exception e) {

            }
            textFieldFlightnumber.setText("");
            textFieldDestination.setText("");
            textFieldOrigin.setText("");
            textFieldDepartureTime.setText("");
            textFieldArivalTime.setText("");
            this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
            System.out.println("work");
        } else {
            System.out.println("not work");
        }
        this.userNotAFK();

    }//GEN-LAST:event_buttonConfirmActionPerformed

    /**
     * sets the user as not afk, resets all the text fields and changes to panel
     * home screen
     *
     * @param evt
     */
    private void butonCancelbuttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelbuttonCancelActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldDestination.setText("");
        textFieldOrigin.setText("");
        textFieldDepartureTime.setText("");
        textFieldArivalTime.setText("");
    }//GEN-LAST:event_butonCancelbuttonCancelActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        textFieldFlightnumber.setText("");
        textFieldDestination.setText("");
        textFieldOrigin.setText("");
        textFieldDepartureTime.setText("");
        textFieldArivalTime.setText("");
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonCancel;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JLabel labelAddCustomer;
    private javax.swing.JFormattedTextField textFieldArivalTime;
    private javax.swing.JFormattedTextField textFieldDepartureTime;
    private javax.swing.JFormattedTextField textFieldDestination;
    private javax.swing.JFormattedTextField textFieldFlightnumber;
    private javax.swing.JFormattedTextField textFieldOrigin;
    // End of variables declaration//GEN-END:variables
}