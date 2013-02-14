package theisland.gui;

import java.util.Scanner;

import theisland.Action;
import theisland.castaway.Castaway;
import theisland.world.World;

/**
 *
 * @author Xavier
 */
public class Gui {
	/**
	 * Display a string with a \n return at the end
	 */
    public static void display(String displayString) {
        if (!displayString.isEmpty()) {
            System.out.println(displayString);
        }
    }
    
    /**
     * Display a string without a \n return at the end.
     * Useful when information is asked to the user.
     */
    public static void displayInline(String displayString) {
        if (!displayString.isEmpty()) {
            System.out.print(displayString);
        }
    }
    
    /**
     * Display an error with a \n return at the end
     */
    public static void displayError(String displayString) {
    	if (!displayString.isEmpty()) {
    		System.err.println(displayString);
    	}
    }
    
    /**
     * Display HUD with vital variables about the hero
     * Displayed: hero's name, hero's health, hero's energy, hero's moral
     * @param hero the hero to display its details
     */
    public static void displayHud(Castaway hero, World world) {
    	display("Day " + world.getDayNumber() + " – [Health]" + hero.getHealth() + " [Energy]" + hero.getEnergy() + " [Moral]" + hero.getMoral());
    }
    
    /**
     * Display a menu to ask the player which action he wants to perform
     * @return the action within an Action enum
     */
    public static Action promptUserAction(Scanner sc) {
    	
    	Gui.display("1. Use an item");
    	Gui.display("2. Eat an item");
    	Gui.display("3. Look for an item in the island");
    	Gui.display("4. Speak with someone");
    	Gui.display("5. Steal someone");
    	Gui.display("6. Trade with someone");
    	Gui.display("7. Throw an item");
    	Gui.display("8. Sleep until tomorrow");
    	Gui.displayInline("What do you want to do now? ");
    	
    	int choice = sc.nextInt();
    	
    	switch(choice) {
    	case 1: return Action.USE;
    	case 2: return Action.EAT;
    	case 3: return Action.LOOKFOR; 
    	case 4: return Action.SPEAK; 
    	case 5: return Action.STEAL; 
    	case 6: return Action.TRADE; 
    	case 7: return Action.THROW; 
    	default: return Action.SLEEP;
    	}
    }
    
    /**
     * Prompt the user for a yes/no choice
     * @return true if it's a yes, false otherwise
     */
    public static boolean promptYesNoQuestion(Scanner sc) {
    	String enteredChoice = sc.nextLine();
    	if (enteredChoice.equals("n") || enteredChoice.equals("N")) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    /**
     * Display all castaways except the hero
     */
    public static void displayCastaways(World world) {
    	for (int i = 1 ; i < world.getNumberOfCastaway() ; i++) {
    		Gui.display(i + ". " + world.getCastaway(i).getName());
    	}
    }
}
