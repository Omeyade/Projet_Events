package com.example.esiee_events;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReglagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReglagesFragment extends Fragment {
    EditText mEditText;
    String text;
    TextView Name;

    public ReglagesFragment() {
        // Required empty public constructor
    }

    public static ReglagesFragment newInstance(String param1, String param2) {
        ReglagesFragment fragment = new ReglagesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //lecture du text tapé dans le EditText
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            text = mEditText.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            if(text==null){
                Name.setText("Hello !");
            }
            else{
                Name.setText("Hello " +text+" !");
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On utilise inflate pour afficher le fragment dans le bon layout
        View myView = inflater.inflate(R.layout.fragment_reglages, container, false);

        //Afichage du Nom tapé dans le Edit Text
        mEditText = myView.findViewById(R.id.Nom_Utilisateur);
        Name = myView.findViewById(R.id.Name);
        mEditText.addTextChangedListener(textWatcher);

        // Inflate the layout for this fragment
        return myView;
    }
}