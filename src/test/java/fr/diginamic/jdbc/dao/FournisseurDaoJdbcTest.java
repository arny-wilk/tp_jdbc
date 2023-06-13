package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Fournisseur;
import junit.framework.TestCase;

import java.util.List;

public class FournisseurDaoJdbcTest extends TestCase {

    private FournisseurDaoJdbc testSample = new FournisseurDaoJdbc();

    public void testExtraire() {
        // A
        List<Fournisseur> resultExtraction = null;
        try {
            resultExtraction = testSample.extraire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // A
        String[] resultExpected = {"Fournisseur{id=1, nom='Française d'Imports'}",
                "Fournisseur{id=2, nom='FDM SA'}",
                "Fournisseur{id=3, nom='Dubois & Fils'}",
                "Fournisseur{id=4, nom='Espace création'}"};

        // A
        for (int i = 0; i < resultExpected.length; i++) {
            assertEquals(resultExpected[i], String.valueOf(resultExtraction.get(i)));
        }

    }

    public void testInsert() {
        try {
            testSample.insert(new Fournisseur(5, "L'Espace Création"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testUpdate() {
        try {
            int helloTest = testSample.update("L'Espace Création", "ESPACE CREATION");
            assertEquals(1, helloTest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testDelete() {
        try {
            testSample.delete(new Fournisseur(5, "ESPACE CREATION"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}