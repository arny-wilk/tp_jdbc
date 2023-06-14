package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.articles.ArticleDaoJdbc;
import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class TestJdbcArticles {
    private static final ArticleDaoJdbc jdbcOperations = new ArticleDaoJdbc();

    private static final String FOURNISSEUR = "La Maison de la Peinture";

    private static final Logger LOG = LoggerFactory.getLogger(TestJdbcArticles.class);

    public static void main(String[] args) {

        // Insertion Operation
        insertion(new Article(11, "M01", "Peinture Blanche 1L", BigDecimal.valueOf(12.5), new Fournisseur(5, FOURNISSEUR)));
        insertion(new Article(12, "M02", "Peinture rouge mate 1L", BigDecimal.valueOf(15.5), new Fournisseur(5, FOURNISSEUR)));
        insertion(new Article(13, "M03", "Peinture noir laquée 1L", BigDecimal.valueOf(17.8), new Fournisseur(5, FOURNISSEUR)));
        insertion(new Article(14, "M04", "Peinture bleue mate 1L", BigDecimal.valueOf(15.5), new Fournisseur(5, FOURNISSEUR)));

        // Extraction Data
        extracted();

        // Update Operation
        update();

        delete();
    }

    /**
     * Delete operation for 'La Maison de la Peinture'
     * and all articles where Designation include 'Peinture'
     */
    private static void delete() {
        try {
            jdbcOperations.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * -0.25% for all "Peinture mate" from La maison de la Peinture
     */
    private static void update() {
        try {
            jdbcOperations.update();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    /**
     * @param article from the new Fournisseur La Maison de la Peinture
     *                Go to Test to verify the new Fournisseur added
     */
    private static void insertion(Article article) {
        try {
            jdbcOperations.insert(article);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    /**
     * Operation to show all results from SQL Query
     */
    private static void extracted() {
        List<Article> extraire;
        try {
            extraire = jdbcOperations.extraireArticles();
            extraire.forEach(System.out::println);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }
}
