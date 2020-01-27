/**
 * Door.java. Stores information and provides locking
 * and unlocking of doors, lockable exits in Zuul.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Lock extends Exit
{
    private boolean locked;

    /**
     * Constructor van klasse door
     */
    public Lock(String direction, Room neighbor, boolean locked)
    {
        this.locked = locked;
        this.setDirection(direction);
        this.setNeighbor(neighbor);
        
    }
     
    /**
     * Zorgt ervoor dat een deur opslot is
     */
    public void lock()
    {
        locked = true;
        return;
    }
    
    /**
     * Zorgt ervoor dat de deur open kan
     */
    public void unlock()
    {
        locked = false;
        return;
    }
    
    public boolean getLocked()
    {
        return locked;
    }
}