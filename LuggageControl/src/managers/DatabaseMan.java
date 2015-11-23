/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DataBaseManager is an class with predefined credentials for the database. It
 * also provides query methods which can be used to query directly to the
 * database.
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

    public void exportDatabase(String file) {
        if (System.getProperty("os.name").equals("Linux")) {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"/bin/sh", "-c", "mysqldump -u lugcontroluser -p -r gucci.sql LuggageControlData", "verysecure"};
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
            // MICHIE put your windows export thing a ma jig here!
        }
    }

    /**
     * Simple select query
     *
     * @param query
     * @return ResultSet result
     */
    public ResultSet query(String query) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * This query will return one value, a string: It returns the value from the
     * first column and the first row.
     *
     * @param query
     * @return String value
     */
    public String queryOneResult(String query) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
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
    public ResultSet queryPrepared(String query, String[] values) {
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
    public void queryPreparedManipulation(String query, String[] values, String[] types) throws SQLException {
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
