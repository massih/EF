package com.example.massih.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseModeActivity extends AppCompatActivity {

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

    public void chooseModeJoin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void chooseModeCreate(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
