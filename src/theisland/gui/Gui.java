package theisland.gui;

import theisland.castaway.Castaway;

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
    public static void displayHud(Castaway hero) {
    	display("HUD – [Health]" + hero.getHealth() + " [Energy]" + hero.getEnergy() + " [Moral]" + hero.getMoral());
    }
}
