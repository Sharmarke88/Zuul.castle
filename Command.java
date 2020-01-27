/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * Constructor voor klasse Command
     * Zorgt ervoor dat er 2 woorden ingevuld moeten worden om een command uit te voeren
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * @return stuurt het eerste woord terug
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return stuurt het tweede woord terug
     * als er geen tweede woord wordt ingevuld krijg je een error
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true als het command niet begrepen is
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true als de command een tweede woord heeft
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
