package com.example.esiee_events;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomMenuFragment.OnItemClickListener{
    //Button boutonMois;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        SemaineFragment semaineFragment = new SemaineFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_layout, bottomMenuFragment)
                .add(R.id.menu_fragment_layout, semaineFragment)
                .commit();

    }

    //@Override // for nested class ListFragment.onItemClickListener
    public void onItemSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // Replace the current fragment with a new one
        MoisFragment moisFragment = new MoisFragment();
        //A faire--------------------------------------
        //Fragment.setIndex(position);
        //HELLOWWWW

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_layout, moisFragment)
                .commit();
        }
    }

}