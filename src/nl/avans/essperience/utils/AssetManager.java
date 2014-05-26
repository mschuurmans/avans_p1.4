package nl.avans.essperience.utils;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class AssetManager
{
	private String[] customAssets = new String[] { 	"Flappy/flappy.png" , "heart.png", "Flappy/pipe1.png", "Flappy/pipeCap.png", 
													"Flappy/background.png", "Flappy/bird.png", 
													"Flappy/bird2.png", "Flappy/bird3.png", "IndianaJantje/background.jpg", 
													"IndianaJantje/stonesspritesheet.png", "IndianaJantje/indianajantje_player_spritesheet.png",
													"IndianaJantje/bloodsplash.png", "Simon/banana.png", "Simon/orange.png", "Simon/apple.png",
													"Simon/pear.png", "Waf/fardoes.png", "Waf/lilly closed.png", "Waf/lilly open.png", "RedButton/arrow.png",
													"Waf/fardoes background.png", "Simon/simon_background_tree.png", "Simon/simon_background.png",
													"Fops/crosshair.png"};

	private String[] soundsList = new String[] { 	"Essperience/background1.wav", "Essperience/background2.wav", "Essperience/background3.wav",
													"Essperience/levelup.wav", "Essperience/lifeloss.wav", "Essperience/gameover.wav",
													"IndianaJantje/bloodsplash.wav", "IndianaJantje/stonebreak.wav", "IndianaJantje/stomp.wav",
													"Waf/hello.wav", "Waf/ouch.wav", "Waf/whack.wav", 
													"Flappy/flap.wav", "Flappy/smack.wav", "Flappy/bading.wav", 
													"Fops/gun shot.wav", "Fops/gun empty.wav", "Fops/splash.wav"};
	
	private Map<String, Image> _assets = new HashMap<String, Image>();
	private static Map<String, Clip> _sounds = new HashMap<String, Clip>();
	
	private String _currentBGMKey = "";
	
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
		//playBackgroundMusic("Essperience/background1.wav");
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
		
		for(String sound : soundsList)
		{
	        System.out.println("Sound loading: " + sound);
			try
			{
				URL url = this.getClass().getClassLoader().getResource(sound);
				System.out.println("Path of sound: " + url.getPath());
				
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
		        AudioFormat format = inputStream.getFormat();
		        DataLine.Info info = new DataLine.Info(Clip.class, format);
		        Clip clip = (Clip)AudioSystem.getLine(info);
		        clip.open(inputStream);
		        _sounds.put(sound, clip);
		        System.out.println("Sound loaded: " + sound);
			}
			catch(Exception e){e.printStackTrace();}
		}
	}

	public void playSound(final String key)
	{
		
		
		//Thread thread = new Thread(new Runnable()
		//{
		//	public void run()
		//	{
		//		try 
		//		{
					_sounds.get(key).stop();
					_sounds.get(key).setFramePosition(0);
					_sounds.get(key).start();
		//		}
		//	    catch (Exception e) 
		//	    {
		//	    	System.err.println(e.getMessage());
		//	    }
		//	}
		//});
		//thread.start();
		
	}
	
	public void playBackgroundMusic(final String key) {
		if (!_currentBGMKey.equals(key)) {
			if (!_currentBGMKey.equals("")) {
			_sounds.get(_currentBGMKey).stop();
		}
			_sounds.get(key).setFramePosition(0);
			_sounds.get(key).loop(Clip.LOOP_CONTINUOUSLY);
			_currentBGMKey = key;
		}
	}
	
	public void stopSound(final String key) {
		_sounds.get(key).stop();
	}
	
	public void stopCurrentBackgroundMusic() {
		System.out.println("stopping music");
		_sounds.get(_currentBGMKey).stop();
	}
	
	public String getCurrentBackgroundMusic() {
		return _currentBGMKey;
	}
	
	public Image getImage(String key)
	{
		return _assets.get(key.trim());
	}
}
