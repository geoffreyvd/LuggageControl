/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoffrey
 */
public class DatabaseMan {

    private final String HOST_NAME = "localhost";
    private final String DATABASE_USERNAME = "root";
    private final String DATABASE_PASSWORD = "Root";
    private final String DATABASE_NAME = "wsdatabase";

    public DatabaseMan() {

    }

    /**
     * access database with query
     *
     * @param query
     */
    public void Query(String query) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
