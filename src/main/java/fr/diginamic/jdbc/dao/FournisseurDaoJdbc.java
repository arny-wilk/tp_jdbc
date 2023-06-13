package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements FournisseurDAO {

    private static final String SECURED_QUERY = "INSERT INTO FOURNISSEUR (ID, NOM) VALUES (?, ?)";
    private static final String SECURED_DELETE = "DELETE FROM FOURNISSEUR WHERE NOM= ?";
    private static final String SECURED_UPDATE = "UPDATE FOURNISSEUR SET NOM = ? WHERE NOM= ?";
    private static final String URL_DB;
    private static final String USER_DB;
    private static final String PASSWORD_DB;


    static {
        ResourceBundle myresConf = ResourceBundle.getBundle("db");
        URL_DB = myresConf.getString("MYSQL_ADDON_HOST");
        USER_DB = myresConf.getString("MYSQL_ADDON_USER");
        PASSWORD_DB = myresConf.getString("MYSQL_ADDON_PASSWORD");
    }

    @Override
    public List<Fournisseur> extraire() throws Exception {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement select = cnx.createStatement();) {
            ResultSet curseur = select.executeQuery("SELECT * FROM FOURNISSEUR");
            while (curseur.next()) {
                Integer id = curseur.getInt("ID");
                String nom = curseur.getString("NOM");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }
        }
        return fournisseurs;
    }

    @Override
    public void insert(Fournisseur fournisseur) throws Exception {
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement insertion = cnx.prepareStatement(SECURED_QUERY);
        ) {
            insertion.setString(1, String.valueOf(fournisseur.getId()));
            insertion.setString(2, fournisseur.getNom());
            int nb = insertion.executeUpdate();
            System.out.println(nb);
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) throws Exception {
        int nb = 0;
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement update = cnx.prepareStatement(SECURED_UPDATE);
        ) {
            update.setString(1, nouveauNom);
            update.setString(2, ancienNom);
            nb = update.executeUpdate();
        }
        return nb;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) throws Exception {
        int nb = 0;
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement delete = cnx.prepareStatement(SECURED_DELETE);
        ) {
            delete.setString(1, fournisseur.getNom());
            nb = delete.executeUpdate();
        }
        return nb > 0;
    }
}
