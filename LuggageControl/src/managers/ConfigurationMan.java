package managers;

import baseClasses.ErrorJDialog;
import baseClasses.PopUpJDialog;
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
 * Manage configuration file and determine if initial system configuration is complete.
 * @author Corne Lukken
 */
public class ConfigurationMan {
    
    // used to determine if we can start initialization immediately
    private boolean proceedInitialization = true;
    
    private DatabaseMan db = new DatabaseMan();
    
    // Configuration file name
    private static final String CONFIG_NAME = "config.ini";
    
    private static final String OS = System.getProperty("os.name");
    
    private static final String MYSQL_DUMP_LOCATION = "mysql_dump_location:";
    
    private final LuggageControl luggageControl;
    
    private FindMysqlJDialog msqlDialog;
    
    /**
     * Special dialog to show the process of searching for <code>mysql.exe</code>  
     */
    public static class FindMysqlJDialog extends PopUpJDialog {

        private FindMysqlJDialog(Frame parent, boolean modal) {
            super(parent, modal);
            
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            javax.swing.JTextPane text = new javax.swing.JTextPane();

            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            setTitle("Configuring mysql export");
            getContentPane().setLayout(layout);

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

            add(text);
            setVisible(true);
            revalidate();
            repaint();
        }
    }
    
    // file writer to write to configuration file
    Writer writer;
    
    // buffered reader to read from configuration file
    BufferedReader br;
    
    /**
     * Performs initial test conditions and determines when to initialize <code>main.LuggageControl</code>
     * @param luggageControl
     */
    public ConfigurationMan(LuggageControl luggageControl) {
        this.luggageControl = luggageControl;
        msqlDialog =  new FindMysqlJDialog(this.luggageControl, false);

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
            
            // do not immediatly initialize
            proceedInitialization = false;
            
            // go to initial config
            this.luggageControl.switchJPanel(this.luggageControl.FIRST_START);
        }

        // windows test for mysqldump.exe location and if our file still exists
        if(this.getMysqlDumpLocationWindows(this.luggageControl).equals("") || !this.mysqlDumpExists(this.luggageControl)) {
            this.findMysqlDumpLocationWindows();
        } 
        
        msqlDialog.dispose();
        
        // proceed with the initialization if the configuration is done
        if(proceedInitialization) {
            this.luggageControl.initComponents();
        }
    }
    
    /**
     * check if our config file exists
     * @return true if the file exists, otherwise false
     */
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
        try {
            if(Integer.parseInt(db.queryOneResult("SELECT COUNT(*) FROM user;", new String[]{})) > 0) {
                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    /**
     * Reads the mysqldump location and returns a absolute path
     * @param luggageControl
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
     * @param luggageControl
     * @return true if the file exists, false if it does not.
     */
    public static boolean mysqlDumpExists(LuggageControl luggageControl) {
        if(OS.equals("Linux")) {
            return true;
        }
        String path = getMysqlDumpLocationWindows(luggageControl).replace("^ ", " ");
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Find <code>mysqldump.exe</code> on the windows file system and store the location in the configuration file.
     * @return if true if we found the location false if we failed to find it.
     */
    public void findMysqlDumpLocationWindows() {
        if(OS.equals("Linux")) {
            return;
        } 
        
        Thread findMysqlDumpLocationThread = new Thread("findMysqlDumpLocationThread") {
            @Override
            public void run() {
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
                        line = line.replace(" ", "^ ");
                        bw.write(MYSQL_DUMP_LOCATION + line + "\\mysqldump.exe");
                        bw.close();
                        
                    }
                }
                catch(Exception e) {
                    
                }
            }
        };
        findMysqlDumpLocationThread.run();
    }
}
