package theisland.item;

/**
 *
 * @author Xavier
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
