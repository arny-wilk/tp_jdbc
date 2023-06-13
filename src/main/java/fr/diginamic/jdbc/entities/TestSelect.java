package fr.diginamic.jdbc.entities;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

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
