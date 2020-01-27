/**
 * Item.java. Holds information about each item in the game.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Item
{
    private String description;
    private String name;

    /**
     * Constructor voor klasse Item
     * @param zorgt ervoor dat items een beschrijving en naam krijgen.
     */
    public Item(String description, String name)
    {
        this.description = description;
        this.name = name;
    }

    /**
     * @return stuurt de beschrijving terug
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return stuurt de naam terug 
     */
    public String getName()
    {
        return name;
    }
}