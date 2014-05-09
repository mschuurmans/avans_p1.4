package nl.avans.essperience.events;

import nl.avans.essperience.utils.Enums.GameKeys;

/**
 * This is a class used for parsing button pressed events.
 * @author michiel
 *
 */
public class ButtonPressedEventListener 
{
	public void keyboardButtonPressed(int code){}
	public void keyboardButtonReleased(int code){}
	public void wiimoteButtonPressed(GameKeys key){}
	public void wiimoteMotionGForceAcceleration(){}
	
}
