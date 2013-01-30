/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class NameOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>NameOutOfRange</code> without detail message.
     */
    public NameOutOfRange() {
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
