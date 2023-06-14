package fr.diginamic.jdbc.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Article {
    private int idArticle;
    private String ref;
    private String designation;
    private BigDecimal prix;
    private Fournisseur fournisseur;

    public Article(int idArticle, String ref, String designation, BigDecimal prix, Fournisseur fournisseur) {
        this.idArticle = idArticle;
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", ref='" + ref + '\'' +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", fournisseur=" + fournisseur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return idArticle == article.idArticle && Objects.equals(ref, article.ref) && Objects.equals(designation, article.designation) && Objects.equals(prix, article.prix) && Objects.equals(fournisseur, article.fournisseur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, ref, designation, prix, fournisseur);
    }
}
