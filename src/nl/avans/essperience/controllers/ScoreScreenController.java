package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.ScoreModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.ScoreScreen;

public class ScoreScreenController extends GameController
{
	private ScoreModel _model;
	private ScoreScreen _view;
	
	public ScoreScreenController(ScoreModel model, ScoreScreen view)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				
			}
		});
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
		_view.setLivesLeft(Main.GAME.getLivesLeft());
		_view.setLevel(Main.GAME.getLevel());
		
	}
	
	public void start()
	{
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				_view.stopTimer();
				if(_finishedListener != null)
					_finishedListener.microGameFinishedEvent(true);
			}
			
		});
		t.start();
		
	}
	
}
