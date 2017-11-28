/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 84 "../../../class.ump"
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

  /**
   * void promptConfirmAction(String message) {} //WTF is this?
   * void modifyTask(Task task) {}
   */
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

  public User addUser(String aName, Home aHome)
  {
    return new User(aName, aHome, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Facade existingFacade = aUser.getFacade();
    boolean isNewFacade = existingFacade != null && !this.equals(existingFacade);
    if (isNewFacade)
    {
      aUser.setFacade(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a facade
    if (!this.equals(aUser.getFacade()))
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

  public Task addTask(String aName, String aDeadline)
  {
    return new Task(aName, aDeadline, this);
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    Facade existingFacade = aTask.getFacade();
    boolean isNewFacade = existingFacade != null && !this.equals(existingFacade);
    if (isNewFacade)
    {
      aTask.setFacade(this);
    }
    else
    {
      tasks.add(aTask);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    //Unable to remove aTask, as it must always have a facade
    if (!this.equals(aTask.getFacade()))
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

  public Tools addTool(String aName)
  {
    return new Tools(aName, this);
  }

  public boolean addTool(Tools aTool)
  {
    boolean wasAdded = false;
    if (tools.contains(aTool)) { return false; }
    Facade existingFacade = aTool.getFacade();
    boolean isNewFacade = existingFacade != null && !this.equals(existingFacade);
    if (isNewFacade)
    {
      aTool.setFacade(this);
    }
    else
    {
      tools.add(aTool);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTool(Tools aTool)
  {
    boolean wasRemoved = false;
    //Unable to remove aTool, as it must always have a facade
    if (!this.equals(aTool.getFacade()))
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

  public ShoppingList addShoppingList(String aLocation)
  {
    return new ShoppingList(aLocation, this);
  }

  public boolean addShoppingList(ShoppingList aShoppingList)
  {
    boolean wasAdded = false;
    if (shoppingLists.contains(aShoppingList)) { return false; }
    Facade existingFacade = aShoppingList.getFacade();
    boolean isNewFacade = existingFacade != null && !this.equals(existingFacade);
    if (isNewFacade)
    {
      aShoppingList.setFacade(this);
    }
    else
    {
      shoppingLists.add(aShoppingList);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShoppingList(ShoppingList aShoppingList)
  {
    boolean wasRemoved = false;
    //Unable to remove aShoppingList, as it must always have a facade
    if (!this.equals(aShoppingList.getFacade()))
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
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
    }
    for(int i=tasks.size(); i > 0; i--)
    {
      Task aTask = tasks.get(i - 1);
      aTask.delete();
    }
    for(int i=tools.size(); i > 0; i--)
    {
      Tools aTool = tools.get(i - 1);
      aTool.delete();
    }
    for(int i=shoppingLists.size(); i > 0; i--)
    {
      ShoppingList aShoppingList = shoppingLists.get(i - 1);
      aShoppingList.delete();
    }
  }

  // line 91 "../../../class.ump"
  public void createNewAccount(String name){
    currentHome.addUser(name, this);
  }

  // line 95 "../../../class.ump"
  public void allocateTask(User user, Task task){
    new Allocation(task, user);
  }

  // line 99 "../../../class.ump"
  public boolean markCompleted(Task task){
    if(currentUser == task.getUser())
			return task.markCompleted();  // Not sure how to respond if task is not InProgress
		return false;
  }

  // line 105 "../../../class.ump"
  public void addToShopping(ShoppingList list, String item){
    list.add(item);
  }


  /**
   * Figure out if you can deel with this (automatically using currentX)
   */
  // line 111 "../../../class.ump"
  public User addUser(String aName, int icon){
    User user = new User(aName, currentHome, this);
		user.setIcon(icon);
		return user;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currentHome" + "=" + (getCurrentHome() != null ? !getCurrentHome().equals(this)  ? getCurrentHome().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentUser" + "=" + (getCurrentUser() != null ? !getCurrentUser().equals(this)  ? getCurrentUser().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}