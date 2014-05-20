package nl.avans.essperience.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

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
		else if(code == 32)
			return GameKeys.KeySpacebar;
		else if(code == 49)
			return GameKeys.Key1;
		else if(code == 50)
			return GameKeys.Key2;
		else if(code == 51)
			return GameKeys.Key3;
		else if(code == 52)
			return GameKeys.Key4;
		else if(code == 53)
			return GameKeys.Key5;
		else if(code == 54)
			return GameKeys.Key6;
		else if(code == 55)
			return GameKeys.Key7;
		else if(code == 56)
			return GameKeys.Key8;
		else if(code == 57)
			return GameKeys.Key9;
		else
			return GameKeys.None;
	}
	
	public static int percentOf(int percent, int maxValue)
	{
		return (maxValue / 100) * percent;
	}
	
	/**
	 * measures the width of a string
	 * @param s string to be measured
	 * @return width of the string in pixels
	 * @author jack
	 */
	public static int getWidth(String s, Font font)
	{
		int width;

//		width = s.length() * 3;

		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
//		Font font = new Font("Tahoma", Font.PLAIN, 12);
		width = (int)(font.getStringBounds(s, frc).getWidth());

		return width;
	}
}
