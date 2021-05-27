/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class BaseDAO {

    public Connection con;

    public BaseDAO() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://localhost:3306/electricity_payment?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.jdbc.Driver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "12022020");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
