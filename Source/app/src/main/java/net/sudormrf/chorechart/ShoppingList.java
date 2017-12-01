/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 80 "../../../class.ump"
public class ShoppingList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShoppingList Attributes
  private String name;
  private String location;
  private String icon;
  private String id;
  private List<String> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingList()
  {
    name = "";
    location = "";
    icon = "";
    id = Facade.getInstance().getShoppingRef().push().getKey();
    items = new ArrayList<String>();
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

  public String getIcon()
  {
    return icon;
  }

  public String getId()
  {
    return id;
  }

  public String getItem(int index)
  {
    String aItem = items.get(index);
    return aItem;
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

  public void delete()
  {}

  public List<String> getItems() {
    return items;
  }

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