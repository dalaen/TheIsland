package theisland.item.food;

import theisland.gui.Gui;
import theisland.item.Item;

/**
 * Master class Food. Every food item in the game must inherit from this class.
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public abstract class Food extends Item {
    /**
	 * 
	 */
	private static final long serialVersionUID = -978387935297724604L;
	protected boolean isRotten;
    protected int lifetime;
    
    /**
     * Constructor above every food item, setting the food properties of an item.
     */
    Food() {
    	isFood = true;
        lifetime = 0;
        isRotten = true;
    }
    
    /**
     * Sets food's lifetime to a specific number
     *
     * @param lifetime the lifetime to set on the item
     * @param MAXIMUM_LIFETIME the maximum lifetime the item can be
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
    
    /**
     * Is called upon day shifting. Remove 1 day to the lifetime. Eventually the food will get rotten.
     */
    public void decreaseLifetime() {
        lifetime--;
        if (lifetime <= 0) {
            isRotten = true;
        }
    }
    
    /**
     * @return true if it's rotten
     */
    public boolean isRotten() {
        return isRotten;
    }
    
    /**
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
    
	/**
	 * Implements the use() function.
	 * This actually makes the castaway eat the item.
	 */
    public void use() {
		this.eat();
	}
}
