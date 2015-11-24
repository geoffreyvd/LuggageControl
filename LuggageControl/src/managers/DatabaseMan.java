/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import baseClasses.ErrorJDialog;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import main.LuggageControl;

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
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"/bin/sh", "-c", "mysqldump -u lugcontroluser -p -r gucci.sql luggagecontroldata", "verysecure"};
            Process proc;
            try {
                proc = rt.exec(commands);
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            JTextField username = new JTextField(5);
            JPasswordField password = new JPasswordField(5);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Username:"));
            myPanel.add(username);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Password:"));
            myPanel.add(password);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter your mysql username and password", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) {

            } else {

                JFileChooser fileChooser = new JFileChooser();

                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".sql");
                        fw.close();
                    } catch (Exception e) {
                        new ErrorJDialog(luggageControl, true, e.getMessage(), e.getStackTrace());
                    }
                }
                String[] command = {"CMD", "/C", "dir", "/s", "*mysqldump.exe*"};
                ProcessBuilder pb = new ProcessBuilder(command);
                char schijf;
                String line = null;
                for (schijf = 'A';
                        schijf <= 'Z'; schijf++) {
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
                            }
                        }

                        if (line != null) {
                            schijf = 'Z';
                        }

                    } catch (java.io.IOException e) {
                       new ErrorJDialog(luggageControl, true, e.getMessage(), e.getStackTrace());
                    }
                }

                try {
                    line = line + "/mysqldump.exe";
                    Process process2 = Runtime.getRuntime().exec("CMD /C " + line + " luggagecontroldata -u" + username.getText() + " -p" + password.getText() + " -r" + fileChooser.getSelectedFile() + ".sql");
                    BufferedReader bri = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                    BufferedReader bre = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
                    while ((line = bri.readLine()) != null) {
                        
                    }
                    bri.close();

                    while ((line = bre.readLine()) != null) {
                        if (line.startsWith("mysqldump: Got error:")) {
                            JOptionPane.showMessageDialog(null, "U heeft het verkeerde gebruikersnaam of wachtwoordingevuld.", "Er ging iets fout", JOptionPane.WARNING_MESSAGE);
                            File fileCreator = new File(fileChooser.getSelectedFile() + ".sql");
                            System.out.println(fileChooser.getSelectedFile() + ".sql");
                            if(!fileCreator.delete()){
                                new ErrorJDialog(luggageControl, true, "Error: trying to remove file", new Throwable().getStackTrace());
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
            return result.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * used for select queries while using user input
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
