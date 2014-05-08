package nl.avans.essperience.utils;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

public class AssetManager
{
	private String[] customAssets = new String[] { "res/flappy.png" };

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

/*		for (Stage stage : FestivalHandler.Instance().getStages())
		{
			if (!stage.getImage().trim().equals(""))
			{
				File file = new File(stage.getImage());

				if (file.exists())
				{
					try
					{
						Image img = ImageIO.read(file);
						_assets.put(stage.getImage().trim(), img);
					}
					catch (Exception e)
					{
					}
				}
			}
		}
*/
		for (String asset : customAssets)
		{
			File file = new File(asset);
			if (file.exists())
			{
				Image img;
				try
				{
					img = ImageIO.read(file);
					_assets.put(asset, img);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public Image getImage(String key)
	{
		// System.out.println(_assets);
		return _assets.get(key.trim());
	}
}