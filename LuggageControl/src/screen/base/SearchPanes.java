package screen.base;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import static helpers.ImageMaker.getImagePath;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Contains all the classes to implement tabbed search pane mutations
 * and the tabbed <code>searchpane</code> itself.
 * @author Corne Lukken
 */
public class SearchPanes extends javax.swing.JTabbedPane {
    
    public static final String IMAGE = "panelImage";
    public static final String SEARCH_CUSTOMER = "panelSearchCustomer";
    public static final String SEARCH_FLIGHT = "panelSearchFlight";
    public static final String SEARCH_LUGGAGE = "panelSearchLuggage";
    public static final String SEARCH_USER = "panelSearchUser";
    
    private ResultSet customerResults;
    private ResultSet flightResults;
    private ResultSet luggageResults;
    private ResultSet userResults;
    
    private String image;
    private final DatabaseMan db;
    private LuggageControl luggageControl;
    
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
    
    // comboboxes for inclusive, excusive and loose searching
    private javax.swing.JComboBox comboBoxCustomerSearchType;
    private javax.swing.JComboBox comboBoxFlightSearchType;
    private javax.swing.JComboBox comboBoxLuggageSearchType;
    private javax.swing.JComboBox comboBoxUserSearchType;
    
    private javax.swing.JLabel labelSearchTypeCustomer;
    private javax.swing.JLabel labelSearchTypeFlight;
    private javax.swing.JLabel labelSearchTypeLuggage;
    private javax.swing.JLabel labelSearchTypeUser;
    
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchFlight;
    private javax.swing.JPanel panelSearchLuggage;
    private javax.swing.JPanel panelSearchUser;
    private javax.swing.JPanel panelImage;
    
    private javax.swing.JTable tableSearchFlight;
    private javax.swing.JTable tableSearchLuggage;
    private javax.swing.JTable tableSearchUser;
    private javax.swing.JTable tableSearchCustomer;
    
    private javax.swing.JLabel labelImageContainer;
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
    
    private javax.swing.JFormattedTextField textFieldLuggageOwnerId;
    private javax.swing.JFormattedTextField textFieldLuggageFlightId;
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
    
    private javax.swing.JScrollPane scrollPaneLuggageDescription;
    private javax.swing.JScrollPane scrollPaneCustomerTable;
    private javax.swing.JScrollPane scrollPaneFlightTable;
    private javax.swing.JScrollPane scrollPaneLuggageTable;
    private javax.swing.JScrollPane scrollPaneUserTable;
    //</editor-fold>
    
    public SearchPanes(LuggageControl luggageConrol, DatabaseMan db) {
        this.db = db;
        initComponents();
        setLabelPrompts();
    }
    
    // java beans must have constructor with no parameters
    public SearchPanes() {
        this.db = new DatabaseMan();
    }
    
    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents() {
        // panels
        panelImage = new javax.swing.JPanel();
        panelSearchCustomer = new javax.swing.JPanel();
        panelSearchFlight = new javax.swing.JPanel();
        panelSearchLuggage = new javax.swing.JPanel();
        panelSearchUser = new javax.swing.JPanel();
        
        // search types
        comboBoxCustomerSearchType = new javax.swing.JComboBox();
        comboBoxFlightSearchType = new javax.swing.JComboBox();
        comboBoxLuggageSearchType = new javax.swing.JComboBox();
        comboBoxUserSearchType = new javax.swing.JComboBox();
        
        // search text label
        labelSearchTypeCustomer = new javax.swing.JLabel();
        labelSearchTypeFlight = new javax.swing.JLabel();
        labelSearchTypeLuggage = new javax.swing.JLabel();
        labelSearchTypeUser = new javax.swing.JLabel();
        
        // image panel
        buttonUploadImage = new javax.swing.JButton();
        labelImageContainer = new javax.swing.JLabel();
        labelImageContainer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelImageContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelImageContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        // customer panel
        buttonSearchCustomer = new javax.swing.JButton();
        comboBoxCustomerGender = new javax.swing.JComboBox();
        scrollPaneCustomerTable = new javax.swing.JScrollPane();
        tableSearchCustomer = new javax.swing.JTable();
        textFieldCustomerAdress = new javax.swing.JFormattedTextField();
        textFieldCustomerBirthday = new javax.swing.JFormattedTextField();
        textFieldCustomerCellphone = new javax.swing.JFormattedTextField();
        textFieldCustomerEmail = new javax.swing.JFormattedTextField();
        textFieldCustomerFirstname = new javax.swing.JFormattedTextField();
        textFieldCustomerId = new javax.swing.JFormattedTextField();
        textFieldCustomerPostcode = new javax.swing.JFormattedTextField();
        textFieldCustomerSurname = new javax.swing.JFormattedTextField();
        
        // flight panel
        buttonSearchFlight = new javax.swing.JButton();
        scrollPaneFlightTable = new javax.swing.JScrollPane();
        tableSearchFlight = new javax.swing.JTable();
        textFieldFlightArrival = new javax.swing.JFormattedTextField();
        textFieldFlightDeparture = new javax.swing.JFormattedTextField();
        textFieldFlightDestination = new javax.swing.JFormattedTextField();
        textFieldFlightId = new javax.swing.JFormattedTextField();
        textFieldFlightOrigin = new javax.swing.JFormattedTextField();

        // luggage panel
        buttonSearchLuggage = new javax.swing.JButton();
        comboBoxLuggageSize = new javax.swing.JComboBox();
        comboBoxLuggageStatus = new javax.swing.JComboBox();
        labelLuggageDescription = new javax.swing.JLabel();
        scrollPaneLuggageDescription = new javax.swing.JScrollPane();
        scrollPaneLuggageTable = new javax.swing.JScrollPane();
        tableSearchLuggage = new javax.swing.JTable();
        textFieldLuggageColor = new javax.swing.JFormattedTextField();
        textFieldLuggageLocation = new javax.swing.JFormattedTextField();
        textFieldLuggageId = new javax.swing.JFormattedTextField();
        textFieldLuggageOwnerId = new javax.swing.JFormattedTextField();
        textFieldLuggageFlightId = new javax.swing.JFormattedTextField();
        textFieldLuggageWeight = new javax.swing.JFormattedTextField();
        textPaneLuggageDescription = new javax.swing.JTextPane();       
     
        // user panel
        buttonSearchUser = new javax.swing.JButton();
        comboBoxUserGender = new javax.swing.JComboBox();     
        scrollPaneUserTable = new javax.swing.JScrollPane();
        tableSearchUser = new javax.swing.JTable();
        textFieldUserAdress = new javax.swing.JFormattedTextField();
        textFieldUserBirthday = new javax.swing.JFormattedTextField();
        textFieldUserCellphone = new javax.swing.JFormattedTextField();
        textFieldUserFirstName = new javax.swing.JFormattedTextField();
        textFieldUserId = new javax.swing.JFormattedTextField();
        textFieldUserName = new javax.swing.JFormattedTextField();
        textFieldUserNationality = new javax.swing.JFormattedTextField();
        textFieldUserPostcode = new javax.swing.JFormattedTextField();
        textFieldUserSurName = new javax.swing.JFormattedTextField();
        
        DefaultComboBoxModel<String> searchTypeModel = new javax.swing.DefaultComboBoxModel(new String[] { "Inclusive", "Exclusive", "Loose" });
        comboBoxCustomerSearchType.setModel(searchTypeModel);
        comboBoxFlightSearchType.setModel(searchTypeModel);
        comboBoxLuggageSearchType.setModel(searchTypeModel);
        comboBoxUserSearchType.setModel(searchTypeModel);
        
        comboBoxCustomerSearchType.setMaximumSize(new java.awt.Dimension(150, 150));
        comboBoxFlightSearchType.setMaximumSize(new java.awt.Dimension(150, 150));
        comboBoxLuggageSearchType.setMaximumSize(new java.awt.Dimension(150, 150));
        comboBoxUserSearchType.setMaximumSize(new java.awt.Dimension(150, 150));
        
        labelSearchTypeCustomer.setText("Search type:");
        labelSearchTypeFlight.setText("Search type:");
        labelSearchTypeLuggage.setText("Search type:");
        labelSearchTypeUser.setText("Search type:");
        
        buttonUploadImage.setText("Uploadimage");
        buttonUploadImage.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setImage();
            }
        });

        javax.swing.GroupLayout panelUploadImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelUploadImageLayout);
        panelUploadImageLayout.setHorizontalGroup(panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonUploadImage, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(labelImageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUploadImageLayout.setVerticalGroup(panelUploadImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
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

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchCustomer.setColumnSelectionAllowed(true);
        tableSearchCustomer.setFocusable(false);
        tableSearchCustomer.getTableHeader().setReorderingAllowed(false);
        scrollPaneCustomerTable.setViewportView(tableSearchCustomer);
        tableSearchCustomer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxCustomerGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender", "male", "female", "androgenous" }));
        comboBoxCustomerGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerSurname.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldCustomerId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchCustomer.setText("Search");
        buttonSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomer();
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
        panelSearchCustomerLayout.setHorizontalGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(labelSearchTypeCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxCustomerSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        panelSearchCustomerLayout.setVerticalGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchTypeCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxCustomerSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        tableSearchFlight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight number", "Origin", "Destination", "Departure", "Arrival"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchFlight.setColumnSelectionAllowed(true);
        tableSearchFlight.setFocusable(false);
        tableSearchFlight.setRowSelectionAllowed(false);
        tableSearchFlight.getTableHeader().setReorderingAllowed(false);
        scrollPaneFlightTable.setViewportView(tableSearchFlight);
        tableSearchFlight.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        textFieldFlightOrigin.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchFlight.setText("Search");
        buttonSearchFlight.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFlight();
            }
        });

        textFieldFlightDestination.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightArrival.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldFlightDeparture.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSearchFlightLayout = new javax.swing.GroupLayout(panelSearchFlight);
        panelSearchFlight.setLayout(panelSearchFlightLayout);
        panelSearchFlightLayout.setHorizontalGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFlightTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelSearchFlightLayout.createSequentialGroup()
                        .addComponent(labelSearchTypeFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxFlightSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        panelSearchFlightLayout.setVerticalGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchFlightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchFlightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchTypeFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxFlightSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        tableSearchLuggage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Luggage ID", "Location", "Color", "Weight", "Size", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchLuggage.setColumnSelectionAllowed(true);
        tableSearchLuggage.setFocusable(false);
        tableSearchLuggage.getTableHeader().setReorderingAllowed(false);
        scrollPaneLuggageTable.setViewportView(tableSearchLuggage);
        tableSearchLuggage.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxLuggageStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found" }));
        comboBoxLuggageStatus.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageLocation.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchLuggage.setText("Search");
        buttonSearchLuggage.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLuggage();
            }
        });

        scrollPaneLuggageDescription.setViewportView(textPaneLuggageDescription);

        comboBoxLuggageSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Size", "Small", "Medium", "Large" }));
        comboBoxLuggageSize.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageWeight.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldLuggageColor.setMaximumSize(new java.awt.Dimension(150, 150));

        labelLuggageDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLuggageDescription.setText("Description: ");

        javax.swing.GroupLayout panelSearchLuggageLayout = new javax.swing.GroupLayout(panelSearchLuggage);
        panelSearchLuggage.setLayout(panelSearchLuggageLayout);
        panelSearchLuggageLayout.setHorizontalGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(labelSearchTypeLuggage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxLuggageSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageOwnerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(textFieldLuggageFlightId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollPaneLuggageDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(textFieldLuggageLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(comboBoxLuggageStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxLuggageSize,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addComponent(textFieldLuggageWeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(textFieldLuggageColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSearchLuggageLayout.createSequentialGroup()
                        .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchLuggage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLuggageDescription))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchLuggageLayout.setVerticalGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchLuggageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchTypeLuggage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxLuggageSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLuggageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLuggageOwnerId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLuggageFlightId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(scrollPaneLuggageDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneLuggageTable, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearchLuggage)
                .addGap(12, 12, 12))
        );

        this.addTab("Luggage", panelSearchLuggage);

        tableSearchUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Firstname", "Surname", "Email", "Cellphone", "Birthday", "Gender", "Nationality", "Adress", "Postcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSearchUser.setColumnSelectionAllowed(true);
        tableSearchUser.setFocusable(false);
        tableSearchUser.getTableHeader().setReorderingAllowed(false);
        scrollPaneUserTable.setViewportView(tableSearchUser);
        tableSearchUser.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        comboBoxUserGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender", "Male", "Female", "Androgenous" }));
        comboBoxUserGender.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserName.setMaximumSize(new java.awt.Dimension(150, 150));

        textFieldUserId.setMaximumSize(new java.awt.Dimension(150, 150));

        buttonSearchUser.setText("Search");
        buttonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUser();
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
        panelSearchUserLayout.setHorizontalGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneUserTable, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(panelSearchUserLayout.createSequentialGroup()
                        .addComponent(labelSearchTypeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(comboBoxUserSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        panelSearchUserLayout.setVerticalGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchTypeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxUserSearchType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    
    //<editor-fold defaultstate="collapsed" desc="labelPrompts">
    private void setLabelPrompts() {
        // customer label prompts
        PromptSupport.setPrompt("Adress", textFieldCustomerAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerAdress);
        PromptSupport.setPrompt("Birthday [yyyy-mm-dd]", textFieldCustomerBirthday);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerBirthday);
        PromptSupport.setPrompt("Cellphone", textFieldCustomerCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerCellphone);
        PromptSupport.setPrompt("Email", textFieldCustomerEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerEmail);
        PromptSupport.setPrompt("Firstname", textFieldCustomerFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerFirstname);
        PromptSupport.setPrompt("Customer ID", textFieldCustomerId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerId);
        PromptSupport.setPrompt("Postcode", textFieldCustomerPostcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerPostcode);
        PromptSupport.setPrompt("Surname", textFieldCustomerSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldCustomerSurname);
        
        // flight label prompts
        PromptSupport.setPrompt("Arrival [yyyy-mm-dd hh-mm-ss]", textFieldFlightArrival);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightArrival);
        PromptSupport.setPrompt("Departure [yyyy-mm-dd hh-mm-ss]", textFieldFlightDeparture);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightDeparture);
        PromptSupport.setPrompt("Destination", textFieldFlightDestination);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightDestination);
        PromptSupport.setPrompt("Flight number", textFieldFlightId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightId);
        PromptSupport.setPrompt("Origin", textFieldFlightOrigin);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightOrigin);
        
        // luggage label prompts
        PromptSupport.setPrompt("FlightNumber", textFieldLuggageFlightId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageFlightId);
        PromptSupport.setPrompt("Owner ID", textFieldLuggageOwnerId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageOwnerId);
        PromptSupport.setPrompt("Color", textFieldLuggageColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageColor);
        PromptSupport.setPrompt("Luggage ID", textFieldLuggageId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageId);
        PromptSupport.setPrompt("Location", textFieldLuggageLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageLocation);
        PromptSupport.setPrompt("Weight", textFieldLuggageWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageWeight);
        
        // user label prompts
        PromptSupport.setPrompt("Adress", textFieldUserAdress);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserAdress);
        PromptSupport.setPrompt("Birthday [yyyy-mm-dd]", textFieldUserBirthday);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserBirthday);
        PromptSupport.setPrompt("Cellphone", textFieldUserCellphone);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserCellphone);
        PromptSupport.setPrompt("Firstname", textFieldUserFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserFirstName);
        PromptSupport.setPrompt("User ID", textFieldUserId);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserId);
        PromptSupport.setPrompt("Username", textFieldUserName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserName);
        PromptSupport.setPrompt("Nationality", textFieldUserNationality);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserNationality);
        PromptSupport.setPrompt("Postcode", textFieldUserPostcode);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserPostcode);
        PromptSupport.setPrompt("Surname", textFieldUserSurName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUserSurName); 
    }
    //</editor-fold>
    
    /**
     * 
     * @param originalBox
     * @param columnName
     * @param textField
     * @param values
     * @return 
     */
    private String checkCombobox(JComboBox originalBox, String columnName, JFormattedTextField textField,  ArrayList<String> values) {
        switch (originalBox.getSelectedItem().toString()) {
            case "Inclusive":
                values.add(helpers.Filters.filteredString(textField.getText()));
                return " AND " + columnName + " = ? ";
            case "Loose":
                values.add(helpers.Filters.filteredString("%" + textField.getText() + "%"));
                return " OR " + columnName + " LIKE ? ";
            default:
                values.add(helpers.Filters.filteredString(textField.getText()));
                return " OR " + columnName + " = ? ";
        }
    }
    
    /**
     * 
     * @param originalBox
     * @param columnName
     * @param comboBox
     * @param values
     * @return 
     */
    private String checkCombobox(JComboBox originalBox, String columnName, JComboBox comboBox,  ArrayList<String> values) {
        switch (originalBox.getSelectedItem().toString()) {
            case "Inclusive":
                values.add(helpers.Filters.filteredString(comboBox.getSelectedItem().toString()));
                return " AND " + columnName + " = ? ";
            case "Loose":
                values.add(helpers.Filters.filteredString("%" + comboBox.getSelectedItem().toString() + "%"));
                return " OR " + columnName + " LIKE ? ";
            default:
                values.add(helpers.Filters.filteredString(comboBox.getSelectedItem().toString()));
                return " OR " + columnName + " = ? ";
        }
    }
    
    /**
     * 
     * @param originalBox
     * @param columnName
     * @param textPane
     * @param values
     * @return 
     */
    private String checkCombobox(JComboBox originalBox, String columnName, JTextPane textPane,  ArrayList<String> values) {
        switch (originalBox.getSelectedItem().toString()) {
            case "Inclusive":
                values.add(helpers.Filters.filteredString(textPane.getText()));
                return " AND " + columnName + " = ? ";
            case "Loose":
                values.add(helpers.Filters.filteredString("%" + textPane.getText() + "%"));
                return " OR " + columnName + " LIKE ? ";
            default:
                values.add(helpers.Filters.filteredString(textPane.getText()));
                return " OR " + columnName + " = ? ";
        }
    }
    
    public void searchCustomer() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT * FROM customer ";
        ArrayList<String> values = new ArrayList<String>();
        
        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldCustomerAdress.getText().equals("") ||
            !textFieldCustomerBirthday.getText().equals("") ||
            !textFieldCustomerCellphone.getText().equals("") ||
            !textFieldCustomerEmail.getText().equals("") ||
            !textFieldCustomerFirstname.getText().equals("") || 
            !textFieldCustomerId.getText().equals("") ||
            !textFieldCustomerPostcode.getText().equals("") ||
            !textFieldCustomerSurname.getText().equals("") || 
            !comboBoxCustomerGender.getSelectedItem().equals("Gender")) {
            if (comboBoxCustomerSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxCustomerSearchType.getSelectedItem().toString().equals("Exclusive")
                    || comboBoxCustomerSearchType.getSelectedItem().toString().equals("Loose")) {
                query += "WHERE 1=0 ";
            }
        }
        
        try {
            if(!textFieldCustomerAdress.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "adress", textFieldCustomerAdress, values);
            }
            
            if(!textFieldCustomerBirthday.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "birthday", textFieldCustomerBirthday, values);
            }
            
            if(!helpers.Filters.filteredCellphone(textFieldCustomerCellphone.getText()).equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "cellphone", textFieldCustomerCellphone, values);
            }
            
            if(!textFieldCustomerEmail.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "email", textFieldCustomerEmail, values);
            } 
            
            if (!comboBoxCustomerGender.getSelectedItem().toString().equals("Gender")) {
                query += checkCombobox(comboBoxCustomerSearchType, "gender", comboBoxCustomerGender, values);
            }
            
            if(!textFieldCustomerId.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "customer_id", textFieldCustomerId, values);
            }
            
            if(!textFieldCustomerPostcode.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "postcode", textFieldCustomerPostcode, values);
            } 
            
            if(!textFieldCustomerFirstname.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "firstname", textFieldCustomerFirstname, values);
            }
            
            if(!textFieldCustomerSurname.getText().equals("")) {
                query += checkCombobox(comboBoxCustomerSearchType, "surname", textFieldCustomerSurname, values);
            }
            
            // If you get a mysql error saying: not unique table/alias look here 
            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
            // You need to create a mysql alias if you select multiple times from the same table!
//            if(!textFieldFlightnumber.getText().equals("")) {
//                query += "UNION SELECT customer.customer_id, firstname, surname, email, cellphone, birthday, gender, adress, postcode ";
//                query += "FROM `customer_flight` INNER JOIN `customer` ON `customer`.`customer_id` ";
//                query += "WHERE `customer_flight`.`flight_id` = ? AND `customer`.`customer_id` = `customer_flight`.`customer_id`";
//                values.add(helpers.Filters.filteredString(textFieldFlightnumber.getText()));
//            }
            
            result = db.query(query + ";", values.toArray(new String[values.size()]));
                        
            DefaultTableModel datamodel = (DefaultTableModel) tableSearchCustomer.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            
            while(result.next()) {

                Object[] data = {
                    result.getString("customer_id"), 
                    result.getString("firstname"), 
                    result.getString("surname"), 
                    result.getString("email"), 
                    result.getString("cellphone"), 
                    result.getString("birthday"), 
                    result.getString("gender"), 
                    result.getString("adress"), 
                    result.getString("postcode")
                };
                
                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableSearchCustomer.setModel(datamodel);
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * DONE
     */
    public void searchFlight() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT `flight`.`flight_id`, origin, destination, departure, arrival FROM flight ";
        ArrayList<String> values = new ArrayList<String>();

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldFlightArrival.getText().equals("") || 
            !textFieldFlightDeparture.getText().equals("") || 
            !textFieldFlightDestination.getText().equals("") || 
            !textFieldFlightId.getText().equals("") ||
            !textFieldFlightOrigin.getText().equals("")) {
            if (comboBoxFlightSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxFlightSearchType.getSelectedItem().toString().equals("Exclusive") || 
                comboBoxFlightSearchType.getSelectedItem().toString().equals("Loose")
            ) {
                query += "WHERE 1=0 ";
            }
        }

        try {
            if (!textFieldFlightArrival.getText().equals("")) {
                query += checkCombobox(comboBoxFlightSearchType, "`arrival`", textFieldFlightArrival, values);
            }

            if (!textFieldFlightDeparture.getText().equals("")) {
                query += checkCombobox(comboBoxFlightSearchType, "departure", textFieldFlightDeparture, values);
            }
            
            if (!textFieldFlightDestination.getText().equals("")) {
                query += checkCombobox(comboBoxFlightSearchType, "destination", textFieldFlightDestination, values);
            }
            
            if (!textFieldFlightId.getText().equals("")) {
                query += checkCombobox(comboBoxFlightSearchType, "flight_id", textFieldFlightId, values);
            }
            
            if (!textFieldFlightOrigin.getText().equals("")) {
                query += checkCombobox(comboBoxFlightSearchType, "origin", textFieldFlightOrigin, values);
            }

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableSearchFlight.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("flight_id"),
                    result.getString("origin"),
                    result.getString("destination"),
                    result.getString("departure"),
                    result.getString("arrival")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableSearchFlight.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * DONE
     */
    public void searchLuggage() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT `luggage`.`luggage_id`, location, color, weight, size, description, `status` FROM luggage ";
        ArrayList<String> values = new ArrayList<String>();

        if (!textFieldLuggageFlightId.getText().equals("") && 
            comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "INNER JOIN `luggage_flight` ON `luggage`.`luggage_id` = `luggage_flight`.`luggage_id` ";
        }
        
        if (!textFieldLuggageOwnerId.getText().equals("") && 
            comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "INNER JOIN `customer_luggage` ON `luggage`.`luggage_id` = `customer_luggage`.`luggage_id` ";
        }

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldLuggageId.getText().equals("") || 
            !textFieldLuggageWeight.getText().equals("") || 
            !textFieldLuggageColor.getText().equals("") || 
            !textPaneLuggageDescription.getText().equals("") ||
            !textFieldLuggageLocation.getText().equals("") || 
            !textFieldLuggageOwnerId.getText().equals("") || 
            !textFieldLuggageFlightId.getText().equals("") ||
            !comboBoxLuggageStatus.getSelectedItem().toString().equals("Status") ||
            !comboBoxLuggageSize.getSelectedItem().toString().equals("Size")) {
            if (comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxLuggageSearchType.getSelectedItem().toString().equals("Exclusive") || 
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Loose")
            ) {
                query += "WHERE 1=0 ";
            }
        }

        try {
            if (!textFieldLuggageId.getText().equals("")) {
                query += checkCombobox(comboBoxLuggageSearchType, "`luggage`.`luggage_id`", textFieldLuggageId, values);
            }

            if (!textFieldLuggageLocation.getText().equals("")) {
                query += checkCombobox(comboBoxLuggageSearchType, "location", textFieldLuggageLocation, values);
            }
            
            if (!textFieldLuggageWeight.getText().equals("")) {
                query += checkCombobox(comboBoxLuggageSearchType, "weight", textFieldLuggageWeight, values);
            }
            
            if (!textFieldLuggageColor.getText().equals("")) {
                query += checkCombobox(comboBoxLuggageSearchType, "color", textFieldLuggageColor, values);
            }
            
            if (!textPaneLuggageDescription.getText().equals("")) {
                query += checkCombobox(comboBoxLuggageSearchType, "description", textPaneLuggageDescription, values);
            }

            if (!comboBoxLuggageStatus.getSelectedItem().toString().equals("Status")) {
                query += checkCombobox(comboBoxLuggageSearchType, "status", comboBoxLuggageStatus, values);
            }
            
            if (!comboBoxLuggageSize.getSelectedItem().toString().equals("Size")) {
                query += checkCombobox(comboBoxLuggageSearchType, "size", comboBoxLuggageSize, values);
            }
            
            if (!textFieldLuggageFlightId.getText().equals("") && 
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += checkCombobox(comboBoxLuggageSearchType, "flight_id", textFieldLuggageFlightId, values);
            }
            
            if (!textFieldLuggageOwnerId.getText().equals("") && 
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += checkCombobox(comboBoxLuggageSearchType, "customer_id", textFieldLuggageOwnerId, values);
            }
            
            // If you get a mysql error saying: not unique table/alias look here 
            // <link>http://stackoverflow.com/questions/19590007/1066-not-unique-table-alias</link>
            // You need to create a mysql alias if you select multiple times from the same table!
              
            if (!textFieldLuggageFlightId.getText().equals("") &&
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Exclusive") || 
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Loose")) {
                query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, description, status ";
                query += "FROM `luggage_flight` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
                query += "`luggage`.`luggage_id` = `luggage_flight`.`luggage_id` ";
                query += "AND flight_id = ?";
                values.add(helpers.Filters.filteredString(textFieldLuggageFlightId.getText()));
            }
            
            if (!textFieldLuggageOwnerId.getText().equals("") &&
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Exclusive") || 
                comboBoxLuggageSearchType.getSelectedItem().toString().equals("Loose")) {
                query += "UNION SELECT `luggage`.`luggage_id`, location, color, weight, size, description, status ";
                query += "FROM `customer_luggage` INNER JOIN `luggage` ON `luggage`.`luggage_id` WHERE ";
                query += "`luggage`.`luggage_id` = `customer_luggage`.`luggage_id` ";
                query += "AND customer_id = ?";
                values.add(helpers.Filters.filteredString(textFieldLuggageOwnerId.getText()));
            }

            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableSearchLuggage.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("luggage_id"),
                    result.getString("location"),
                    result.getString("color"),
                    result.getString("weight"),
                    result.getString("size"),
                    result.getString("description"),
                    result.getString("status")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableSearchLuggage.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    /**
     * DONE
     */
    public void searchUser() {
        ResultSet result = new EmptyResultSet();
        String query = "SELECT `user`.`user_id`, username, email, firstname, surname, cellphone, `birthday`, nationality, adress, postcode, gender FROM user ";
        ArrayList<String> values = new ArrayList<String>();

//        if (!textFieldLuggageFlightId.getText().equals("") && 
//            comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
//                query += "INNER JOIN `luggage_flight` ON `luggage`.`luggage_id` = `luggage_flight`.`luggage_id` ";
//        }
//        
//        if (!textFieldLuggageOwnerId.getText().equals("") && 
//            comboBoxLuggageSearchType.getSelectedItem().toString().equals("Inclusive")) {
//                query += "INNER JOIN `customer_luggage` ON `luggage`.`luggage_id` = `customer_luggage`.`luggage_id` ";
//        }

        // If Some text fields are not empty we add the WHERE clause
        if (!textFieldUserAdress.getText().equals("") || 
            !textFieldUserBirthday.getText().equals("") || 
            !textFieldUserCellphone.getText().equals("") || 
            !textFieldUserFirstName.getText().equals("") ||
            !textFieldUserId.getText().equals("") || 
            !textFieldUserName.getText().equals("") || 
            !textFieldUserNationality.getText().equals("") ||
            !textFieldUserPostcode.getText().equals("") ||
            !textFieldUserSurName.getText().equals("") ||
            !comboBoxUserGender.getSelectedItem().toString().equals("Gender")) {
            if (comboBoxUserSearchType.getSelectedItem().toString().equals("Inclusive")) {
                query += "WHERE 1=1 ";
            }
            if (comboBoxUserSearchType.getSelectedItem().toString().equals("Exclusive") || 
                comboBoxUserSearchType.getSelectedItem().toString().equals("Loose")
            ) {
                query += "WHERE 1=0 ";
            }
        }

        try {
            if (!textFieldUserAdress.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "`adress`", textFieldUserAdress, values);
            }

            if (!textFieldUserBirthday.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "birthday", textFieldUserBirthday, values);
            }
            
            if (!textFieldUserCellphone.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "cellphone", textFieldUserCellphone, values);
            }
            
            if (!textFieldUserFirstName.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "firstname", textFieldUserFirstName, values);
            }
            
            if (!comboBoxUserGender.getSelectedItem().toString().equals("Gender")) {
                query += checkCombobox(comboBoxUserSearchType, "gender", comboBoxUserGender, values);
            }
            
            if (!textFieldUserId.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "user_id", textFieldUserId, values);
            }
            
            if (!textFieldUserNationality.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "nationality", textFieldUserNationality, values);
            }
            
            if (!textFieldUserName.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "username", textFieldUserName, values);
            }
            
            if (!textFieldUserPostcode.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "postcode", textFieldUserPostcode, values);
            }
            
            if (!textFieldUserSurName.getText().equals("")) {
                query += checkCombobox(comboBoxUserSearchType, "surname", textFieldUserSurName, values);
            }
            
            result = db.query(query + ";", values.toArray(new String[values.size()]));

            DefaultTableModel datamodel = (DefaultTableModel) tableSearchUser.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while (result.next()) {

                Object[] data = {
                    result.getString("user_id"),
                    result.getString("username"),
                    result.getString("firstname"),
                    result.getString("surname"),
                    result.getString("email"),
                    result.getString("cellphone"),
                    result.getString("birthday"),
                    result.getString("gender"),
                    result.getString("nationality"),
                    result.getString("adress"),
                    result.getString("postcode")
                };

                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableSearchLuggage.setModel(datamodel);
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
    }
    
    public ResultSet getCustomerResults() {
        // THIS IS A PARSE BY REFERENCE ORIGINAL OBJECT WILL BE LEAKED
        return customerResults;
    }
    
    public ResultSet getFlightResults() {
        // THIS IS A PARSE BY REFERENCE ORIGINAL OBJECT WILL BE LEAKED
        return flightResults;
    }
    
    public ResultSet getLuggageResults() {
        // THIS IS A PARSE BY REFERENCE ORIGINAL OBJECT WILL BE LEAKED
        return luggageResults;
    }
    
    public ResultSet getUserResults() {
        // THIS IS A PARSE BY REFERENCE ORIGINAL OBJECT WILL BE LEAKED
        return userResults;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Image related methods">
    public void setImage() {
        String path = getImagePath();
        this.setImage(path, false);
    }
    
    public void setImage(String image, boolean bytes) {
        this.image = image;
        ImageIcon imageIcon;
        
        if(bytes) {
            imageIcon = new ImageIcon(image.getBytes());
        }
        else {
            imageIcon = new ImageIcon(image);
        }
        
        System.out.println(imageIcon);
        if(imageIcon.getIconHeight() > imageIcon.getIconWidth()) {
            labelImageContainer.setIcon(helpers.ImageMaker.resizeImage(-1, labelImageContainer.getHeight(), image));
        }
        else if(imageIcon.getIconHeight() < imageIcon.getIconWidth()) {
            labelImageContainer.setIcon(helpers.ImageMaker.resizeImage(labelImageContainer.getWidth(), -1, image));
        }
        else {
            labelImageContainer.setIcon(helpers.ImageMaker.resizeImage(labelImageContainer.getWidth(), labelImageContainer.getHeight(), image));
        }
        
        labelImageContainer.setHorizontalAlignment(JLabel.CENTER);
        labelImageContainer.setVerticalAlignment(JLabel.CENTER);
    }
    
    
    public String getImage() {
        return image;
    }
    //</editor-fold>
    
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
