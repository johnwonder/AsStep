package com.example.john.databasetest;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cengalabs.flatui.FlatUI;


public class MainActivity extends Activity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // FlatUI.setDefaultTheme(FlatUI.ORANGE);
    }

    private  MyDatabaseHelper dbHelper;

    public MainActivity() {

        super();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FlatUI.setDefaultTheme(FlatUI.ORANGE);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FlatUI.setDefaultTheme(FlatUI.ORANGE);
        //FlatUI.setDefaultTheme(R.array.blood);
        //ActionBar actionBar = getActionBar();
        //actionBar.show();
        getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this,FlatUI.ORANGE, false));
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
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
}
