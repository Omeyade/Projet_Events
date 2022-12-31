package com.example.esiee_events;

import android.content.Context;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class MoisFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView gridView;
    Button previousMonth;
    Button nextMonth;
    int jourActuel;
    int moisActuel;


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {

    }

    //L'interface permet de faire le lien entre le fragment la Main Activity
    //Recuperer le numero du jour qui a été cliqué
    interface OnItemClickListener {
        void onItemSelectedJour(int position, ArrayList<Integer> dayEvent, int mois);
    }
    //Recuperer si le bouton Next ou Previous a été cliqué
    interface OnButtonClickListener {
        void onItemSelectedButton(int position);
    }

    private MoisFragment.OnItemClickListener callback1;
    private MoisFragment.OnButtonClickListener callback2;

    public MoisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            callback1 = (MoisFragment.OnItemClickListener) context;
            callback2 = (MoisFragment.OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Lecture de la date actuelle
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        jourActuel = Integer.parseInt(date.substring(0, 2));
        moisActuel = Integer.parseInt(date.substring(3, 5));

        // On utilise inflate pour afficher le fragment dans le bon layout
        View myView = inflater.inflate(R.layout.fragment_mois, container, false);

        //Si on clique sur le bouton Next Month, on renvoie la valeur 2 dans la callback
        nextMonth = (Button) myView.findViewById(R.id.Next_month);
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback2.onItemSelectedButton(2);

            }
        });

        //Si on clique sur le bouton Previous Month, on renvoie la valeur 1 dans la callback
        previousMonth = (Button) myView.findViewById(R.id.Previous_month);
        previousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback2.onItemSelectedButton(1);

            }
        });


        this.gridView = (GridView)myView.findViewById(R.id.gridView);
        ListeMois listeMois = new ListeMois();
        //On recupère le mois qui sera affiché
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("displayMonth")));

        //Liste correspondant aux mois de l'année pour l'affichage
        ArrayList<String> Annee = new ArrayList<String>(12){{
            add("Janvier");
            add("Fevrier");
            add("Mars");
            add("Avril");
            add("Mai");
            add("Juin");
            add("Juillet");
            add("Aout");
            add("Septembre");
            add("Octobre");
            add("Novembre");
            add("Decembre");
        }};

        //Affichage du nom du mois
        TextView nomMois = (TextView)myView.findViewById(R.id.Nom_Mois);
        nomMois.setText(Annee.get(mois.getNumeroMois()-1));

        //On crée le tableau de strings qui sera affiché dans la gridView
        ArrayList<String > numerosMois = new ArrayList<>(mois.getTailleMois());
        //On ajoute d'abord des cases vides car le premier jour du mois n'est pas forcement un lundi
        for(int k=0; k<mois.getPremierJour(); k++){
            numerosMois.add("");
        }
        //On ajoute tous les jours du mois
        for(int k=0; k<mois.getTailleMois();k++){
            numerosMois.add(String.valueOf(k+1));
        }


        //Affichage de la liste des jours dans la gridView, afin de former le calendrier
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), R.layout.grid_view_item_1,numerosMois );
        gridView.setAdapter(arrayAdapter);

        //On lit quel jour a été cliqué dessus, et cette valeur est renvoyée dans la main activity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback1.onItemSelectedJour(i-mois.getPremierJour()+1, mois.getListeEvents(), Integer.parseInt(getArguments().getString("displayMonth")));
            }

        });

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Changer la couleur de l'element de la gridView
                for (int k = 0; k < mois.tailleMois; k++) {
                    //S'il s'agit du jour actuel, il est coloré en gris
                    if (k==jourActuel && mois.getNumeroMois()==moisActuel) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFFBEC1E9);

                    }
                    //S'il y a un évenement ce jour là, il est coloré en bleu
                    if (mois.getListeJours().get(k).isEvent()) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFF0000FF);

                    }
                    //S'il s'agit du jour actuel et il y a un évenement ce jour là, il est coloré en rose
                    if (mois.getListeJours().get(k).isEvent() && k==jourActuel && mois.getNumeroMois()==moisActuel) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFFA526A8);

                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gridView.getViewTreeObserver()
                            .removeOnGlobalLayoutListener(this);
                } else {
                    gridView.getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                }

            }
        }
);
        return myView;
    }
}