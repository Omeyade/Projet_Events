package com.example.esiee_events;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class EvenementFragment extends Fragment {
    int event;
    int day;

    public EvenementFragment() {
        // Required empty public constructor
    }


    public static EvenementFragment newInstance(String param1, String param2) {
        EvenementFragment fragment = new EvenementFragment();
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
        // On utilise inflate pour afficher le fragment dans le bon layout
        View myView = inflater.inflate(R.layout.fragment_evenement, container, false);

        ListeMois listeMois = new ListeMois();
        //on récupère le mois dans lequel se trouve l'évenement
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent")));
        ArrayList <Jour> listeJours = mois.getListeJours();
        //on récupère le jour dans lequel se trouve l'évenement
        Jour jour = listeJours.get(Integer.parseInt(getArguments().getString("dayEvent")));
        //on récupère l'évenement
        Evenement evenement = jour.getEvent().get(Integer.parseInt(getArguments().getString("event")));

        //Affichage de toutes les données de l'évenement

        TextView nomEvent = (TextView)myView.findViewById(R.id.Nom_Event);
        nomEvent.setText(evenement.getNom());

        TextView dateHeureDetail = (TextView)myView.findViewById(R.id.Date_et_Heure_detail);
        dateHeureDetail.setText("Le "+jour.getJour()+"/"+jour.getMois()+"/"+jour.getAnnee()+", à "+evenement.getHeure()+"H");

        TextView localisationDetail = (TextView)myView.findViewById(R.id.Localisation_detail);
        localisationDetail.setText(evenement.getLieu());

        TextView commentairesDetail = (TextView)myView.findViewById(R.id.Commentaires_detail);
        commentairesDetail.setText(evenement.getCommentaire());

        TextView prixDetail = (TextView)myView.findViewById(R.id.Prix_detail);
        float prix=evenement.getPrix();
        if (prix>0){
            prixDetail.setText(prix+"€");
        }
        else{
            prixDetail.setText("Gratuit !");
        }

        return myView;
    }
}