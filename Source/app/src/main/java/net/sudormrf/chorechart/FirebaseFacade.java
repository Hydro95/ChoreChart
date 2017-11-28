/* A mockup of what facade should look like after Firebase Integration*/
/* Delet this if necessary.*/

package net.sudormrf.chorechart;

import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseFacade
{
    private static FirebaseFacade inst;

    private DatabaseReference userRef;
    private DatabaseReference taskRef;
    private DatabaseReference listRef;
    private DatabaseReference toolsRef;

    private List<User> users;
    private List<Task> tasks;
    private List<ShoppingList> lists;
    private List<Tools> tools;

    private Home currentHome;

    private FirebaseFacade()
    {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference(currentHome.getName());
        userRef = db.child("users");
        taskRef = db.child("tasks");
        listRef = db.child("shopping_lists");
        toolsRef = db.child("tools");
    }

    public FirebaseFacade getInstance()
    {
        if(inst == null) {
            inst = new FirebaseFacade();
        }

        return inst;
    }

    public User getUserById(String id)
    {
        return null;
    }

    public Task getTaskById(String id)
    {
        return null;
    }

    public ShoppingList getShoppingListById(String id)
    {
        return null;
    }

    public void addUser(User nUser) {}
    public void addTask(Task nTask) {}
    public void addShoppingList(ShoppingList nList) {}
    public void addTools(Tools nTools) {}
}
