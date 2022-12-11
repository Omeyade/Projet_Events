package com.example.esiee_events;

import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;

public class ListeMois{
    static ArrayList<Mois> listeMois = new ArrayList<>(2);

    //creation d'un mois pour le test

    public ListeMois(){
        Mois novembre = new Mois(30, 11, 2022, 1);
        Mois decembre = new Mois(31, 12, 2022, 3);
        Mois janvier = new Mois(31, 01, 2023, 2);
        Mois fevrier = new Mois(31, 02, 2023, 2);
        Jour j1 = new Jour(true, 6, 11, 2022);
        Evenement karaoke = new Evenement(18, 2, "Karaoke", "Amphi 210", "Karaoke organisé par le Club Chorale", 5);
        Jour j2 = new Jour(true, 5, 11, 2022);
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
        decembre.setJour(j2.getJour(), j2);
        decembre.setJour(j3.getJour(), j3);
        //janvier.setJour(j2.getJour(), j2);

        listeMois.add(novembre);
        listeMois.add(decembre);
        listeMois.add(janvier);
        listeMois.add(fevrier);
    }


    public static ArrayList<Mois> getDatalist() {
        return listeMois;
    }

    /*public Jour getNextDay(){
        int jourActuel;
        int moisActuel;
        int anneeActuel;
        int nextEvent=1;
        ArrayList<Integer> listeEvents=null;
        int m=0, j=0;
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        jourActuel = Integer.parseInt(date.substring(0, 2));
        moisActuel = Integer.parseInt(date.substring(3, 5));
        anneeActuel = Integer.parseInt(date.substring(6, 10));

        m=0; j=0;



        listeEvents = listeMois.get(0).getListeEvents();
        while (listeEvents.get(j)<jourActuel){
            j=j+1;
            nextEvent = listeEvents.get(j);
        }
        boolean b=false;

        while (b==false) {
            listeMois.size();m+ +
            int j=0;
            while()int j=0;j< listeMois.get(m).getListeEvents().size(); j++){
                if(j)
            }
        return listeMois.get(moisActuel).getListeJours().get(nextEvent);


    }*/
}
