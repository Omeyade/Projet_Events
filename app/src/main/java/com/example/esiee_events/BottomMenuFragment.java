package com.example.esiee_events;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BottomMenuFragment extends Fragment implements View.OnClickListener {
    Button boutonJour;
    Button boutonMois;
    Button boutonReglages;

    //L'interface permet de faire le lien entre le fragment la Main Activity
    interface OnItemClickListener {
        void onItemSelectedBottom(int position);
    }

    private OnItemClickListener callback;

    public BottomMenuFragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            callback = (OnItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        // On utilise inflate pour afficher le fragment dans le bon layout
        View myView = inflater.inflate(R.layout.fragment_bottom_menu, container, false);
        boutonJour = (Button) myView.findViewById(R.id.Jour);
        boutonJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si on clique sur le bouton Jour, on renvoie la valeur 1 dans la callback
                callback.onItemSelectedBottom(1);

            }
        });
        boutonMois = (Button) myView.findViewById(R.id.Mois);
        boutonMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si on clique sur le bouton Mois, on renvoie la valeur 2 dans la callback
                callback.onItemSelectedBottom(2);

            }
        });
        boutonReglages = (Button) myView.findViewById(R.id.Reglages);
        boutonReglages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si on clique sur le bouton Reglages, on renvoie la valeur 3 dans la callback
                callback.onItemSelectedBottom(3);
            }
        });

        return myView;
    }

    @Override
    public void onClick(View v) {
        // implements your things
    }
}