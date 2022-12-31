package com.example.esiee_events;

public class Evenement {
    int heure;
    String nom;
    String lieu;
    String commentaire;
    float prix;

    public Evenement(int heure, String nom, String lieu, String commentaire, float prix) {
        this.heure = heure;
        this.nom = nom;
        this.lieu = lieu;
        this.commentaire = commentaire;
        this.prix = prix;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }



    public int getHeure() {
        return heure;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public float getPrix() {
        return prix;
    }

}
