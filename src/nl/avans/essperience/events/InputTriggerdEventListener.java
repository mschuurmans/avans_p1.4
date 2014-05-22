package nl.avans.essperience.events;

import nl.avans.essperience.utils.Enums.GameKeys;
import wiiusej.wiiusejevents.physicalevents.IREvent;

public class InputTriggerdEventListener 
{
	public void keyPressed(GameKeys key){}
	public void keyReleased(GameKeys key){}
	public void WiimotionGForceMovement(){}
	public void wiimoteIREvent(IREvent event){}
}
