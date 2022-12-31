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

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    interface OnItemClickListener {
        void onItemSelectedEvent(int position, String  jour, String mois);
    }

    private JourFragment.OnItemClickListener callback;




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

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        /*jourActuel = date.substring(0, 2);
        moisActuel = Annee.get(Integer.parseInt(date.substring(3, 5))-1);
        anneeActuelle = date.substring(6, 10);*/

        /*int moisVal;
        if(Integer.parseInt(getArguments().getString("monthEvent"))<=12){
            moisVal=Integer.parseInt(getArguments().getString("monthEvent"))-1;
        }
        else{
            moisVal=Integer.parseInt(getArguments().getString("monthEvent"))%12-1;
        }*/




        ArrayList<String> listeEvents = new ArrayList<>();
        View myView = inflater.inflate(R.layout.fragment_jour, container, false);
        ListeMois listeMois = new ListeMois();
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent")));
        ArrayList<Jour> listeJours = mois.getListeJours();
        Jour j1 = listeJours.get(Integer.parseInt(getArguments().getString("dayEvent")));

        jourAffiche = getArguments().getString("dayEvent");
        moisAffiche = Annee.get(mois.getNumeroMois()-1);
        anneeAffiche = String.valueOf(j1.getAnnee());

        for(int k=0; k<j1.getEvent().size(); k++){
            listeEvents.add(j1.getEvent().get(k).getNom());
        }

        this.list = (ListView) myView.findViewById(R.id.list_item);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), R.layout.grid_view_item_1,listeEvents);

        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback.onItemSelectedEvent(i, getArguments().getString("dayEvent"), getArguments().getString("monthEvent"));
            }

        });

        TextView dateJour = (TextView)myView.findViewById(R.id.jour_actuel);
        dateJour.setText(jourAffiche);
        TextView dateMoisAnnee = (TextView)myView.findViewById(R.id.mois_annee_actuel);
        dateMoisAnnee.setText(moisAffiche+" "+anneeAffiche);
        //TextView userName = (TextView)myView.findViewById(R.id.user_name);
        //userName.setText(getArguments().getString("Name"));



        return myView;
    }
}