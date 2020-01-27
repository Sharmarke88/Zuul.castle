 import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Player.java. Stores information about the player character and his inventory.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Player
{
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    Stack<Room> roomHistory = new Stack<Room>();

    /**
     * constructor van klasse player
     */
    public Player()
    {
        currentRoom = new Room("starting room");
        inventory = new HashMap<String, Item>();
    }

    /**
     * @return stuurt de huidige kamer terug van de player
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
    /**
     * Maakt van alle items een string 
     * @return stuurt de items terug
     */
    public String getInventoryString()
    {
        String returnString = "Items in bag:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }

    /**
     * Controleert de inventory voor een key
     */
    public boolean checkKey()
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            if (item.equals("key"))
                return true;
        return false;
    }
    
          public boolean hasItemInInventory(String treasure)
          {
    return inventory.containsKey(treasure);
    }
    
    /**
     * @return stuurt de beschrijving van de ingevoerde item terug
     */
    public String getStudyString(String name)
    {
        String returnString = "You study the " + name + ".\n";
        Item temp = inventory.get(name);
        if (temp != null) {
            returnString += "It's " + temp.getDescription() + ".";
            return returnString;
        }
        return "You can only study items in your inventory.";
    }
    
    /**
     * voegt item naam en beschrijving toe aan inventory
     */
    public void addInventory(String name, String description)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                System.out.println("We've already got one!");
                return;
            }   
        }
        Item newItem = new Item(description, name);
        inventory.put(name, newItem);
    }
    
     /**
     * geeft de history van de rooms weer.
     * @return history van de rooms.
     */
    public Stack<Room> getRoomHistory() {
        return roomHistory;
    }
   
    /**
     * voegt item toe aan inventory
     */
    public void addInventory(Item item)
    {
        inventory.put(item.getName(), item);
    }
    
    /**
     * Laat een item vallen
     */
    public Item dropInventory(String name)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = inventory.get(name);
                inventory.remove(name);
                return temp;

            }   
        }
        System.out.println("We've haven't got one!");
        return null;

    }
   
    /**
     * zorgt ervoor dat we kunnen ruilen met character
     */
    public Item dropInventory()
    {
        Item temp = inventory.entrySet().iterator().next().getValue();
        inventory.clear();
        return temp;
    }
}