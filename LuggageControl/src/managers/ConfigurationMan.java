package managers;

import baseClasses.ErrorJDialog;
import baseClasses.PopUpJDialog;
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
    
    private final LuggageControl luggageControl;
    
    private static final String OS = System.getProperty("os.name");
    
    private static final String MYSQL_DUMP_LOCATION = "mysql_dump_location:";
    
    // Our file writer
    Writer writer;
    
    BufferedReader br;
    
    public ConfigurationMan(LuggageControl luggageControl) {
        this.luggageControl = luggageControl;

        try {
            if(!checkConfigFile()) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CONFIG_NAME), "utf-8"));
                System.out.println("Created configurationfile: " + CONFIG_NAME);
            }
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace(), true);
        }       

        if(getMysqlDumpLocationWindows(this.luggageControl).equals("")) {
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
     * Reads the mysqldump location and returns a absolute path
     * @return absolute path to <file>mysqldump.exe</file>
     */
    public static String getMysqlDumpLocationWindows(LuggageControl luggageControl) {
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
    
    public boolean findMysqlDumpLocationWindows() {
        if(OS.equals("Linux")) {
            return false; 
        }
        
        new PopUpJDialog(luggageControl, true);
        
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
            FileWriter fw = new FileWriter(new File(CONFIG_NAME).getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(MYSQL_DUMP_LOCATION + line + "\\mysqldump.exe");
            bw.close();
            return true;
        }
        catch(Exception e) {
            new ErrorJDialog(this.luggageControl, true, e.getMessage(), e.getStackTrace());
            return false;
        }
    }
}
