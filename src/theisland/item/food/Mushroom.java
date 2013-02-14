package theisland.item.food;

import java.util.Random;

import theisland.gui.Gui;
import theisland.world.World;

/**
 *
 * @author Xavier
 */
public class Mushroom extends Food {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3637084421735024654L;
	private final int MAXIMUM_LIFETIME = 4;
    private final String[] MUSHROOM_POSSIBILITIES = {"Champignon de Paris", "Morille"};
    
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
        	World.getInstance().getHero().addEnergy(10);
        	Gui.display("You gained 10 energy!");
        }
    }

	@Override
	public void use() {
		this.eat();
	}
}
