package com.example.esiee_events;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class JourFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView list;
    String jourAffiche;
    String  moisAffiche;
    String  anneeAffiche;


    //L'interface permet de faire le lien entre le fragment la Main Activity
    interface OnItemClickListener {
        void onItemSelectedEvent(int position, String  jour, String mois);
    }

    private JourFragment.OnItemClickListener callback;

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }


    public JourFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            callback = (JourFragment.OnItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickListener");
        }
    }

    public static JourFragment newInstance(String param1, String param2) {
        JourFragment fragment = new JourFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        ArrayList<String> listeEvents = new ArrayList<>();
        // On utilise inflate pour afficher le fragment dans le bon layout
        View myView = inflater.inflate(R.layout.fragment_jour, container, false);
        ListeMois listeMois = new ListeMois();
        //on récupère le mois dans lequel se trouve l'évenement
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent")));
        ArrayList<Jour> listeJours = mois.getListeJours();
        //on récupère le jour dans lequel se trouve l'évenement
        Jour j1 = listeJours.get(Integer.parseInt(getArguments().getString("dayEvent")));

        jourAffiche = getArguments().getString("dayEvent");
        moisAffiche = Annee.get(mois.getNumeroMois()-1);
        anneeAffiche = String.valueOf(j1.getAnnee());

        //création de la liste des évenements qui sera affiché
        for(int k=0; k<j1.getEvent().size(); k++){
            listeEvents.add(j1.getEvent().get(k).getNom());
        }

        //afichage de la liste des évenements dans la listView
        this.list = (ListView) myView.findViewById(R.id.list_item);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), R.layout.grid_view_item_1,listeEvents);
        list.setAdapter(arrayAdapter);

        //On  lit la position de l'élément de la liste qui a été cliqué dessus, pour savoir quel évenement sera affiché
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback.onItemSelectedEvent(i, getArguments().getString("dayEvent"), getArguments().getString("monthEvent"));
            }

        });

        //affichage de la date
        TextView dateJour = (TextView)myView.findViewById(R.id.jour_actuel);
        dateJour.setText(jourAffiche);
        TextView dateMoisAnnee = (TextView)myView.findViewById(R.id.mois_annee_actuel);
        dateMoisAnnee.setText(moisAffiche+" "+anneeAffiche);

        return myView;
    }
}