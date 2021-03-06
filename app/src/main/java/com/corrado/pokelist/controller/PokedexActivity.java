package com.corrado.pokelist.controller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;



import com.corrado.pokelist.views.FragAdapter;
import com.corrado.pokelist.R;

/**
 * Created by Corrado on 20/12/2019.
 */

public class PokedexActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private FragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new FragAdapter(getSupportFragmentManager());
        tablayout.setTabTextColors(getResources().getColor(R.color.Whitecolor),getResources().getColor(R.color.bleu));

        adapter.AddFragment(new FragmentPokedex(), "Pokedex");
        adapter.AddFragment(new FragmentVideo(), "Video");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

    }


}
