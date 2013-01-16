package theisland.item.food;

import theisland.item.Item;

/**
 *
 * @author Xavier
 */
public abstract class Food extends Item {
    private boolean isRotten;
    protected int lifetime;
    
    /*
     * Is called when the day is shifting. Remove 1 day to the lifetime. Eventually, the food will get rotten.
     */
    public void decreaseLifeTime() {
        lifetime--;
        if (lifetime <= 0) {
            isRotten = true;
        }
    }
}
