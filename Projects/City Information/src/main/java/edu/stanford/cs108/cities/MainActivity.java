package edu.stanford.cs108.cities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("cities",MODE_PRIVATE,null);

        Cursor tableCursor = db.rawQuery(
                "SELECT * FROM sqlite_master WHERE type='table' AND name='cities';",
                null);
        if(tableCursor.getCount() == 0) {
            setupDatabase();
            populateDatabase();
        }

        final Button resetBtn = (Button) findViewById(R.id.reset_id);

        resetBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetDatabase();
                        showToast();
                    }
                }

        );
    }

    private void setupDatabase() {

        String setupStr = "CREATE TABLE cities ("
                + "name TEXT, continent TEXT, population INTEGER,"
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ");";
        System.err.println(setupStr);
        db.execSQL(setupStr);


    }


    private void populateDatabase() {
        String dataStr = "INSERT INTO cities VALUES "
                + "('Cairo','Africa',15200000,NULL),"
                + "('Lagos','Africa',21000000,NULL),"
                + "('Kyoto','Asia',1474570,NULL),"
                + "('Mumbai','Asia',20400000,NULL),"
                + "('Shanghai','Asia',24152700,NULL),"
                + "('Melbourne','Australia',3900000,NULL),"
                + "('London','Europe',8580000,NULL),"
                + "('Rome','Europe',2715000,NULL),"
                + "('Rostov-on-Don','Europe',1052000,NULL),"
                + "('San Francisco','North America',5780000,NULL),"
                + "('San Jose','North America',7354555,NULL),"
                + "('New York','North America',21295000,NULL),"
                + "('Rio de Janeiro','South America',12280702,NULL),"
                + "('Santiago','South America',5507282,NULL)"
                + ";";
        System.err.println(dataStr);
        db.execSQL(dataStr);
    }


    public void resetDatabase( ) {
        String clearStr = "DROP TABLE IF EXISTS cities;";
        db.execSQL(clearStr);
        setupDatabase();
        populateDatabase();
    }

    public void showToast() {
        Toast toast = Toast.makeText(MainActivity.this,
                "Database Reset", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onLookUp(View view) {
        Intent intent = new Intent(view.getContext(), SecondActivity.class);
        startActivity(intent);

    }

    public void onAdd(View view) {
        Intent intent2 = new Intent(view.getContext(), ThirdActivity.class);
        startActivity(intent2);
    }


}
