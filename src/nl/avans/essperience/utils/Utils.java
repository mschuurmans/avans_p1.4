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
		else if(code == 87)
			return GameKeys.KeyW;
		else if(code == 68)
			return GameKeys.KeyD;
		else if(code == 10)
			return GameKeys.KeyEnter;
		else if(code == 38)
			return GameKeys.KeyUp;
		else if(code == 40)
			return GameKeys.KeyDown;
		else
			return GameKeys.None;
	}
	
	public static int percentOf(int percent, int maxValue)
	{
		return (maxValue / 100) * percent;
	}
}
