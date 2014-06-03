package nl.avans.essperience.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nl.avans.essperience.utils.Enums.GameKeys;

public class Utils 
{
	private static boolean _debug = false;
	public static GameKeys getFromKeyboardCode(int code)
	{
		if (_debug)
			System.out.println(code);
		if(code == 65)
			return GameKeys.KeyA;
		else if(code == 66)
			return GameKeys.KeyB;
		else if(code == 87)
			return GameKeys.KeyW;
		else if(code == 68)
			return GameKeys.KeyD;
		else if(code == 85)
			return GameKeys.KeyU;
		else if(code == 73)
			return GameKeys.KeyI;
		else if(code == 79)
			return GameKeys.KeyO;
		else if(code == 80)
			return GameKeys.KeyP;
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
	
	/**
	 * measures the width of a string
	 * @param s string to be measured
	 * @return width of the string in pixels
	 * @author jack
	 */
	public static int getWidth(String s)
	{  
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		return getWidth(s, font);
	}
	
	/**
	 * Crops a string to fit the maxWidth that has been passed in
	 * @param s string to be cropped
	 * @param maxWidth: max width(in pixels) the returned string has to fit in.
	 * @return a cropped string that fits the specified maxWidth
	 * @author jack
	 */
	public static String cropString(String s, int maxWidth)
	{
//		System.out.println("StringWidth and maxWidth"); //DEBUGGING PURPOSES
//		System.out.println(getWidth(s));
//		System.out.println(maxWidth);

		String string = s;

		if(getWidth(string) < maxWidth)
			return string;

		while(getWidth(string + "...") > maxWidth)
			string = string.substring(0, string.length() - 1);

		string += "...";

		return string;
	}
	
	public static boolean isUnix(String OS) {
		 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
	
	public static String parseName(String name)
	{
		String parsedName = "";
		
		if(name.length() == 0)
		{
			return "Unknown%20Soldier";
		}
		
		for(int i = 0; i < name.length(); i++)
		{
			if(name.charAt(i) == ' ')
			{
				parsedName += "%20";
			}
			else
			{
				parsedName += name.charAt(i);
			}
		}
		
		return parsedName;
	}
	
	public static void drawStringWithOutline(Graphics g, String string, Font font, int x, int y)
	{
		drawStringWithOutline(g, string, font, Color.white, Color.black, 2, x, y);
	}
	
	public static void drawStringWithOutline(Graphics g, String string, Font font, Color stringColor, Color outlineColor, int outlineThickness, int x, int y)
	{
		g.setColor(outlineColor);
		g.drawString(string, x - outlineThickness, y);
		g.drawString(string, x + outlineThickness, y);
		g.drawString(string, x, y - outlineThickness);
		g.drawString(string, x, y + outlineThickness);
		g.setColor(stringColor);
		g.drawString(string, x, y);
	}

	public static void addHighScore(final String name, final int score)
	{	
		final String parsedName = parseName(name);
		
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					String url = "http://essperience.tostring.nl/highscore_add/" + parsedName + "/" + score;
					 
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
					con.setRequestMethod("GET");
					con.setRequestProperty("User-Agent", "Mozilla/5.0");
			 
					int responseCode = con.getResponseCode();
					System.out.println("\nSending 'GET' request to URL : " + url);
					System.out.println("Response Code : " + responseCode);
			 
					BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) 
					{
						response.append(inputLine);
					}
					in.close();
			 
					//print result
					System.out.println(response.toString());
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
				
	}
	
	public static void disableAutoPress()
	{
		if(Utils.isUnix(System.getProperty("os.name").toLowerCase()))
		{
			try
			{
				Process proc = Runtime.getRuntime().exec("xset r off");
				System.out.println("Repeat is off");
				BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				proc.waitFor();
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void enableAutoPress()
	{
		if(Utils.isUnix(System.getProperty("os.name").toLowerCase()))
		{
			try
			{
				Process proc = Runtime.getRuntime().exec("xset r on");

				System.out.println("Rpeat is on");
				BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				proc.waitFor();
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
