package screen.base;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Contains all the classes to implement tabbed search pane mutations
 * and the tabbed <code>searchpane</code> itself.
 * @author Corne Lukken
 */
public class SearchPanes extends JTabbedPane{
    
    public static final String IMAGE = "panelImage";
    public static final String SEARCH_CUSTOMER = "panelSearchCustomer";
    public static final String SEARCH_FLIGHT = "panelSearchFlight";
    public static final String SEARCH_LUGGAGE = "panelSearchLuggage";
    public static final String SEARCH_USER = "panelSearchUser";
    
    private String image;
    
    /* these have been not neccesary so far but I made them as a precaution
        public static final String UPLOAD_IMAGE_NAME = "Upload image";
        public static final String SEARCH_CUSTOMER_NAME = "Customer";
        public static final String SEARCH_FLIGHT_NAME = "Flight";
        public static final String SEARCH_LUGGAGE_NAME = "Luggage";
        public static final String SEARCH_USER_NAME = "User";
    */
    
    // <editor-fold defaultstate="collapsed" desc="Giant list of screen components">
    private javax.swing.JButton buttonSearchCustomer;
    private javax.swing.JButton buttonSearchFlight;
    private javax.swing.JButton buttonSearchLuggage;
    private javax.swing.JButton buttonSearchUser;
    private javax.swing.JButton buttonUploadImage;
    
    private javax.swing.JComboBox comboBoxCustomerGender;
    private javax.swing.JComboBox comboBoxLuggageSize;
    private javax.swing.JComboBox comboBoxLuggageStatus;
    private javax.swing.JComboBox comboBoxUserGender;
    
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchFlight;
    private javax.swing.JPanel panelSearchLuggage;
    private javax.swing.JPanel panelSearchUser;
    private javax.swing.JPanel panelImage;
    
    private javax.swing.JTable tableLugSearchFlight;
    private javax.swing.JTable tableLugSearchLuggage;
    private javax.swing.JTable tableLugSearchLuggage1;
    private javax.swing.JTable tableSearchCustomer;
    
    private javax.swing.JLabel labelLuggageImage;
    private javax.swing.JLabel labelLuggageDescription;
    
    private javax.swing.JFormattedTextField textFieldCustomerAdress;
    private javax.swing.JFormattedTextField textFieldCustomerBirthday;
    private javax.swing.JFormattedTextField textFieldCustomerCellphone;
    private javax.swing.JFormattedTextField textFieldCustomerEmail;
    private javax.swing.JFormattedTextField textFieldCustomerFirstname;
    private javax.swing.JFormattedTextField textFieldCustomerId;
    private javax.swing.JFormattedTextField textFieldCustomerPostcode;
    private javax.swing.JFormattedTextField textFieldCustomerSurname;
    
    private javax.swing.JFormattedTextField textFieldFlightArrival;
    private javax.swing.JFormattedTextField textFieldFlightDeparture;
    private javax.swing.JFormattedTextField textFieldFlightDestination;
    private javax.swing.JFormattedTextField textFieldFlightId;
    private javax.swing.JFormattedTextField textFieldFlightOrigin;
    
    private javax.swing.JFormattedTextField textFieldLuggageColor;
    private javax.swing.JFormattedTextField textFieldLuggageId;
    private javax.swing.JFormattedTextField textFieldLuggageLocation;
    private javax.swing.JFormattedTextField textFieldLuggageWeight;
    
    private javax.swing.JFormattedTextField textFieldUserAdress;
    private javax.swing.JFormattedTextField textFieldUserBirthday;
    private javax.swing.JFormattedTextField textFieldUserCellphone;
    private javax.swing.JFormattedTextField textFieldUserFirstName;
    private javax.swing.JFormattedTextField textFieldUserId;
    private javax.swing.JFormattedTextField textFieldUserName;
    private javax.swing.JFormattedTextField textFieldUserNationality;
    private javax.swing.JFormattedTextField textFieldUserPostcode;
    private javax.swing.JFormattedTextField textFieldUserSurName;
    
    private javax.swing.JTextPane textPaneLuggageDescription;
    
    private javax.swing.JScrollPane scrollPaneContent;
    private javax.swing.JScrollPane scrollPaneCustomerTable;
    private javax.swing.JScrollPane scrollPaneFlightTable;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JScrollPane scrollPaneUserTable;
    //</editor-fold>
    
    public SearchPanes() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents() {
        panelImage = new javax.swing.JPanel();
        panelSearchCustomer = new javax.swing.JPanel();
        
        scrollPaneCustomerTable = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();
        comboBoxCustomerGender = new javax.swing.JComboBox();
        textFieldCustomerSurname = new javax.swing.JFormattedTextField();
        textFieldCustomerId = new javax.swing.JFormattedTextField();
        buttonSearchCustomer = new javax.swing.JButton();
        textFieldCustomerFirstname = new javax.swing.JFormattedTextField();
        textFieldCustomerEmail = new javax.swing.JFormattedTextField();
        textFieldCustomerCellphone = new javax.swing.JFormattedTextField();
        textFieldCustomerBirthday = new javax.swing.JFormattedTextField();
        textFieldCustomerPostcode = new javax.swing.JFormattedTextField();
        textFieldCustomerAdress = new javax.swing.JFormattedTextField();
        panelSearchFlight = new javax.swing.JPanel();
        scrollPaneFlightTable = new javax.swing.JScrollPane();
        tableLugSearchFlight = new javax.swing.JTable();
        textFieldFlightOrigin = new javax.swing.JFormattedTextField();
        textFieldFlightId = new javax.swing.JFormattedTextField();
        buttonSearchFlight = new javax.swing.JButton();
        textFieldFlightDestination = new javax.swing.JFormattedTextField();
        textFieldFlightArrival = new javax.swing.JFormattedTextField();
        textFieldFlightDeparture = new javax.swing.JFormattedTextField();
        panelSearchLuggage = new javax.swing.JPanel();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage = new javax.swing.JTable();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        textFieldLuggageLocation = new javax.swing.JFormattedTextField();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        buttonSearchLuggage = new javax.swing.JButton();
        scrollPaneContent = new javax.swing.JScrollPane();
        textPaneLuggageDescription = new javax.swing.JTextPane();
        comboBoxLuggageSize = new javax.swing.JComboBox();
        textFieldLuggageWeight = new javax.swing.JFormattedTextField();
        textFieldLuggageColor = new javax.swing.JFormattedTextField();
        labelLuggageImage = new javax.swing.JLabel();
        labelLuggageDescription = new javax.swing.JLabel();
        panelSearchUser = new javax.swing.JPanel();
        scrollPaneUserTable = new javax.swing.JScrollPane();
        tableLugSearchLuggage1 = new javax.swing.JTable();
        comboBoxUserGender = new javax.swing.JComboBox();
        
        textFieldUserName = new javax.swing.JFormattedTextField();
        textFieldUserId = new javax.swing.JFormattedTextField();
        
        buttonSearchUser = new javax.swing.JButton();
        buttonUploadImage = new javax.swing.JButton();
        
        textFieldUserFirstName = new javax.swing.JFormattedTextField();
        textFieldUserSurName = new javax.swing.JFormattedTextField();
        textFieldUserCellphone = new javax.swing.JFormattedTextField();
        textFieldUserBirthday = new javax.swing.JFormattedTextField();
        textFieldUserNationality = new javax.swing.JFormattedTextField();
        textFieldUserAdress = new javax.swing.JFormattedTextField();
        textFieldUserPostcode = new javax.swing.JFormattedTextField();
        
        labelLuggageImage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelLuggageImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelLuggageImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonUploadImage.setText("Uploadimage");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUploadImagebutton(evt);
            }

            private void buttonUploadImagebutton(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        javax.swing.GroupLayout panelUploadImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelUploadImageLayout);
        panelUploadImageLayout.setHorizontalGroup(
            panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(labelLuggageImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUploadImageLayout.setVerticalGroup(
            panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLuggageImage, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUploadImage)
                .addContainerGap())
        );
        
        this.addTab("Image", panelImage);

        tableSearchCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Firstname", "Surname", "Email", "Cellphone", "Birthday", "Gender", "Adress", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchCustomer.setColumnSelectionAllowed(true);
        tableSearchCustomer.setFocusable(false);
        tableSearchCustomer.getTableHeader().setReorderingAllowed(false);
        scrollPaneCustomerTable.setViewportView(tableSearchCustomer);
        tableSearchCustomer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxCustomerGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "gender", "male", "female", "androgenous" }));
        comboBoxCustomerGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerSurname.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomer(evt);
            }

            private void buttonSearchCustomer(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        textFieldCustomerFirstname.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerEmail.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerCellphone.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerBirthday.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerPostcode.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerAdress.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchCustomerLayout = new javax.swing.GroupLayout(panelSearchCustomer);
        panelSearchCustomer.setLayout(panelSearchCustomerLayout);
        panelSearchCustomerLayout.setHorizontalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneCustomerTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(buttonSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCustomerFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCustomerCellphone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerPostcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCustomerSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxCustomerGender, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchCustomerLayout.setVerticalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCustomerGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCustomerPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCustomerAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneCustomerTable, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchCustomer)
                .addGap(12, 12, 12))
        );

        this.addTab("Customer", panelSearchCustomer);

        tableLugSearchFlight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight ID", "Origin", "Destination", "Departure", "Arrival"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchFlight.setColumnSelectionAllowed(true);
        tableLugSearchFlight.setFocusable(false);
        tableLugSearchFlight.setRowSelectionAllowed(false);
        tableLugSearchFlight.getTableHeader().setReorderingAllowed(false);
        scrollPaneFlightTable.setViewportView(tableLugSearchFlight);
        tableLugSearchFlight.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        textFieldFlightOrigin.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchFlight.setText("Search");
        buttonSearchFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchFlight(evt);
            }

            private void buttonSearchFlight(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        textFieldFlightDestination.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightArrival.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightDeparture.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchFlightLayout = new javax.swing.GroupLayout(panelSearchFlight);
        panelSearchFlight.setLayout(panelSearchFlightLayout);
        panelSearchFlightLayout.setHorizontalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldFlightId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(276, 276, 276))
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchFlightLayout.setVerticalGroup(
            panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldFlightId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldFlightArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldFlightDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearchFlight)
                .addGap(6, 6, 6))
        );

        this.addTab("Flight", panelSearchFlight);

        tableLugSearchLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Location", "Color", "Wieght", "Size", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchLuggage.setColumnSelectionAllowed(true);
        tableLugSearchLuggage.setFocusable(false);
        tableLugSearchLuggage.getTableHeader().setReorderingAllowed(false);
        scrollPaneLuggageTable.setViewportView(tableLugSearchLuggage);
        tableLugSearchLuggage.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found", "Not returned" }));
        comboBoxLuggageStatus.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageLocation.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchLuggage(evt);
            }

            private void buttonSearchLuggage(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        scrollPaneContent.setViewportView(textPaneLuggageDescription);

        comboBoxLuggageSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Size", "Small", "Medium", "Large" }));
        comboBoxLuggageSize.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageWeight.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageColor.setMaximumSize(new java.awt.Dimension(150, 150));

        labelLuggageDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLuggageDescription.setText("Destination: ");

        javax.swing.GroupLayout panelSearchLuggageLayout = new javax.swing.GroupLayout(panelSearchLuggage);
        panelSearchLuggage.setLayout(panelSearchLuggageLayout);
        panelSearchLuggageLayout.setHorizontalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneContent, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldLuggageLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(comboBoxLuggageStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxLuggageSize, 0, 359, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(textFieldLuggageColor, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLuggageDescription))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchLuggageLayout.setVerticalGroup(
            panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLuggageId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLuggageLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxLuggageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLuggageWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLuggageColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelLuggageDescription)
                .addGap(0, 0, 0)
                .addComponent(scrollPaneContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addGap(12, 12, 12))
        );

        this.addTab("Luggage", panelSearchLuggage);

        tableLugSearchLuggage1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Firstname", "Email", "Cellphone", "Birthday", "Gender", "Nationality", "Adress", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLugSearchLuggage1.setColumnSelectionAllowed(true);
        tableLugSearchLuggage1.setFocusable(false);
        tableLugSearchLuggage1.getTableHeader().setReorderingAllowed(false);
        scrollPaneUserTable.setViewportView(tableLugSearchLuggage1);
        tableLugSearchLuggage1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxUserGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender", "Male", "Female", "Androgenous" }));
        comboBoxUserGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchUser.setText("Search");
        buttonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchUser(evt);
            }

            private void buttonSearchUser(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        textFieldUserFirstName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserSurName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserCellphone.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserBirthday.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserNationality.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserAdress.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserPostcode.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchUserLayout = new javax.swing.GroupLayout(panelSearchUser);
        panelSearchUser.setLayout(panelSearchUserLayout);
        panelSearchUserLayout.setHorizontalGroup(
            panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserSurName, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(textFieldUserCellphone, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUserBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(buttonSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldUserNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldUserAdress, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUserPostcode, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(comboBoxUserGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSearchUserLayout.setVerticalGroup(
            panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserSurName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserCellphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldUserPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldUserNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchUser)
                .addGap(12, 12, 12))
        );
        
        this.addTab("User", panelSearchUser);
        this.setVisible(true);
    }
    //</editor-fold>
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getImage() {
        return image;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Tab related methods">
    /**
     * Add and appropriately name a tab, <U>does not check if tab already exists!</U>
     * @param panelId static string to identify the tab to be added.
     */
    public void addSearchTab(String panelId) {
        switch(panelId) {
            case IMAGE:
                this.insertTab("Image", null, panelImage, "", this.getTabCount());
                break;
            case SEARCH_CUSTOMER:
                this.insertTab("Customer", null, panelSearchCustomer, "", this.getTabCount());
                break;
            case SEARCH_FLIGHT:
                this.insertTab("Flight", null, panelSearchFlight, "", this.getTabCount());
                break;   
            case SEARCH_LUGGAGE:
                this.insertTab("Luggage", null, panelSearchLuggage, "", this.getTabCount());
                break;
            case SEARCH_USER:
                this.insertTab("User", null, panelSearchUser, "", this.getTabCount());
                break;  
            default:
                System.err.println("Trying to add none existing tab");
                break;
        }
    }
    
    /**
     * Removes the tab from the panel if it exists.
     * @param panelId identifies the tab to remove from the static strings
     */
    public void removeSearchTab(String panelId) {
        switch(panelId) {
            case IMAGE:
                this.remove(panelImage);
                break;
            case SEARCH_CUSTOMER:
                this.remove(panelSearchCustomer);
                break;
            case SEARCH_FLIGHT:
                this.remove(panelSearchFlight);
                break;   
            case SEARCH_LUGGAGE:
                this.remove(panelSearchLuggage);
                break;
            case SEARCH_USER:
                this.remove(panelSearchUser);
                break;  
            default:
                System.err.println("Trying to remove none existing tab");
                break;
        }
    }
    //</editor-fold>
}
