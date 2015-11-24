/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseClasses;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author root
 */
public class ErrorJDialog extends PopUpJDialog {
    
    private boolean critical = false;

    /**
     * Creates new form NewJDialog
     */
    public ErrorJDialog(java.awt.Frame parent, boolean modal) {
        this(parent, modal, "Error", "");
    }
    
    /**
     * Display a error dialog with a given title and some text.
     * @param title determines the dialog (window) title.
     * @param text text displayed within a textPane to the user.
     */
    public ErrorJDialog(java.awt.Frame parent, boolean modal, String title, String text) {
        super(parent, modal);
        initComponents();
        
        this.setTitle(title);
        
        textPane.setEditable(false);
        textPane.setText(text);
        
        this.haltProgram();
    }
    
    /**
     *  Display a error with a dialog and present the user with a stracktrace.
     * @param title Title of the dialog
     * @param strackTrace array of calls which led to the point the error occured.
     */
    public ErrorJDialog(java.awt.Frame parent, boolean modal, String title, StackTraceElement[] stackTrace) {
        this(parent, modal, title, stackTrace, false);
    }
    
    /**
     *  Display a error with a dialog and present the user with a stracktrace.
     * @param title Title of the dialog
     * @param strackTrace array of calls which led to the point the error occured.
     * @param critcal true if critical error and application should quit.
     */
    public ErrorJDialog(java.awt.Frame parent, boolean modal, String title, StackTraceElement[] strackTrace, boolean critical) {
        super(parent, modal);
        initComponents();
        
        this.critical = critical;
        this.setTitle(title);
        
        String text = ""; 
        for(StackTraceElement stackElement: strackTrace) {
            text += stackElement.toString() + "\r\n";
        }
        textPane.setEditable(false);
        textPane.setText(text);
        
        this.haltProgram();
        if(critical) {
            System.exit(1);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        buttonConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(400, 300));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        textPane.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jScrollPane1.setViewportView(textPane);

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(buttonConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        if(this.critical) {
            System.exit(1);
        }
    }//GEN-LAST:event_buttonConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ErrorJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ErrorJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ErrorJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ErrorJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ErrorJDialog dialog = new ErrorJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
