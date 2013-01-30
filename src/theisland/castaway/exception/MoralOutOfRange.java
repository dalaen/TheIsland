/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class MoralOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>MoralOutOfRange</code> without detail message.
     */
    public MoralOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>MoralOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MoralOutOfRange(String msg) {
        super(msg);
    }
}
