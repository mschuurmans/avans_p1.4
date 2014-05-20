package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.GameOverModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.GameOverScreen;

public class GameOverController extends GameController 
{
	private GameOverModel _model;
	private GameOverScreen _view;
	
	public GameOverController(GameOverModel model, GameOverScreen view)
	{
		this._model = model;
		this._view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{ 
			public void keyPressed(GameKeys key)
			{
				
			}
		});
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
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
