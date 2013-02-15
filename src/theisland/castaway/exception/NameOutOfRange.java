/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

import theisland.gui.Gui;

/**
 * The NameOutOfRange exception occurs when the name of the character is either too short, or exceeds 20 characters.
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public class NameOutOfRange extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4046591275743081654L;

	/**
     * Creates a new instance of
     * <code>NameOutOfRange</code> without detail message.
     */
    public NameOutOfRange() {
        Gui.display("Your name is not valid ! Please retry !");
    }

    /**
     * Constructs an instance of
     * <code>NameOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NameOutOfRange(String msg) {
        super(msg);
    }
}
