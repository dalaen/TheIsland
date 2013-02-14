/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

import theisland.gui.Gui;

/**
 *
 * @author isen
 */
public class InventoryOutOfRange extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1992799926893773521L;

	/**
     * Creates a new instance of
     * <code>InventoryOutOfRange</code> without detail message.
     */
    public InventoryOutOfRange() {
        Gui.display("Your inventory is allready full !");
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
