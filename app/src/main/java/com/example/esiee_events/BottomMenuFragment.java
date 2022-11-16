package com.example.esiee_events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BottomMenuFragment extends Fragment implements View.OnClickListener {
    Button boutonSemaine;
    Button boutonMois;
    Button boutonReglages;

    interface OnItemClickListener {
        void onItemSelected(int position);
    }

    private OnItemClickListener callback;

    @Override
    public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        View myView = inflater.inflate(R.layout.fragment_bottom_menu, container, false);
        boutonSemaine = (Button) myView.findViewById(R.id.Semaine);
        boutonSemaine.setOnClickListener(this);
        boutonMois = (Button) myView.findViewById(R.id.Mois);
        boutonMois.setOnClickListener(this);
        boutonReglages = (Button) myView.findViewById(R.id.Reglages);
        boutonReglages.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        // implements your things
    }
}