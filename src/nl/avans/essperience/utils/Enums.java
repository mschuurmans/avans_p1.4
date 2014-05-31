package nl.avans.essperience.utils;

import nl.avans.essperience.controllers.WiiController;

public class Enums
{

	public static enum GameKeys
	{
		KeyA,
		KeyB,
		KeyEnter,
		WiiA,
		WiiB,
		WiiUp,
		WiiRight,
		WiiDown,
		WiiLeft,
		WiiMinus,
		WiiPlus,
		WiiHome,
		KeyW,
		KeyD,
		KeyS,
		KeyUp,
		KeyDown,
		KeySpacebar,
		KeyU,
		KeyI,
		KeyO,
		KeyP,
		Key1,
		Key2,
		Key3,
		Key4,
		Key5,
		Key6,
		Key7,
		Key8,
		Key9,
		None;	
		
		public int getID(){return WiiController.getID();}
	}
	
	public static enum DockLocations
	{
		Top,
		Bottom,
		Left,
		Right
	}
}
