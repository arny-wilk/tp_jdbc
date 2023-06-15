package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.fournisseurs.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

import java.util.List;

public class TestSelect {

    public static void main(String[] args) {
        FournisseurDaoJdbc select = new FournisseurDaoJdbc();
        List<Fournisseur> extraire = null;
        try {
            extraire = select.extraire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        extraire.forEach(System.out::println);
    }

}
