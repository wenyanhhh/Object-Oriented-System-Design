package edu.stanford.cs108.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String shoppingList = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    On click method which gets new item
    from editText view and add the item
    to the shopping list.
    Display one item per line.
     */
    public void onClickAdd(View v){
        TextView DisplayShoppingList = findViewById(R.id.text_view_id);
        EditText editItem = findViewById(R.id.edit_text_id);
        String newItem = editItem.getText().toString();
        shoppingList += newItem;
        shoppingList += "\n";
        DisplayShoppingList.setText(shoppingList);
        editItem.setText("");
    }

    /*
    On click method which clears all the items
    from the shopping list.
     */
    public void onClickClear(View v){
        TextView DisplayShoppingList = findViewById(R.id.text_view_id);
        shoppingList = "";
        DisplayShoppingList.setText(shoppingList);
    }

}
