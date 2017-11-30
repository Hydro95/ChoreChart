/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;

// line 62 "../../../class.ump"
public class Allocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Allocation Attributes
  private String id;

  //Allocation Associations
  private Task task;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Allocation(Task aTask, User aUser)
  {
    id = Facade.getInstance().getDatabase().getReference("allocations").push().getKey();
    boolean didAddTask = setTask(aTask);
    if (!didAddTask)
    {
      throw new RuntimeException("Unable to create allocation due to task");
    }
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create allocation due to user");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public Task getTask()
  {
    return task;
  }

  public User getUser()
  {
    return user;
  }

  public boolean setTask(Task aNewTask)
  {
    boolean wasSet = false;
    if (aNewTask == null)
    {
      //Unable to setTask to null, as allocation must always be associated to a task
      return wasSet;
    }
    
    Allocation existingAllocation = aNewTask.getAllocation();
    if (existingAllocation != null && !equals(existingAllocation))
    {
      //Unable to setTask, the current task already has a allocation, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Task anOldTask = task;
    task = aNewTask;
    task.setAllocation(this);

    if (anOldTask != null)
    {
      anOldTask.setAllocation(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeAllocation(this);
    }
    user.addAllocation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Task existingTask = task;
    task = null;
    if (existingTask != null)
    {
      existingTask.setAllocation(null);
    }
    User placeholderUser = user;
    this.user = null;
    placeholderUser.removeAllocation(this);
  }

  // line 66 "../../../class.ump"
  public void markCompleted(){
    
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "task = "+(getTask()!=null?Integer.toHexString(System.identityHashCode(getTask())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null")
     + outputString;
  }
}