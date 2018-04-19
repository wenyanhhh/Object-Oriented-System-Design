package edu.stanford.cs108.cities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;

public class SecondActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        db = openOrCreateDatabase("cities",MODE_PRIVATE,null);

        Button searchBtn = findViewById(R.id.search_Btn);

        searchBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onSearch();
                    }
                }

        );
    }

    public void onSearch() {
        String command = getSearchCommand();
        Cursor cursor = db.rawQuery(command,null);

        String[] fromArray = {"name", "continent", "population"};
        int[] toArray = {R.id.custom_text_1, R.id.custom_text_3, R.id.custom_text_2};


        ListAdapter adapter = new SimpleCursorAdapter(this,R.layout.custom_list_layout,cursor,fromArray,toArray,0);

        ListView listView = findViewById(R.id.search_Output);

        listView.setAdapter(adapter);
    }

    private String getSearchCommand() {
        String command = "SELECT * FROM cities";
        EditText nameEditText =  findViewById(R.id.name_editText);
        EditText continentEditText = findViewById(R.id.continent_editText);
        EditText populationEditText = findViewById(R.id.population_editText);

        String name = nameEditText.getText().toString();
        String continent = continentEditText.getText().toString();
        String population = populationEditText.getText().toString();

        boolean checkName = false;
        boolean checkContinent = false;
        //boolean checkPopulation = false;

        String populationChoice = "";
        RadioGroup populationChoiceGroup = findViewById(R.id.populationChoice_group);
        int currentCheck = populationChoiceGroup.getCheckedRadioButtonId();

        switch(currentCheck) {
            case R.id.less:
                populationChoice = "<";
                break;
            case R.id.greater:
                populationChoice = ">=";
                break;

        }

        if(!name.equals("") || !continent.equals("") || !population.equals("")) {
            command = command + " WHERE";
        }


        if (!name.equals("")) {
            command = command + " name LIKE " + "\"" + name + "%" + "\"";
            checkName = true;
        }

        if (!continent.equals("")) {
            if(checkName) {
                command = command + " AND";
            }
            command = command + " continent LIKE " + "\"" + "%" + continent + "%" + "\"";
            checkContinent = true;
        }

        if (!population.equals("")) {
            if(checkName || checkContinent) {
                command = command + " AND";
            }
            command = command + " population " +  populationChoice + " " + population;
        }

        command = command + ";";

        return command;

    }




}
