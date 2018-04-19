package edu.stanford.cs108.mobiledraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onUpdate(View view){
        CustomView customView = findViewById(R.id.customView);
        customView.updateObject();
    }
}
