/**
 * 
 */
package theisland.item;

import theisland.gui.Gui;
import theisland.world.World;

/**
 * @author xavier
 *
 */
public class Wood extends Item {

	private static final long serialVersionUID = -4889222304123652433L;

	public Wood() {
		setName("Wood");
	}
	
	/* (non-Javadoc)
	 * @see theisland.item.Item#eat()
	 */
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		World.getInstance().getHero().removeHealth(15);
		World.getInstance().getHero().removeMoral(15);
		Gui.display("It hurts your teeth! You lost 15 health and 15 moral.");
	}

	@Override
	public void use() {
		World.getInstance().tryBuildCabin();
	}

}
