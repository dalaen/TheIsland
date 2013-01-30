/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway.exception;

/**
 *
 * @author isen
 */
public class EnergyOutOfRange extends Exception {

    /**
     * Creates a new instance of
     * <code>EnergyOutOfRange</code> without detail message.
     */
    public EnergyOutOfRange() {
    }

    /**
     * Constructs an instance of
     * <code>EnergyOutOfRange</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public EnergyOutOfRange(String msg) {
        super(msg);
    }
}
