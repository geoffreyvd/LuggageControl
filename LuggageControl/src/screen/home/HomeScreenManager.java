/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.home;

import baseClasses.SwitchingJPanel;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.LuggageControl;
import managers.DatabaseMan;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import snippits.PieChart_AWT;

/**
 *
 * @author Admin
 */
public class HomeScreenManager extends SwitchingJPanel {

    DatabaseMan DB = new DatabaseMan();

    /**
     *
     * @param luggageControl
     */
    public HomeScreenManager(LuggageControl luggageControl) {
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
        comboBoxStatistics = new javax.swing.JComboBox();
        buttonExportStatistics = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonChangeSettings = new javax.swing.JButton();
        panelChart = new javax.swing.JPanel();

        labelHome.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelHome.setText("Home");

        comboBoxStatistics.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select statistics type", "Percentage found luggage", "Percentage returned luggage" }));
        comboBoxStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxStatisticsActionPerformed(evt);
            }
        });

        buttonExportStatistics.setText("Export statistics");
        buttonExportStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportStatisticsActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(comboBoxStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonChangeSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonExportStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonChangeSettings)
                            .addComponent(comboBoxStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 268, Short.MAX_VALUE)
                        .addComponent(buttonExportStatistics))
                    .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void piechartPercentageFoundLuggage(DefaultPieDataset dataset) {
        //finding percentage of lost luggage

        String[] values1 = new String[0];
        String query = "SELECT count(*) FROM luggagecontroldata.luggage where status = \"Lost\";";
        String result = DB.queryOneResult(query, values1);
        
        int lost = Integer.parseInt(result);
        
        query = "SELECT count(*) FROM luggagecontroldata.luggage where status = \"Found\";";
        result = DB.queryOneResult(query, values1);
        
        int found = Integer.parseInt(result);
        int notFound = lost - found;

        // create a dataset with dataset.setValue and give the dataset a name with a new value 
        dataset.setValue("Found", found);
        dataset.setValue("Not found", notFound);
    }

    private void piechartPercentageReturnedLuggage(DefaultPieDataset dataset) {
        //finding percentage of returned luggage        
        int lost = 50;
        int returned = 30;
        int notReturned = lost - returned;

        // create a dataset with dataset.setValue and give the dataset a name with a new value 
        dataset.setValue("Returned", returned);
        dataset.setValue("Not returned", notReturned);
    }

    private void buildChart(DefaultPieDataset dataset, String name) {
        JFreeChart chart = ChartFactory.createPieChart( // creates the chart as chart
                name, // chart title 
                dataset, // data    
                true, // include legend   
                true, // include tooltips
                false);

        PieChart_AWT demo = new PieChart_AWT(chart);

        panelChart.removeAll();
        panelChart.setLayout(new java.awt.BorderLayout());
        panelChart.add(demo, BorderLayout.CENTER);
        demo.setVisible(true); // makes the demo visible
        panelChart.revalidate();
        panelChart.repaint();
    }

    private void buttonExportStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportStatisticsActionPerformed
        this.userNotAFK();
    }//GEN-LAST:event_buttonExportStatisticsActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.HELP);
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void buttonChangeSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeSettingsActionPerformed
        this.userNotAFK();
        this.luggageControl.switchJPanel(this.luggageControl.CHANGE_SETTINGS);
    }//GEN-LAST:event_buttonChangeSettingsActionPerformed

    private void comboBoxStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxStatisticsActionPerformed
        comboBoxStatistics.removeItem("Select statistics type");
        DefaultPieDataset dataset = new DefaultPieDataset();
        String chartName = "title";
        if (comboBoxStatistics.getSelectedItem().equals("Percentage found luggage")) {
            //generate percentage found luggage
            piechartPercentageFoundLuggage(dataset);
            chartName = "Finding percentage of lost luggage";
        } else if (comboBoxStatistics.getSelectedItem().equals("Percentage returned luggage")) {
            //generate percentage returned luggage
            piechartPercentageReturnedLuggage(dataset);
            chartName = "Percentage of returned luggage to customer";
        }
        buildChart(dataset, chartName);
    }//GEN-LAST:event_comboBoxStatisticsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChangeSettings;
    private javax.swing.JButton buttonExportStatistics;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JComboBox comboBoxStatistics;
    private javax.swing.JLabel labelHome;
    private javax.swing.JPanel panelChart;
    // End of variables declaration//GEN-END:variables
}
