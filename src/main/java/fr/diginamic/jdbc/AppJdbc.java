package fr.diginamic.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

import java.util.List;

public class AppJdbc {

    private static FournisseurDaoJdbc jdbcOperations = new FournisseurDaoJdbc();

    private static final Logger LOG = LoggerFactory.getLogger(fr.diginamic.jdbc.TestInsertion.class);

    public static void main(String[] args) {
        // Extraction
        extracted();

        // Insertion
        insertion();

        // Update
        update();
        // Delete
        delete();
    }

    private static void extracted() {
        List<Fournisseur> extraire = null;
        try {
            extraire = jdbcOperations.extraire();
            extraire.forEach(System.out::println);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void insertion() {
        try {
            jdbcOperations.insert(new Fournisseur(4, "L'Espace Création"));
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void update() {
        try {
            jdbcOperations.update("L Espace Création", "Espace création");
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void delete() {
        try {
            jdbcOperations.delete(new Fournisseur(4, "Espace Création"));
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }
}