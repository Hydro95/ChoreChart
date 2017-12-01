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

    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
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

        item = itemQuantity + "x " + itemName;

        finish();

    }


}
