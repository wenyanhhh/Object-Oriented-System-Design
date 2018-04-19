package edu.stanford.cs108.colorpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    On click method which get the chosen RGB numbers
    and show the corresponding color
     */
    public void onClickChangeColor(View v){
        TextView colorView = findViewById(R.id.color_id);
        TextView colorRgb = findViewById(R.id.rgb_id);
        SeekBar redRgb = findViewById(R.id.seekBar_red_id);
        SeekBar greenRgb = findViewById(R.id.seekBar_green_id);
        SeekBar blueRgb = findViewById(R.id.seekBar_blue_id);

        int red = redRgb.getProgress();
        int green = greenRgb.getProgress();
        int blue = blueRgb.getProgress();

        String displayRgb = "Red: " + String.valueOf(red) + ", Green: " + String.valueOf(green) + ", Blue: " + String.valueOf(blue);
        colorView.setBackgroundColor(Color.rgb(red,green,blue));
        colorRgb.setText(displayRgb);

    }
}
