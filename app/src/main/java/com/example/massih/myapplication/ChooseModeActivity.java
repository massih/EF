package com.example.massih.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseModeActivity extends AppCompatActivity {

    public static final String TAG = "ChooseModeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void chooseModeJoin(View v){
        Intent intent = new Intent(this, JoinServerActivity.class);
        startActivity(intent);
    }

    public void chooseModeCreate(View v){
        Intent intent = new Intent(this, CreateServerActivity.class);
        startActivity(intent);
    }
}
