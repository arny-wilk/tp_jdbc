package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestDelete {

    public static void main(String[] args) {
        FournisseurDaoJdbc deleteOp = new FournisseurDaoJdbc();
        try {
            deleteOp.delete(new Fournisseur(4, "Espace Création"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
