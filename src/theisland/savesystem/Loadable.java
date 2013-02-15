package theisland.savesystem;

/**
 * The interface Loadable must be implemented in order to make a system that can load the game
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 *
 */

public interface Loadable {
	/**
	 * Load the game
	 * @return true if load process has been successful, false otherwise
	 */
	public boolean load();
}
