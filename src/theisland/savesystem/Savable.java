package theisland.savesystem;

import theisland.castaway.Castaway;
import theisland.world.World;

/**
 * These functions must be implemented in order to make a system that can save the game
 * @author xavier
 *
 */
public interface Savable {
	public void saveCharacterData(Castaway castaway, int characterId);
	public void saveWorldData(World world);
}
