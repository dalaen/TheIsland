package theisland.world.exception;

import theisland.gui.Gui;

/**
 * The InvalidDayNumber exception is thrown when an invalid day number (either negative, either too big) is being loaded.
 * Main sources of corruption is manual modification of the save file.
 * @author Xavier
 *
 */
public class InvalidDayNumber extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368801159187335947L;

	/**
	 * Display an error message upon exception throwing.
	 */
	public InvalidDayNumber() {
		Gui.displayError("The number of day is incorrect. Either it's negative or it's greater than the maximum allowed.");
	}
}
