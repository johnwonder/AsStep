package com.example.john.databasetest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);//upgrade fill 2
        Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("name","The Da vinci code");
                values.put("author","Dan brow");
                values.put("pages",454);
                values.put("price",16.96);

                db.insert("Book",null,values);
                values.clear();

                values.put("name","The lost sybmol");
                values.put("author","Dan brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book", null, values);
            }
        });

        Button updateData = (Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book", values, "name=?", new String[]{"The Da vinci code"});
            }
        });

        Button deleteButton  =(Button)findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages> ?", new String[]{"500"});
            }
        });

        Button queryButton = (Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);

                if(cursor.moveToFirst())
                {
                   do {
                       String name = cursor.getString(cursor.getColumnIndex("name"));
                       String author = cursor.getString(cursor.getColumnIndex("author"));

                       int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                       double price = cursor.getDouble(cursor.getColumnIndex("price"));

                       Log.d("MainActivity","book name is "+ name);
                       Log.d("MainActivity","book author is "+ author);
                       Log.d("MainActivity","book pages is "+ pages);
                       Log.d("MainActivity","book price is "+ price);
                   }while (cursor.moveToNext());

                }
                cursor.close();
            }
        });

        Button replaceData = (Button)findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    db.delete("Book",null,null);
                    if(true){
                        throw new  NullPointerException();
                    }
                    ContentValues values = new ContentValues();
                    values.put("name","Game is Thrones");
                    values.put("author","George Martin");
                    values.put("pages",602);
                    values.put("price",20.85);
                    db.insert("Book",null,values);
                    db.setTransactionSuccessful();

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    db.endTransaction();
                }
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
