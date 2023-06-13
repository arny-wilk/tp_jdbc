package dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final String URL_DB;
    private static final String USER_DB;
    private static final String PASSWORD_DB;


    static {
        ResourceBundle myresConf = ResourceBundle.getBundle("db");
        URL_DB = myresConf.getString("MYSQL_ADDON_HOST");
        USER_DB = myresConf.getString("MYSQL_ADDON_USER");
        PASSWORD_DB = myresConf.getString("MYSQL_ADDON_PASSWORD");
    }
    public static void main(String[] args) {
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB)) {
            System.out.println(cnx);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
