/**
 * 
 */
package theisland.item;

import theisland.gui.Gui;
import theisland.world.World;

/**
 * A Stone is a simple piece of rock.
 * This is used in the construction of a cabin.
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 */
public class Stone extends Item {

	private static final long serialVersionUID = 366519576944331758L;

	/**
	 * Create a stone with its basic properties.
	 */
	public Stone() {
		setName("Stone");
	}
	
	/**
	 * Allows the castaway to actually eat a stone. Yes, it might hurt!
	 */
	@Override
	public void eat() {
		World.getInstance().getHero().removeHealth(25);
		World.getInstance().getHero().removeMoral(25);
		Gui.display("Why did you do that? You lost a tooth! You lost 25 health and 25 moral.");
	}

	/**
	 * Use a stone to try building a cabin along with other items in the castaway's inventory.
	 */
	@Override
	public void use() {
		World.getInstance().tryBuildCabin();
	}

}
