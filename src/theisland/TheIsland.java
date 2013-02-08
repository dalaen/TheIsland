package theisland;

import theisland.gui.Gui;
import theisland.savesystem.Save;
import theisland.world.World;

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
        
        Save.getInstance().saveWorldData(World.getInstance());
        
        Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();
        
        for (int i = 0 ; i < World.getInstance().getNumberOfCastaway() ; i++) {
        	Save.getInstance().saveCharacterData(World.getInstance().getCastaway(i), i);
        }
    }
}
