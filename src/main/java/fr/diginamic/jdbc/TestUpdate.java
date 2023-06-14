package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.fournisseurs.FournisseurDaoJdbc;

public class TestUpdate {

    public static void main(String[] args) {
        FournisseurDaoJdbc update = new FournisseurDaoJdbc();
        try {
            update.update("L Espace Création", "Espace création");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
