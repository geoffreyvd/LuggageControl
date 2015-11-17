package screen;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import main.LuggageControl;

/**
 *
 * @author root
 */
public class AddCustomer extends SwitchingJPanel {

    public AddCustomer(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
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
        textFieldLastName = new javax.swing.JTextField();
        textFieldCellphoneNumber = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        textFieldFlightNumber = new javax.swing.JTextField();
        labelQuickSearch = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        table = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        textFieldQuickSearchFlightNumber = new javax.swing.JTextField();

        labelAddCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelAddCustomer.setText("Add customer");

        textFieldName.setText("Name");
        textFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldNameActionPerformed(evt);
            }
        });

        textFieldLastName.setText("Last name");
        textFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLastNameActionPerformed(evt);
            }
        });

        textFieldCellphoneNumber.setText("Cellphone number");
        textFieldCellphoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCellphoneNumberActionPerformed(evt);
            }
        });

        textFieldEmail.setText("Email");
        textFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldEmailActionPerformed(evt);
            }
        });

        textFieldFlightNumber.setText("Flight number");
        textFieldFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFlightNumberActionPerformed(evt);
            }
        });

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Luggage ID", "Origin", "Location", "Flightnumber"
            }
        ));
        table.setViewportView(tableCustomer);

        textFieldQuickSearchFlightNumber.setText("Flight number");
        textFieldQuickSearchFlightNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldQuickSearchFlightNumberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelAddCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldName)
                    .addComponent(textFieldLastName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldEmail)
                    .addComponent(textFieldFlightNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelQuickSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldQuickSearchFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(76, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelQuickSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldQuickSearchFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBack)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldCellphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldNameActionPerformed

    private void textFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLastNameActionPerformed

    private void textFieldCellphoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCellphoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCellphoneNumberActionPerformed

    private void textFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldEmailActionPerformed

    private void textFieldFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFlightNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFlightNumberActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_EMPLOYEE);
    }//GEN-LAST:event_buttonBackActionPerformed

    private void textFieldQuickSearchFlightNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldQuickSearchFlightNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldQuickSearchFlightNumberActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JLabel labelAddCustomer;
    private javax.swing.JLabel labelQuickSearch;
    private javax.swing.JScrollPane table;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField textFieldCellphoneNumber;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldFlightNumber;
    private javax.swing.JTextField textFieldLastName;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldQuickSearchFlightNumber;
    // End of variables declaration//GEN-END:variables
}
