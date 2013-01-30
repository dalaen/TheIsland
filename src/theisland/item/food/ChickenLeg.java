package theisland.item.food;

import java.util.Random;

/**
 *
 * @author Xavier
 */
public class ChickenLeg extends Food {
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
    }
}
