package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import nl.avans.essperience.entities.flappy.FlappyPipe;
import nl.avans.essperience.entities.flappy.FlappyPlayer;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.DockLocations;

public class FlappyBirdScreen extends GameScreen
{
	// Images
	private BufferedImage _spritesheet;
	private Image _background;
	
	// pipes
	private FlappyPipe _pipeTop;
	private FlappyPipe _pipeBottom;
	
	//player 
	private FlappyPlayer _player;
	
	private static final long serialVersionUID = -2013215913618586135L;

	public FlappyBirdScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		_spritesheet = (BufferedImage) AssetManager.Instance().getImage("Flappy/flappy.png");
		_background = AssetManager.Instance().getImage("Flappy/background.png");
		init();
	}
	
	public void flap()
	{
		//if position more than 10 it can move up. (0 is the top of the screen)
		if(_player.getY() > 10)
			_player.moveY(-6);
	}
	
	public void init()
	{		
		/**
		 * TODO make dynamic..
		 */
		_player = new FlappyPlayer();
		
		_pipeTop = new FlappyPipe(DockLocations.Top, Main.GAME.getDifficulty() * 2);
		_pipeTop.setX(600);
		_pipeTop.setY(0);
		_pipeTop.setWidth(100);
		_pipeTop.setHeight(400);
		
		_pipeBottom = new FlappyPipe(DockLocations.Bottom,Main.GAME.getDifficulty() * 2);
		_pipeBottom.setX(600);
		_pipeBottom.setY(Main.GAME.getHeight() - 200);
		_pipeBottom.setWidth(100);
		_pipeBottom.setHeight(200);
	}
	
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		_pipeTop.moveLeft();
		_pipeBottom.moveLeft();
		
		if (_pipeTop.collision(_player.getShape()) || _pipeBottom.collision(_player.getShape())) 
		{
			_timer.stop();
			if (_listener != null)
			_listener.sendGamefinishedEvent(false);
		}
		
		// if the bird is above the GAME.getHeight -20(height of birdimage) it can be moved up. else the game should be ended cause the bird hit the ground
		if(_player.getY() < Main.GAME.getHeight() - 20)
			_player.moveY(10);
		else
		{
			_timer.stop();
			_listener.sendGamefinishedEvent(false);
		}
		
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		
		int backgroundWidth = _background.getWidth(null);
		
		for(int i = 0; (i * backgroundWidth) < screenWidth; i++)
		{
			g.drawImage(_background, i * backgroundWidth, 0, backgroundWidth, screenHeight, null);
		}
		
		_pipeTop.draw(g);
		_pipeBottom.draw(g);
		
		_player.draw(g);
		
		super.drawLives(g);
	}

}
