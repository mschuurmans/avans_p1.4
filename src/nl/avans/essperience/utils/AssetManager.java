package nl.avans.essperience.utils;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import nl.avans.essperience.entities.fops.BulletHole;
import nl.avans.essperience.entities.simon.FruitPiece;

public class AssetManager
{
	private String[] customAssets = new String[] { 	"Flappy/flappy.png" , "heart.png", "Flappy/pipe1.png", "Flappy/pipeCap.png", 
													"Flappy/background.png", "Flappy/bird.png", 
													"Flappy/bird2.png", "Flappy/bird3.png", "IndianaJantje/background.jpg", 
													"IndianaJantje/stonesspritesheet.png", "IndianaJantje/indianajantje_player_spritesheet.png",
													"IndianaJantje/bloodsplash.png", "Simon/banana.png", "Simon/orange.png", "Simon/apple.png",
													"Simon/pear.png", "Waf/fardoes.png", "Waf/lilly closed.png", "Waf/lilly open.png", "RedButton/arrow.png",
													"Waf/fardoes background.png", "Simon/simon_background_tree.png", "Simon/simon_background.png",
													"Fops/crosshair.png", "Fops/bullet.png", "Fops/splashbanana.png", "Fops/splashapple.png", "Fops/splashorange.png", "Fops/splashpear.png",
													"Fops/apple_pieces.png", "Fops/pear_pieces.png", "Fops/banana_pieces.png", "Fops/orange_pieces.png", "Fops/fruitops_background.png",
													"Fops/bullethole.png", "Fops/fruitops_splashscreen.png", "IndianaJantje/indianajantje_splashscreen.png",
													"Essperience/keyboardkey.png", "Essperience/keyboardkey_shift.png", "Essperience/keyboardkey_shift_down.png",
													"Essperience/keyboardkey_return.png", "Essperience/keyboardkey_spacebar.png", "Essperience/cursorhand.png",
													"Essperience/wiimote.png", "Essperience/gameover.png", "Essperience/footsteps.png", "Essperience/footsteps_green.png",
													"Essperience/essteling_logo.png","Essperience/bliss_background.jpg", "Essperience/essperience_logo.png"};


	private String[] soundsList = new String[] { 	"Essperience/background1.wav", "Essperience/background2.wav", "Essperience/background3.wav",
													"Essperience/levelup.wav", "Essperience/lifeloss.wav", "Essperience/gameover.wav", "Essperience/unrealsuperhero.wav",
													"IndianaJantje/bloodsplash.wav", "IndianaJantje/stonebreak.wav", "IndianaJantje/stomp.wav",
													"Waf/hello.wav", "Waf/ouch.wav", "Waf/whack.wav", 
													"Flappy/flap.wav", "Flappy/smack.wav", "Flappy/bading.wav", 
													"Simon/fall.wav", "Simon/splat.wav", 
													"Fops/gun shot.wav", "Fops/gun empty.wav", "Fops/splash.wav", "Fops/throw.wav", 
													"Essperience/unrealsuperhero.wav"};
	
	private Map<String, Image> _assets = new HashMap<String, Image>();
	private static Map<String, Clip> _sounds = new HashMap<String, Clip>();
	
	private String _currentBGMKey = "";
	
	private List<BulletHole> _bulletHoles = new ArrayList<BulletHole>();
	private List<FruitPiece> _fruitPieces = new ArrayList<FruitPiece>();
	
	//quicklist of names to enter on gameOverScreen
	private List<String> _highscoreQuickList = new ArrayList<String>();

	private boolean _soundEnabled = true;
	
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
		System.out.println("");
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
		
		readQuicklist();

	}
	
	private void readQuicklist()
	{
		try
		{
			String url = this.getClass().getClassLoader().getResource("Essperience/namesQuicklist.dat").getFile();
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(url));

			@SuppressWarnings("unchecked")
			List<String> list = (ArrayList<String>)in.readObject();
			
			this._highscoreQuickList = list;
			
			in.close();
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("file: namesQuickList.dat does not currently exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeQuickList()
	{
		ObjectOutputStream out;
		try {
			String url = this.getClass().getClassLoader().getResource("Essperience/namesQuicklist.dat").getFile();
			out = new ObjectOutputStream(new FileOutputStream(url));
			
			out.writeObject(_highscoreQuickList);
			
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void addQuickListEntry(String s)
	{
		if(_highscoreQuickList.contains(s))
			_highscoreQuickList.remove(s);
		
		_highscoreQuickList.add(s);
		
		if(_highscoreQuickList.size() > 7)
		{
			_highscoreQuickList = _highscoreQuickList.subList(1, 8);
		}
		
		System.out.println("HighScoreQuickList: " + _highscoreQuickList);
		
		writeQuickList();
	}
	
	public List<String> getHighscoreQuickList()
	{
		return this._highscoreQuickList;
	}

	public void playSound(final String key)
	{
		if(_soundEnabled)
		{
			if (!key.equals("Fops/gun shot.wav"))
				_sounds.get(key).stop();
			_sounds.get(key).setFramePosition(0);
			_sounds.get(key).start();
		}
	}
	
	public void playBackgroundMusic(final String key) 
	{
		if(_soundEnabled)
		{
			if (!_currentBGMKey.equals(key)) 
			{
				if (!_currentBGMKey.equals(""))
				{
					_sounds.get(_currentBGMKey).stop();
				}
				_sounds.get(key).setFramePosition(0);
				_sounds.get(key).loop(Clip.LOOP_CONTINUOUSLY);
				_currentBGMKey = key;
			}
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
	
	public List<BulletHole> getBulletHoles()
	{
		return _bulletHoles;
	}

	public void setBulletHoles(List<BulletHole> _bulletHoles)
	{
		this._bulletHoles = _bulletHoles;
	}
	
	public void addBulletHole(BulletHole bulletHole)
	{
		this._bulletHoles.add(bulletHole);
	}
	
	public void addFruitPiece(FruitPiece fp)
	{
		this._fruitPieces.add(fp);
	}
	
	public List<FruitPiece> getFruitPieces()
	{
		return _fruitPieces;
	}
	
	public void flushPersistentData()
	{
		this._bulletHoles.clear();
		this._fruitPieces.clear();
	}
	
}
