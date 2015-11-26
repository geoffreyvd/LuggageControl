package managers;

import baseClasses.ErrorJDialog;
import baseClasses.PopUpJDialog;
import constants.ScreenNames;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import main.LuggageControl;

/**
 * Manage configuration file
 * @author Corne Lukken
 */
public class ConfigurationMan {
    
    // Configuration file name
    private static final String CONFIG_NAME = "config.ini";
    
    private static final String OS = System.getProperty("os.name");
    
    private static final String MYSQL_DUMP_LOCATION = "mysql_dump_location:";
    
    private final LuggageControl luggageControl;
    
    private FindMysqlJDialog msqlDialog;
    
    class FindMysqlJDialog extends PopUpJDialog {

        public FindMysqlJDialog(Frame parent, boolean modal) {
            super(parent, modal);
            
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            javax.swing.JTextPane text = new javax.swing.JTextPane();
            
            this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            this.setTitle("Configuring mysql export");
            this.getContentPane().setLayout(layout);

            text.setText("Locating mysqldump.exe please wait");
            text.setEditable(false);
            text.setPreferredSize(new Dimension(1920, 1080));
            
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            );
            
            this.add(text);
            this.setVisible(true);
            this.pack();
            this.revalidate();
            this.repaint();
        }
    }
    
    // Our file writer
    Writer writer;
    
    BufferedReader br;
    
    public ConfigurationMan(LuggageControl luggageControl) {
        this.luggageControl = luggageControl;

        // test for if config file exists
        try {
            if(!checkConfigFile()) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CONFIG_NAME), "utf-8"));
                System.out.println("Created configurationfile: " + CONFIG_NAME);
            }
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace(), true);
        }      
        
        // test if initial configuration of database and user is complete
        if(!this.getInitialConfiguration()) {
            // go to initial config
            //this.luggageControl.switchJPanel(ScreenNames.FIRST_START);
        }

        // windows test for mysqldump.exe location and if our file still exists
        if(this.getMysqlDumpLocationWindows(this.luggageControl).equals("") || !this.mysqlDumpExists()) {
            this.findMysqlDumpLocationWindows();
        }
    }
    
    private boolean checkConfigFile() {
        if(new File(CONFIG_NAME).isFile()){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Tests if the initial configuration sequence has been performed
     * @return true if initial configuration is complete, false otherwise. 
     */
    public boolean getInitialConfiguration() {
        return false;
    }
    
    /**
     * Reads the mysqldump location and returns a absolute path
     * @return absolute path to <file>mysqldump.exe</file> or Linux if on linux, empty when no path exists
     */
    public static String getMysqlDumpLocationWindows(LuggageControl luggageControl) {
       
        // skip this if we are linux
        if(OS.equals("Linux")) {
            return "Linux";
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(CONFIG_NAME));

            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(MYSQL_DUMP_LOCATION)) {
                    return line.replace(MYSQL_DUMP_LOCATION, "");
                }
            }
        }
        catch(Exception e) {
            new ErrorJDialog(luggageControl, true, e.getMessage(), e.getStackTrace());
        }
        
        System.out.println("Could not find mysqldump location configuration");
        return "";
    }
    
    /**
     * Check if our reference to the <code>mysqldump.exe</code> on windows still exists.
     * @return true if the file exists, false if it does not.
     */
    public static boolean mysqlDumpExists() {
        if(OS.equals("Linux")) {
            return true;
        }
        return false;
    }
    
    public boolean findMysqlDumpLocationWindows() {
        if(OS.equals("Linux")) {
            return false;
        }
        
        msqlDialog = new FindMysqlJDialog(luggageControl, false);
        String[] command = {"CMD", "/C", "dir", "/s", "*mysqldump.exe*"};
        ProcessBuilder pb = new ProcessBuilder(command);
        char schijf;
        String line = null;
        boolean found = false;
        for (schijf = 'A'; schijf <= 'Z'; schijf++) {
            if(!found) {
                pb.directory(new File(schijf + ":\\"));
                try {
                    Process process = pb.start();

                    InputStream is = process.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);

                    String tempLine;
                    while ((tempLine = br.readLine()) != null && line == null) {
                        if (!tempLine.contains("Directory of")) {
                        } else {
                            line = (tempLine.replace("Directory of", ""));
                            line = (line.trim());
                            found = true;
                        }
                    }

                } catch (java.io.IOException e) {
                    System.out.println("Drive " + schijf + " does not exist");
                }
            }
        }
        try {
            if(line != null) {
                FileWriter fw = new FileWriter(new File(CONFIG_NAME).getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(MYSQL_DUMP_LOCATION + line + "\\mysqldump.exe");
                bw.close();
                msqlDialog.dispose();
                return true;
            }
            else {
                msqlDialog.dispose();
                return false;
            }
        }
        catch(Exception e) {
            msqlDialog.dispose();
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            return false;
        }
    }
}
