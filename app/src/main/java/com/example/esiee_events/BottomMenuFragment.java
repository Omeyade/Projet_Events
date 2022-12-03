package com.example.esiee_events;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


public class BottomMenuFragment extends Fragment implements View.OnClickListener {
    Button boutonSemaine;
    Button boutonMois;
    Button boutonReglages;

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
        View myView = inflater.inflate(R.layout.fragment_bottom_menu, container, false);
        boutonSemaine = (Button) myView.findViewById(R.id.Semaine);
        boutonSemaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemSelectedBottom(1);

            }
        });
        boutonMois = (Button) myView.findViewById(R.id.Mois);
        boutonMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemSelectedBottom(2);

            }
        });
        boutonReglages = (Button) myView.findViewById(R.id.Reglages);
        boutonReglages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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