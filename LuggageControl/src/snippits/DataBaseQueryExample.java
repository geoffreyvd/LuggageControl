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
        ResultSet result = DB1.query("select * from users");
        try {
            //This while loop, loops trough every row in result
            while (result.next()) {
                //print the first 3 columns of every row
                System.out.println(result.getString("adress") + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println("An error occured while querying the database");
        }

        
        //example 2: selecting all values from ws.database.cijfer, those values are printed in console
        System.out.println("\nExample 2: ");

        DatabaseMan DB2 = new DatabaseMan();

        //This query will return a string, it only returns 1 value!
        String result1 = DB2.queryOneResult("select * from users");
        System.out.println(result1);

        
        //example 3 user log in
        System.out.println("Example 3");
        DatabaseMan DB3 = new DatabaseMan();

        String result2 = DB3.queryOneResult("select users.permissions from users where username = \"geoffreyvd\" and password = \"gucciguc\"");
        System.out.println(result2);

        
//        //example 4 insert query example
//        System.out.println("example 4:");
//        DatabaseMan DB4 = new DatabaseMan();
//
//        String query = "INSERT INTO users"
//                + "(username,password,firstname,surname,cellphone,birthday,gender,nationality,adress,city,postcode,permissions) VALUES"
//                + "(?,?,?,?,?,?,?,?,?,?,?,?)";
//
//        String[] values = new String[12];
//        String[] types = new String[12];
//
//        values[0] = "geoffreyvd4";
//        values[1] = "gucciguc";
//        values[2] = "geoffrey";
//        values[3] = "geoffrey";
//        values[4] = "56465464";
//        values[5] = "1996-12-12";
//        values[6] = "male";
//        values[7] = "dutchy";
//        values[8] = "sduyfgi";
//        values[9] = "haarlem";
//        values[10] = "2033v";
//        values[11] = "1";
//
//        types[0] = "String";
//        types[1] = "String";
//        types[2] = "String";
//        types[3] = "String";
//        types[4] = "Int";
//        types[5] = "String";
//        types[6] = "String";
//        types[7] = "String";
//        types[8] = "String";
//        types[9] = "String";
//        types[10] = "String";
//        types[11] = "Int";
//
//        try {
//
//            DB4.queryInsert(query, values, types);
//
//        } catch (SQLException e) {
//
//            System.out.println(e.getMessage());
//
//        }
        
        // example 5 prepared statement query
        System.out.println("example 5");
        DatabaseMan DB5 = new DatabaseMan();
        
        String[] values1 = new String[1];
        values1[0] = "geoffreyvd4";        
        String query = "select * from users where username = ?";
        
        try {
            result = DB5.queryPrepared(query, values1);
            while (result.next()) {
                System.out.println(result.getString("password") + " " + result.getString("firstname") + " " + result.getString(3));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        
    }
}
