package theisland.item;

import java.io.Serializable;

/**
 *
 * @author Xavier
 */
public abstract class Item implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3377128896015718496L;
	private String name;
    
    /**
     * Eat the item. If the item is not eatable, this must produce a message!
     */
    public abstract void eat();
    
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
}
