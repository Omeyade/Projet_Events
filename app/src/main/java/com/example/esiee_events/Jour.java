package com.example.esiee_events;

import java.util.ArrayList;

public class Jour {
    boolean event;//si le jour contient au moins un évenement ou non
    int jour;
    int mois;
    int annee;
    ArrayList<Evenement> listeEvents= new ArrayList<>();

    public Jour(boolean event, int jour, int mois, int annee) {
        this.event = event;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;

    }


    public void setEvent(boolean event) {
        this.event = event;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }


    public boolean isEvent() {
        return event;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }


    public void setEvent(Evenement evenement) {
        listeEvents.add(evenement);
    }

    public ArrayList<Evenement> getEvent() {
        return listeEvents;
    }


}
