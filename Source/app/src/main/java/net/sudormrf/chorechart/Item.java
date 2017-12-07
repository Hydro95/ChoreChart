/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package net.sudormrf.chorechart;

// line 93 "../../../class.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private String quantity;
  private boolean bought;
  private String id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item()
  {
    name = "";
    quantity = "";
    bought = false;
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

  public boolean setQuantity(String aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
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

  public String getQuantity()
  {
    return quantity;
  }

  public boolean getBought()
  {
    return bought;
  }

  public String getId()
  {
    return id;
  }

  public void delete()
  {}

  // line 100 "../../../class.ump"
   public boolean equals(Item other){
    return this.name.equals(other.name) && this.bought == other.bought;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "," +
            "bought" + ":" + getBought()+ "," +
            "id" + ":" + getId()+ "]"
     + outputString;
  }
}