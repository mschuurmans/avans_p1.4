package nl.avans.essperience.utils;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class AssetManager
{
	private String[] customAssets = new String[] { "flappy.png" , "heart.png"};

	private Map<String, Image> _assets = new HashMap<String, Image>();

	private static AssetManager _instance = null;

	public static AssetManager Instance()
	{
		if (_instance == null)
			_instance = new AssetManager();

		return _instance;
	}

	private AssetManager()
	{
		loadAssets();
	}

	public void loadAssets()
	{
		_assets = new HashMap<String, Image>();

		for (String asset : customAssets)
		{
			URL url = this.getClass().getClassLoader().getResource(asset);
			if(url == null)
			{
				return;
			}
			else
			{
				try 
				{
					Image img = ImageIO.read(url);
					_assets.put(asset, img);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public Image getImage(String key)
	{
		return _assets.get(key.trim());
	}
}