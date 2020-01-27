/**
 * Exit.java. Stores information on exits, non-lockable
 * travel avenues in Zuul.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Exit
{
    private String direction;
    private Room neighbor;

    /**
     * Constructor for objects of class Exit
     */
     
     public Exit()
     {
     }
     
    public Exit(String direction, Room neighbor)
    {
        this.direction = direction;
        this.neighbor = neighbor;

    }
    
    public String getDirection()
    {
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
    
    public void setDirection(String direction)
    {
        this.direction = direction;
    }
    
    public void setNeighbor(Room neighbor)
    {
        this.neighbor = neighbor;
    }
    
}
