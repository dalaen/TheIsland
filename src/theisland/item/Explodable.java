package theisland.item;

/**
 * The Explodable interface implements the ability to explode to an item. This item can be anything, be creative!
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public interface Explodable {
    /**
     * Give the item the ability to explode
     * Implementation can vary in its effects, the intensity of the explosion (which would cause more damage, remove more moral, etc.)
     * 
     * The implementation must include the random part
     * @return true if the item actually exploded, false otherwise
     */
    public boolean explode();
}
