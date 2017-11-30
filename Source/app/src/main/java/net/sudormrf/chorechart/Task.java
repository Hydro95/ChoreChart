/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 30 "../../../class.ump"
public class Task
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Task Attributes
  private String name;
  private String deadline;
  private String duration;
  private String comment;
  private boolean completed;
  private Repeat frequency;
  private String id;

  //Task State Machines
  enum Status { Unallocated, InProgress, Completed, Failed }
  private Status status;

  //Task Associations
  private Allocation allocation;
  private List<Tools> tools;
  private Facade facade;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Task(String aName, String aDeadline, Facade aFacade)
  {
    name = aName;
    deadline = aDeadline;
    duration = "Unknown";
    comment = "";
    completed = false;
    frequency = Repeat.NEVER;
    id = Facade.getInstance().getTaskRef().push().getKey();
    tools = new ArrayList<Tools>();
    boolean didAddFacade = setFacade(aFacade);
    if (!didAddFacade)
    {
      throw new RuntimeException("Unable to create task due to facade");
    }
    setStatus(Status.Unallocated);
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

  public boolean setDeadline(String aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(String aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setComment(String aComment)
  {
    boolean wasSet = false;
    comment = aComment;
    wasSet = true;
    return wasSet;
  }

  public boolean setCompleted(boolean aCompleted)
  {
    boolean wasSet = false;
    completed = aCompleted;
    wasSet = true;
    return wasSet;
  }

  public boolean setFrequency(Repeat aFrequency)
  {
    boolean wasSet = false;
    frequency = aFrequency;
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

  public String getDeadline()
  {
    return deadline;
  }

  public String getDuration()
  {
    return duration;
  }

  public String getComment()
  {
    return comment;
  }

  public boolean getCompleted()
  {
    return completed;
  }

  public Repeat getFrequency()
  {
    return frequency;
  }

  public String getId()
  {
    return id;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean setAllocation()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Unallocated:
        setStatus(Status.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean deadlinePassed()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Unallocated:
        setStatus(Status.Failed);
        wasEventProcessed = true;
        break;
      case InProgress:
        setStatus(Status.Failed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean markCompleted()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InProgress:
        setStatus(Status.Completed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean release()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InProgress:
        setStatus(Status.Unallocated);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean extendDeadline()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Failed:
        setStatus(Status.Unallocated);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }

  public Allocation getAllocation()
  {
    return allocation;
  }

  public boolean hasAllocation()
  {
    boolean has = allocation != null;
    return has;
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

  public Facade getFacade()
  {
    return facade;
  }

  public boolean setAllocation(Allocation aNewAllocation)
  {
    boolean wasSet = false;
    if (allocation != null && !allocation.equals(aNewAllocation) && equals(allocation.getTask()))
    {
      //Unable to setAllocation, as existing allocation would become an orphan
      return wasSet;
    }

    allocation = aNewAllocation;
    Task anOldTask = aNewAllocation != null ? aNewAllocation.getTask() : null;

    if (!this.equals(anOldTask))
    {
      if (anOldTask != null)
      {
        anOldTask.allocation = null;
      }
      if (allocation != null)
      {
        allocation.setTask(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfTools()
  {
    return 0;
  }

  public boolean addTool(Tools aTool)
  {
    boolean wasAdded = false;
    if (tools.contains(aTool)) { return false; }
    tools.add(aTool);
    if (aTool.indexOfTask(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTool.addTask(this);
      if (!wasAdded)
      {
        tools.remove(aTool);
      }
    }
    return wasAdded;
  }

  public boolean removeTool(Tools aTool)
  {
    boolean wasRemoved = false;
    if (!tools.contains(aTool))
    {
      return wasRemoved;
    }

    int oldIndex = tools.indexOf(aTool);
    tools.remove(oldIndex);
    if (aTool.indexOfTask(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTool.removeTask(this);
      if (!wasRemoved)
      {
        tools.add(oldIndex,aTool);
      }
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
      existingFacade.removeTask(this);
    }
    facade.addTask(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Allocation existingAllocation = allocation;
    allocation = null;
    if (existingAllocation != null)
    {
      existingAllocation.delete();
    }
    ArrayList<Tools> copyOfTools = new ArrayList<Tools>(tools);
    tools.clear();
    for(Tools aTool : copyOfTools)
    {
      aTool.removeTask(this);
    }
    Facade placeholderFacade = facade;
    this.facade = null;
    placeholderFacade.removeTask(this);
  }

  // line 44 "../../../class.ump"
  public User getUser(){
    return allocation.getUser();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "deadline" + ":" + getDeadline()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "comment" + ":" + getComment()+ "," +
            "completed" + ":" + getCompleted()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "frequency" + "=" + (getFrequency() != null ? !getFrequency().equals(this)  ? getFrequency().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "allocation = "+(getAllocation()!=null?Integer.toHexString(System.identityHashCode(getAllocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "facade = "+(getFacade()!=null?Integer.toHexString(System.identityHashCode(getFacade())):"null")
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 32 ../../../class.ump
  public enum Repeat 
  {
    NEVER, DAILY, WEEKLY, MONTHY, YEARLY
  }

  
}