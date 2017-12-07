/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.*;

// line 118 "../../../class.ump"
public class Facade
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Facade theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Facade Attributes
  private FirebaseDatabase database;
  private DatabaseReference userRef;
  private DatabaseReference shoppingRef;
  private DatabaseReference taskRef;
  private Home currentHome;
  private User currentUser;

  //Facade Associations
  private List<User> users;
  private List<Task> tasks;
  private List<Tools> tools;
  private List<ShoppingList> shoppingLists;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private Facade()
  {
    database = FirebaseDatabase.getInstance();
    userRef = database.getReference("users");
    shoppingRef = database.getReference("shopping");
    taskRef = database.getReference("tasks");
    currentHome = new Home("Test Home");
    currentUser = null;
    users = new ArrayList<User>();
    tasks = new ArrayList<Task>();
    tools = new ArrayList<Tools>();
    shoppingLists = new ArrayList<ShoppingList>();
  }

  public static Facade getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new Facade();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDatabase(FirebaseDatabase aDatabase)
  {
    boolean wasSet = false;
    database = aDatabase;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserRef(DatabaseReference aUserRef)
  {
    boolean wasSet = false;
    userRef = aUserRef;
    wasSet = true;
    return wasSet;
  }

  public boolean setShoppingRef(DatabaseReference aShoppingRef)
  {
    boolean wasSet = false;
    shoppingRef = aShoppingRef;
    wasSet = true;
    return wasSet;
  }

  public boolean setTaskRef(DatabaseReference aTaskRef)
  {
    boolean wasSet = false;
    taskRef = aTaskRef;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentHome(Home aCurrentHome)
  {
    boolean wasSet = false;
    currentHome = aCurrentHome;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentUser(User aCurrentUser)
  {
    boolean wasSet = false;
    currentUser = aCurrentUser;
    wasSet = true;
    return wasSet;
  }

  public FirebaseDatabase getDatabase()
  {
    return database;
  }

  public DatabaseReference getUserRef()
  {
    return userRef;
  }

  public DatabaseReference getShoppingRef()
  {
    return shoppingRef;
  }

  public DatabaseReference getTaskRef()
  {
    return taskRef;
  }

  /**
   * END OF DATABASE ZONE//
   */
  public Home getCurrentHome()
  {
    return currentHome;
  }

  public User getCurrentUser()
  {
    return currentUser;
  }

  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }

  public Task getTask(int index)
  {
    Task aTask = tasks.get(index);
    return aTask;
  }

  public List<Task> getTasks()
  {
    List<Task> newTasks = Collections.unmodifiableList(tasks);
    return newTasks;
  }

  public int numberOfTasks()
  {
    int number = tasks.size();
    return number;
  }

  public boolean hasTasks()
  {
    boolean has = tasks.size() > 0;
    return has;
  }

  public int indexOfTask(Task aTask)
  {
    int index = tasks.indexOf(aTask);
    return index;
  }

  public Tools getTool(int index)
  {
    Tools aTool = tools.get(index);
    return aTool;
  }

  public List<Tools> getTools()
  {
    List<Tools> newTools = Collections.unmodifiableList(tools);
    return newTools;
  }

  public int numberOfTools()
  {
    int number = tools.size();
    return number;
  }

  public boolean hasTools()
  {
    boolean has = tools.size() > 0;
    return has;
  }

  public int indexOfTool(Tools aTool)
  {
    int index = tools.indexOf(aTool);
    return index;
  }

  public ShoppingList getShoppingList(int index)
  {
    ShoppingList aShoppingList = shoppingLists.get(index);
    return aShoppingList;
  }

  public List<ShoppingList> getShoppingLists()
  {
    List<ShoppingList> newShoppingLists = Collections.unmodifiableList(shoppingLists);
    return newShoppingLists;
  }

  public int numberOfShoppingLists()
  {
    int number = shoppingLists.size();
    return number;
  }

  public boolean hasShoppingLists()
  {
    boolean has = shoppingLists.size() > 0;
    return has;
  }

  public int indexOfShoppingList(ShoppingList aShoppingList)
  {
    int index = shoppingLists.indexOf(aShoppingList);
    return index;
  }

  public static int minimumNumberOfUsers()
  {
    return 0;
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    users.add(aUser);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    if (users.contains(aUser))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTasks()
  {
    return 0;
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    tasks.add(aTask);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    if (tasks.contains(aTask))
    {
      tasks.remove(aTask);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTaskAt(Task aTask, int index)
  {  
    boolean wasAdded = false;
    if(addTask(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTaskAt(Task aTask, int index)
  {
    boolean wasAdded = false;
    if(tasks.contains(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTaskAt(aTask, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTools()
  {
    return 0;
  }

  public boolean addTool(Tools aTool)
  {
    boolean wasAdded = false;
    if (tools.contains(aTool)) { return false; }
    tools.add(aTool);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTool(Tools aTool)
  {
    boolean wasRemoved = false;
    if (tools.contains(aTool))
    {
      tools.remove(aTool);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addToolAt(Tools aTool, int index)
  {  
    boolean wasAdded = false;
    if(addTool(aTool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTools()) { index = numberOfTools() - 1; }
      tools.remove(aTool);
      tools.add(index, aTool);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveToolAt(Tools aTool, int index)
  {
    boolean wasAdded = false;
    if(tools.contains(aTool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTools()) { index = numberOfTools() - 1; }
      tools.remove(aTool);
      tools.add(index, aTool);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addToolAt(aTool, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfShoppingLists()
  {
    return 0;
  }

  public boolean addShoppingList(ShoppingList aShoppingList)
  {
    boolean wasAdded = false;
    if (shoppingLists.contains(aShoppingList)) { return false; }
    shoppingLists.add(aShoppingList);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShoppingList(ShoppingList aShoppingList)
  {
    boolean wasRemoved = false;
    if (shoppingLists.contains(aShoppingList))
    {
      shoppingLists.remove(aShoppingList);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addShoppingListAt(ShoppingList aShoppingList, int index)
  {  
    boolean wasAdded = false;
    if(addShoppingList(aShoppingList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShoppingLists()) { index = numberOfShoppingLists() - 1; }
      shoppingLists.remove(aShoppingList);
      shoppingLists.add(index, aShoppingList);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShoppingListAt(ShoppingList aShoppingList, int index)
  {
    boolean wasAdded = false;
    if(shoppingLists.contains(aShoppingList))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShoppingLists()) { index = numberOfShoppingLists() - 1; }
      shoppingLists.remove(aShoppingList);
      shoppingLists.add(index, aShoppingList);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShoppingListAt(aShoppingList, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    users.clear();
    tasks.clear();
    tools.clear();
    shoppingLists.clear();
  }

  // line 184 "../../../class.ump"
  public void publishUsers(){
    for (User user : users) {
			this.getUserRef().child(user.getId()).setValue(user);
		}
  }

  // line 190 "../../../class.ump"
  public void publishTasks(){
    for (Task task : tasks) {
			this.getTaskRef().child(task.getId()).setValue(task);
		}
  }

  // line 196 "../../../class.ump"
  public void publishShoppingLists(){
    for (ShoppingList list : shoppingLists) {
			this.getShoppingRef().child(list.getId()).setValue(list);
		}
  }

  // line 202 "../../../class.ump"
  public User getUser(String id){
    for (User user : users) {
      if (user.getId().equals(id)) {
        return user;
      }
    }
    return null;
  }

  // line 211 "../../../class.ump"
  public Task getTask(String id){
    for (Task task : tasks) {
      if (task.getId().equals(id)) {
        return task;
      }
    }
    return null;
  }

  // line 225 "../../../class.ump"
  public void allocateTask(User user, Task task){
    task.setUserId(user.getId());
		user.addTaskId(task.getId());
  }

  // line 230 "../../../class.ump"
  public boolean markCompleted(Task task){
    if(currentUser.getId() == task.getUserId())
			return task.markCompleted();  // Not sure how to respond if task is not InProgress
		return false;
  }

  // line 236 "../../../class.ump"
  public void addToShopping(ShoppingList list, String item){
    list.addItem(item);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "database" + "=" + (getDatabase() != null ? !getDatabase().equals(this)  ? getDatabase().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRef" + "=" + (getUserRef() != null ? !getUserRef().equals(this)  ? getUserRef().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "shoppingRef" + "=" + (getShoppingRef() != null ? !getShoppingRef().equals(this)  ? getShoppingRef().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "taskRef" + "=" + (getTaskRef() != null ? !getTaskRef().equals(this)  ? getTaskRef().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentHome" + "=" + (getCurrentHome() != null ? !getCurrentHome().equals(this)  ? getCurrentHome().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentUser" + "=" + (getCurrentUser() != null ? !getCurrentUser().equals(this)  ? getCurrentUser().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 127 ../../../class.ump
  void createListeners (final MainActivity.SectionsPagerAdapter sectionsPagerAdapter) 
  {
    userRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				users.clear();

				for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
						User user = userSnapshot.getValue(User.class);
						Facade.getInstance().addUser(user);
				}
				sectionsPagerAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(DatabaseError error) {
					//don't worry this will definitely never fail (nope)
			}
		});

		shoppingRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				shoppingLists.clear();

				for (DataSnapshot shoppingListSnapshot : dataSnapshot.getChildren()) {
					ShoppingList list = shoppingListSnapshot.getValue(ShoppingList.class);
					Facade.getInstance().addShoppingList(list);
				}
				sectionsPagerAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(DatabaseError error) {
					//don't worry this will probably never ever fail (never ever)
			}
		});

		taskRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				tasks.clear();

				for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
					Task task = taskSnapshot.getValue(Task.class);
					Facade.getInstance().addTask(task);
				}
				sectionsPagerAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(DatabaseError error) {
					//don't worry this will never fail (not even once)
			}
		});
  }

  
}