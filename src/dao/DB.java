/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kassi
 */
public class DB {
    
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/votacao";
    private static final String ID = "root";
    private static final String PASS = "root";
    private static final String DB_NAME = "votacao";
    private static final String DATABASE = "CREATE DATABASE IF NOT EXISTS "+DB_NAME;
    

    /**
     * cria o banco de dados
     */
    public void createDatabase(){
        
        Connection conn = null;
        
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",ID, PASS);
            Statement statement = conn.createStatement();
            statement.executeUpdate(DATABASE);
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * cria a tabela
     */
    public void createTable(String tb_name){
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute(tb_name);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
 
    /**
     * realiza a conexao do banco de dados
     * @return 
     */
    public Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fecha a conexao do banco de dados
     * @param con 
     */
    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * fecha o statement
     * @param stmt 
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * cria o banco de dados e a tabela se ainda nao existirem
     */
    public void iniciaDB(){
        createDatabase();
    }
    
}
