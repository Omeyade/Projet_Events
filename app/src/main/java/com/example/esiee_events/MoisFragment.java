package com.example.esiee_events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        this.gridView = (GridView)findViewById(R.id.gridView);

        ArrayList<Integer> Mois = new ArrayList<>(31);
        for(int k=0; k<31;k++ ){
            Mois.add(k+1);
        }

        ArrayAdapter<Integer> arrayAdapter
                = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, Mois);
        gridView.setAdapter(arrayAdapter);



    }



        this.gridView = (GridView)findViewById(R.id.gridView);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mois, container, false);
    }
}