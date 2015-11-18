/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snippits;

import java.sql.ResultSet;
import java.sql.SQLException;
import managers.DatabaseMan;

/**
 *
 * @author geoffrey
 */
public class DataBaseQueryExample {

    public static void main(String[] args) {
        //example 1: selecting all values from ws.database.cijfer, those values are printed in console
        System.out.println("Example 1: ");

        DatabaseMan DB1 = new DatabaseMan();

        //ResulSet result is a table wth values
        ResultSet result = DB1.Query("select * from users");
        try {
            //This while loop, loops trough every row in result
            while (result.next()) {
                //print the first 3 columns of every row
                System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println("An error occured while querying the database");
        }

        System.out.println("\nExample 2: ");
        //example 2: selecting all values from ws.database.cijfer, those values are printed in console

        DatabaseMan DB2 = new DatabaseMan();

        //This query will return a string, it only returns 1 value!
        String result1 = DB2.QueryOneResult("select * from users");
        System.out.println(result1);
        
        DatabaseMan DB3 = new DatabaseMan();
        
        String result2 = DB3.QueryOneResult("select users.permissions from users where username = \"geoffreyvd\" and password = \"gucciguc\"");
        System.out.println(result1);
    }
}
