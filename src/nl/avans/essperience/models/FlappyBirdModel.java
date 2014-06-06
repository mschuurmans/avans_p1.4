package nl.avans.essperience.models;

import java.awt.Image;

import nl.avans.essperience.entities.flappy.FlappyPipe;
import nl.avans.essperience.entities.flappy.FlappyPlayer;
import nl.avans.essperience.events.CollisionDetectedEventListener;
import nl.avans.essperience.events.FlappyBirdFinishedListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.DockLocations;

public class FlappyBirdModel extends GameModel
{
	private Image _background;
	private boolean _debug = false;
	
	// pipes
	private FlappyPipe _pipeTop;
	private FlappyPipe _pipeBottom;
	
	//player 
	private FlappyPlayer _player;
	private CollisionDetectedEventListener _collision = null;
	private FlappyBirdFinishedListener _flappyFinished = null;
	public  int _maxDiff = 12;
	//Defines FlapSensitivity at start
	public 	int _flapSensitivity = -10;
	public int _minimumFlapSensitivity = -4;
	public FlappyBirdModel()
	{
		
		_background = AssetManager.Instance().getImage("Flappy/background.png");
		init();
	}
	
	public Image getBackground()
	{
		return _background;
	}
	
	public FlappyPipe getPipeTop()
	{
		return _pipeTop;
	}
	
	public FlappyPipe getPipeBottom()
	{
		return _pipeBottom;
	}
	
	public FlappyPlayer getPlayer()
	{
		return _player;
	}
	
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		_pipeTop.moveLeft();
		_pipeBottom.moveLeft();
		
		if (_pipeTop.collision(_player.getShape()) || _pipeBottom.collision(_player.getShape())) 
		{
			if(_collision != null)
				_collision.collisionDetected();
			
			
		}
		
		// if the bird is above the GAME.getHeight -20(height of birdimage) it can be moved up. else the game should be ended cause the bird hit the ground
		if(_player.getY() < Main.GAME.getHeight() - 20)
			_player.moveY(10);
		else
		{
			if(_collision != null)
				_collision.collisionDetected();
		}
		
		if(_pipeTop.isPast(_player))
		{
			if(_flappyFinished != null)
				_flappyFinished.flappyFinishedListener();
		}
		
	}
	public void addCollisionListener(CollisionDetectedEventListener e)
	{
		_collision = e;
	}
	public void addFlappyFinishedListener(FlappyBirdFinishedListener e)
	{
		_flappyFinished = e;
	}
	public void init()
	{		
		int gap = 200;
		
		int pipeTopHeight = (int)(Math.random() * Main.GAME.getHeight() / 1.3f) + 1;
		
		_player = new FlappyPlayer();
		int pipeX = (Main.GAME.getWidth()/10)*9;
		int diff;
		if (Main.GAME.getDifficulty() > _maxDiff)
		{
			diff = _maxDiff;
		}
		else
		{
			diff = Main.GAME.getDifficulty();
		}
		
		if (Main.GAME.getDifficulty() > _maxDiff)
		{
			int decreaseDiffBy = Main.GAME.getDifficulty() - _maxDiff;
			if (_flapSensitivity + decreaseDiffBy < _minimumFlapSensitivity)
			{
				_flapSensitivity += decreaseDiffBy;
			}
			else
			{
				_flapSensitivity = _minimumFlapSensitivity;
			}
			
			if (_debug)
			{
				System.out.println("flapsensitivity decreased." + _flapSensitivity);
			}
		}
		
		_pipeTop = new FlappyPipe(DockLocations.Top, diff * 2 + 2);
		_pipeTop.setX(pipeX);
		_pipeTop.setY(0);
		_pipeTop.setWidth(100);
		_pipeTop.setHeight(pipeTopHeight);
		
		_pipeBottom = new FlappyPipe(DockLocations.Bottom, diff * 2 + 2);
		_pipeBottom.setX(pipeX);
		_pipeBottom.setY(pipeTopHeight + gap);
		_pipeBottom.setWidth(100);
		_pipeBottom.setHeight(Main.GAME.getHeight() - (pipeTopHeight + gap));
	}
	
	public void flap()
	{
		//if position more than 10 it can move up. (0 is the top of the screen)
		if(_player.getY() > 10)
		{
			AssetManager.Instance().playSound("Flappy/flap.wav");
			_player.moveY(_flapSensitivity);
		}
	}
}
