package net.sudormrf.chorechart;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

public class ItemList extends AppCompatActivity {

    ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        int index = getIntent().getIntExtra("index", -1);

        shoppingList = Facade.getInstance().getShoppingList(index);

        setContentView(R.layout.item_layout);

        ItemAdapter adapter = new ItemAdapter(this, shoppingList.getItems());
        ListView listView = findViewById(R.id.itemList);

        listView.setAdapter(adapter);
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
