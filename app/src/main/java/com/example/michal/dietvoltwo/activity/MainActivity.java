package com.example.michal.dietvoltwo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.slajder.GolFragment;
import com.example.michal.dietvoltwo.slajder.ParametersFragment;
import com.example.michal.dietvoltwo.slajder.PersonFragment;
import com.example.michal.dietvoltwo.slajder.SectionPageAdapter;
import com.example.michal.dietvoltwo.util.ConvertToByte;
import com.example.michal.dietvoltwo.util.Prefs;
import com.example.michal.dietvoltwo.util.SetSharedPreferences;

import java.util.Date;

import io.realm.Realm;

import static com.example.michal.dietvoltwo.util.Constant.CARBOHYDRATE_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.FAT_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.PROTEIN_TYPE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionPageAdapter sectionPageAdapter;
    private ViewPager viewPager;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("DietVol2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!Prefs.with(getApplicationContext()).getPreLoad()) {
                        setRealmData();
                    }
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });

        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        if (SetSharedPreferences.getEmailSharedPreferences(getApplicationContext()) != null && !SetSharedPreferences.getEmailSharedPreferences(getApplicationContext()).isEmpty()) {
            startActivity(new Intent(this, MealActivity.class));
        } else {
            setViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new PersonFragment(), "Dane identyfikacyjne");
        sectionPageAdapter.addFragment(new GolFragment(), "Cel diety");
        sectionPageAdapter.addFragment(new ParametersFragment(), "Kondycja organizmu");
        viewPager.setAdapter(sectionPageAdapter);
    }


    private void setRealmData() {
        realm.beginTransaction();

        ProductDto product = realm.createObject(ProductDto.class);
        product.setName("Ziemniaki");
        product.setProducent("test1");
        product.setProductTyp(PROTEIN_TYPE);
        product.setTimeOfDay("test1");
        product.setKcal(123);
        product.setB(1);
        product.setT(1);
        product.setW(1);
        product.setWW(1);
        product.setIg(1);
        product.setForDiabets(1);
        product.setCreate(new Date());
        product.setImage(ConvertToByte.convert(R.drawable.ziemniaki, getApplicationContext()));

        ProductDto product1 = realm.createObject(ProductDto.class);
        product1.setName("Losoś");
        product1.setProducent("test2");
        product1.setProductTyp(FAT_TYPE);
        product1.setTimeOfDay("test2");
        product1.setKcal(1234);
        product1.setB(12);
        product1.setT(12);
        product1.setW(12);
        product1.setWW(12);
        product1.setIg(12);
        product1.setForDiabets(12);
        product1.setCreate(new Date());
        product1.setImage(ConvertToByte.convert(R.drawable.losos, getApplicationContext()));


        ProductDto product2 = realm.createObject(ProductDto.class);
        product2.setName("Dzem");
        product2.setProducent("test3");
        product2.setProductTyp(PROTEIN_TYPE);
        product2.setTimeOfDay("test3");
        product2.setKcal(12345);
        product2.setB(123);
        product2.setT(123);
        product2.setW(123);
        product2.setWW(123);
        product2.setIg(123);
        product2.setForDiabets(123);
        product2.setCreate(new Date());
        product2.setImage(ConvertToByte.convert(R.drawable.dzem, getApplicationContext()));

        ProductDto product3 = realm.createObject(ProductDto.class);
        product3.setName("Kasza jaglana");
        product3.setProducent("test4");
        product3.setProductTyp(CARBOHYDRATE_TYPE);
        product3.setTimeOfDay("test4");
        product3.setKcal(123456);
        product3.setB(1234);
        product3.setT(1234);
        product3.setW(1234);
        product3.setWW(1234);
        product3.setIg(1234);
        product3.setForDiabets(1234);
        product3.setCreate(new Date());
        product3.setImage(ConvertToByte.convert(R.drawable.jaglak, getApplicationContext()));

        realm.commitTransaction();
        Prefs.with(this).setPreLoad(true);
    }

}
