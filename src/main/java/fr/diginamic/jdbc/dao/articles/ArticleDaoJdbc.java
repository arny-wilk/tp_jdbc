package fr.diginamic.jdbc.dao.articles;

import fr.diginamic.jdbc.dao.ArticleDAO;
import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleDaoJdbc implements ArticleDAO {

    private static final String SECURED_QUERY = "INSERT INTO ARTICLE (ID, REF, DESIGNATION, PRIX, ID_FOU) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT a.ID, a.REF, a.DESIGNATION, a.PRIX, f.ID, f.NOM  FROM ARTICLE a, FOURNISSEUR f \n" +
            "WHERE a.ID_FOU = f.ID;";

    private static final String CALC_AVERAGE_PRICE = "SELECT f.NOM, AVG(PRIX) avg_price FROM ARTICLE a, FOURNISSEUR f \n" +
            "WHERE a.ID_FOU = f.ID\n" +
            "GROUP BY a.ID_FOU;";
    private static final String SECURED_DELETE = "DELETE FROM ARTICLE WHERE ARTICLE.DESIGNATION LIKE '%Peinture%'";
    private static final String SECURED_DELETE_FOURNISSEUR = "DELETE FROM FOURNISSEUR WHERE FOURNISSEUR.NOM=?";
    private static final String SECURED_UPDATE = "UPDATE ARTICLE SET PRIX=(PRIX-PRIX*0.25) WHERE ARTICLE.DESIGNATION LIKE '%mate%'";
    private static final String URL_DB;
    private static final String USER_DB;
    private static final String PASSWORD_DB;


    static {
        ResourceBundle myresConf = ResourceBundle.getBundle("db");
        URL_DB = myresConf.getString("MYSQL_ADDON_HOST");
        USER_DB = myresConf.getString("MYSQL_ADDON_USER");
        PASSWORD_DB = myresConf.getString("MYSQL_ADDON_PASSWORD");
    }

    public HashMap<String, BigDecimal> avgPrice() throws Exception {
        HashMap<String, BigDecimal> avgPriceByFournisseurs = new HashMap<>();
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement find = cnx.createStatement()) {
            ResultSet rs = find.executeQuery(CALC_AVERAGE_PRICE);
            while (rs.next()) {
                avgPriceByFournisseurs.put(rs.getString("f.NOM")
                        , BigDecimal.valueOf(rs.getDouble("avg_price")));
            }
        }
        return avgPriceByFournisseurs;
    }

    @Override
    public List<Article> extraireArticles() throws Exception {
        List<Article> articles = new ArrayList<>();
        HashMap<Integer, Fournisseur> fournisseurs = new HashMap<>();
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement select = cnx.createStatement()) {
            ResultSet curseur = select.executeQuery(SELECT_QUERY);
            while (curseur.next()) {
                Integer id = curseur.getInt("a.ID");
                String ref = curseur.getString("a.REF");
                String designation = curseur.getString("a.DESIGNATION");
                BigDecimal prix = BigDecimal.valueOf(curseur.getDouble("a.PRIX"));
                Integer idFou = curseur.getInt("f.ID");
                String nom = curseur.getString("f.NOM");
                if(!fournisseurs.containsKey(idFou)){
                    fournisseurs.put(idFou, new Fournisseur(idFou, nom));
                }
                Article article = new Article(id
                        , ref
                        , designation
                        , prix
                        , fournisseurs.get(idFou));
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public void insert(Article article) throws Exception {
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement insertion = cnx.prepareStatement(SECURED_QUERY);
        ) {
            insertion.setString(1, String.valueOf(article.getIdArticle()));
            insertion.setString(2, article.getRef());
            insertion.setString(3, article.getDesignation());
            insertion.setString(4, String.valueOf(article.getPrix()));
            insertion.setString(5, String.valueOf(article.getFournisseur().getId()));
            int nb = insertion.executeUpdate();
            System.out.println(nb);
        }

    }

    @Override
    public int update() throws Exception {
        int nb;
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             Statement update = cnx.createStatement();
        ) {
            nb = update.executeUpdate(SECURED_UPDATE);
        }
        return nb;
    }

    @Override
    public boolean delete() throws Exception {
        int nb;
        int nb2;
        try (Connection cnx = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
             PreparedStatement delete = cnx.prepareStatement(SECURED_DELETE_FOURNISSEUR);
             PreparedStatement delete2 = cnx.prepareStatement(SECURED_DELETE)
        ) {
            delete.setString(1, "La Maison de la Peinture");
            nb = delete.executeUpdate();
            nb2 = delete2.executeUpdate();
        }
        return nb > 0 && nb2 > 0;
    }
}
