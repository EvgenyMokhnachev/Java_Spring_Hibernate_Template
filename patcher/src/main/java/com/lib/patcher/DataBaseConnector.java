package com.lib.patcher;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector{
    public static DataSource ds;
    public static String name;
    public static String pswd;
    public static String ip = "127.0.0.1";
    public static String port = "5432";
    public static String db;

    public static void connect(){
        ds = new DataSource();
        ds.setUsername(name);
        ds.setPassword(pswd);
        ds.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://" + ip + ":" + port + "/" + db);
    }

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public static void execute(String q) throws SQLException{
        Connection cn = getConnection();
        Statement cursor = null;
        cursor = cn.createStatement();
        cursor.executeUpdate(q);
        cursor.close();
        cn.close();
    }

    public static int getLastPatch(String root) throws SQLException{
        String q = "SELECT patch FROM " + root + ".patch";
        Connection cn = getConnection();
        Statement cursor = null;
        cursor = cn.createStatement();
        ResultSet result = cursor.executeQuery(q);
        result.next();
        int path = result.getInt("patch");
        cursor.close();
        cn.close();
        return path;
    }


    public static void setLastPatch(String root, Integer val) throws SQLException{
        String q = "TRUNCATE " + root + ".patch; INSERT INTO " + root + ".patch (patch) VALUES  (" + val + ")";
        Connection cn = getConnection();
        Statement cursor = null;
        cursor = cn.createStatement();
        cursor.executeUpdate(q);
        cursor.close();
        cn.close();
    }

    public static void dropSchema(String schemed) throws SQLException{
        String q = "DROP SCHEMA " + schemed + " CASCADE;";
        Connection cn = getConnection();
        Statement cursor = null;
        cursor = cn.createStatement();
        cursor.executeUpdate(q);
        cursor.close();
        cn.close();
    }


}
