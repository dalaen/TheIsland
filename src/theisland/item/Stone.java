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
public class Stone extends Item {

	private static final long serialVersionUID = 366519576944331758L;

	public Stone() {
		setName("Stone");
	}
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		World.getInstance().getHero().removeHealth(25);
		World.getInstance().getHero().removeMoral(25);
		Gui.display("Why did you do that? You lost a tooth! You lost 25 health and 25 moral.");
	}

	@Override
	public void use() {
		World.getInstance().tryBuildCabin();
	}

}
