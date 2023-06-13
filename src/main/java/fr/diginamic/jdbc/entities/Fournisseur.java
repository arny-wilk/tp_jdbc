package fr.diginamic.jdbc.entities;

import fr.diginamic.jdbc.dao.FournisseurDAO;

import java.util.List;

public class Fournisseur implements FournisseurDAO {
    private int id;
    private String nom;

    public Fournisseur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    @Override
    public List<Fournisseur> extraire() {
        return null;
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        return false;
    }

    @Override
    public void insert(Fournisseur fournisseur) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
