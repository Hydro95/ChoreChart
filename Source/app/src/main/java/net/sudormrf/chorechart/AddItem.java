package net.sudormrf.chorechart;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {

    private Uri mImageUri;
    private Bitmap listImg;


    ShoppingList shoppingList;

    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        int index = getIntent().getIntExtra("index", -1);

        shoppingList = Facade.getInstance().getShoppingList(index);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    public void onSaveButtonClick(View view) {


        EditText name = findViewById(R.id.itemName);
        EditText quantity = findViewById(R.id.itemQuantity);

        String itemName = name.getText().toString();
        String itemQuantity = quantity.getText().toString();

        item = new Item();

        item.setName(itemName);
        item.setQuantity(itemQuantity);

        if (item.getId() == null) {
            item.setId(Facade.getInstance().getShoppingRef().child(shoppingList.getId()).push().getKey());
        }

        shoppingList.addItem(item);

        Facade.getInstance().publishShoppingLists();

        finish();
    }


}
