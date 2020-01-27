/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Jahmiro Kooijstra and Sharmarke Hussein
 * @version (25-01-2020)
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Character porter;
        
    /**
     * zorgt ervoor dat de game en de map gemaakt worden.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * maakt alle kamers en zorgt dat ze verbonden zijn
     */
    private void createRooms()
    {
        Room outside, mainhall, library, diningroom, kitchen, stairs, bedroom, bathroom;
      
        // Maakt kamers aan
        outside = new Room("outside the main entrance of the castle");
        mainhall = new Room("in the main Hall");
        library = new Room("in the library");
        diningroom = new Room("in the dining room ");
        kitchen = new Room("in the kitchen");
        stairs = new Room("on the stairs ");
        bedroom = new Room("in the bedroom ");
        bathroom = new Room("in the bathroom ");
        
        // Zorgt dat kamers verbonden zijn met elkaar
        outside.setExit("north", mainhall);
        
        mainhall.setExit("south", outside);
        mainhall.setExit("west", diningroom);
        mainhall.setExit("north", stairs);
        
        mainhall.setDoor("door", library, true);
        
        library.setDoor("door", mainhall, false);

        diningroom.setExit("north", kitchen);
        diningroom.setExit("east", mainhall);

        kitchen.setExit("south", diningroom);
        
        stairs.setExit("up", bedroom);
        stairs.setExit("down", mainhall);
        
        bedroom.setExit("south", stairs);
        bedroom.setExit("east", bathroom);
        
        bathroom.setExit("west", bedroom);
        
        // legt items in een kamer
        kitchen.setItem("cookie", "Chocolate cookie");
        library.setItem("treasure", "the treasure of the Baron ");
        // zet een character neer in een kamer met een item
        bathroom.addCharacter("Skeleton", "There is a skeleton sitting on the toilet", "key", "A golden key");
        
        // begint spel "outside"
        player.setCurrentRoom(outside); 
    }

    /**
     * zorgt ervoor dat we kunnen spelen.
     */
    public void play() 
    {            
        printWelcome();

        //commands worden uitgevoerd en gelezen tot het spel over is
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing pirate aarrghhh");
    }
    
    private void printLocationInfo(){
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println();
    }

    /**
     * begin bericht voor speler
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Pirates!");
        System.out.println("World of Pirates is a new, incredibly fun adventure game.");
        System.out.println();
        System.out.println("For centuries The Treasure of baron was lost. You were desparete to find his treasure, but years went by...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("You were sailing on sea, but there was a storm. Your boat was damaged and you fell in sea the waves brought you to a mysterious place");
        System.out.println("You look around an-and th-there i-it is! You finally found the baron's castle");
        System.out.println("There is only one thing left to do and that is to find the treasure!!");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getInventoryString());
    }

    /**
     * Alle commands die er zijn om het spel te spelen.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean pirate...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")){
            printHelp();
        }
        else if (commandWord.equals("go")){
            goRoom(command);
        }
        else if (commandWord.equals("drop")){
            dropItem(command);
        }
        else if (commandWord.equals("get")){
            wantToQuit = getItem(command);
        }
        else if (commandWord.equals("trade")){
            tradeItem(command);
        }
        else if (commandWord.equals("study")){
            studyItem(command);
        }
        else if (commandWord.equals("unlock")){
            unlockDoor(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("dance")) {
            printDance();
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        return wantToQuit;
    }

    // implementatie voor de command words. 
    
    /** 
     * Zorgt ervoor dat de speler een bepaalde deur kan openen als die opslot is
     */
    private void unlockDoor(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Unlock what?");
            return;
        }
        
        String desiredDoor = command.getSecondWord();
        
        if (player.checkKey())
        {    
            player.getCurrentRoom().getActualDoor(desiredDoor).unlock();
            System.out.println("Door unlocked");
        }
        else
        {
            System.out.println("You don't have a key!");
        }
        
    }


    /**
     * Als je de command words bent vergeten.
     */
    private void printHelp() 
    {
        System.out.println("You finally found the baron's Castle!");
        System.out.println("you're a happy pirate");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
     /**
      * Laat je dansen
     */
    private void printDance() 
    {
        System.out.println("You show your best dance moves");
        System.out.println("...");
        System.out.println("...");
        System.out.println("That feels good now move on");
    }
   
    /**
     * Laat je de gegevens van speler en kamer nog eens zien. 
     */
    private void look()
     {
      System.out.println(player.getCurrentRoom().getLongDescription());   
     }

    /** 
     * Kijkt als er een kamer is waar je heen kan, anders krijg je een error.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // Zonder tweede woord kunnen we nergens heen
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        // Proberen om de kamer te verlaten
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        Room nextDoor = player.getCurrentRoom().getDoor(direction);
        
        if (nextDoor != null)
        {
          // Als er een door is
        if (player.getCurrentRoom().getLocked(direction) == true)
            {
                System.out.println("The door is locked!");
                return;
            }
            else
            {
                player.setCurrentRoom(nextDoor);
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println(player.getInventoryString());
                return;
            }
        }

        if (nextRoom == null)
            System.out.println("There is no exit!");
        else {
               player.getRoomHistory().push(player.getCurrentRoom());
               player.setCurrentRoom(nextRoom);
               printLocationInfo();
        }
        
        
    }
    
    /**
     * Gaat terug naar de vorige kamer
     */
    private void back(Command command)
    {
        if (command.hasSecondWord()){
            System.out.println("Go back??");
        }
        else {
            if (!player.getRoomHistory().empty()) {
                player.setCurrentRoom(player.getRoomHistory().pop());
                printLocationInfo();
            } else {
                System.out.println("You can't go back any further");
            }
        }
    }
    
    /**
     * Laat een item naar keuze vallen in de kamer, als er geen item is krijg je een error.
     */
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            // Tweede woord is nodig om iets te laten vallen
            System.out.println("Drop what?");
            return;
        }
        
        String droppedItem = command.getSecondWord();
        
        // laat item vallen
        
        Item temp = player.dropInventory(droppedItem);
        if (temp != null)
        {
            
            // voegt het toe aan de kamer
            player.getCurrentRoom().setItem(temp.getName(), temp.getDescription());
            
            // laat nieuwe info zien
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }      
        
    }
    
    /** 
     * Laat je een item uit de kamer oppakken, als er geen items zijn krijg je een error
     */
    private boolean getItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // Zonder tweede woord kunnen we niks oppakken
            System.out.println("Take what?");
            return false;
        }
        
        String desiredItem = command.getSecondWord();
              
        // Haalt item uit kamer
        Item temp = player.getCurrentRoom().delItem(desiredItem);
        if (temp != null)
        {     
            // Voegt het toe aan inventory
            player.addInventory(temp.getName(), temp.getDescription());
            
            if (player.hasItemInInventory("treasure")) {
            
                     System.out.println("Finally after al these years you have found the Baron's treasure.");
                     System.out.println("You got the treasure, but your ship as been damaged by the storm you have nowhere to go.");
                     System.out.println("Will this be the end of your adventure...");
                     System.out.println("...");
                     System.out.println("...");
                     System.out.println("Wa-wait there is skeleton he transforming in-into the ba-baron!!");
                     System.out.println("He has given you his ship because you gave him his favorite cookie!");
                     System.out.println("So your adventure goes on! YOU WIN!!!");
                     return true;   
                    }
            // Laat nieuwe info zien
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
        return false;
    }
    
     
    /** 
     * Dit zorgt ervoor dat je een bepaalde item ruilen met een ander item, als er geen item is om te ruilen krijg je een error.
     */
    private void tradeItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // Zonder tweedee woord kunnen we niks ruilen.
            System.out.println("Trade what?");
            return;
        }
        
        String tradingItem = command.getSecondWord();
        
        Character characters = player.getCurrentRoom().getCharacter();
        //haalt item weg bij character
        Item key = characters.dropInventory();
        // voegt item toe aan player
        player.addInventory(key);
        // haalt item weg bij player
        Item cookie = player.dropInventory(tradingItem);
        
        if (cookie == null)
        {
            characters.addInventory(key);
            return;
        }
        // voegt item toe aan character
        characters.addInventory(cookie);
        // laat nieuwe info zien
        System.out.println(player.getInventoryString());
    }
    
    /** 
     * Bekijkt het item in je inventory, als er geen item is krijg je een error
     */
    private void studyItem(Command command)
    {
       if (!command.hasSecondWord()) {
            // Zonder tweede woord weten we niet wat we moeten bestuderen
            System.out.println("Study what?");
            return;
        }
            
        System.out.println(player.getStudyString(command.getSecondWord()));
        return;
    }
    
    /**
     * Dit zorgt ervoor dat je kan stoppen met het spel
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
        // Zorgt ervoor dat we kunnen stoppen
            return true;
    }
}