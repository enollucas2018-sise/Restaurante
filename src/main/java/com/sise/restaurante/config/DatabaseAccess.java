/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

/**
 *
 * @author User02
 */
public class DatabaseAccess {
    private final static String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final static String URL = "jdbc:sqlserver://DESKTOP-LCJVT2J;encrypt=false;databaseName=RestauranteDB";
    private final static String USERNAME = "sa";
    private final static String PASSWORD = "sqladmin";
    
    private DatabaseAccess(){
    }
        @SuppressWarnings("deprecation")
        public static Connection getConnection() throws SQLException{
            Connection cn = null;
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
            cn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e){
            throw e;
        } catch (ClassNotFoundException e){
            System.out.println(e);
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e);
        }
        return cn;
    }
    }

    


