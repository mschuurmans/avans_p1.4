package nl.avans.essperience.utils;

import nl.avans.essperience.utils.Enums.GameKeys;

public class Utils 
{
	public static GameKeys getFromKeyboardCode(int code)
	{
		if(code == 65)
			return GameKeys.KeyA;
		else if(code == 66)
			return GameKeys.KeyB;
		else if(code == 10)
			return GameKeys.KeyEnter;
		else
			return GameKeys.None;
	}
}
