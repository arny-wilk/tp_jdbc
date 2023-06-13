package fr.diginamic.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {

    private static final String URL_DB;
    private static final String USER_DB;
    private static final String PASSWORD_DB;
    private static final String SELECTED_DB;
    private static final String PORT;
    private static final Logger LOG = LoggerFactory.getLogger(fr.diginamic.jdbc.TestInsertion.class);


    static {
        ResourceBundle myresConf = ResourceBundle.getBundle("db");
        URL_DB = myresConf.getString("MYSQL_ADDON_HOST");
        USER_DB = myresConf.getString("MYSQL_ADDON_USER");
        PASSWORD_DB = myresConf.getString("MYSQL_ADDON_PASSWORD");
        SELECTED_DB = myresConf.getString("MYSQL_ADDON_DB");
        PORT = myresConf.getString("MYSQL_ADDON_PORT");
    }

    public static void main(String[] args) {
        deleteData("La Maison des Peintures");
    }

    private static void deleteData(String nomFournisseur) {
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement update = cnx.createStatement();
        ) {
            int nb = update.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM='" + nomFournisseur + "'");
            System.out.println(nb);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            LOG.debug(e.getMessage());
        }
    }
}
