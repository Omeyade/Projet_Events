package com.example.esiee_events;

import android.content.Context;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class MoisFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private GridView gridView;
    Button previousMonth;
    Button nextMonth;
    int jourActuel;
    int moisActuel;


    // TODO: Rename and change types of parameters


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {

    }


    interface OnItemClickListener {
        void onItemSelectedJour(int position, ArrayList<Integer> dayEvent, int mois);
    }
    interface OnButtonClickListener {
        void onItemSelectedButton(int position);
    }

    private MoisFragment.OnItemClickListener callback1;
    private MoisFragment.OnButtonClickListener callback2;

    public MoisFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            callback1 = (MoisFragment.OnItemClickListener) context;
            callback2 = (MoisFragment.OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickListener");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment MoisFragment.
     */
    // TODO: Rename and change types and number of parameters



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        }
        String date = sdf.format(System.currentTimeMillis());
        jourActuel = Integer.parseInt(date.substring(0, 2));
        moisActuel = Integer.parseInt(date.substring(3, 5));


        View myView = inflater.inflate(R.layout.fragment_mois, container, false);

        nextMonth = (Button) myView.findViewById(R.id.Next_month);
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback2.onItemSelectedButton(2);

            }
        });

        previousMonth = (Button) myView.findViewById(R.id.Previous_month);
        previousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback2.onItemSelectedButton(1);

            }
        });

        this.gridView = (GridView)myView.findViewById(R.id.gridView);
        ListeMois listeMois = new ListeMois();
        Mois mois = listeMois.getDatalist().get(Integer.parseInt(getArguments().getString("displayMonth")));
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

        TextView nomMois = (TextView)myView.findViewById(R.id.Nom_Mois);
        nomMois.setText(Annee.get(mois.getNumeroMois()-1));



        //tableau d'entiers pour afficher dans le calendrier
        ArrayList<String > numerosMois = new ArrayList<>(mois.getTailleMois());
        for(int k=0; k<mois.getPremierJour(); k++){
            numerosMois.add("");
        }
        for(int k=0; k<mois.getTailleMois();k++){
            numerosMois.add(String.valueOf(k+1));
        }


        //Afficher le calendrier
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), R.layout.grid_view_item_1,numerosMois );

        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callback1.onItemSelectedJour(i-mois.getPremierJour()+1, mois.getListeEvents(), Integer.parseInt(getArguments().getString("displayMonth")));
            }

        });
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Changer la couleur de l'element si il y a un evenement ce jour là
                for (int k = 0; k < mois.tailleMois; k++) {
                    if (k==jourActuel && mois.getNumeroMois()==moisActuel) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFFBEC1E9);

                    }
                    if (mois.getListeJours().get(k).isEvent()) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFF0000FF);

                    }
                    if (mois.getListeJours().get(k).isEvent() && k==jourActuel && mois.getNumeroMois()==moisActuel) {
                        gridView.getChildAt(k+mois.getPremierJour()-1).setBackgroundColor(0xFFA526A8);

                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    gridView.getViewTreeObserver()
                            .removeOnGlobalLayoutListener(this);
                } else {
                    gridView.getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                }

            }
        }
);
        return myView;
    }
}