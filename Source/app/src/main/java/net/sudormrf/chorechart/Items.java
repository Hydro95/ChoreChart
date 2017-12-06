/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;

// line 96 "../../../class.ump"
public class Items
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Items Attributes
  private String name;
  private boolean bought;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Items()
  {
    name = "";
    bought = false;
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

  public boolean setBought(boolean aBought)
  {
    boolean wasSet = false;
    bought = aBought;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getBought()
  {
    return bought;
  }

  public boolean isBought()
  {
    return bought;
  }

  public void delete()
  {}

  // line 101 "../../../class.ump"
  public boolean equals(Items other){
    return this.name.equals(other.name) && this.bought == other.bought;
  }

  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "bought" + ":" + getBought()+ "]"
     + outputString;
  }
}