package com.example.esiee_events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EvenementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EvenementFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    public EvenementFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EvenementFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_evenement, container, false);

        ListeMois listeMois = new ListeMois();
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent"))-11);
        ArrayList <Jour> listeJours = mois.getListeJours();

        TextView nomEvent = (TextView)myView.findViewById(R.id.Nom_Event);
        nomEvent.setText(listeJours.get(Integer.parseInt(getArguments().getString("dayEvent"))).getNom());

        Toast.makeText(getActivity(), mois.getListeJours().get(Integer.parseInt(getArguments().getString("dayEvent"))).getNom(), Toast.LENGTH_SHORT).show();
        return myView;
    }
}