package com.example.esiee_events;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoisFragment extends Fragment {
    private GridView gridView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoisFragment newInstance(String param1, String param2) {
        MoisFragment fragment = new MoisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /*@Override
    public void onCreate(Bundle savedInstanceState) {


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_mois, container, false);
        this.gridView = (GridView)myView.findViewById(R.id.gridView);
        //Jour jourNov = new Jour(false, 0, 11, 2022, 0, 0, "", "", 0);

        //creation d'un mois pour le test
        ArrayList<Jour> Novembre = new ArrayList<>(30);
        for(int k=0; k<30;k++ ){
            Jour jourNov = new Jour(false, k+1, 11, 2022, 0, 0, "", "", "", 0);
            if (k==5){
                jourNov.setEvent(true);
                jourNov.setHeure(18);
                jourNov.setMinute(30);
                jourNov.setNom("Karaoke");
                jourNov.setLieu("Amphi 210");
                jourNov.setCommentaire("Karaoke organisé par le Club Chorale");
                jourNov.setPrix(0);
            }
            Novembre.add(jourNov);
        }

        //tableau d'entiers pour afficher dans le calendrier
        ArrayList<Integer> Mois = new ArrayList<>(30);
        for(int k=0; k<30;k++ ){
            Mois.add(Novembre.get(k).jour);
        }

        //Changer la couleur de l'element si il y a un evenement ce jour là
        for(int k=0; k<30; k++){
            if(Novembre.get(k).isEvent()==true){
                //gridView.getChildAt(k).setBackgroundColor(Color.parseColor("#18A608"));
                gridView.getChildAt(k).setBackgroundColor(0xFF0000FF);
                //Toast.makeText(getActivity(), Novembre.get(k).getNom(), Toast.LENGTH_SHORT).show();

            }
        }

        //Afficher le calendrier
        ArrayAdapter<Integer> arrayAdapter
                = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_list_item_1, Mois);
        gridView.setAdapter(arrayAdapter);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mois, container, false);

        return myView;

    }
}