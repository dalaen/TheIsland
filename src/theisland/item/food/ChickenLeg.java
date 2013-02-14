package theisland.item.food;

import java.util.Random;

import theisland.gui.Gui;
import theisland.item.Explodable;
import theisland.world.World;

/**
 *
 * @author Xavier
 */
public class ChickenLeg extends Food implements Explodable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2966331841836108616L;
	private final int MAXIMUM_LIFETIME = 2;
    
    public ChickenLeg() {
        setName("Chicken Leg");
        
        // Chance manipulation
        // 20% chance to get a rotten item (lifetime = 0)
        // 60% chance to get a half-fresh item (lifetime = 1). Eat it fast!
        // 20% chance to get a brand new item (lifetime = 2)
        int diceRoll = (new Random()).nextInt(100);
        if (diceRoll >= 20 && diceRoll < 80) {
            setLifetime(1, MAXIMUM_LIFETIME);
        } else if (diceRoll >= 80 && diceRoll < 100) {
            setLifetime(2, MAXIMUM_LIFETIME);
        } else {
            setLifetime(0, MAXIMUM_LIFETIME);
        }
    }
    
    /**
     * Eat a chicken leg
     */
    public void eat() {
        super.eat();
        
        if (isRotten) {
        	World.getInstance().getHero().removeEnergy(15);
        	World.getInstance().getHero().removeHealth(15);
        	Gui.display("You lost 15 energy and health! Ay...");
        } else {
        	World.getInstance().getHero().addEnergy(20);
        	Gui.display("You gained 20 energy!");
        }
    }

	@Override
	public boolean explode() {
		int diceRoll = (new Random()).nextInt(15);
		
		if (diceRoll == 7) { // 1 chance upon 15
			World.getInstance().getHero().removeEnergy(15);
			World.getInstance().getHero().removeHealth(60);
			return true;
		}
		return false;
	}
}
