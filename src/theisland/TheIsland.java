package theisland;

import theisland.gui.Gui;
import theisland.savesystem.Save;
import theisland.world.World;

/**
 *
 * @author Xavier Poirot
 */
public class TheIsland {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World.getInstance().createWorld();
        
        Save.getInstance().saveWorldData(World.getInstance());
        
        //World.getInstance().getHero().createRandomInventory();
        
        Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();
        
        Save.getInstance().saveAllCharacterData(World.getInstance());
        
        World.getInstance().getHero().displayInventory();
        
        Gui.displayHud(World.getInstance().getCastaway(0));
    }
}
