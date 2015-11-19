/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

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
 * also provides Query methods which can be used to query directly to the
 * database.
 *
 * @author geoffrey
 */
public class DatabaseMan {

    private final String HOST_NAME = "localhost";
    private final String DATABASE_USERNAME = "lugcontroluser";
    private final String DATABASE_PASSWORD = "verysecurepassword";
    private final String DATABASE_NAME = "LuggageControlData";

    public DatabaseMan() {

    }

    /**
     * access database with query
     *
     * @param query
     * @return ResultSet result
     */
    public ResultSet Query(String query) {
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
     * @param query
     * @return String value
     */
    public String QueryOneResult(String query) {
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
     * Prepared statements insert query
     * @param query
     * @param values the values to be inserted
     * @param types the value types, supported: String, Int
     * @throws SQLException 
     */
    public void QueryInsertUser(String query, String[] values, String[] types) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = dbConnection.prepareStatement(query);

            //For every value in the arrays, values and types, fill in the prepared statement
            for (int i = 0; i < values.length; i++) {
                switch (types[i]) {
                    case "String":
                        {
                            String value = values[i];
                            preparedStatement.setString((i + 1), value);
                            break;
                        }
                    case "Int":
                        {
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
