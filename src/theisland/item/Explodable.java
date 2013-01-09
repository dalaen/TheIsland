/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.item;

/**
 *
 * @author Xavier
 */
public interface Explodable {
    // Allow the item the ability to explode
    // Implementation can vary in its effects, the intensity of the explosion (which would cause more damage, remove more moral, etc.)
    public void explode();
}
