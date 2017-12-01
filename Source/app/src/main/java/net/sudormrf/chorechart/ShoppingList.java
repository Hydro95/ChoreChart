/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 79 "../../../class.ump"
public class ShoppingList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShoppingList Attributes
  private String name;
  private String location;
  private List<String> items;
  private int icon;
  private String id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingList()
  {
    name = "";
    location = "";
    items = new ArrayList<String>();
    icon = 0;
    id = Facade.getInstance().getShoppingRef().push().getKey();
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

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean addItem(String aItem)
  {
    boolean wasAdded = false;
    wasAdded = items.add(aItem);
    return wasAdded;
  }

  public boolean removeItem(String aItem)
  {
    boolean wasRemoved = false;
    wasRemoved = items.remove(aItem);
    return wasRemoved;
  }

  public boolean setIcon(int aIcon)
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

  /**
   * list name
   */
  public String getName()
  {
    return name;
  }

  /**
   * example hardware or grocery store
   */
  public String getLocation()
  {
    return location;
  }

  public String getItem(int index)
  {
    String aItem = items.get(index);
    return aItem;
  }

  public List<String> getItems()
  {
    return items;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(String aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }

  public int getIcon()
  {
    return icon;
  }

  public String getId()
  {
    return id;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "location" + ":" + getLocation()+ "," +
            "icon" + ":" + getIcon()+ "," +
            "id" + ":" + getId()+ "]"
     + outputString;
  }
}