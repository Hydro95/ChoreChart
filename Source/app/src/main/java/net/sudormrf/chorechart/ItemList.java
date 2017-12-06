package net.sudormrf.chorechart;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;

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

}
