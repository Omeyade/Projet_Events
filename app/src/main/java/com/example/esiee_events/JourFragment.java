package com.example.esiee_events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JourFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JourFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        View myView = inflater.inflate(R.layout.fragment_jour, container, false);
        ListeMois listeMois = new ListeMois();
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent"))-11);
        ArrayList<Jour> listeJours = mois.getListeJours();
        Jour j1 = listeJours.get(Integer.parseInt(getArguments().getString("dayEvent")));
        // Inflate the layout for this fragment
        TextView jour1 = (TextView)myView.findViewById(R.id.jour1);
        jour1.setText(j1.getEvent().get(0).getNom());
        TextView jour2 = (TextView)myView.findViewById(R.id.jour2);
        jour2.setText(j1.getEvent().get(1).getNom());


        return myView;
    }
}