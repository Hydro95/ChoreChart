/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;
import java.util.*;

// line 84 "../../../class.ump"
public class Tools
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tools Attributes
  private String name;

  //Tools Associations
  private List<Task> tasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tools(String aName)
  {
    name = aName;
    tasks = new ArrayList<Task>();
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

  public String getName()
  {
    return name;
  }

  public Task getTask(int index)
  {
    Task aTask = tasks.get(index);
    return aTask;
  }

  public List<Task> getTasks()
  {
    List<Task> newTasks = Collections.unmodifiableList(tasks);
    return newTasks;
  }

  public int numberOfTasks()
  {
    int number = tasks.size();
    return number;
  }

  public boolean hasTasks()
  {
    boolean has = tasks.size() > 0;
    return has;
  }

  public int indexOfTask(Task aTask)
  {
    int index = tasks.indexOf(aTask);
    return index;
  }

  public static int minimumNumberOfTasks()
  {
    return 0;
  }

  public boolean addTask(Task aTask)
  {
    boolean wasAdded = false;
    if (tasks.contains(aTask)) { return false; }
    tasks.add(aTask);
    if (aTask.indexOfTool(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTask.addTool(this);
      if (!wasAdded)
      {
        tasks.remove(aTask);
      }
    }
    return wasAdded;
  }

  public boolean removeTask(Task aTask)
  {
    boolean wasRemoved = false;
    if (!tasks.contains(aTask))
    {
      return wasRemoved;
    }

    int oldIndex = tasks.indexOf(aTask);
    tasks.remove(oldIndex);
    if (aTask.indexOfTool(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTask.removeTool(this);
      if (!wasRemoved)
      {
        tasks.add(oldIndex,aTask);
      }
    }
    return wasRemoved;
  }

  public boolean addTaskAt(Task aTask, int index)
  {  
    boolean wasAdded = false;
    if(addTask(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTaskAt(Task aTask, int index)
  {
    boolean wasAdded = false;
    if(tasks.contains(aTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTasks()) { index = numberOfTasks() - 1; }
      tasks.remove(aTask);
      tasks.add(index, aTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTaskAt(aTask, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Task> copyOfTasks = new ArrayList<Task>(tasks);
    tasks.clear();
    for(Task aTask : copyOfTasks)
    {
      aTask.removeTool(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}