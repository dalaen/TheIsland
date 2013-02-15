/**
 * 
 */
package theisland.world.exception;

import theisland.gui.Gui;

/**
 * The InvalidMaximumDayNumber exception is called when a invalid maximum day number is set.
 * This generally occurs in the loading phases, if the user manually altered the save file.
 * @author Xavier
 *
 */
public class InvalidMaximumDayNumber extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7884424807947571775L;

	/**
	 * Display an error message upon exception throwing.
	 */
	public InvalidMaximumDayNumber() {
		Gui.displayError("The maximum number of day is incorrect. It is simply out of bounds.");
	}
}
