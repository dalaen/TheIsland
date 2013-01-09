/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.item;

/**
 *
 * @author Xavier
 */
public abstract class Item {
    private String name;
    
    // Eat the item. If the item is not eatable, this must produce a message!
    public abstract void eat();
}
