package com.example.esiee_events;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JourFragment extends Fragment {
    private GridView gridView;
    String jourActuel;
    String  moisActuel;
    String  anneeActuelle;
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
        ArrayList<String> Annee = new ArrayList<String>(12){{
            for(int i=0; i<10; i++) add("");
            add("Novembre");
            add("Décembre");
        }};

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        jourActuel = date.substring(0, 2);
        moisActuel = Annee.get(Integer.parseInt(date.substring(3, 5))-1);
        anneeActuelle = date.substring(6, 10);


        ArrayList<String> listeEvents = new ArrayList<>();
        View myView = inflater.inflate(R.layout.fragment_jour, container, false);
        ListeMois listeMois = new ListeMois();
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("monthEvent"))-11);
        ArrayList<Jour> listeJours = mois.getListeJours();
        Jour j1 = listeJours.get(Integer.parseInt(getArguments().getString("dayEvent")));

        for(int k=0; k<j1.getEvent().size(); k++){
            listeEvents.add(j1.getEvent().get(k).getNom());
        }

        ListView list = (ListView) myView.findViewById(R.id.list_item);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), R.layout.grid_view_item_1,listeEvents);

        list.setAdapter(arrayAdapter);

        TextView dateJour = (TextView)myView.findViewById(R.id.jour_actuel);
        dateJour.setText(jourActuel);
        TextView dateMoisAnnee = (TextView)myView.findViewById(R.id.mois_annee_actuel);
        dateMoisAnnee.setText(moisActuel+" "+anneeActuelle);



        return myView;
    }
}