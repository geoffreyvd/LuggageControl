package screen;

import baseClasses.EmptyResultSet;
import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * Screen to remove flights from the database
 * @author Corne Lukken
 */
public class DeleteFlight extends SwitchingJPanel {
    
    private DatabaseMan db = new DatabaseMan();

    public DeleteFlight(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
        PromptSupport.setPrompt("Flightnumber", textFieldFlightNumber);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, textFieldFlightNumber);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        textFieldFlightNumber = new javax.swing.JFormattedTextField();
        buttonSearch = new javax.swing.JButton();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableFlights = new javax.swing.JTable();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N

        labelName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelName.setText("Delete Flight");

        textFieldFlightNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                flightNumberKeyPressed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearch(evt);
            }
        });

        scrollPaneTable.setPreferredSize(new java.awt.Dimension(1920, 1080));

        tableFlights.setAutoCreateRowSorter(true);
        tableFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight number", "Origin", "Destination", "Departure", "Arrival", "Remove"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableFlights.setPreferredSize(new java.awt.Dimension(1920, 1080));
        tableFlights.getTableHeader().setReorderingAllowed(false);
        scrollPaneTable.setViewportView(tableFlights);

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBack(evt);
            }
        });

        buttonHelp.setText("Help");
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelp(evt);
            }
        });

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdate(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelName)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(buttonSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldFlightNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdate)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Switches the screen to the <code>HomeScreenAdministrator</code> screen
     * @param evt event with key and component information
     */
    private void buttonBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.HOME_SCREEN_ADMINISTRATOR);
    }//GEN-LAST:event_buttonBack

    /**
     * Opens the help screen about removing data entries
     * @param evt event with key and component information
     */
    private void buttonHelp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelp
        this.userNotAFK();
        this.luggageControl.switchJPanel(ScreenNames.Help.REMOVING);
        this.luggageControl.switchTab(screen.help.Removing.REMOVE_ENTRIES, ScreenNames.Help.REMOVING);
    }//GEN-LAST:event_buttonHelp

    /**
     * Fills the table with the flight database data when the button is pressed.
     * @param evt event with key and component information
     */
    private void buttonSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearch
        this.userNotAFK();
        this.fillFlightTable();
    }//GEN-LAST:event_buttonSearch

    /**
     * Calls <code>fillFlightTable()</code> if the pressed key is enter. 
     * @param evt event with key and component information
     */
    private void flightNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_flightNumberKeyPressed
        this.userNotAFK();
        
        // identify the pressed key
        if(evt.getKeyCode() == evt.VK_ENTER) {
            this.fillFlightTable();
        }
    }//GEN-LAST:event_flightNumberKeyPressed

    /**
     * Loops through the table dataset and removes any rows which are marked for removal.
     * @param evt event with key and component information
     */
    private void buttonUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdate
        DefaultTableModel datamodel = (DefaultTableModel)tableFlights.getModel();
        String query = "DELETE FROM flights WHERE 1=1";
        ArrayList<String> data = new ArrayList();
        ArrayList<String> types = new ArrayList();
        boolean[] idRemove = new boolean[datamodel.getRowCount()]; 
        
        for (int i = datamodel.getRowCount() - 1; i > 0; i--) {
            
            // if this entry equals true - true to remove
            if((boolean)datamodel.getValueAt(i, (datamodel.getColumnCount() - 1))) {
                query += " OR flight_id = ?";
                data.add((String)datamodel.getValueAt(i, 0));
                types.add(db.PS_TYPE_INT);
            }
        }
        
        try {
            db.queryPreparedManipulation(query, data.toArray(new String[data.size()]), data.toArray(new String[data.size()]));
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, "Critical error: my god what have you done!", e.getStackTrace(), true);
        }
        
        for(boolean idrem: idRemove) {
            System.out.println(idrem);
        }
    }//GEN-LAST:event_buttonUpdate

    /**
     * Looks at the text fields and if their filled in, 
     * determines how our database query should look and runs it and finally fills the table with the data.
     */
    private void fillFlightTable() {
        ResultSet result = new EmptyResultSet();
        try {
            if(textFieldFlightNumber.getText().equals("")) {
                String[] values = {};
                result = db.queryPrepared("SELECT * FROM flights;", values);
            }
            else {
                String[] values = {textFieldFlightNumber.getText()};
                result = db.queryPrepared("SELECT * FROM flights WHERE flight_id = ? ;", values);
            }
            DefaultTableModel datamodel = (DefaultTableModel)tableFlights.getModel();
            for (int i = datamodel.getRowCount() - 1; i > -1; i--) {
                datamodel.removeRow(i);
            }
            while(result.next()) {
                System.out.println(result.getString("origin"));
                Object[] data = {result.getString("flight_id"), result.getString("origin"), result.getString("destination"), result.getString("departure"), result.getString("arrival"), false};
                // datamodel.addRow is skipped problaby exception
                datamodel.addRow(data);
            }
            tableFlights.setModel(datamodel);
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, "Error: retrieving flights dataset", (new Throwable()).getStackTrace());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel labelName;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tableFlights;
    private javax.swing.JFormattedTextField textFieldFlightNumber;
    // End of variables declaration//GEN-END:variables

}