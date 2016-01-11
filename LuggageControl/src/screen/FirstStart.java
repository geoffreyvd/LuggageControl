package screen;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import main.LuggageControl;
import managers.DatabaseMan;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Screen when the application is started for the first time
 * @author Corne Lukken
 */
public class FirstStart extends SwitchingJPanel {

    private DatabaseMan db = new DatabaseMan();
    
    private SecurityMan secman;
    
    private String userImage = "";
    
    /**
     * Creates new form FirstStart
     * @param luggageControl
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
        this.panelFour.setVisible(false);
    }
    
    private void createUser() {
        // check if all fields are entered
        // we user or so we dont have to check all conditions every time
        if( textFieldAdress.getText().equals("") || 
            textFieldBirthday.getText().equals("") || 
            textFieldCellphone.getText().equals("") || 
            textFieldEmail.getText().equals("") || 
            textFieldFirstname.getText().equals("") ||
            textFieldSurname.getText().equals("") ||
            textFieldNationality.getText().equals("") ||
            textFieldPassword.getText().equals("") ||   
            textFieldPostalcode.getText().equals("") || 
            textFieldUsername.getText().equals("") ) 
        {
            labelUserStatus.setText("Not all fields are entered!");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if the username does not yet exist
        if(!db.queryOneResult("SELECT `username` FROM user WHERE username = ?", new String[]{textFieldUsername.getText()}).equals("")) {
            labelUserStatus.setText("Username already taken!");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if the email does not yet exist
        if(!db.queryOneResult("SELECT `email` FROM user WHERE email = ?", new String[]{textFieldEmail.getText()}).equals("")) {
            labelUserStatus.setText("Email adress already taken!");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if our cellphone only contains numbers
        if(helpers.Filters.filteredCellphone(textFieldCellphone.getText()).equals("")) {
            labelUserStatus.setText("Invalid characters in cellphone, can only contain numbers");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if our birthday is a date and in the past
        if(helpers.Filters.filteredDate(textFieldBirthday.getText(), "yyyy-MM-dd").equals("")) {
            labelUserStatus.setText("Birthday needs to be valid date in the past");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if our email is valid
        if(helpers.Filters.filteredEmail(textFieldEmail.getText()).equals("")) {
            labelUserStatus.setText("Needs to be valid email adress");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        // check if we have a user image
        if(userImage.equals("")) {
            labelUserStatus.setText("No image was uploaded");
            this.resetLabel(5000, labelUserStatus);
            return;
        }
        
        String salt = "";
        String password = "";
        
        try {
            salt = secman.createSalt();
            password = secman.encodePassword(textFieldPassword.getText(), salt);
        } 
        catch (NoSuchAlgorithmException e) {
            new ErrorJDialog(this.luggageControl, true, "Required algorithm does not exists", e.getStackTrace());
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }      
        
        String[] userData = {
            textFieldUsername.getText(), 
            password, 
            salt,
            textFieldEmail.getText(), 
            textFieldFirstname.getText(), 
            textFieldSurname.getText(), 
            textFieldCellphone.getText(),
            textFieldBirthday.getText(),
            comboBoxGender.getSelectedItem().toString(),
            textFieldNationality.getText(),
            textFieldAdress.getText(),
            textFieldPostalcode.getText(),
            userImage,
            3 + ""
        };
        
        String[] userTypes = {
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "String",
            "Int",
        };
        
        try {
            db.queryManipulation(
                    "INSERT INTO user "+
                            "(username,password,salt,email,firstname,surname,cellphone,birthday,gender,nationality,adress,postcode,image,permission)" +
                            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    userData, userTypes
            );
        } catch (SQLException ex) {
            labelUserStatus.setText("Something went wrong in the creation fo your user!");
        }
        
        labelUserStatus.setText("User succesfully added");
        this.resetLabel(10000, labelUserStatus);
        
        buttonPThreeNext.setEnabled(true);

        textFieldUsername.setText("");
        textFieldPassword.setText(""); 
        textFieldFirstname.setText(""); 
        textFieldSurname.setText(""); 
        textFieldEmail.setText(""); 
        textFieldCellphone.setText("");
        textFieldBirthday.setText("");
        comboBoxGender.setSelectedIndex(0);
        textFieldNationality.setText("");
        textFieldAdress.setText("");
        textFieldPostalcode.setText("");
        userImage = "";
        userPic.setIcon(null);
    }
    
    private void testUser() {
        try {
            if(Integer.parseInt(db.queryOneResult("SELECT COUNT(*) FROM user;", new String[]{})) > 0) {
                buttonPThreeNext.setEnabled(true);
            }
            else {
                buttonPThreeNext.setEnabled(false);
            }
        }
        catch(Exception e) {
            buttonPThreeNext.setEnabled(false);
        }
    }
    
    private void testDatabase() {
        try {
            if(Integer.parseInt(db.queryOneResult("SELECT COUNT(*) FROM user;", new String[]{})) == 0) {
                labelDatabaseStatus.setText("DatabaseConnection: succes!");
                buttonPTwoNext.setEnabled(true);
            }
            else {
                labelDatabaseStatus.setText("DatabaseConnection: failed!");
                buttonPTwoNext.setEnabled(false);
            }
        }
        catch(Exception e) {
            labelDatabaseStatus.setText("DatabaseConnection: failed!");
            buttonPTwoNext.setEnabled(false);
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
        textFieldUsername = new javax.swing.JFormattedTextField();
        textFieldPassword = new javax.swing.JFormattedTextField();
        labelUserStatus = new javax.swing.JLabel();
        buttonUploadImage = new javax.swing.JButton();
        buttonPthreeCreateUser = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        panelUserImage = new javax.swing.JPanel();
        userPic = new javax.swing.JLabel();
        panelFour = new javax.swing.JPanel();
        labelPtwoHeader1 = new javax.swing.JLabel();
        buttonFinish = new javax.swing.JButton();
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
                        .addGap(0, 1076, Short.MAX_VALUE)
                        .addComponent(buttonPOneNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelPoneHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOneLayout.setVerticalGroup(
            panelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPoneHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPOneNext)
                .addContainerGap())
        );

        buttonPTwoNext.setText("Next");
        buttonPTwoNext.setEnabled(false);
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
        buttonPThreeNext.setEnabled(false);
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

        textFieldUsername.setMaximumSize(new java.awt.Dimension(120, 120));

        textFieldPassword.setMaximumSize(new java.awt.Dimension(120, 120));

        labelUserStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buttonUploadImage.setText("Upload image");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadImageActionPerformed(evt);
            }
        });

        buttonPthreeCreateUser.setText("Create User");
        buttonPthreeCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPthreeCreateUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUserImageLayout = new javax.swing.GroupLayout(panelUserImage);
        panelUserImage.setLayout(panelUserImageLayout);
        panelUserImageLayout.setHorizontalGroup(
            panelUserImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelUserImageLayout.setVerticalGroup(
            panelUserImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelThreeLayout = new javax.swing.GroupLayout(panelThree);
        panelThree.setLayout(panelThreeLayout);
        panelThreeLayout.setHorizontalGroup(
            panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThreeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPthreeCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(labelUserStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(labelPthreeHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addComponent(textFieldFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addComponent(textFieldNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textFieldCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(textFieldBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(comboBoxGender, 0, 0, Short.MAX_VALUE)
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(textFieldAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                        .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThreeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThreeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textFieldPostalcode, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThreeLayout.createSequentialGroup()
                        .addGap(0, 584, Short.MAX_VALUE)
                        .addComponent(buttonPThreeNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelUserImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelThreeLayout.setVerticalGroup(
            panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThreeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThreeLayout.createSequentialGroup()
                        .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThreeLayout.createSequentialGroup()
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
                                .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelUserStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(panelUserImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonPthreeCreateUser)
                            .addComponent(buttonUploadImage, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPThreeNext))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        labelPtwoHeader1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelPtwoHeader1.setText("Initial configuration complete");

        buttonFinish.setText("Finish configuration and start LuggageControl");
        buttonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFourLayout = new javax.swing.GroupLayout(panelFour);
        panelFour.setLayout(panelFourLayout);
        panelFourLayout.setHorizontalGroup(
            panelFourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFourLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonFinish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPtwoHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelFourLayout.setVerticalGroup(
            panelFourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFourLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPtwoHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonFinish, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneSubScreensLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
            .addGroup(layeredPaneSubScreensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneSubScreensLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layeredPaneSubScreens.setLayer(panelOne, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPaneSubScreens.setLayer(panelTwo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPaneSubScreens.setLayer(panelThree, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPaneSubScreens.setLayer(panelFour, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(layeredPaneSubScreens)
                    .addComponent(progressBarConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE))
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
        this.progressBarConfig.setValue(33);
        this.panelOne.setVisible(false);
        this.panelTwo.setVisible(true);
        this.testDatabase();
    }//GEN-LAST:event_buttonPOneNextActionPerformed

    private void buttonPTwoNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPTwoNextActionPerformed
        this.progressBarConfig.setValue(66);
        this.panelTwo.setVisible(false);
        this.panelThree.setVisible(true);
    }//GEN-LAST:event_buttonPTwoNextActionPerformed

    private void buttonPThreeNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPThreeNextActionPerformed
        this.progressBarConfig.setValue(100);
        this.panelThree.setVisible(false);
        this.panelFour.setVisible(true);
    }//GEN-LAST:event_buttonPThreeNextActionPerformed

    private void buttonDatabaseRetryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDatabaseRetryActionPerformed
        this.testDatabase();
    }//GEN-LAST:event_buttonDatabaseRetryActionPerformed

    private void buttonUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUploadImageActionPerformed
        this.userNotAFK();
        System.out.println(String.valueOf(buttonUploadImage.getWidth()) + " here"); // check button width
        String path = helpers.ImageMaker.getImagePath();
        ImageIcon imageIcon = new ImageIcon(path);
        
        if(imageIcon.getIconHeight() > imageIcon.getIconWidth()) {
            userPic.setIcon(helpers.ImageMaker.resizeImage(-1, userPic.getHeight(), path));
        }
        else if(imageIcon.getIconHeight() < imageIcon.getIconWidth()) {
            userPic.setIcon(helpers.ImageMaker.resizeImage(userPic.getWidth(), -1, path));
        }
        else {
            userPic.setIcon(helpers.ImageMaker.resizeImage(userPic.getWidth(), userPic.getHeight(), path));
        }

        userImage = helpers.ImageMaker.encodeImage(path);
    }//GEN-LAST:event_buttonUploadImageActionPerformed

    private void buttonPthreeCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPthreeCreateUserActionPerformed
        this.createUser();
    }//GEN-LAST:event_buttonPthreeCreateUserActionPerformed

    private void buttonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinishActionPerformed
        this.luggageControl.remove(this);
        this.luggageControl.initComponents();
    }//GEN-LAST:event_buttonFinishActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDatabaseRetry;
    private javax.swing.JButton buttonFinish;
    private javax.swing.JButton buttonPOneNext;
    private javax.swing.JButton buttonPThreeNext;
    private javax.swing.JButton buttonPTwoNext;
    private javax.swing.JButton buttonPthreeCreateUser;
    private javax.swing.JButton buttonUploadImage;
    private javax.swing.JComboBox comboBoxGender;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JLabel labelDatabaseStatus;
    private javax.swing.JLabel labelPoneHeader;
    private javax.swing.JLabel labelPthreeHeader;
    private javax.swing.JLabel labelPtwoHeader;
    private javax.swing.JLabel labelPtwoHeader1;
    private javax.swing.JLabel labelUserStatus;
    private javax.swing.JLayeredPane layeredPaneSubScreens;
    private javax.swing.JPanel panelFour;
    private javax.swing.JPanel panelOne;
    private javax.swing.JPanel panelThree;
    private javax.swing.JPanel panelTwo;
    private javax.swing.JPanel panelUserImage;
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
    private javax.swing.JLabel userPic;
    // End of variables declaration//GEN-END:variables
}
