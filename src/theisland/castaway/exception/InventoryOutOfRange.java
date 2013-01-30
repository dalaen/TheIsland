/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class InventoryOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>InventoryOutOfRange</code> without detail message.
     */
    public InventoryOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>InventoryOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InventoryOutOfRange(String msg) {
        super(msg);
    }
}
