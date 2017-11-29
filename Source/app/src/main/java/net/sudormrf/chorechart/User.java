/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 3 "../../../class.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private int points;
  private int icon;
  private String id;

  //User Associations
  private List<Allocation> allocations;
  private Home home;
  private Facade facade;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, Home aHome, Facade aFacade)
  {
    name = aName;
    points = 0;
    icon = 0;
    id = Facade.getInstance().getUserRef().push().getKey();
    allocations = new ArrayList<Allocation>();
    boolean didAddHome = setHome(aHome);
    if (!didAddHome)
    {
      throw new RuntimeException("Unable to create user due to home");
    }
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

  public String getName()
  {
    return name;
  }

  public int getPoints()
  {
    return points;
  }

  public int getIcon()
  {
    return icon;
  }

  public String getId()
  {
    return id;
  }

  public Allocation getAllocation(int index)
  {
    Allocation aAllocation = allocations.get(index);
    return aAllocation;
  }

  public List<Allocation> getAllocations()
  {
    List<Allocation> newAllocations = Collections.unmodifiableList(allocations);
    return newAllocations;
  }

  public int numberOfAllocations()
  {
    int number = allocations.size();
    return number;
  }

  public boolean hasAllocations()
  {
    boolean has = allocations.size() > 0;
    return has;
  }

  public int indexOfAllocation(Allocation aAllocation)
  {
    int index = allocations.indexOf(aAllocation);
    return index;
  }

  public Home getHome()
  {
    return home;
  }

  public Facade getFacade()
  {
    return facade;
  }

  public static int minimumNumberOfAllocations()
  {
    return 0;
  }

  public Allocation addAllocation(Task aTask)
  {
    return new Allocation(aTask, this);
  }

  public boolean addAllocation(Allocation aAllocation)
  {
    boolean wasAdded = false;
    if (allocations.contains(aAllocation)) { return false; }
    if (allocations.contains(aAllocation)) { return false; }
    User existingUser = aAllocation.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aAllocation.setUser(this);
    }
    else
    {
      allocations.add(aAllocation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllocation(Allocation aAllocation)
  {
    boolean wasRemoved = false;
    //Unable to remove aAllocation, as it must always have a user
    if (!this.equals(aAllocation.getUser()))
    {
      allocations.remove(aAllocation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllocationAt(Allocation aAllocation, int index)
  {  
    boolean wasAdded = false;
    if(addAllocation(aAllocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocations()) { index = numberOfAllocations() - 1; }
      allocations.remove(aAllocation);
      allocations.add(index, aAllocation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllocationAt(Allocation aAllocation, int index)
  {
    boolean wasAdded = false;
    if(allocations.contains(aAllocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocations()) { index = numberOfAllocations() - 1; }
      allocations.remove(aAllocation);
      allocations.add(index, aAllocation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllocationAt(aAllocation, index);
    }
    return wasAdded;
  }

  public boolean setHome(Home aHome)
  {
    boolean wasSet = false;
    if (aHome == null)
    {
      return wasSet;
    }

    Home existingHome = home;
    home = aHome;
    if (existingHome != null && !existingHome.equals(aHome))
    {
      existingHome.removeUser(this);
    }
    home.addUser(this);
    wasSet = true;
    return wasSet;
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
    for(int i=allocations.size(); i > 0; i--)
    {
      Allocation aAllocation = allocations.get(i - 1);
      aAllocation.delete();
    }
    Home placeholderHome = home;
    this.home = null;
    placeholderHome.removeUser(this);
    Facade placeholderFacade = facade;
    this.facade = null;
    placeholderFacade.removeUser(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "points" + ":" + getPoints()+ "," +
            "icon" + ":" + getIcon()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "home = "+(getHome()!=null?Integer.toHexString(System.identityHashCode(getHome())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "facade = "+(getFacade()!=null?Integer.toHexString(System.identityHashCode(getFacade())):"null")
     + outputString;
  }
}