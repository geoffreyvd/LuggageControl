/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;

/**
 *
 * @author root
 */
public class LoginScreen extends SwitchingJPanel {

    private SwitchingJPanel.SJPanelEvents sJPanelEventsInstance;

    /**
     * Creates new form LoginScreen
     */
    public LoginScreen(SwitchingJPanel.SJPanelEvents sJPanelEventsReference) {
        sJPanelEventsInstance = sJPanelEventsReference;
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

        buttonLogin = new javax.swing.JButton();
        textFieldUsername = new javax.swing.JFormattedTextField();
        textFieldPassword = new javax.swing.JPasswordField();

        buttonLogin.setText("Log in");
        buttonLogin.setToolTipText("");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        textFieldUsername.setText("Username");
        textFieldUsername.setName(""); // NOI18N
        textFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsernameActionPerformed(evt);
            }
        });

        textFieldPassword.setText("Password");
        textFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(textFieldUsername))
                    .addComponent(buttonLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonLogin)
                .addContainerGap(138, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        String inputUsername = textFieldUsername.getText();
        String inputPassword = textFieldPassword.getText();
        sJPanelEventsInstance.loginUser(inputUsername, inputPassword);
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void textFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameActionPerformed

    private void textFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldPasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JPasswordField textFieldPassword;
    private javax.swing.JFormattedTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
