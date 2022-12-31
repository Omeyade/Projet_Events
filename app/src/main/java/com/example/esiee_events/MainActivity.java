package com.example.esiee_events;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomMenuFragment.OnItemClickListener,MoisFragment.OnItemClickListener, MoisFragment.OnButtonClickListener, JourFragment.OnItemClickListener {
    int displayMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = new Bundle();

        //On récupere la date actuelle
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        //Au lancement de l'application, on va lancer le fragment Mois avec le mois actuel
        displayMonth = Integer.parseInt(date.substring(3, 5))-10;

        setContentView(R.layout.activity_main);
        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        MoisFragment moisFragment = new MoisFragment();

        //On envoie le numéro du mois à afficher dans le fragment mois
        b.putString("displayMonth", String.valueOf(displayMonth));
        moisFragment.setArguments(b);

        //affichage des deux fragments dans leurs layouts
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_layout, moisFragment)
                .add(R.id.menu_fragment_layout, bottomMenuFragment)
                .commit();
    }

    //Lecture des boutons du Bottom Menu
    public void onItemSelectedBottom(int position) {
        Bundle b = new Bundle();

        //On récupere la date actuelle
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        int jourActuel = Integer.parseInt(date.substring(0, 2));
        int moisActuel = Integer.parseInt(date.substring(3, 5));
        int anneeActuel = Integer.parseInt(date.substring(6, 10));

        int m=0; int j=0;

        // Replace the current fragment with a new one
        //BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        JourFragment jourFragment = new JourFragment();
        MoisFragment moisFragment = new MoisFragment();
        ReglagesFragment reglagesFragment = new ReglagesFragment();

        //Calcul du prochain jour contenant un evenement

        boolean thisMonth=true;
        boolean found=false;
        int nextDay=0;
        int nextMonth=moisActuel-10;
        int nextYear=anneeActuel;

        ListeMois listeMois = new ListeMois();
        //On récupère la liste des évemenements du mois actuel
        ArrayList<Integer> listeEvents = listeMois.getDatalist().get(moisActuel-10).getListeEvents();

        //Si il n'y a pas d'évenement dans le mois actuel, on passe au mois suivant
        if(listeEvents.size()==0){
            thisMonth=false;
        }
        //Sinon, on essaye de trouver le prochain jour avec evenement dans le mois actuel
        else{
            //On parcours la liste des evenements du mois
            for(j=0;j<listeEvents.size() && !found;j++) {
                //Tant que la date des évenements est inférieure au jour actuel, on laisse nextDay à 0
                if (listeEvents.get(j) <= jourActuel) {
                    nextDay = 0;
                }
                //Le premier évenement après le jour actuel sera notre prochain évenement
                else {
                    nextDay = listeEvents.get(j);
                    //On sort de la boucle for
                    found = true;
                }
            }
            //si on ne trouve pas de prochain evenement dans le mois actuel, on passe au mois suivant
            if(nextDay==0){thisMonth=false;}
        }


       if(thisMonth==false){
           thisMonth=true;
           //On parcours tous les mois après le mois actuel, jusqu'à trouver un evenement
            for (m=moisActuel-9;m<listeMois.getDatalist().size() && thisMonth;m++){
                nextMonth = nextMonth+1;
                listeEvents = listeMois.getDatalist().get(m).getListeEvents();
                //Si il y a des évenements dans le mois
                if(listeEvents.size()>0){
                    //On recupère le premier évenement
                    nextDay=listeEvents.get(0);
                    //On récupère l'année de l'evenement
                    nextYear=listeMois.getDatalist().get(m).getAnnee();
                    //On sort de la boucle
                    thisMonth=false;

                }
            }
        }

       //Si on clique sur le bouton "Prochain evenement"
        if(position==1){
            //On envoie la date du prochain jour contenant un évenement dans le fragment jour
            b.putString("dayEvent", String.valueOf(nextDay));
            b.putString("monthEvent", String.valueOf(nextMonth));
            b.putString("yearEvent", String.valueOf(nextYear));
            jourFragment.setArguments(b);
            //On affiche le fragment jours
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, jourFragment)
                    .commit();
        }
        //Si on clique sur le bouton "Mois"
        else if(position==2){
            //On envoie le mois numéro du mois à afficher dans le fragment Mois
            b.putString("displayMonth", String.valueOf(displayMonth));
            moisFragment.setArguments(b);
            //On affiche le fragment Mois
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, moisFragment)
                    .commit();
        }
        //Si on clique sur le bouton "Réglages"
        else if(position==3){
            //On affiche le fragment Reglages
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, reglagesFragment)
                    .commit();
        }
    }

    public void onItemSelectedJour(int position, ArrayList<Integer> listEvent, int mois) {
        Bundle b = new Bundle();
        int taille=listEvent.size();

        JourFragment jourFragment = new JourFragment();

        //On parcours la liste des jours avec au moins un évenement
        for(int k=0; k<taille; k++){
            //Si le jour cliqué contient un évenement
            if(position == listEvent.get(k)){
                //On envoie toues les informations dans le fragment Jour
                b.putString("dayEvent", String.valueOf(position));
                b.putString("monthEvent", String.valueOf(mois));
                b.putString("yearEvent", String.valueOf(2022));
                jourFragment.setArguments(b);
                //On affiche le fragment Jour
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_layout, jourFragment)
                        .commit();
            }
        }

    }

    public void onItemSelectedButton(int position) {
        Bundle b = new Bundle();
        int tailleListe=5;

        //Si on clique sur le bouton Previous Month
        if(position==1){
            //On passe au mois précédent
            if(displayMonth>0){displayMonth=displayMonth-1;}
            else {displayMonth=0;}
        }
        //Si on clique sur le bouton Next Month
        if(position==2){
            //On passe au mois suivant
            if(displayMonth<tailleListe-1){displayMonth=displayMonth+1;}
            else{displayMonth=tailleListe-1;}
        }

        MoisFragment moisFragment = new MoisFragment();

        //On affiche le nouveau mois
        b.putString("displayMonth", String.valueOf(displayMonth));
        moisFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_layout, moisFragment)
                .commit();

    }

    public void onItemSelectedEvent(int position, String jour, String mois) {
        Bundle b = new Bundle();

        EvenementFragment evenementFragment = new EvenementFragment();

        //on envoie la date de l'évenement et le numero de l'évenement cliqué dans la liste
        b.putString("event", String.valueOf(position));
        b.putString("dayEvent", jour);
        b.putString("monthEvent", mois);
        evenementFragment.setArguments(b);

        //On affiche le fragment Evenement
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_layout, evenementFragment)
                .commit();
    }
}

