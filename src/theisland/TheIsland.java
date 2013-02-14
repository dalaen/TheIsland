package theisland;

import java.util.Scanner;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
import theisland.history.History;
import theisland.history.Mode;
import theisland.savesystem.Save;
import theisland.world.World;

/**
 *
 * @author Xavier Poirot
 */
public class TheIsland {
	
	private static final World WORLD = World.getInstance();
	private final static Scanner SCANNER = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        WORLD.createWorld();

    	final Castaway HERO = WORLD.getHero();
        
        if (WORLD.isNew()) {
        	// TODO: Prompt user which mode he want to play
        	History.displayIntroduction(Mode.SOLO);
        }
        
        while (WORLD.getNumberOfCastaway() > 1) {
        	Action whatToDo = null;

        	Save.getInstance().saveWorldData(WORLD);
        	Save.getInstance().saveAllCharacterData(WORLD);
        	
        	Gui.displayHud(HERO, WORLD);
        	// Utiliser un objet
        	// Manger un objet
        	// Chercher un objet
        	// Parler avec quelqu'un
        	// Voler un objet à quelqu'un
        	// Échanger un objet avec quelqu'un
        	// Jeter un objet
        	// Dormir
        	whatToDo = Gui.promptUserAction(SCANNER);
        	if (HERO.getMoral() == 0) {
        		Gui.display("You are not going well... Boulimia is watching upon you!");
        		whatToDo = Action.EAT;
        	}
        	if (whatToDo.equals(Action.USE)) {
        		HERO.displayInventory();
        		if (HERO.getInventory().isEmpty()) {
        			Gui.display("Too bad! Your inventory is empty!");
        		} else {
	        		Gui.displayInline("What do you want to use? ");
	        		int choice = 0;
	        		do {
	        			choice = SCANNER.nextInt() - 1; // Don't forget -1 for array handling
	        		} while (choice < 0 || choice > (HERO.getInventory().size() - 1));
	        		// TODO: Change this function to use()
	        		HERO.getInventory().get(choice).eat();
        		}
        	} else if (whatToDo.equals(Action.EAT)) {
        		HERO.displayInventory();
        		if (HERO.getInventory().isEmpty()) {
        			Gui.display("Too bad! Your inventory is empty!");
        		} else {
	        		Gui.displayInline("What do you want to eat? ");
	        		int choice = 0;
	        		do {
	        			choice = SCANNER.nextInt() - 1; // Don't forget -1 for array handling
	        		} while (choice < 0 || choice > (HERO.getInventory().size() - 1));
	        		// TODO: Change this function to use()
	        		HERO.getInventory().get(choice).eat();
        		}
        	} else if (whatToDo.equals(Action.LOOKFOR)) {
        		HERO.lookForItem();
        	} else if (whatToDo.equals(Action.SPEAK)) {
        		Gui.displayCastaways(WORLD);
        		Gui.displayInline("Who do you want to speak to? ");
        		int choice = 0;
        		do {
        			choice = SCANNER.nextInt();
        		} while (choice < 1 || choice >= WORLD.getNumberOfCastaway());
        		HERO.speakTo(WORLD.getCastaway(choice));
        	} else if (whatToDo.equals(Action.STEAL)) {
        		Gui.displayCastaways(WORLD);
        		Gui.displayInline("Who do you want to steal from? ");
        		int choice = 0;
        		do {
        			choice = SCANNER.nextInt();
        		} while (choice < 1 || choice >= WORLD.getNumberOfCastaway());
        		HERO.steal(WORLD.getCastaway(choice));
        	} else if (whatToDo.equals(Action.TRADE)) {
        		Gui.displayCastaways(WORLD);
        		Gui.displayInline("Who do you want to trade with? ");
        		int choice = 0;
        		do {
        			choice = SCANNER.nextInt();
        		} while (choice < 1 || choice >= WORLD.getNumberOfCastaway());
        		HERO.dealWith(WORLD.getCastaway(choice));
        	} else if (whatToDo.equals(Action.THROW)) {
        		HERO.displayInventory();
        		if (HERO.getInventory().isEmpty()) {
        			Gui.display("Too bad! Your inventory is empty!");
        		} else {
	        		Gui.displayInline("What do you want to use? ");
	        		int choice = 0;
	        		do {
	        			choice = SCANNER.nextInt() - 1; // Don't forget -1 for array handling
	        		} while (choice < 0 || choice > (HERO.getInventory().size() - 1));
	        		HERO.deleteItemFromInventory(HERO.getInventory().get(choice));
        		}
        	} else if (whatToDo.equals(Action.SLEEP)) {
        		WORLD.nextDay();
        	}
        }
        
        
        
        //World.getInstance().getHero().createRandomInventory();
        
        /*Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();*/
        
        
        SCANNER.close();
        //World.getInstance().getHero().displayInventory();
    }
}
