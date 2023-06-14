package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> extraireArticles() throws Exception;
    void insert(Article article) throws Exception;
    int update() throws Exception;
    boolean delete() throws Exception;
}
