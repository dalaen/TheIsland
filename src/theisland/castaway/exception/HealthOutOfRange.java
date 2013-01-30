/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class HealthOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>HealthOutOfRange</code> without detail message.
     */
    public HealthOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>HealthOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public HealthOutOfRange(String msg) {
        super(msg);
    }
}
