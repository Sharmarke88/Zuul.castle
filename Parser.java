import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */
public class Parser 
{
    // Bewaart alle commandwords
    private CommandWords commands;  
    // geeft player input terug
    private Scanner reader;         

    /**
     * Maakt een parser aan om player input te lezen
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 
     * @return Stuurt player command terug
     */
    public Command getCommand() 
    {
        String inputLine = ""; 
        String word1;
        String word2;

        System.out.print("> ");     

        String line = reader.nextLine();
        Scanner scan = new Scanner(line);
        
        if(scan.hasNext())
        // eerste woord
            word1 = scan.next();     
        else
            word1 = null;
        if(scan.hasNext())
        // Tweede woord
            word2 = scan.next();      
        else
            word2 = null;


        // maakt een command
        return new Command(word1, word2);
    }

    /**
     * 
     * Brengt alle commands naar het scherm
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
