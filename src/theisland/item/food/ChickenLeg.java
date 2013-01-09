package theisland.item.food;

/**
 *
 * @author Xavier
 */
public class ChickenLeg extends Food {
    private final int MAXIMUM_LIFETIME = 2;
    
    public ChickenLeg() {
        setName("Chicken Leg");
        lifetime = MAXIMUM_LIFETIME; // TODO: Random lifetime between 0 and MAXIMUM_LIFETIME
    }
    
    /**
     * Eat a chicken leg
     */
    @Override
    public void eat() {
        
    }
}
