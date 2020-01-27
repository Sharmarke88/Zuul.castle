/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */

public class CommandWords
{
    // array van commandwords
    private static final String[] validCommands = {
        "go", "quit", "help", "get", "drop", "trade", "study","unlock","look","back","dance"
    };

    /**
     * Constructor van de klasse commandwords
     */
    public CommandWords()
    {
    }

    /**
     * `Controleert als de commandwoord juist is ingevuld
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    /**
     * Zet alle commandwoorden op het scherm
     */
    public void showAll() 
    {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.print(validCommands[i] + "  ");
        }
        System.out.println();
    }
}
