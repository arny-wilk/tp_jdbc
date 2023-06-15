package fr.diginamic.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.jdbc.dao.fournisseurs.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

import java.util.List;

public class TestJdbcFournisseur {

    private static final FournisseurDaoJdbc jdbcOperations = new FournisseurDaoJdbc();

    private static final Logger LOG = LoggerFactory.getLogger(TestJdbcFournisseur.class);

    public static void main(String[] args) {
        // Extraction
        extracted();

        // Insertion
        // insertion(new Fournisseur(4, "L'Espace Création"));
        insertion(new Fournisseur(4, "La Maison de la Peinture"));

        // Update
        update("L Espace Création", "Espace création");

        // Delete
        delete(new Fournisseur(4, "Espace Création"));
    }

    private static void extracted() {
        List<Fournisseur> extraire;
        try {
            extraire = jdbcOperations.extraire();
            extraire.forEach(System.out::println);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void insertion(Fournisseur fournisseur) {
        try {
            jdbcOperations.insert(fournisseur);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void update(String ancienNom, String nvxNom) {
        try {
            jdbcOperations.update(ancienNom, nvxNom);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    private static void delete(Fournisseur fournisseur) {
        try {
            jdbcOperations.delete(fournisseur);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }
}
