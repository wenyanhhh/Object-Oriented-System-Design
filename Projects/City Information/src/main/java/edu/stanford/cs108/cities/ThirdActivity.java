package edu.stanford.cs108.cities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;



public class ThirdActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        db = openOrCreateDatabase("cities",MODE_PRIVATE,null);

        Button addBtn = (Button) findViewById(R.id.add_Btn);

        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAdd();
                        clear();
                        showToast();
                    }
                }

        );

    }

    public void onAdd() {
        String command = getInsertCommand();
        db.execSQL(command);
    }

    public void showToast() {
        Toast toast = Toast.makeText(ThirdActivity.this, nameInput+ " Added",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clear() {
        EditText nameInputText = (EditText) findViewById(R.id.nameInput_editText);
        EditText continentInputText = (EditText) findViewById(R.id.continentInput_editText);
        EditText populationInputText = (EditText) findViewById(R.id.populationInput_editText);
        nameInputText.setText("");
        continentInputText.setText("");
        populationInputText.setText("");


    }

    public String getInsertCommand() {
        String command = "INSERT INTO cities VALUES";
        EditText nameInputText = findViewById(R.id.nameInput_editText);
        EditText continentInputText = findViewById(R.id.continentInput_editText);
        EditText populationInputText = findViewById(R.id.populationInput_editText);

        nameInput = nameInputText.getText().toString();
        String continentInput = continentInputText.getText().toString();
        int populationInput = Integer.parseInt(populationInputText.getText().toString());

        command = command + "(" + "\"" + nameInput + "\"" + ", " + "\""+ continentInput + "\"" + ", "
                + populationInput + ", " + "NULL" + ");";

        return command;

    }

}
