package theisland.item.food;

import theisland.gui.Gui;
import theisland.item.Item;

/**
 *
 * @author Xavier
 */
public abstract class Food extends Item {
    private boolean isRotten;
    protected int lifetime;
    
    void Food() {
        lifetime = 0;
        isRotten = true;
    }
    
    /*
     * Sets lifetime to a specific number
     *
     * @param lifetime to set on the item
     * @param maximumLifetime is the maximum lifetime the item can be
     */
    protected void setLifetime(int lifetime, final int MAXIMUM_LIFETIME)
    {
        if (MAXIMUM_LIFETIME >= 0) {
            if (lifetime > 0 && lifetime <= MAXIMUM_LIFETIME) {
                this.lifetime = lifetime;
                this.isRotten = false;
            } else if (lifetime <= 0) {
                this.lifetime = 0;
                this.isRotten = true;
            }
        }
    }
    
    /*
     * Is called when the day is shifting. Remove 1 day to the lifetime. Eventually, the food will get rotten.
     */
    public void decreaseLifeTime() {
        lifetime--;
        if (lifetime <= 0) {
            isRotten = true;
        }
    }
    
    /*
     * @return true if it's rotten
     */
    public boolean isRotten() {
        return isRotten;
    }
    
    /*
     * Eat the food item. Purpose here is to write a general message if the item is rotten.
     */
    @Override
    public void eat() {
        if (isRotten) {
            Gui.display("Your food was rotten!");
        } else {
            Gui.display("Yum yum! That was tasty!");
        }
    }
}
