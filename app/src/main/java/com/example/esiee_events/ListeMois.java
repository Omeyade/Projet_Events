package com.example.esiee_events;

import java.util.ArrayList;

public class ListeMois{
    static ArrayList<Mois> listeMois = new ArrayList<>(2);

    //creation d'un mois pour le test

    public ListeMois(){
        Mois novembre = new Mois(30, 11, 2022, 1);
        Mois decembre = new Mois(31, 12, 2022, 3);
        Jour j1 = new Jour(true, 6, 11, 2022);
        Evenement karaoke = new Evenement(18, 2, "Karaoke", "Amphi 210", "Karaoke organisé par le Club Chorale", 5);
        Jour j2 = new Jour(true, 9, 11, 2022);
        Evenement jpo = new Evenement(13, 4, "JPO", "ESIEE", "JPO", 0);
        Jour j3 = new Jour(true, 14, 12, 2022);
        Evenement blindtest = new Evenement(19, 1, "BlindTest", "Foyer", "Blindtest organisé au foyer", 0);

        //ajouter l'evenement au jour
        j1.setEvent(karaoke);
        j1.setEvent(jpo);
        j2.setEvent(jpo);
        j3.setEvent(blindtest);

        //ajouter l'evenement au mois
        novembre.setJour(j1.getJour(), j1);
        novembre.setJour(j2.getJour(), j2);
        decembre.setJour(j3.getJour(), j3);

        listeMois.add(novembre);
        listeMois.add(decembre);
    }


    public static ArrayList<Mois> getDatalist() {
        return listeMois;
    }
}
