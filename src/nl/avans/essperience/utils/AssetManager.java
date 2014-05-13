package nl.avans.essperience.utils;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class AssetManager
{
	private String[] customAssets = new String[] { "Flappy/flappy.png" , "heart.png", "Flappy/pipe1.png", "Flappy/background.png", "Flappy/bird.png", 
			"Flappy/bird2.png", "Flappy/bird3.png", "IndianaJantje/background.jpg", "IndianaJantje/stonesspritesheet.png"};


	
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
			System.out.println("Asset: " + asset);
			URL url = this.getClass().getClassLoader().getResource(asset);
			if(url == null)
			{
				System.out.println("Asset is null: " + asset);
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