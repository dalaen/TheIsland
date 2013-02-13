package theisland.world.exception;

import theisland.gui.Gui;

public class InvalidDayNumber extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368801159187335947L;

	public InvalidDayNumber() {
		Gui.displayError("The number of day is incorrect. Either it's negative or it's greater than the maximum allowed.");
	}
}
