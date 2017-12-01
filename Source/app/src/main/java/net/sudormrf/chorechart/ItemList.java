package net.sudormrf.chorechart;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ItemList extends AppCompatActivity {

    ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        int index = getIntent().getIntExtra("index", -1);

        shoppingList = Facade.getInstance().getShoppingList(index);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_add) {
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
        }

        return true;
    }

    public void onAddButtonClick(View view) {

        EditText item = findViewById(R.id.itemName);

        shoppingList.addItem(item.getText().toString());

        Facade.getInstance().publishShoppingLists();
    }
}
