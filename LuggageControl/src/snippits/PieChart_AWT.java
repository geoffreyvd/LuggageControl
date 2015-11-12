/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snippits;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * creates a pie chart with the selfmade datasets, the displays the pie chart.
 * needed libaries from the JFreeChart folder in snippits
 * @author KonradLL
 */
public class PieChart_AWT extends ApplicationFrame {

    public PieChart_AWT(String title) {
        super(title);
        setContentPane(createDemoPanel()); // sets the type of the panel
    }

    private static PieDataset createDataset() {
        // creata an object DefaultPieDatabse
        DefaultPieDataset dataset = new DefaultPieDataset();
        // create a dataset with dataset.setValue and give the dataset a name with a new value 
        dataset.setValue("IPhone 5s", new Double(20));
        dataset.setValue("SamSung Grand", new Double(20));
        dataset.setValue("MotoG", new Double(40));
        dataset.setValue("Nokia Lumia", new Double(10));
        return dataset;
    }
    
    // create a pie chart that has the datasets
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart( // creates the chart as chart
                "Mobile Sales", // chart title 
                dataset, // data    
                true, // include legend   
                true, // include tooltips
                false); // don't include tooltips

        return chart;
    }
    
    // create a demo of the piechart
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    
    public static void main(String[] args) {
        PieChart_AWT demo = new PieChart_AWT("Mobile Sales");
        demo.setSize(560, 367);  // makes the demo with a preset size
        RefineryUtilities.centerFrameOnScreen(demo); // creates the demo in the center of the screen
        demo.setVisible(true); // makes the demo visible
    }
}
