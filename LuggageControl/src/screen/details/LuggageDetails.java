package screen.details;

import baseClasses.ErrorJDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.LuggageControl;
import managers.SecurityMan;
import org.jdesktop.swingx.prompt.PromptSupport;
import screen.base.BaseDetails;
import screen.base.SearchPanes;

/**
 * This JPanel changes the data from the corresponding luggage into the database
 *
 * @author Admin
 */
public class LuggageDetails extends BaseDetails {

    private int currentLuggageId = 0;

    private String luggageImage = "";

    private SecurityMan sc;

    /**
     * Creates new form AddFlight and sets a prompt on all the textfields
     */
    public LuggageDetails(LuggageControl luggageControl) {
        super(luggageControl);

        initComponents();
        PromptSupport.setPrompt("Location", textFieldUpdateLocation);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateLocation);
        PromptSupport.setPrompt("OwnerID", textFieldOwnerID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldOwnerID);
        PromptSupport.setPrompt("Color", textFieldUpdateColor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateColor);
        PromptSupport.setPrompt("Weight", textFieldUpdateWeight);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldUpdateWeight);
        PromptSupport.setPrompt("Corresponding Luggage ID", textFieldLuggageCorresponding);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldLuggageCorresponding);

        searchPanel.removeSearchTab(SearchPanes.SEARCH_LUGGAGE);
        searchPanel.removeSearchTab(SearchPanes.SEARCH_USER);
    }

    @Override
    public void updatePanelInformation() {
        this.loadLuggage(currentLuggageId);
    }

    @Override
    public void updatePanelInformation(int luggageId) {
        this.loadLuggage(luggageId);
    }

    @Override
    public void clearPanelInformation() {
        this.clearLuggage();
    }

    /**
     * Clears all textfields
     */
    private void clearLuggage() {
        textFieldUpdateLocation.setText("");
        textFieldOwnerID.setText("");
        textFieldUpdateColor.setText("");
        comboBoxUpdateStatus.setSelectedIndex(0);
        comboBoxSize.setSelectedIndex(0);
        textFieldUpdateWeight.setText("");
        textPaneUpdateDescription.setText("");
    }

    /**
     * Prepares the screen with data based on the supplied customer id
     *
     * @param luggageID the luggage id get this from the database
     */
    private void loadLuggage(int luggageID) {
        try {
            ResultSet resultLuggage = db.query("SELECT * FROM luggage WHERE luggage_id = ?", new String[]{luggageID + ""});
            while (resultLuggage.next()) {
                textFieldUpdateLocation.setText(resultLuggage.getString("location"));
                textFieldUpdateColor.setText(resultLuggage.getString("color"));
                textFieldUpdateWeight.setText(resultLuggage.getString("weight"));
                textPaneUpdateDescription.setText(resultLuggage.getString("description"));

                switch (resultLuggage.getString("size")) {
                    case "Small":
                        comboBoxSize.setSelectedIndex(1);
                        break;
                    case "Medium":
                        comboBoxSize.setSelectedIndex(2);
                        break;
                    case "Large":
                        comboBoxSize.setSelectedIndex(3);
                        break;
                    default:
                        System.err.println("invalid result for comboBox size");
                        break;
                }

                switch (resultLuggage.getString("status")) {
                    case "Lost":
                        comboBoxUpdateStatus.setSelectedIndex(1);
                        break;
                    case "Found":
                        comboBoxUpdateStatus.setSelectedIndex(2);
                        break;
                    default:
                        System.err.println("invalid result for comboBox status");
                        break;
                }

                currentLuggageId = Integer.parseInt(resultLuggage.getString("luggage_id"));
            }

            ResultSet resultFlight = db.query("SELECT * FROM luggage_flight WHERE luggage_id = ?", new String[]{luggageID + ""});
            while (resultFlight.next()) {
                labelDisplayFlightnumber.setText(resultFlight.getString("flight_id"));
            }

            ResultSet resultFlightDetails = db.query("SELECT * FROM flight "
                    + "INNER JOIN luggage_flight ON `luggage_flight`.`flight_id` "
                    + "WHERE luggage_id = ? AND `luggage_flight`.`flight_id` = `flight`.`flight_id`", new String[]{luggageID + ""});
            while (resultFlightDetails.next()) {
                labelDisplayOrigin.setText(resultFlightDetails.getString("origin"));
                labelDisplayDestination.setText(resultFlightDetails.getString("destination"));
            }

            // do we have a owner and if so set it
            if (Integer.parseInt(db.queryOneResult("SELECT COUNT(customer_id) FROM customer_luggage WHERE luggage_id = ?;", new String[]{luggageID + ""})) > 0) {
                ResultSet resultOwner = db.query("SELECT customer_id FROM customer_luggage WHERE luggage_id = ?;", new String[]{luggageID + ""});
                while (resultOwner.next()) {
                    textFieldOwnerID.setText(resultOwner.getString("customer_id"));
                }
            } else {
                // we dont have a owner so reset the text field
                textFieldOwnerID.setText("");
            }

            // check for correspondig luggage id
            String query, result, result2;

            query = "SELECT luggage_lost_id FROM luggagecontroldata.luggage_lost_found WHERE luggage_lost_id = ? OR luggage_found_id = ? ;";
            result = db.queryOneResult(query, new String[]{luggageID + "", luggageID + ""});
            if (result != "") {
                if (Integer.parseInt(result) != currentLuggageId) {
                    textFieldLuggageCorresponding.setText(result);
                }
            }

            query = "SELECT luggage_found_id FROM luggagecontroldata.luggage_lost_found WHERE luggage_lost_id = ? OR luggage_found_id = ? ;";
            result2 = db.queryOneResult(query, new String[]{luggageID + "", luggageID + ""});
            if (result2 != "") {
                if (Integer.parseInt(result2) != currentLuggageId) {
                    textFieldLuggageCorresponding.setText(result2);
                }
            }

            if (result == "" && result2 == "") {
                textFieldLuggageCorresponding.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    private void updateLuggage() {
        String query = "UPDATE luggage";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        // If Some text fields are not empty we add the SET clause
        if (!textFieldUpdateLocation.getText().equals("") || !textFieldUpdateColor.getText().equals("")
                || !textFieldUpdateWeight.getText().equals("") || !textPaneUpdateDescription.getText().equals("")) {
            query += " SET ";
        }

        try {

            // check if our email is not taken yet
            if (!textFieldUpdateLocation.getText().equals("")) {
                query += "location = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateLocation.getText()));
                types.add("String");
            } else {
                labelStatus.setText("Email adress already taken!");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // Validate the status
            if (comboBoxUpdateStatus.getSelectedItem().toString().equals("Lost")
                    || comboBoxUpdateStatus.getSelectedItem().toString().equals("Found")
                    || comboBoxUpdateStatus.getSelectedItem().toString().equals("Returned")) {
                query += " status = ?,";
                values.add(helpers.Filters.filteredString(comboBoxUpdateStatus.getSelectedItem().toString()));
                types.add("String");
            } else {
                labelStatus.setText("You used memory manipulation to edit this combobox, close but no cigar.");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // validate postcode placeholder
            if (!textFieldUpdateColor.getText().equals("")) {
                query += " color = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateColor.getText()));
                types.add("String");
            } else {
                labelStatus.setText("Color is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // validate weight placeholder
            if (!textFieldUpdateWeight.getText().equals("")) {
                query += " weight = ?,";
                values.add(helpers.Filters.filteredString(textFieldUpdateWeight.getText()));
                types.add("String");
            } else {
                labelStatus.setText("Weight is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // validate size placeholder
            if (!helpers.Filters.filteredLuggageSize(comboBoxSize.getSelectedItem().toString()).equals("")) {
                query += " size = ?,";
                values.add(helpers.Filters.filteredString(comboBoxSize.getSelectedItem().toString()));
                types.add("String");
            } else {
                labelStatus.setText("Size is not valid");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // validate size placeholder
            if (!textPaneUpdateDescription.getText().equals("")) {
                query += " description = ?,";
                values.add(helpers.Filters.filteredString(textPaneUpdateDescription.getText()));
                types.add("String");
            } else {
                labelStatus.setText("Size is not filled in");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // remove the last , from the string
            query = query.substring(0, query.length() - 1); //????

            // add the where clause to only update current luggage
            query += " WHERE luggage_id = ?";
            values.add(currentLuggageId + "");
            types.add("Int");

            db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));

            query = "";
            values.clear();
            types.clear();

            // check if our customer is not already linked to this flight
            // this is the uglieest if statement I ever made.
            if (!textFieldOwnerID.getText().equals("")) {
                if (db.queryOneResult("SELECT `customer_id` FROM customer WHERE customer_id = ?",
                        new String[]{textFieldOwnerID.getText()})
                        .equals(textFieldOwnerID.getText())) {

                    query = "INSERT INTO customer_luggage (customer_id, luggage_id) VALUES (?, ?)";
                    values.add(helpers.Filters.filteredInt(textFieldOwnerID.getText(), 1, Integer.MAX_VALUE));
                    values.add(helpers.Filters.filteredInt(currentLuggageId + "", 1, Integer.MAX_VALUE));
                    types.add("Int");
                    types.add("Int");
                } else {
                    labelStatus.setText("Customer does not exist");
                    this.resetLabel(5000, labelStatus);
                    return;
                }
            } else if (!helpers.Filters.filteredString(textFieldOwnerID.getText(), new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, true).equals("")) {
                labelStatus.setText("Customer id must be numbers only");
                this.resetLabel(5000, labelStatus);
                return;
            }

            // Only execute this query if it exists
            if (!query.equals("")) {
                db.queryManipulation(query + ";", values.toArray(new String[values.size()]), types.toArray(new String[types.size()]));
            }
            if (!textFieldLuggageCorresponding.getText().equals("")) {
                //first check if there's not already a row with this id in the luggage_lost_found table
                String query2 = "delete from luggage_lost_found where luggage_lost_id = ? or luggage_found_id = ? ;";
                query = "SELECT * FROM luggagecontroldata.luggage_lost_found WHERE luggage_lost_id = ? OR luggage_found_id = ? ;";

                boolean luggageLinkExits = true;
                types = new ArrayList<>();
                values = new ArrayList<>();

                types.add("Int");
                types.add("Int");

                //blijf checken voor overeenkomsten
                while (luggageLinkExits) {
                    luggageLinkExits = false;
                    values = new ArrayList<>();
                    values.add(Integer.toString(currentLuggageId));
                    values.add(Integer.toString(currentLuggageId));

                    String result = db.queryOneResult(query, values.toArray(new String[values.size()]));

                    if (!(result == "" || result == null)) {
                        luggageLinkExits = true;
                        System.out.println("Luggage link met luggage id bestaat al, wordt verwijdert");
                        //verwijder bestaande links
                        db.queryManipulation(query2, values.toArray(new String[values.size()]), types.toArray(new String[values.size()]));
                    }

                    values = new ArrayList<>();
                    values.add(textFieldLuggageCorresponding.getText());
                    values.add(textFieldLuggageCorresponding.getText());

                    result = db.queryOneResult(query, values.toArray(new String[values.size()]));

                    if (!(result == "" || result == null)) {
                        luggageLinkExits = true;
                        System.out.println("Luggage link met opgegeven 'corresponding id' bestaat al, wordt verwijdert");
                        //verwijder bestaande links
                        db.queryManipulation(query2, values.toArray(new String[values.size()]), types.toArray(new String[values.size()]));
                    }
                }

                values = new ArrayList<>();
                values.add(Integer.toString(currentLuggageId));
                values.add(textFieldLuggageCorresponding.getText());

                query = "INSERT INTO `luggagecontroldata`.`luggage_lost_found` (`luggage_lost_id`, `luggage_found_id`) VALUES (?, ?);";
                db.queryManipulation(query, values.toArray(new String[values.size()]), types.toArray(new String[values.size()]));

            }

        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }

        loadLuggage(currentLuggageId);
    }

    private void removeLuggageOwnerLink() {
        try {
            db.queryManipulation("DELETE FROM customer_luggage WHERE luggage_id = ?",
                    new String[]{currentLuggageId + ""}, new String[]{"Int"});
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
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

        labelLuggageDetails = new javax.swing.JLabel();
        labelFlightnumber = new javax.swing.JLabel();
        labelDisplayFlightnumber = new javax.swing.JLabel();
        labelOrigin = new javax.swing.JLabel();
        labelDisplayOrigin = new javax.swing.JLabel();
        labelDestination = new javax.swing.JLabel();
        labelDisplayDestination = new javax.swing.JLabel();
        textFieldUpdateLocation = new javax.swing.JFormattedTextField();
        textFieldOwnerID = new javax.swing.JFormattedTextField();
        comboBoxUpdateStatus = new javax.swing.JComboBox();
        textFieldUpdateColor = new javax.swing.JFormattedTextField();
        textFieldUpdateWeight = new javax.swing.JFormattedTextField();
        separatorCenter = new javax.swing.JSeparator();
        labelSearch = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneUpdateDescription = new javax.swing.JTextPane();
        labelStatus = new javax.swing.JLabel();
        labelDescription = new javax.swing.JLabel();
        searchPanel = new screen.base.SearchPanes(luggageControl, db);
        comboBoxSize = new javax.swing.JComboBox();
        buttonRemoveOwner = new javax.swing.JButton();
        textFieldLuggageCorresponding = new javax.swing.JTextField();

        labelLuggageDetails.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelLuggageDetails.setText("Luggage details");

        labelFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFlightnumber.setText("Flightnumber: ");

        labelDisplayFlightnumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayFlightnumber.setText(" XXXXXXXXX");

        labelOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelOrigin.setText("Origin: ");

        labelDisplayOrigin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayOrigin.setText(" XXXXXXXXX");

        labelDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDestination.setText("Destination: ");

        labelDisplayDestination.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDisplayDestination.setText(" XXXXXXXXX");

        textFieldUpdateLocation.setToolTipText("Location");

        textFieldOwnerID.setToolTipText("Owner id");

        comboBoxUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Status", "Lost", "Found" }));

        textFieldUpdateColor.setToolTipText("Color");

        textFieldUpdateWeight.setToolTipText("Weight");

        labelSearch.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelSearch.setText("Search");

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelActionPerformed(evt);
            }
        });

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        textPaneUpdateDescription.setToolTipText("Description");
        jScrollPane2.setViewportView(textPaneUpdateDescription);

        labelDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDescription.setText("Description:");

        comboBoxSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Size", "Small", "Medium", "Large" }));

        buttonRemoveOwner.setText("Remove");
        buttonRemoveOwner.setToolTipText("Remove the current owner");
        buttonRemoveOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOwnerbuttonRemoveFlight(evt);
            }
        });

        textFieldLuggageCorresponding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldLuggageCorrespondingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxUpdateStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldUpdateColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUpdateWeight)
                            .addComponent(comboBoxSize, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLuggageDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDescription, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDestination)
                                    .addComponent(labelFlightnumber)
                                    .addComponent(labelOrigin))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelDisplayDestination)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelDisplayOrigin)
                                        .addComponent(labelDisplayFlightnumber)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textFieldLuggageCorresponding, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                        .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorCenter)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLuggageDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFlightnumber)
                            .addComponent(labelDisplayFlightnumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelOrigin)
                            .addComponent(labelDisplayOrigin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDestination)
                            .addComponent(labelDisplayDestination)
                            .addComponent(textFieldLuggageCorresponding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldUpdateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldOwnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRemoveOwner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldUpdateColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldUpdateWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescription)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonCancel)
                            .addComponent(buttonBack)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonHelp))
                        .addGap(29, 29, 29)
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param evt
     */
    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        this.userNotAFK();
        updateLuggage();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    /**
     * sets the user as not afk and changes to panel home screen
     *
     * @param evt
     */
    private void butonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCancelActionPerformed
        this.userNotAFK();
        this.loadLuggage(currentLuggageId);
        System.out.println(this.searchPanel.getImage());
    }//GEN-LAST:event_butonCancelActionPerformed

    /**
     * sets the user as not afk and changes to panel searchLuggage
     *
     * @param evt
     */
    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.userNotAFK();
        this.clearLuggage();
        this.luggageControl.switchJPanel(this.luggageControl.SEARCH_LUGGAGE);
    }//GEN-LAST:event_buttonBackActionPerformed

    /**
     * sets the user as not afk and changes to panel help_linking
     *
     * @param evt
     */
    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP_LINKING);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonRemoveOwnerbuttonRemoveFlight(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOwnerbuttonRemoveFlight
        this.userNotAFK();
        try {
            removeLuggageOwnerLink();
        } catch (NullPointerException e) {
            labelStatus.setText("Failed to delete owner");
            this.resetLabel(5000, labelStatus);
            return;
        } catch (Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        loadLuggage(currentLuggageId);
    }//GEN-LAST:event_buttonRemoveOwnerbuttonRemoveFlight

    private void textFieldLuggageCorrespondingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldLuggageCorrespondingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldLuggageCorrespondingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonRemoveOwner;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboBoxSize;
    private javax.swing.JComboBox comboBoxUpdateStatus;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelDestination;
    private javax.swing.JLabel labelDisplayDestination;
    private javax.swing.JLabel labelDisplayFlightnumber;
    private javax.swing.JLabel labelDisplayOrigin;
    private javax.swing.JLabel labelFlightnumber;
    private javax.swing.JLabel labelLuggageDetails;
    private javax.swing.JLabel labelOrigin;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JLabel labelStatus;
    private screen.base.SearchPanes searchPanel;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JTextField textFieldLuggageCorresponding;
    private javax.swing.JFormattedTextField textFieldOwnerID;
    private javax.swing.JFormattedTextField textFieldUpdateColor;
    private javax.swing.JFormattedTextField textFieldUpdateLocation;
    private javax.swing.JFormattedTextField textFieldUpdateWeight;
    private javax.swing.JTextPane textPaneUpdateDescription;
    // End of variables declaration//GEN-END:variables
}
