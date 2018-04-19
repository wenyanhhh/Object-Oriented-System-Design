package edu.stanford.cs108.shippingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int sum = 0;
    double rate = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*
    On click method which records
    the shipping type the user choose.
    Only one type can be chosen at a time.
    Initial type is Next Day.
     */
    public void onClickRadioButton(View v){

        RadioButton nextDay = findViewById(R.id.next_day_id);
        RadioButton secondDay = findViewById(R.id.second_day_id);
        RadioButton standard = findViewById(R.id.standard_id);

        switch (v.getId()){
            case R.id.next_day_id:
                secondDay.setChecked(false);
                standard.setChecked(false);
                rate = 10;
                break;
            case R.id.second_day_id:
                nextDay.setChecked(false);
                standard.setChecked(false);
                rate = 5;
                break;
            case R.id.standard_id:
                nextDay.setChecked(false);
                secondDay.setChecked(false);
                rate = 3;
                break;
        }

    }

    /*
    On click method which calculate the cost
    based on the shipping type, insurance
    and object weight.
    Round the cost up to the nearest dollar.
     */
    public void onClickCalculate(View v){
        EditText inputWeight = findViewById(R.id.weight_id);
        CheckBox insurance = findViewById(R.id.insurance_id);
        Boolean checked = insurance.isChecked();
        TextView displayCost = findViewById(R.id.cost_id);
        double weight = Double.parseDouble(inputWeight.getText().toString());
        sum = 0;
        if (checked){
            sum = (int) Math.round(weight * rate * 1.2);
        } else {
            sum = (int) Math.round(weight * rate);
        }
        String cost = "Cost:" + String.valueOf(sum);
        displayCost.setText(cost);
        inputWeight.setText("");
    }
}
