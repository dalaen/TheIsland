package theisland.item;

import java.io.Serializable;

/**
 * The Item class is the master class for any items created in the game.
 * It may implement the Serializable interface if you wish to save in a file your current set of item.
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public abstract class Item implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3377128896015718496L;
	protected String name;
	protected boolean isFood;
    
    /**
     * Eat the item. If the item is not eatable, this must produce a message!
     */
    public abstract void eat();
    
    /**
     * Use the item. This could be to build something from it!
     */
    public abstract void use();
    
    /**
     * Set a new name to the Item
     * 
     * @param name must be between 1 and 20 characters
     */
    public void setName(String name) {
        if (name.length() >= 1 && name.length() <= 20) {
            this.name = name;
        }
    }
    
    /**
     * Return the item's name
     * 
     * @return the item's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Tell if the item is a food item or not
     * 
     * @return true if it's food, false otherwise
     */
    public boolean isFood() {
		return isFood;
	}
}
