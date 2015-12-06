package com.example.massih.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateServerActivity extends AppCompatActivity {

    public static final String TAG = "CreateServerActivity";
    private EditText server_name_edittxt;
    NsdHelper nsdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_server);
        server_name_edittxt = (EditText)findViewById(R.id.server_name_input);
        nsdHelper = new NsdHelper(this);
        nsdHelper.initializeRegistrationListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        nsdHelper.tearDown();
        super.onDestroy();
    }

    public void createServerBtn(View v){
        String server_name = server_name_edittxt.getText().toString();
        nsdHelper.registerService(33333, server_name);
    }

}
