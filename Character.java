
/**
 * Character.java. Stores information about characters, non player characters.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Character extends Player
{
    private String name;
    private String description;

    /**
     * @param zorgt ervoor dat de character aan de juiste informatie voldoet
     */
    public Character(String name, String description, String itemname, String itemdesc)
    {
        this.description = description;
        this.name = name;
        addInventory(itemname, itemdesc);
        
    }

    /**
     * @return zorgt ervoor dat de description terug gestuurd wordt
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return zorgt ervoor dat de naam terug gestuurd wordt
     */
    public String getName()
    {
        return name;
    }
    
}
