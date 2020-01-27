
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */

public class Room 
{
    private String description;
    private HashMap<String, Exit> exits;        
    private HashMap<String, Item> items;        
    private HashMap<String, Character> characters;
    private HashMap<String, Lock> doors;

    /**
     * Constructor van klasse room
     * @param zorgt ervoor dat description wordt opgeslagen als string
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Exit>();
        items = new HashMap<String, Item>();
        characters = new HashMap<String, Character>();
        doors = new HashMap<String, Lock>();
    }
    
    /**
     * voegt character toe aan het spel met benodigde info
     * @param geeft character informatie
     */
    public void addCharacter(String name, String description, String itemname, String itemdesc)
    {
    
        Set<String> keys = characters.keySet();
        for(String character : keys)
            if (character.equals(name))
                return;
    
        Character newCharacter = new Character(name, description, itemname, itemdesc);
        characters.put(name, newCharacter);
        
    }
    
    /**
     * voegt item toe aan item lijst met naam en beschrijving
     */
    public void setItem(String name, String description)
    {
        // controleert als item al bestaat
               
        Set<String> keys = items.keySet();
        for(String item : keys)
            if (item.equals(name))
                return;
        
        Item newItem = new Item(description, name);
        items.put(name, newItem);
    }
    
    
    /**
     * controleert als de item in de kamer ligt en haalt item als nodig is weg
     * @param geeft item naam
     */
    public Item delItem(String name)
    {
     
        Set<String> keys = items.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = items.get(name);
                items.remove(name);
                return temp;
            }            
        }
        System.out.println("That isn't here.");
        return null;
        
    }

    /**
     * Zet room uitgangen
     */
    public void setExit(String direction, Room neighbor) 
    {
        Exit temp = new Exit(direction, neighbor);
        exits.put(direction, temp);
    }
    
    /**
     * Zet een locked door ergens neer
     */
    public void setDoor(String direction, Room neighbor, boolean locked)
    {
        Lock temp = new Lock(direction, neighbor, locked);
        doors.put(direction, temp);
    }

    /**
     * geeft beschrijving van de kamer weer
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * @return geeft beschrijving terug van de kamer
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getDoorString() + "\n" + getItemString() + "\n" + getCharacterstring();
    }

    /**
     * @return stuurt de uitgangen terug
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
            returnString += " " + exit;
        return returnString;
    }
    
    /**
     * @return stuurt de door terug als die er is
     */
    private String getDoorString()
    {
        String returnString = "Go through Door:";
        Set<String> keys = doors.keySet();
        for(String door : keys)
            returnString += " " + door;
        return returnString;
    }
    
    /**
     * @return stuurt items terug uit de kamer
     */
    private String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    /**
     * @return stuurt stuurt character terug als die er is
     */
    private String getCharacterstring()
    {
        String returnString = "Creature's:";
        Set<String> keys = characters.keySet();
        for(String character : keys)
            returnString += " " + character;
        return returnString;
    }

    /**
     * Zorgt ervoor als er geen uitgang is naar een bepaalde richting dat we daar niet heen kunnen
     */
    public Room getExit(String direction) 
    {
        Exit tempExit = exits.get(direction);
        if (tempExit != null)
        {
            return tempExit.getNeighbor();
        }
        return null;
    }
    
    /**
     *  Zorgt ervoor als er geen uitgang is naar een bepaalde richting dat we daar niet heen kunnen 
     */
    public Room getDoor(String direction)
    {
        Lock tempDoor = doors.get(direction);
        if (tempDoor != null)
        {
            return tempDoor.getNeighbor();
        }
        return null;
    }
    
    public boolean getLocked(String direction)
    {
        return doors.get(direction).getLocked();
    }
    
    public Lock getActualDoor(String direction)
    {
        return doors.get(direction);
    }
    
    public Item getItem(String name)
    {
        return items.get(name);
    }
    
    public Character getCharacter()
    {
        return characters.entrySet().iterator().next().getValue();
    }    
}
