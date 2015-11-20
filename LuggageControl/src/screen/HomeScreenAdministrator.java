package screen;

import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import main.LuggageControl;

/**
 *
 * @author user
 */
public class HomeScreenAdministrator extends SwitchingJPanel{

    public HomeScreenAdministrator(LuggageControl luggageControl) {
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

        labelHome = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton();
        buttonChangeSettings = new javax.swing.JButton();
        buttonDeleteCustomer = new javax.swing.JButton();
        buttonDeleteLuggage = new javax.swing.JButton();
        buttonUserManagement = new javax.swing.JButton();
        buttonAddUser = new javax.swing.JButton();
        labelDatabaseManagement = new javax.swing.JLabel();
        buttonDatabaseImport = new javax.swing.JButton();
        buttonDatabaseExport = new javax.swing.JButton();

        labelHome.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHome.setText("Home");

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonChangeSettings.setText("Change settings");
        buttonChangeSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeSettingsActionPerformed(evt);
            }
        });

        buttonDeleteCustomer.setText("Delete costumer");
        buttonDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteCustomerActionPerformed(evt);
            }
        });

        buttonDeleteLuggage.setText("Delete luggage");
        buttonDeleteLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteLuggageActionPerformed(evt);
            }
        });

        buttonUserManagement.setText("User management");
        buttonUserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUserManagementActionPerformed(evt);
            }
        });

        buttonAddUser.setText("Add user");
        buttonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddUserActionPerformed(evt);
            }
        });

        labelDatabaseManagement.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelDatabaseManagement.setText("Database management");

        buttonDatabaseImport.setText("Database import");
        buttonDatabaseImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDatabaseImportActionPerformed(evt);
            }
        });

        buttonDatabaseExport.setText("Database export");
        buttonDatabaseExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDatabaseExportActionPerformed(evt);
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
                        .addComponent(labelHome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelDatabaseManagement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonChangeSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonDeleteLuggage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDeleteCustomer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonUserManagement, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonDatabaseExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDatabaseImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 369, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addGap(18, 18, 18)
                        .addComponent(buttonChangeSettings))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelDatabaseManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDeleteCustomer)
                    .addComponent(buttonDatabaseImport))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDeleteLuggage)
                    .addComponent(buttonDatabaseExport))
                .addGap(18, 18, 18)
                .addComponent(buttonUserManagement)
                .addGap(18, 18, 18)
                .addComponent(buttonAddUser)
                .addContainerGap(150, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonChangeSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeSettingsActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.CHANGE_SETTINGS);
    }//GEN-LAST:event_buttonChangeSettingsActionPerformed

    private void buttonDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteCustomerActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.DELETE_CUSTOMER);
    }//GEN-LAST:event_buttonDeleteCustomerActionPerformed

    private void buttonDeleteLuggageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteLuggageActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.DELETE_LUGGAGE);
    }//GEN-LAST:event_buttonDeleteLuggageActionPerformed

    private void buttonUserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUserManagementActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.USER_MANAGEMENT);
    }//GEN-LAST:event_buttonUserManagementActionPerformed

    private void buttonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddUserActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.ADD_USER);
    }//GEN-LAST:event_buttonAddUserActionPerformed

    private void buttonDatabaseImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDatabaseImportActionPerformed
        this.userNotAFK();
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File databaseImport = fileChooser.getSelectedFile();
            System.out.println(fileChooser.getSelectedFile());
        }   
    }//GEN-LAST:event_buttonDatabaseImportActionPerformed

    private void buttonDatabaseExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDatabaseExportActionPerformed

        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
            FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".sql");
                System.out.println(fileChooser.getSelectedFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }   
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("CMD /C mysqldump luggagecontroldata -uroot -pVjdo1v!! -r " + fileChooser.getSelectedFile() + ".sql");
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }        
        
        

        this.userNotAFK();

    }//GEN-LAST:event_buttonDatabaseExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddUser;
    private javax.swing.JButton buttonChangeSettings;
    private javax.swing.JButton buttonDatabaseExport;
    private javax.swing.JButton buttonDatabaseImport;
    private javax.swing.JButton buttonDeleteCustomer;
    private javax.swing.JButton buttonDeleteLuggage;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonUserManagement;
    private javax.swing.JLabel labelDatabaseManagement;
    private javax.swing.JLabel labelHome;
    // End of variables declaration//GEN-END:variables
}
