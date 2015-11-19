/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.SwitchingJPanel;
import main.LuggageControl;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author root
 */
public class LoginScreen extends SwitchingJPanel {

    public LoginScreen(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Username", textFieldUsername);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUsername);
        PromptSupport.setPrompt("Password", textFieldPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPassword);

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

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panelMouseMoved(evt);
            }
        });

        buttonLogin.setText("Log in");
        buttonLogin.setToolTipText("");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        textFieldUsername.setToolTipText("");
        textFieldUsername.setName(""); // NOI18N
        textFieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldUserKeyPress(evt);
            }
        });

        textFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldPassKeyPress(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(textFieldUsername)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.userNotAFK();
        String inputUsername = textFieldUsername.getText();
        String inputPassword = textFieldPassword.getText();
        this.luggageControl.loginUser(inputUsername, inputPassword);
        inputPassword = null;
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void textFieldUserKeyPress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUserKeyPress
        this.userNotAFK();
        // textFieldPassword set focus
    }//GEN-LAST:event_textFieldUserKeyPress

    private void textFieldPassKeyPress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPassKeyPress
        this.userNotAFK();
        String inputUsername = textFieldUsername.getText();
        String inputPassword = textFieldPassword.getText();
        if(evt.getKeyCode() == evt.VK_ENTER) {
            try {
                this.luggageControl.loginUser(inputUsername, inputPassword);
            }
            catch(Exception e) {
            }
        }
    }//GEN-LAST:event_textFieldPassKeyPress

    private void panelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseMoved
        this.userNotAFK();
    }//GEN-LAST:event_panelMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JPasswordField textFieldPassword;
    private javax.swing.JFormattedTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
