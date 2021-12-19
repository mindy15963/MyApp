package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.techtown.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;  // 하단바
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Recommend_Btn frag1;
    private Star_Btn frag2;
    private login frag3;
    private ExtendSearch_Btn frag4;
    private alarm frag5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recommend:
                        setFrag(0);
                        break;
                    case R.id.action_star:
                        setFrag(1);
                        break;
                    case R.id.action_login:
                        setFrag(2);
                        break;
                    case R.id.action_extend_sc:
                        setFrag(3);
                        break;
                    case R.id.action_alarm:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
        frag1 = new Recommend_Btn();
        frag2 = new Star_Btn();
        frag3 = new login();
        frag4 = new ExtendSearch_Btn();
        frag5 = new alarm();
        setFrag(0);   // 첫 프래그먼트 화면 지정

    }

    public void clockClicked(View view) {
        Intent SwitchAlarm = new Intent(this, alarm.class);
        startActivity(SwitchAlarm);
    }
    public void displayAddAlarm(){
        Intent addAlarm = new Intent(this,AddAlarm.class);
        startActivity(addAlarm);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, frag5);
                ft.commit();
                break;
        }
    }
}