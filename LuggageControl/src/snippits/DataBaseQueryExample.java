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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //insert query example in screen -> adduser

        // example 5 prepared statement query
        System.out.println("example 5");
        DatabaseMan DB5 = new DatabaseMan();
        ResultSet result5;

        String[] values1 = new String[1];
        values1[0] = "geoffreyvd4";
        String query = "select * from user where username = ?";

        try {
            result5 = DB5.query(query, values1);
            while (result5.next()) {
                System.out.println(result5.getString("password") + " " + result5.getString("firstname") + " " + result5.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        
        
//        query example inner join // werkt niet meer na database patch
        
//        select * 
//        from clients 
//        inner join client_luggage
//        on clients.client_id = client_luggage.clients_id
//        inner join luggage
//        on luggage.luggage_id = client_luggage.luggage_id
//        where client_id = 1;
    }
}
