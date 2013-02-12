package theisland;

import java.util.ArrayList;

import theisland.castaway.exception.InventoryOutOfRange;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.item.food.ChickenLeg;
import theisland.item.food.Mushroom;
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
        
        // This is for test purpose
        ArrayList<Item> inventory = new ArrayList<Item>();
        inventory.add(new ChickenLeg());
        inventory.add(new Mushroom());
        try {
			World.getInstance().getCastaway(0).setInventory(inventory);
		} catch (InventoryOutOfRange e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Gui.display("There are "+ World.getInstance().getNumberOfCastaway() + " castaway on your island.");
        World.getInstance().printWeather();
        World.getInstance().nextDay();
        World.getInstance().printWeather();
        
        Save.getInstance().saveAllCharacterData(World.getInstance());
    }
}
