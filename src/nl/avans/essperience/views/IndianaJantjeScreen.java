package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.IndianaJantjeModel;
import nl.avans.essperience.utils.AssetManager;

public class IndianaJantjeScreen extends GameScreen
{
	// Images
	private BufferedImage _spriteSheet;
	private BufferedImage _playerSheet;
	private Image _bloodImage;
	private Image _background;
	private int _side;
	private int _drawStoneX;
	private int _drawStoneY;
	private int _drawPlayerX;
	private int _drawPlayerY;
	private int _position;
	private int _sizeX;
	private int _sizeY;
	private int _index;
	private int _difficulty;
	private final double FACTOR = 1;
	private int _startSpeed;
	private int _screenWidth;
	private int _screenHeight;
	private boolean _dead;
	
	private BufferedImage[] rock;
	private BufferedImage[] player;

	private int _games;
	private int _gameAmount;
	private GameModel _model;


	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		_model = model;
		_background = AssetManager.Instance().getImage("IndianaJantje/background.jpg");
		_playerSheet = (BufferedImage) AssetManager.Instance().getImage("IndianaJantje/indianajantje_player_spritesheet.png");
		_spriteSheet = (BufferedImage) AssetManager.Instance().getImage("IndianaJantje/stonesspritesheet.png");
		_bloodImage = AssetManager.Instance().getImage("IndianaJantje/bloodsplash.png");
		_difficulty = ((IndianaJantjeModel) model).getDifficulty();
		_startSpeed = 15;
		_games = 0;
		_gameAmount = ((IndianaJantjeModel)_model).getAmountOfBalls();
		_screenWidth = Main.GAME.getWidth();
		_screenHeight = Main.GAME.getHeight();
		init();

		createImageArrays();
	}
	
	private void createImageArrays()
	{
		rock = new BufferedImage[16];
		player = new BufferedImage[3];
		for (int i = 0; i < 4; i++) 
		{
			for (int j = 0; j < 4; j++) 
			{
				rock[j+(4*i)] = _spriteSheet.getSubimage(j*500, i*500, 500, 500);
			}
		}
		for (int i = 0; i < 3; i++) 
		{
			player[i] = _playerSheet.getSubimage(i*500, 0, 500, 900);
		}
	}

	public void init()
	{		
		_sizeX = 0;
		_sizeY = 0;
		_index = 0;
		_side = chooseSide();
		_dead = false;
		_timer.start();
	}

	@Override
	public void update() 
	{
		_position = ((IndianaJantjeModel)_model).getCurrentPosition();
		_drawStoneX = (_index%4);
		_drawStoneY = (_index/4);
		_drawPlayerX = (_position%3);
		_drawPlayerY = 350;
		_index++;
		_index%=16;

		_sizeY += _startSpeed + (FACTOR * _difficulty-1);
		_sizeX += _startSpeed + (FACTOR * _difficulty-1);
		
		if (_dead) 
		{
			AssetManager.Instance().playSound("IndianaJantje/bloodsplash.wav");
			//System.out.println("HAHA JE BENT DOOD!");
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			_timer.stop();
			_listener.sendGamefinishedEvent(false);	
		}
		
		if (_sizeY >= _screenHeight/2 && !_dead) 
		{
			_timer.stop();
			AssetManager.Instance().playSound("IndianaJantje/stonebreak.wav");
			_listener.sendGamefinishedEvent(true);
		}
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		//System.out.println("drawing");
		g.drawImage(_background, 0, 0, _screenWidth, _screenHeight, null);
		
		g.drawImage(rock[_drawStoneX+(_drawStoneY*4)], (_screenWidth/2*_side) + (_screenHeight/4)-(_sizeX/2), _screenHeight/2, _sizeX, _sizeY, null);
		g.drawImage(player[_drawPlayerX], (_screenWidth/3) * _position, _screenHeight-(_drawPlayerY), _drawPlayerY, _drawPlayerY*2, null);
	
		if (_dead)
		{
			g.drawImage(_bloodImage, 0, 0, _screenWidth, _screenHeight, null);
			//System.out.println("dood!");
			_timer.start();
		}
	} 

	private int chooseSide() 
	{
		int rand = (int)(Math.random() * 2);
		System.out.println("rand is: " + rand);
		return rand;
	}

	public int getSide() 
	{
		return this._side;
	}

	public void next() 
	{
		//System.out.println("next game called in view");
		if (_games < _gameAmount) 
		{
			_games++;
			init();
		} 
		else 
		{
			_timer.stop();
			_listener.sendGamefinishedEvent(false);
		}
	}
	
	public void fail()
	{
		_dead = true;
		//System.out.println("fail");
	}
	
	public boolean getDead() 
	{
		//System.out.println("checking dead");
		return _dead;
	}
}
