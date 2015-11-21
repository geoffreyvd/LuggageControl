package screen;

import baseClasses.SwitchingJPanel;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.LuggageControl;
import managers.DatabaseMan;

/**
 *
 * @author Corne Lukken
 */
public class DeleteFlight extends SwitchingJPanel {

    public DeleteFlight(LuggageControl luggageControl) {
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

        labelName = new javax.swing.JLabel();
        textFieldFlightNumber = new javax.swing.JFormattedTextField();
        buttonSearch = new javax.swing.JButton();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableFlights = new javax.swing.JTable();
        buttonBack = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();

        setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N

        labelName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelName.setText("Delete Flight");

        textFieldFlightNumber.setText("flight number");
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

        scrollPaneTable.setPreferredSize(new java.awt.Dimension(1920, 10080));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                            .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack
        this.userNotAFK();
    }//GEN-LAST:event_buttonBack

    private void buttonHelp(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelp
        this.userNotAFK();
    }//GEN-LAST:event_buttonHelp

    private void buttonSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearch
        this.userNotAFK();
        this.fillFlightTable();
    }//GEN-LAST:event_buttonSearch

    private void flightNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_flightNumberKeyPressed
        this.userNotAFK();
        if(evt.getKeyCode() == evt.VK_ENTER) {
            this.fillFlightTable();
        }
    }//GEN-LAST:event_flightNumberKeyPressed

    private void fillFlightTable() {
        DatabaseMan db = new DatabaseMan();
        //String[] types = {db.PS_TYPE_STRING};
        //String[] values = {"danta"};
        String[] values = {};
        try {
            ResultSet result = db.queryPrepared("SELECT * FROM luggagecontroldata.flights;", values);
            DefaultTableModel datamodel = (DefaultTableModel)tableFlights.getModel();
            for(int i = 0; i < datamodel.getRowCount(); i++) {
                datamodel.removeRow(i);
            }
            while(result.next()) {
                System.out.println(result.getString("origin"));
                Object[] data = {result.getString("flight_id"), result.getString("origin"), result.getString("destination"), result.getString("departure"), result.getString("arrival")};
                datamodel.addRow(data);
            }
            tableFlights.setModel(datamodel);
        }
        catch(Exception e) {
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel labelName;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tableFlights;
    private javax.swing.JFormattedTextField textFieldFlightNumber;
    // End of variables declaration//GEN-END:variables

}