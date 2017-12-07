package net.sudormrf.chorechart;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {

    private ItemListAdapter itmAdpt;
    private OnItemAddListener addListener;
    private String id;
    private List<Item> itemList;

    public ItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ListView lst = (ListView) view.findViewById(R.id.item_list_view);
        lst.setAdapter(itmAdpt);

        Button addItemBtn = (Button) view.findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddItemClick(v);
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Item delThis = itemList.get(i);
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Facade.getInstance().getTask(id).removeItem(delThis);
                                itemList.remove(delThis);
                                itmAdpt.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            id = savedInstanceState.getString("id", "");
        }

        updateList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemAddListener) {
            addListener = (OnItemAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemAddListener");
        }
    }

    public void setId(String id)
    {
        this.id = id;
        updateList();
    }

    public void onAddItemClick(View view)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        Button save = (Button) dialogView.findViewById(R.id.addItemSave);
        final EditText name = (EditText) dialogView.findViewById(R.id.addItemName);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Add Item");
        final AlertDialog b = dialogBuilder.create();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = name.getText().toString().trim();
                Item items = new Item();
                items.setName(itemName);

                if(addListener != null) {
                    addListener.onItemAdd(items);
                }

                itemList.add(items);
                itmAdpt.notifyDataSetChanged();
                b.dismiss();
            }
        });

        b.show();
    }

    private void updateList()
    {
        if(id == null || id.isEmpty()) {
            itemList = new ArrayList<>();
        }
        else {
            itemList = new ArrayList<>(Facade.getInstance().getTask(id).getItem());
        }

        FragmentActivity g = getActivity();
        itmAdpt = new ItemListAdapter(this.getActivity(), itemList);
        itmAdpt.notifyDataSetChanged();

        //Update view
        View v = getView();
        if(v != null) {
            ListView lst = (ListView) v.findViewById(R.id.item_list_view);
            lst.setAdapter(itmAdpt);
        }
    }

    public interface OnItemAddListener
    {
        public void onItemAdd(Item item);
    }

}
