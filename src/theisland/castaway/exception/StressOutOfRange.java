/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class StressOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>StressOutOfRange</code> without detail message.
     */
    public StressOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>StressOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public StressOutOfRange(String msg) {
        super(msg);
    }
}
