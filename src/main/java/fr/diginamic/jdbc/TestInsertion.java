package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.fournisseurs.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestInsertion {
    public static void main(String[] args) {
        FournisseurDaoJdbc insertion = new FournisseurDaoJdbc();
        try {
            insertion.insert(new Fournisseur(4
                    , "L'Espace Création".replace("\'"
                    , " ")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
