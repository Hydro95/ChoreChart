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
  private String icon;
  private String id;

  //ShoppingList Associations
  private List<Item> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingList()
  {
    name = "";
    location = "";
    icon = "";
    id = Facade.getInstance().getShoppingRef().push().getKey();
    items = new ArrayList<Item>();
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

  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  /**
   * public List<String> getItems() {
   * return taskIds;
   * }
   * void createList(String name, Home home, String[] items) {}
   * 1 -- 0..1 Task;
   */
  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
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

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }

  public static int minimumNumberOfItems()
  {
    return 0;
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    items.add(aItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    if (items.contains(aItem))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    items.clear();
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