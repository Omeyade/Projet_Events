package com.example.esiee_events;

import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;

public class ListeMois{
    static ArrayList<Mois> listeMois = new ArrayList<>();

    //creation d'un mois pour le test

    public ListeMois(){

        Mois octobre = new Mois(31, 10, 2022, 5);
        Mois novembre = new Mois(30, 11, 2022, 1);
        Mois decembre = new Mois(31, 12, 2022, 3);
        Mois janvier = new Mois(31, 01, 2023, 6);
        Mois fevrier = new Mois(28, 02, 2023, 2);

        Jour j1 = new Jour(true, 4, 10, 2022);
        Evenement OpenSession = new Evenement(17, "Open Session Chorale", "Amphi 210", "Venez découvrir le CLub Chorale", 0);
        Jour j2 = new Jour(true, 27, 10, 2022);
        Evenement CoursSolfege = new Evenement(18, "Apprenez à lire une partition !", "Amphi 110", "Cours de musique pour débutants", 4);

        Jour j3 = new Jour(true, 17, 11, 2022);

        Jour j4 = new Jour(true, 10, 12, 2022);
        Evenement jpo = new Evenement(13, "JPO", "ESIEE", "Journée Portes Ouvertes de l'ESIEE", 0);
        Jour j5 = new Jour(true, 6, 12, 2022);
        Evenement blindtest = new Evenement(19, "BlindTest", "Foyer", "BlindTest par le Club Foyer et le Club Chorale", 0);
        Jour j6 = new Jour(true, 12, 12, 2022);
        Evenement StandNoel = new Evenement(9, "Stand Marché de Noël", "En face du MD", "Le Club Musique tiendra son stand de Noël toute la journée à l'occasion de la semaine de Noël à l'ESIEE", 0);
        Evenement jam = new Evenement(19, "Jam Session", "Studio Musique", "Jam organisée par le Club Musique", 0);
        Jour j7 = new Jour(true, 16, 12, 2022);
        Evenement presentation = new Evenement(13, "Presentation des applications", "4201", "Les eleves de E4FE présentent leur application Android", 0);

        Jour j8 = new Jour(true, 2, 1, 2023);
        Evenement rentree = new Evenement(8, "Rentrée", "ESIEE", "Rentrée des élèves", 0);


        //ajouter l'evenement au jour
        j1.setEvent(OpenSession);
        j2.setEvent(CoursSolfege);

        j4.setEvent(jpo);
        j5.setEvent(blindtest);
        j6.setEvent(StandNoel);
        j6.setEvent(jam);
        j7.setEvent(presentation);

        j8.setEvent(rentree);

        //ajouter l'evenement au mois
        octobre.setJour(j1.getJour(), j1);
        octobre.setJour(j2.getJour(), j2);
        novembre.setJour(j3.getJour(), j3);
        decembre.setJour(j4.getJour(), j4);
        decembre.setJour(j5.getJour(), j5);
        decembre.setJour(j6.getJour(), j6);
        decembre.setJour(j7.getJour(), j7);
        janvier.setJour(j8.getJour(), j8);

        listeMois.add(octobre);
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
