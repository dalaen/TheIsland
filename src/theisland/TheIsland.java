package theisland;

import theisland.gui.Gui;
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
        World.getInstance();
        
        Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();
    }
}
