package theisland.item;

/**
 *
 * @author Xavier
 */
public abstract class Item {
    private String name;
    
    // Eat the item. If the item is not eatable, this must produce a message!
    public abstract void eat();
    
    /*
     * Set a new name to the Item
     * @param name: must be between 1 and 20 characters
     */
    public void setName(String name) {
        if (name.length() >= 1 && name.length() <= 20) {
            this.name = name;
        }
    }
    
    /*
     * Return the item's name
     * @return the item's name
     */
    public String getName() {
        return name;
    }
}
