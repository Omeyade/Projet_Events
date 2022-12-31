package com.example.esiee_events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mois {
    ArrayList<Jour> listeJours = new ArrayList<>(31);
    ArrayList<Integer> listeEvents=new ArrayList<>();
    int tailleMois;
    int numeroMois;
    int annee;
    int premierJour;

    public Mois(int tailleMois, int numeroMois, int annee, int premierJour) {
        this.tailleMois=tailleMois;
        this.numeroMois=numeroMois;
        this.annee=annee;
        this.premierJour=premierJour;

        //on remplit la liste avec des jours sans évenements pour l'initialiser
        for(int k=0; k<tailleMois;k++ ){
            Jour jour = new Jour(false, k+1, numeroMois, annee);
            listeJours.add(jour);
        }
    }

    public ArrayList<Jour> getListeJours() {
        return listeJours;
    }
    public int getTailleMois() {
        return tailleMois;
    }
    public int getNumeroMois() {
        return numeroMois;
    }
    public int getAnnee() {
        return annee;
    }
    public int getPremierJour() {
        return premierJour;
    }

    public ArrayList<Integer> getListeEvents(){
        Collections.sort(listeEvents);
        return listeEvents;
    }

    public void setListeJours() {
        this.listeJours=listeJours;
    }
    public void setTailleMois() {
        this.tailleMois=tailleMois;
    }
    public void setNumeroMois(){
        this.numeroMois=numeroMois;
    }
    public void setAnnee() {
        this.annee=annee;
    }
    public void setPremierJour(){
        this.premierJour=premierJour;
    }

    public void setJour(int dateJour, Jour jour) {
        //Le jour est modifié dans la liste de jours
        listeJours.set(dateJour, jour);
        //contient toutes les dates des evenements dans le mois
        listeEvents.add(dateJour);
    }

}
