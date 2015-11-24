package managers;

import baseClasses.ErrorJDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import main.LuggageControl;

/**
 * Manage configuration file
 * @author Corne Lukken
 */
public class ConfigurationMan {
    
    // Configuration file name
    private final String configName = "config.ini";
    
    private final LuggageControl LuggageControl;
    
    // Our file writer
    Writer writer;
    
    public ConfigurationMan(LuggageControl luggageControl) {
        this.LuggageControl = luggageControl;
        
        if(!checkConfigFile()) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configName), "utf-8"));
            }
            catch(Exception e) {
                new ErrorJDialog(this.LuggageControl, true, e.getMessage(), e.getStackTrace(), true);
            }
        }
    }
    
    private boolean checkConfigFile() {
        if(new File(configName).isFile()){
            return true;
        }
        else {
            return false;
        }
    }
}
