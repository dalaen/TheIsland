/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class AffinityOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>AffinityOutOfRange</code> without detail message.
     */
    public AffinityOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>AffinityOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public AffinityOutOfRange(String msg) {
        super(msg);
    }
}
