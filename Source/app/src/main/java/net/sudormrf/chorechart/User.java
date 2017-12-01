/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import com.google.firebase.database.Exclude;

import java.util.*;

/**
 * every time you recompile you must:
 * 
 * add @Exclude above User.getFacade(), Task.getFacade(), ShoppingList.getFacade()
 * Remove the umple gen'd getter for User.getTaskIds() (might be fixed)
 */
// line 11 "../../../class.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private int points;
  private String icon;
  private String id;
  private List<String> taskIds;

  //User Associations
  private Facade facade;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, Facade aFacade)
  {
    name = aName;
    points = 0;
    icon = "";
    id = Facade.getInstance().getUserRef().push().getKey();
    taskIds = new ArrayList<String>();
    boolean didAddFacade = setFacade(aFacade);
    if (!didAddFacade)
    {
      throw new RuntimeException("Unable to create user due to facade");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setIcon(String aIcon)
  {
    boolean wasSet = false;
    icon = aIcon;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean addTaskId(String aTaskId)
  {
    boolean wasAdded = false;
    wasAdded = taskIds.add(aTaskId);
    return wasAdded;
  }

  public boolean removeTaskId(String aTaskId)
  {
    boolean wasRemoved = false;
    wasRemoved = taskIds.remove(aTaskId);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public int getPoints()
  {
    return points;
  }

  public String getIcon()
  {
    return icon;
  }

  public String getId()
  {
    return id;
  }

  public String getTaskId(int index)
  {
    String aTaskId = taskIds.get(index);
    return aTaskId;
  }

  public String[] getTaskIds()
  {
    String[] newTaskIds = taskIds.toArray(new String[taskIds.size()]);
    return newTaskIds;
  }

  public int numberOfTaskIds()
  {
    int number = taskIds.size();
    return number;
  }

  public boolean hasTaskIds()
  {
    boolean has = taskIds.size() > 0;
    return has;
  }

  public int indexOfTaskId(String aTaskId)
  {
    int index = taskIds.indexOf(aTaskId);
    return index;
  }

  @Exclude
  public Facade getFacade()
  {
    return facade;
  }

  public boolean setFacade(Facade aFacade)
  {
    boolean wasSet = false;
    if (aFacade == null)
    {
      return wasSet;
    }

    Facade existingFacade = facade;
    facade = aFacade;
    if (existingFacade != null && !existingFacade.equals(aFacade))
    {
      existingFacade.removeUser(this);
    }
    facade.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Facade placeholderFacade = facade;
    this.facade = null;
    placeholderFacade.removeUser(this);
  }


  /**
   * default constructor for db
   */
  // line 19 "../../../class.ump"
   public  User(){
    
  }

  // line 22 "../../../class.ump"
   /*public List<String> getTaskIds(){
    return taskIds;
  } */


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "points" + ":" + getPoints()+ "," +
            "icon" + ":" + getIcon()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "facade = "+(getFacade()!=null?Integer.toHexString(System.identityHashCode(getFacade())):"null")
     + outputString;
  }
}