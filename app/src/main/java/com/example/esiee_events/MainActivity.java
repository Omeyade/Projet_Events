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
    //Button boutonMois;
    int displayMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = new Bundle();
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        displayMonth = Integer.parseInt(date.substring(3, 5))-11;


        setContentView(R.layout.activity_main);

        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        MoisFragment moisFragment = new MoisFragment();

        b.putString("displayMonth", String.valueOf(displayMonth));
        moisFragment.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_layout, moisFragment)
                .add(R.id.menu_fragment_layout, bottomMenuFragment)
                .commit();



    }

    //@Override // for nested class ListFragment.onItemClickListener
    public void onItemSelectedBottom(int position) {
        Bundle b = new Bundle();

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        int jourActuel = Integer.parseInt(date.substring(0, 2));
        int moisActuel = Integer.parseInt(date.substring(3, 5));
        int anneeActuel = Integer.parseInt(date.substring(6, 10));

        displayMonth = Integer.parseInt(date.substring(3, 5))-11;

        int m=0; int j=0;

        // Replace the current fragment with a new one
        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        SemaineFragment semaineFragment = new SemaineFragment();
        JourFragment jourFragment = new JourFragment();
        MoisFragment moisFragment = new MoisFragment();
        ReglagesFragment reglagesFragment = new ReglagesFragment();

        boolean thisMonth=true;
        boolean found=false;
        int nextDay=0;
        int nextMonth=moisActuel-11;
        int nextYear=anneeActuel;

        ListeMois listeMois = new ListeMois();
        //ArrayList<Integer> listeEvents = listeMois.getDatalist().get(moisActuel-11).getListeEvents();
        ArrayList<Integer> listeEvents = listeMois.getDatalist().get(moisActuel-11).getListeEvents();



        if(listeEvents.size()==0){
            thisMonth=false;
        }
        else{
            for(j=0;j<listeEvents.size() && !found;j++) {
                if (listeEvents.get(j) <= jourActuel) {
                    nextDay = 0;
                } else {
                    nextDay = listeEvents.get(j);
                    found = true;
                }
            }
            if(nextDay==0){thisMonth=false;}
        }



       if(thisMonth==false){
           thisMonth=true;
            for (m=moisActuel-10;m<listeMois.getDatalist().size() && thisMonth;m++){
                nextMonth = nextMonth+1;
                listeEvents = listeMois.getDatalist().get(m).getListeEvents();
                if(listeEvents.size()>0){
                    nextDay=listeEvents.get(0);
                    nextYear=listeMois.getDatalist().get(m).getAnnee();
                    thisMonth=false;

                }
            }
        }


        if(position==1){
            //Toast.makeText(this, "Position clicked = " + next, Toast.LENGTH_SHORT).show();
            b.putString("dayEvent", String.valueOf(nextDay));
            b.putString("monthEvent", String.valueOf(nextMonth));
            b.putString("yearEvent", String.valueOf(nextYear));
            jourFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, jourFragment)
                    .commit();
        }
        else if(position==2){
            b.putString("displayMonth", String.valueOf(displayMonth));
            moisFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, moisFragment)
                    .commit();
        }
        else if(position==3){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_layout, reglagesFragment)
                    .commit();
        }
    }

    public void onItemSelectedJour(int position, ArrayList<Integer> dayEvent, int mois) {
        Bundle b = new Bundle();
        int taille=dayEvent.size();

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        int jourActuel = Integer.parseInt(date.substring(0, 2));
        int moisActuel = Integer.parseInt(date.substring(3, 5));
        int anneeActuel = Integer.parseInt(date.substring(6, 10));

        //int dayEvent=mois.getListeEvents().get(0);
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // Replace the current fragment with a new one
        //BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        JourFragment jourFragment = new JourFragment();

        for(int k=0; k<taille; k++){
            if(position== dayEvent.get(k)){
                b.putString("dayEvent", String.valueOf(position));
                b.putString("monthEvent", String.valueOf(mois));
                b.putString("yearEvent", String.valueOf(2022));
                jourFragment.setArguments(b);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_layout, jourFragment)
                        .commit();
            }
        }

    }

    public void onItemSelectedButton(int position) {
        Bundle b = new Bundle();

        //ListeMois listeMois = new ListeMois();
        int tailleListe=4;



        if(position==1){
            if(displayMonth>0){displayMonth=displayMonth-1;}
            else {displayMonth=0;}
        }
        if(position==2){
            if(displayMonth<tailleListe-1){displayMonth=displayMonth+1;}
            else{displayMonth=tailleListe-1;}
        }

        Toast.makeText(this, "Position clicked = " + displayMonth, Toast.LENGTH_SHORT).show();

        MoisFragment moisFragment = new MoisFragment();

        b.putString("displayMonth", String.valueOf(displayMonth));
        moisFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_layout, moisFragment)
                .commit();



    }

    public void onItemSelectedEvent(int position, String jour, String mois) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        Bundle b = new Bundle();

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        int jourActuel = Integer.parseInt(date.substring(0, 2));
        int moisActuel = Integer.parseInt(date.substring(3, 5));
        int anneeActuel = Integer.parseInt(date.substring(6, 10));

        // Replace the current fragment with a new one
        EvenementFragment evenementFragment = new EvenementFragment();

        b.putString("event", String.valueOf(position));
        b.putString("dayEvent", jour);
        b.putString("monthEvent", mois);
        b.putString("yearEvent", String.valueOf(2022));
        evenementFragment.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_layout, evenementFragment)
                .commit();

    }
}

