/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 15 "../../../class.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User()
  {
    name = "Anon";
    points = 0;
    icon = "";
    id = Facade.getInstance().getUserRef().push().getKey();
    taskIds = new ArrayList<String>();
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

  public void delete()
  {}

  // line 23 "../../../class.ump"
   public List<String> getTaskIds(){
    return taskIds;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "points" + ":" + getPoints()+ "," +
            "icon" + ":" + getIcon()+ "," +
            "id" + ":" + getId()+ "]"
     + outputString;
  }
}