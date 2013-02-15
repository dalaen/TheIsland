/**
 * 
 */
package theisland.item;

import theisland.gui.Gui;
import theisland.world.World;

/**
 * A piece of Wood that can be used to build a cabin. 
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public class Wood extends Item {

	private static final long serialVersionUID = -4889222304123652433L;

	/**
	 * Create a piece of Wood with its own properties.
	 */
	public Wood() {
		setName("Wood");
	}
	
	/**
	 * Allows the castaway to actually eat the Wood piece. Yes, this might hurt!
	 */
	@Override
	public void eat() {
		World.getInstance().getHero().removeHealth(15);
		World.getInstance().getHero().removeMoral(15);
		Gui.display("It hurts your teeth! You lost 15 health and 15 moral.");
	}

	/**
	 * Use a wood piece to try building a cabin along with other items in the castaway's inventory.
	 */
	@Override
	public void use() {
		World.getInstance().tryBuildCabin();
	}

}
