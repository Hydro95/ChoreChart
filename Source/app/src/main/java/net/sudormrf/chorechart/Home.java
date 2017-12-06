/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 107 "../../../class.ump"
public class Home
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Home Attributes
  private String name;
  private List<String> userIds;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Home(String aName)
  {
    name = aName;
    userIds = new ArrayList<String>();
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

  public boolean addUserId(String aUserId)
  {
    boolean wasAdded = false;
    wasAdded = userIds.add(aUserId);
    return wasAdded;
  }

  public boolean removeUserId(String aUserId)
  {
    boolean wasRemoved = false;
    wasRemoved = userIds.remove(aUserId);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public String getUserId(int index)
  {
    String aUserId = userIds.get(index);
    return aUserId;
  }

  public String[] getUserIds()
  {
    String[] newUserIds = userIds.toArray(new String[userIds.size()]);
    return newUserIds;
  }

  public int numberOfUserIds()
  {
    int number = userIds.size();
    return number;
  }

  public boolean hasUserIds()
  {
    boolean has = userIds.size() > 0;
    return has;
  }

  public int indexOfUserId(String aUserId)
  {
    int index = userIds.indexOf(aUserId);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}