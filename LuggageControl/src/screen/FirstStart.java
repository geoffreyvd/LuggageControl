package screen;

import baseClasses.SwitchingJPanel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Screen when the application is started for the first time
 * @author Corne Lukken
 */
public class FirstStart extends SwitchingJPanel {

    private DatabaseMan db = new DatabaseMan();
    
    private boolean panelOneCanContinue = true;
    private boolean panelTwoCanContinue = false;
    private boolean panelThreeCanContinue = false;
    
    /**
     * Creates new form FirstStart
     */
    public FirstStart(LuggageControl luggageControl) {
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
        PromptSupport.setPrompt("Last name", textFieldSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldSurname);
        PromptSupport.setPrompt("Nationality", textFieldNationality);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldNationality);
        PromptSupport.setPrompt("Postalcode", textFieldPostalcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPostalcode);
        PromptSupport.setPrompt("Username", textFieldUsername);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUsername);
        PromptSupport.setPrompt("Password", textFieldPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldPassword);
        
        this.panelTwo.setVisible(false);
        this.panelThree.setVisible(false);
    }
    
    private void testDatabase() {
        try {
            if(Integer.parseInt(db.queryOneResult("SELECT COUNT(*) FROM user;", new String[]{})) == 0) {
                labelDatabaseStatus.setText("DatabaseConnection: succes!");
                panelTwoCanContinue = true;
            }
            labelDatabaseStatus.setText("DatabaseConnection: failed!");
            panelTwoCanContinue = false;
        }
        catch(Exception e) {
            labelDatabaseStatus.setText("DatabaseConnection: failed!");
            panelTwoCanContinue = false;
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
        jList1 = new javax.swing.JList();
        layeredPaneSubScreens = new javax.swing.JLayeredPane();
        panelOne = new javax.swing.JPanel();
        buttonPOneNext = new javax.swing.JButton();
        labelPoneHeader = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        panelTwo = new javax.swing.JPanel();
        buttonPTwoNext = new javax.swing.JButton();
        labelPtwoHeader = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        buttonDatabaseRetry = new javax.swing.JButton();
        labelDatabaseStatus = new javax.swing.JLabel();
        panelThree = new javax.swing.JPanel();
        buttonPThreeNext = new javax.swing.JButton();
        labelPthreeHeader = new javax.swing.JLabel();
        textFieldFirstname = new javax.swing.JFormattedTextField();
        textFieldSurname = new javax.swing.JFormattedTextField();
        comboBoxGender = new javax.swing.JComboBox();
        textFieldBirthday = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JFormattedTextField();
        textFieldCellphone = new javax.swing.JFormattedTextField();
        textFieldNationality = new javax.swing.JFormattedTextField();
        textFieldAdress = new javax.swing.JFormattedTextField();
        textFieldPostalcode = new javax.swing.JFormattedTextField();
        comboBoxProfession = new javax.swing.JComboBox();
        textFieldUsername = new javax.swing.JFormattedTextField();
        textFieldPassword = new javax.swing.JFormattedTextField();
        progressBarConfig = new javax.swing.JProgressBar();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        buttonPOneNext.setText("Next");
        buttonPOneNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPOneNextActionPerformed(evt);
            }
        });

        labelPoneHeader.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelPoneHeader.setText("Initial configuration");

        jTextPane1.setEditable(false);
        jTextPane1.setText("Welcome administrator to LuggageControl, The following screens will help you trhough a guided setup. After this quick initial configuration you will be able to login and complete the configuration to your desire.");
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout panelOneLayout = new javax.swing.GroupLayout(panelOne);
        panelOne.setLayout(panelOneLayout);
        panelOneLayout.setHorizontalGroup(
            panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelOneLayout.createSequentialGroup()
                        .addGap(0, 535, Short.MAX_VALUE)
                        .addComponent(buttonPOneNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelPoneHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOneLayout.setVerticalGroup(
            panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPoneHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPOneNext)
                .addContainerGap())
        );

        buttonPTwoNext.setText("Next");
        buttonPTwoNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPTwoNextActionPerformed(evt);
            }
        });

        labelPtwoHeader.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelPtwoHeader.setText("Database Configuration");

        jTextPane2.setEditable(false);
        jTextPane2.setText("Please make sure that you have imported the provided mysql database file into your mysql server, upon pressing continue the first administrive user will be created from which the setup can continue.");
        jScrollPane3.setViewportView(jTextPane2);

        buttonDatabaseRetry.setText("Retry");
        buttonDatabaseRetry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDatabaseRetryActionPerformed(evt);
            }
        });

        labelDatabaseStatus.setFont(new java.awt.Font("Ubuntu", 0, 30)); // NOI18N
        labelDatabaseStatus.setText("DatabaseConnection:");

        javax.swing.GroupLayout panelTwoLayout = new javax.swing.GroupLayout(panelTwo);
        panelTwo.setLayout(panelTwoLayout);
        panelTwoLayout.setHorizontalGroup(
            panelTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTwoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(labelPtwoHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTwoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonPTwoNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTwoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelDatabaseStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDatabaseRetry, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTwoLayout.setVerticalGroup(
            panelTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTwoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPtwoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(panelTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDatabaseStatus)
                    .addComponent(buttonDatabaseRetry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(buttonPTwoNext)
                .addContainerGap())
        );

        buttonPThreeNext.setText("Next");
        buttonPThreeNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPThreeNextActionPerformed(evt);
            }
        });

        labelPthreeHeader.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelPthreeHeader.setText("First user configuration");

        textFieldFirstname.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldSurname.setMaximumSize(new java.awt.Dimension(120, 120));

        comboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Female", "Male", "Androgenous" }));
        comboBoxGender.setToolTipText("Gender");
        comboBoxGender.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldBirthday.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldEmail.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldCellphone.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldNationality.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldAdress.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldPostalcode.setMaximumSize(new java.awt.Dimension(250, 250));

        comboBoxProfession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Employee", "Manager", "Administrator" }));
        comboBoxProfession.setMaximumSize(new java.awt.Dimension(250, 250));

        textFieldUsername.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldPassword.setMaximumSize(new java.awt.Dimension(120, 120));

        javax.swing.GroupLayout panelThreeLayout = new javax.swing.GroupLayout(panelThree);
        panelThree.setLayout(panelThreeLayout);
        panelThreeLayout.setHorizontalGroup(
            panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThreeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThreeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonPThreeNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelPthreeHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addComponent(textFieldFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addComponent(textFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addComponent(textFieldAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldPostalcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(comboBoxProfession, 0, 605, Short.MAX_VALUE)
                    .addComponent(textFieldNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelThreeLayout.setVerticalGroup(
            panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThreeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPthreeHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPostalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(buttonPThreeNext)
                .addContainerGap())
        );

        layeredPaneSubScreens.setLayer(panelOne, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPaneSubScreens.setLayer(panelTwo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPaneSubScreens.setLayer(panelThree, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneSubScreensLayout = new javax.swing.GroupLayout(layeredPaneSubScreens);
        layeredPaneSubScreens.setLayout(layeredPaneSubScreensLayout);
        layeredPaneSubScreensLayout.setHorizontalGroup(
            layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneSubScreensLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        layeredPaneSubScreensLayout.setVerticalGroup(
            layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneSubScreensLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneSubScreensLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(layeredPaneSubScreens)
                    .addComponent(progressBarConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(layeredPaneSubScreens)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBarConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPOneNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPOneNextActionPerformed
        if(panelOneCanContinue){
            this.progressBarConfig.setValue(10);
            this.panelOne.setVisible(false);
            this.panelTwo.setVisible(true);
            this.testDatabase();
        }
    }//GEN-LAST:event_buttonPOneNextActionPerformed

    private void buttonPTwoNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPTwoNextActionPerformed
        if(panelTwoCanContinue){
            this.progressBarConfig.setValue(20);
            this.panelTwo.setVisible(false);
            this.panelThree.setVisible(true);
        }
    }//GEN-LAST:event_buttonPTwoNextActionPerformed

    private void buttonPThreeNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPThreeNextActionPerformed
        if(panelThreeCanContinue){
            
        }
    }//GEN-LAST:event_buttonPThreeNextActionPerformed

    private void buttonDatabaseRetryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDatabaseRetryActionPerformed
        this.testDatabase();
    }//GEN-LAST:event_buttonDatabaseRetryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDatabaseRetry;
    private javax.swing.JButton buttonPOneNext;
    private javax.swing.JButton buttonPThreeNext;
    private javax.swing.JButton buttonPTwoNext;
    private javax.swing.JComboBox comboBoxGender;
    private javax.swing.JComboBox comboBoxProfession;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel labelDatabaseStatus;
    private javax.swing.JLabel labelPoneHeader;
    private javax.swing.JLabel labelPthreeHeader;
    private javax.swing.JLabel labelPtwoHeader;
    private javax.swing.JLayeredPane layeredPaneSubScreens;
    private javax.swing.JPanel panelOne;
    private javax.swing.JPanel panelThree;
    private javax.swing.JPanel panelTwo;
    private javax.swing.JProgressBar progressBarConfig;
    private javax.swing.JFormattedTextField textFieldAdress;
    private javax.swing.JFormattedTextField textFieldBirthday;
    private javax.swing.JFormattedTextField textFieldCellphone;
    private javax.swing.JFormattedTextField textFieldEmail;
    private javax.swing.JFormattedTextField textFieldFirstname;
    private javax.swing.JFormattedTextField textFieldNationality;
    private javax.swing.JFormattedTextField textFieldPassword;
    private javax.swing.JFormattedTextField textFieldPostalcode;
    private javax.swing.JFormattedTextField textFieldSurname;
    private javax.swing.JFormattedTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables
}
