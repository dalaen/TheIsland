package theisland;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
import theisland.item.food.ChickenLeg;
import theisland.savesystem.Save;
import theisland.world.World;
import theisland.world.exception.TooManyCastaway;

/**
 *
 * @author Xavier
 */
public class TheIsland {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World.getInstance().createWorld();
        
        //World.getInstance().getCastaway(0).getInventory().add(new ChickenLeg());
        
        Save.getInstance().saveWorldData(World.getInstance());
        
        Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();
        
//        try {
//			World.getInstance().addCastaway(new Castaway("Hero"));
//		} catch (TooManyCastaway e) {
//			Gui.displayError("There is already too much castaway!");
//			e.printStackTrace();
//		}
        Save.getInstance().saveCharacterData(World.getInstance().getCastaway(0));
        Save.getInstance().saveCharacterData(World.getInstance().getCastaway(1));
    }
}
