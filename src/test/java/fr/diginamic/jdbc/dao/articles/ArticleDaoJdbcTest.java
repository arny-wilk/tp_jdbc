package fr.diginamic.jdbc.dao.articles;

import fr.diginamic.jdbc.entities.Article;
import junit.framework.TestCase;

import java.util.List;

public class ArticleDaoJdbcTest extends TestCase {

    ArticleDaoJdbc testSample = new ArticleDaoJdbc();

    public void testExtraireArticles() {
        List<Article> articles;
        try {
            articles = testSample.extraireArticles();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String[] resultExpected = {"Article{idArticle=1, ref='A01', designation='Perceuse P1', prix=74.99, fournisseur=Fournisseur{id=1, nom='Française d'Imports'}}",
                "Article{idArticle=5, ref='A02', designation='Meuleuse 125mm', prix=37.85, fournisseur=Fournisseur{id=1, nom='Française d'Imports'}}",
                "Article{idArticle=7, ref='A03', designation='Perceuse à colonne', prix=185.25, fournisseur=Fournisseur{id=1, nom='Française d'Imports'}}"};

        for (int i = 0; i < resultExpected.length; i++) {
            assertEquals(resultExpected[i], String.valueOf(articles.get(i)));
        }
    }

    public void testInsert() {
        try {
            List<Article> articles = testSample.extraireArticles();
            for (int i = articles.size() - 1; i > articles.size() - 5; i--) {
                assertEquals("Peinture", articles.get(i).getDesignation().substring(0, 8));
            }

            for (int i = articles.size() - 1; i > articles.size() - 5; i--) {
                assertEquals("La Maison de la Peinture", articles.get(i).getFournisseur().getNom());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testUpdate() {
        try {
            int nb = testSample.update();
            assertEquals(2, nb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testDelete() {
        try {
            boolean nb = testSample.delete();
            assertTrue("The Delete operation has been successful", nb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}