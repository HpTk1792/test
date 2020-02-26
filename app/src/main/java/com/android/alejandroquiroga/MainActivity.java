package com.android.alejandroquiroga;

import android.content.Context;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.alejandroquiroga.Models.Assistents;
import com.android.alejandroquiroga.Models.Missatges;
import com.android.alejandroquiroga.ui.firebase.FirebaseViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean y = true;
    private AppBarConfiguration mAppBarConfiguration;
    private static Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ctx = this;
        AccessData.getAccessData(ctx).getInPostgres();

        sampleSqliteData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_sqlite, R.id.nav_postgresql,
                R.id.nav_firebase)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    private void sampleSqliteData(){
        AccessData ad = AccessData.getAccessData(ctx);
        List<Assistents> aList = ad.getAssistents();

        if(aList.size() == 0)
        for(int i = 0; i < 5; i++) ad.saveAssistents(new Assistents());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static Context getContext(){
        return ctx;
    }
}
