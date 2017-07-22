package com.example.michal.dietvoltwo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.michal.dietvoltwo.slajder.GolFragment;
import com.example.michal.dietvoltwo.slajder.HealthFragment;
import com.example.michal.dietvoltwo.slajder.ParametersFragment;
import com.example.michal.dietvoltwo.slajder.PersonFragment;
import com.example.michal.dietvoltwo.slajder.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionPageAdapter sectionPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("DietVol2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.container);
        setViewPager(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setViewPager(ViewPager viewPager){
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new PersonFragment(),"Dane identyfikacyjne");
        sectionPageAdapter.addFragment(new GolFragment(),"Cel diety");
        sectionPageAdapter.addFragment(new HealthFragment(),"Stan zdrowia");
        sectionPageAdapter.addFragment(new ParametersFragment(),"Kondycja organizmu");
        viewPager.setAdapter(sectionPageAdapter);
    }


}
