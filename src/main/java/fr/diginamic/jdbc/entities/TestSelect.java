package fr.diginamic.jdbc.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestSelect {


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
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement select = cnx.createStatement();) {
            ResultSet curseur = select.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");
            ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
            while(curseur.next()){
                Integer id = curseur.getInt("ID");
                String nom = curseur.getString("NOM");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }
            fournisseurs.forEach(System.out::println);
        } catch (SQLException e){

        }

    }

}
