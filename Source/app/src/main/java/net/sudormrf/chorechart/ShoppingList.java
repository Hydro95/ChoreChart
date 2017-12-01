/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import com.google.firebase.database.Exclude;

import java.util.*;

// line 85 "../../../class.ump"
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

  //ShoppingList Associations
  private Facade facade;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingList(String aName, String aLocation, int aIcon, Facade aFacade)
  {
    name = aName;
    location = aLocation;
    items = new ArrayList<String>();
    icon = aIcon;
    id = Facade.getInstance().getShoppingRef().push().getKey();
    boolean didAddFacade = setFacade(aFacade);
    if (!didAddFacade)
    {
      throw new RuntimeException("Unable to create shoppingList due to facade");
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

  public String[] getItems()
  {
    String[] newItems = items.toArray(new String[items.size()]);
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
      existingFacade.removeShoppingList(this);
    }
    facade.addShoppingList(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Facade placeholderFacade = facade;
    this.facade = null;
    placeholderFacade.removeShoppingList(this);
  }

  // line 93 "../../../class.ump"
  public void add(String item){
    
  }

  // line 94 "../../../class.ump"
  public void delete(int index){
    
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "location" + ":" + getLocation()+ "," +
            "icon" + ":" + getIcon()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "facade = "+(getFacade()!=null?Integer.toHexString(System.identityHashCode(getFacade())):"null")
     + outputString;
  }
}