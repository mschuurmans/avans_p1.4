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

	private int _games;
	private int _gameAmount;
	private GameModel _model;
	
	private boolean _end;

	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		_model = model;
		_background = AssetManager.Instance().getImage("IndianaJantje/background.jpg");
		_playerSheet = (BufferedImage) AssetManager.Instance().getImage("IndianaJantje/indianajantje_player_spritesheet.png");
		_spriteSheet = (BufferedImage) AssetManager.Instance().getImage("IndianaJantje/stonesspritesheet.png");
		_difficulty = ((IndianaJantjeModel) model).getDifficulty();
		_startSpeed = 15;
		_games = 0;
		_gameAmount = ((IndianaJantjeModel)_model).getAmountOfBalls();;
		_end = false;
		init();
		_screenWidth = Main.GAME.getWidth();
		_screenHeight = Main.GAME.getHeight();
	}

	public void init()
	{		
		_sizeX = 0;
		_sizeY = 0;
		_index = 0;
		_side = chooseSide();
		_timer.start();
	}

	@Override
	public void update() 
	{
		_position = ((IndianaJantjeModel)_model).getCurrentPosition();
		_drawStoneX = (_index%4) * 500;
		_drawStoneY = (_index/4) * 500;
		_drawPlayerX = (_position%3) * 500;
		_drawPlayerY = 0;
		_index++;
		_index%=16;

		_sizeY += _startSpeed + (FACTOR * _difficulty-1);
		_sizeX += _startSpeed + (FACTOR * _difficulty-1);
		
		if (_sizeY >= _screenHeight/2) {
			_timer.stop();
			_listener.sendGamefinishedEvent(true);
		}
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		g.drawImage(_background, 0, 0, _screenWidth, _screenHeight, null);

		BufferedImage subImg = _spriteSheet.getSubimage(_drawStoneX, _drawStoneY, 500, 500);
		BufferedImage subImg2 = _playerSheet.getSubimage(_drawPlayerX, _drawPlayerY, 500, 900);
		g.drawImage(subImg, (_screenWidth/2*_side) + (_screenHeight/4)-(_sizeX/2), _screenHeight/2, _sizeX, _sizeY, null);
		g.drawImage(subImg2, (_screenWidth/3) * _position, _screenHeight-250, 250, 450, null);
		super.drawLives(g);
	} 

	private int chooseSide() {
		int rand = (int)(Math.random() * 2);
		System.out.println("rand is: " + rand);
		return rand;
	}

	public int getSide() {
		return this._side;
	}

	public void next() {
		System.out.println("next game called in view");
		if (_games < _gameAmount) {
			_games++;
			init();
		} else {
			_timer.stop();
			_listener.sendGamefinishedEvent(false);
		}
	}

	public boolean getEnd() {
		return _end;
	}
}
