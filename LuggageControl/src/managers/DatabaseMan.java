package managers;

import baseClasses.ErrorJDialog;
import com.mysql.jdbc.CommunicationsException;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.LuggageControl;
import static managers.ConfigurationMan.getMysqlDumpLocationWindows;

/**
 * DataBaseManager is a class with predefined credentials for the database. It
 * also provides query methods which can be used to query directly to the
 * database. All query's are with prepared statements.
 *
 * @author geoffrey
 */
public class DatabaseMan {

    private final String HOST_NAME = "localhost";
    private final String DATABASE_USERNAME = "lugcontroluser";
    private final String DATABASE_PASSWORD = "verysecurepassword";
    private final String DATABASE_NAME = "luggagecontroldata";

    public static final String PS_TYPE_STRING = "String";
    public static final String PS_TYPE_INT = "Int";

    public DatabaseMan() {

    }

    public static void exportDatabase(LuggageControl luggageControl) {
        JTextField username = new JTextField(5);
        JPasswordField password = new JPasswordField(5);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Username:"));
        myPanel.add(username);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Password:"));
        myPanel.add(password);
        
        int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Please Enter your mysql username and password", JOptionPane.OK_CANCEL_OPTION
        );
        
        String line = null;
        File file = null;
            
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime rt = Runtime.getRuntime();
            // yes yes we will just use echo to pipe your password through to mysqldump this is so secure lol.
            // yet is the only way since password prompts ignor stdin and similiar and require a physical hid device as input
            // now we could hack our way into a virtual usb hid device but echo is much more simple and elegant.
            String[] commands = {"/bin/sh", "-c", "echo '" + password.getText() + "' | mysqldump -u " + username.getText() + " -p -r gucci.sql luggagecontroldata"};
            Process proc;
            try {
                proc = rt.exec(commands);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                
                if((line = stdInput.readLine()) != null) {
                    System.out.println(line);
                }
                stdInput.close();

                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                
                if((line = stdError.readLine()) != null) {
                    System.out.println(line);
                }
                stdError.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else {
            if (result == JOptionPane.OK_OPTION) {
                JFileChooser fileSaver = new JFileChooser();
                fileSaver.setFileFilter(new FileNameExtensionFilter("Mysqldump File (.sql)", "sql"));
                
                fileSaver.setAcceptAllFileFilterUsed(false);
                fileSaver.setSelectedFile(new File(".sql"));
                

                int returnValue = fileSaver.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        file = fileSaver.getSelectedFile();
                        if(!file.getAbsolutePath().endsWith(".sql")){
                            file = new File(fileSaver.getSelectedFile() + ".sql");
                        }

                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.close();
                    } 
                    catch (Exception e) {
                        new ErrorJDialog(luggageControl, true, e.getMessage(), e.getStackTrace());
                    }
                }
                else{
                    return;
                }

                try {
                    String[] commands = {"CMD", ConfigurationMan.getMysqlDumpLocationWindows(luggageControl)+ " luggagecontroldata -u" + username.getText() + " -p" + password.getText() + " -r" + file, password.getText()};
                    Process process2 = Runtime.getRuntime().exec(commands);
                    BufferedReader bri = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                    BufferedReader bre = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
                    bri.close();
                    while ((line = bre.readLine()) != null) {
                        System.out.println(line);
                        if (line.startsWith("mysqldump: Got error:")) {
                            System.out.println(line);
                            JOptionPane.showMessageDialog(null, "U heeft het verkeerde gebruikersnaam of wachtwoordingevuld.", "Er ging iets fout", JOptionPane.WARNING_MESSAGE);

                            
                            if (!file.delete()) {
                                // new ErrorJDialog(luggageControl, true, "Error: trying to remove file", new Throwable().getStackTrace());
                            }
                        }
                    }
                    bre.close();
                    process2.waitFor();

                } catch (IOException | HeadlessException | InterruptedException e) {
                    new ErrorJDialog(luggageControl, true, "Error: " + e.getMessage(), e.getStackTrace());

                }
            }
        }
    }

    
    /**
     * This query will return one value, a string: It returns the value from the
     * first column and the first row.
     *
     * @param query
     * @param values
     * @return String value
     */
    public String queryOneResult(String query, String[] values) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString((i + 1), values[i]);
            }
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            // check if we are not trying to return a empty resultset
            if(result.getRow() > 0) {
                return result.getString(1);
            }
            else {
                return "";
            }
        } 
        catch(CommunicationsException ex) {
            
            // this could be used to determine that the database is offline
            return "";
        }
        catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
            
            // please dont return NULL this will create errors everywhere
            return "";
        }
    }

    /**
     * used for select queries while using user input
     *
     * @param query String with select query
     * @param values values to be used in query, place ? at value spots
     * @return ResultSet
     */
    public ResultSet query(String query, String[] values) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString((i + 1), values[i]);
            }
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Prepared statement insert query, insert queries ONLY!!
     *
     * @param query String with ? for the valuess
     * @param values the values to be inserted
     * @param types the value types, supported: String, Int
     * @throws SQLException
     */
    public void queryManipulation(String query, String[] values, String[] types) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = dbConnection.prepareStatement(query);

            //For every value in the arrays, values and types, fill in the prepared statement
            for (int i = 0; i < values.length; i++) {
                switch (types[i]) {
                    case "String": {
                        String value = values[i];
                        preparedStatement.setString((i + 1), value);
                        break;
                    }
                    case "Int": {
                        int value = Integer.parseInt(values[i]);
                        preparedStatement.setInt((i + 1), value);
                        break;
                    }
                }
            }
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
