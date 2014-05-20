package nl.avans.essperience.controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.avans.essperience.events.MicroGameFinishedEventListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FlappyBirdModel;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.GameOverModel;
import nl.avans.essperience.models.IndianaJantjeModel;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.models.RedButtonModel;
import nl.avans.essperience.models.ScoreModel;
import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.models.WafModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.views.FlappyBirdScreen;
import nl.avans.essperience.views.GameOverScreen;
import nl.avans.essperience.views.GameScreen;
import nl.avans.essperience.views.IndianaJantjeScreen;
import nl.avans.essperience.views.MenuScreen;
import nl.avans.essperience.views.RedButtonScreen;
import nl.avans.essperience.views.ScoreScreen;
import nl.avans.essperience.views.SimonGameScreen;
import nl.avans.essperience.views.WafScreen;

public class GameHandler extends JFrame
{
	private static final long serialVersionUID = -4608768969398477748L;

	private int _difficulty = 1;
	private final int _NUMBEROFGAMES = 1;

	private int _lives = GameHandler.MAX_LIVES;

	public static final int MAX_LIVES = 3;
	private GameScreen _gameScreen;
	private GameController _gameController;
	private GameModel _gameModel;

	public GameHandler()
	{
		super("Essperience");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init(true);

		setContentPane(_gameScreen);

		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setUndecorated(true);  
		setSize(1280, 800);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public int getLivesLeft()
	{
		return _lives;
	}
	public int getDifficulty()
	{
		return _difficulty;
	}
	public int getLevel()
	{
		return _difficulty;
	}
	public void init(boolean firstRun)
	{
		AssetManager.Instance();
		this._gameScreen = new MenuScreen();
		this._gameModel = new MenuModel();
		this._gameController = new MenuController((MenuScreen)this._gameScreen, (MenuModel)_gameModel);
		this._gameController.addMicroGameFinishedEventListener(new MicroGameFinishedEventListener()
		{
			/**
			 * this is the finshed listener for the menu screen. All screens except the menuscreen will do other stuff in this event. (will be added in start method).
			 */
			@Override
			public void microGameFinishedEvent(boolean succeed) 
			{
				start(); // calls the start method to start the series of minigames. 
			}
		});
		
		if(!firstRun)
			changeScreen();
		
	}

	public void changeScreen()
	{
		System.out.println(_gameScreen.getClass());
		System.out.println(_gameController.getClass());
		System.out.println(_gameModel.getClass());
		_gameScreen.addKeyListener(InputController.Instance().getKeyboardListener());
		Main.GAME.setContentPane(_gameScreen); // updating the game screen.
		Main.GAME.validate();
		Main.GAME.repaint();
		_gameScreen.requestFocus();
		System.out.println("CHANGING SCREEN");
	}
	
	public void start()
	{
		nextGame(true); // for now.
	}

	public void stop()
	{
		reset();
	}

	/**
	 * resets the game back to the menuscreen
	 */
	public void reset()
	{
		_lives = MAX_LIVES;
		_difficulty = 1;
		init(false);
	}

	public void gameOver()
	{
		this._gameModel = new GameOverModel();
		this._gameScreen = new GameOverScreen((GameOverModel) _gameModel);
		this._gameController = new GameOverController((GameOverModel) _gameModel, (GameOverScreen) _gameScreen);
		changeScreen();

		((GameOverController)_gameController).start();
	}

	public void nextGame(boolean succeed)
	{
		if(_gameController instanceof GameOverController)
		{
			reset();
			return;
		}
		//System.out.println("GOING TO CHANGE THE SCREEN");
		System.out.println(succeed);
		if(!succeed)
		{
			if(_lives == 1)
			{
				gameOver();
				return;
			}	
			else
				_lives--;
		}
		setContentPane(new JPanel(null));
		// do logic for next game screen here.
		if(!(_gameController instanceof ScoreScreenController))
		{
			this._gameModel = new ScoreModel();
			this._gameScreen = new ScoreScreen((ScoreModel)_gameModel);
			this._gameController = new ScoreScreenController((ScoreModel)_gameModel, (ScoreScreen)_gameScreen);
		}
		else
		{
			int rand = (int) (Math.random() * _NUMBEROFGAMES) +3 ;
			switch (rand) 
			{
				case 1: 
					this._gameModel = new IndianaJantjeModel();
					this._gameScreen = new IndianaJantjeScreen((IndianaJantjeModel) _gameModel);
					this._gameController = new IndianaJantjeController((IndianaJantjeScreen)_gameScreen, (IndianaJantjeModel)_gameModel);
					break;
				case 2:
					this._gameModel = new FlappyBirdModel();
					this._gameScreen = new FlappyBirdScreen((FlappyBirdModel)this._gameModel);
					this._gameController = new FlappyBirdController((FlappyBirdModel)_gameModel, (FlappyBirdScreen)_gameScreen);
					break;
				case 3:
					this._gameModel = new WafModel();
					this._gameScreen = new WafScreen((WafModel)_gameModel);
					this._gameController = new WafController((WafModel)_gameModel, (WafScreen)_gameScreen);
					break;
				case 4:
					this._gameModel = new RedButtonModel();
					this._gameScreen = new RedButtonScreen((RedButtonModel) _gameModel);
					this._gameController = new RedButtonController((RedButtonModel)_gameModel, (RedButtonScreen)_gameScreen);
					break;
				case 5:
					this._gameModel = new SimonGameModel();
					this._gameScreen = new SimonGameScreen((SimonGameModel) _gameModel);
					this._gameController = new SimonGameController((SimonGameModel) _gameModel, (SimonGameScreen)_gameScreen);
					break;
				case 6:
					this._gameModel = new GameOverModel();
					this._gameScreen = new GameOverScreen((GameOverModel)_gameModel);
					this._gameController = new GameOverController((GameOverModel)_gameModel, (GameOverScreen)_gameScreen);
					break;
				default:
					reset();
					break;
			}
			_difficulty++;
		}
		
		_gameController.addMicroGameFinishedEventListener(new MicroGameFinishedEventListener() {

			@Override
			public void microGameFinishedEvent(boolean succeed) 
			{
				nextGame(succeed);
			}
		});

		changeScreen();
		
		if(_gameController instanceof ScoreScreenController)
			((ScoreScreenController)_gameController).start();
	}

}
