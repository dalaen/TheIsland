package theisland.item.food;

import java.util.Random;

import theisland.gui.Gui;
import theisland.world.World;

/**
 * The Mushroom is a food item in the game.
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public class Mushroom extends Food {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3637084421735024654L;
	private final int MAXIMUM_LIFETIME = 4;
    private final String[] MUSHROOM_POSSIBILITIES = {"Champignon de Paris", "Morille"};
    
    /**
     * Create a mushroom, setting its properties.
     */
    public Mushroom() {
        int diceRoll;
        
        diceRoll = (new Random()).nextInt(MUSHROOM_POSSIBILITIES.length);
        setName(MUSHROOM_POSSIBILITIES[diceRoll]);
        
        // Chance manipulation
        // 25% chance to get a rotten item (lifetime = 0)
        // 75% chance to get it fresh
        diceRoll = (new Random()).nextInt(100);
        if (diceRoll >= 25 && diceRoll < 100) {
            setLifetime(1 + (new Random()).nextInt(MAXIMUM_LIFETIME), MAXIMUM_LIFETIME);
        } else {
            setLifetime(0, MAXIMUM_LIFETIME);
        }
    }
    
    /**
     * Eat a mushroom
     */
    public void eat() {
        super.eat();
        
        if (isRotten) {
        	World.getInstance().getHero().removeEnergy(15);
        	World.getInstance().getHero().removeHealth(50);
        	Gui.display("You lost 15 energy and 50 health! Ay...");
        } else {
        	int diceRoll = (new Random()).nextInt(2);
        	if (diceRoll == 1) {
        		World.getInstance().getHero().addHealth(20);
        	}
        	World.getInstance().getHero().addEnergy(10);
        	Gui.display("You gained 10 energy!");
        }
    }
}
