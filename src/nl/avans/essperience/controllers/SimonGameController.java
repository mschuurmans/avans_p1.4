package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ModelToControllerEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.SimonGameScreen;

public class SimonGameController extends GameController
{
	private SimonGameModel _model;
	private SimonGameScreen _view;
	private boolean _debug = false;
	
	
	
	public SimonGameController(SimonGameModel model, SimonGameScreen view)
	{
		this._model = model;
		this._view = view;
		
		_model.addModelToControllerEventListener(new ModelToControllerEventListener()
		
		{
			@Override
			public void gameFinished(boolean succes)
			{
				_view.stopTimer();
				callFinishedListener(succes);
			}
			
			@Override
			public void timesUpEvent()
			{
				_view.stopTimer();
				callFinishedListener(false);
			}
		});
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{

				
				public void keyReleased(GameKeys key) {
					if(_debug){
						System.out.println("SimonGameController: key has been released " + key);
					}
					if (key == GameKeys.KeyU) {
						_model.setCurrentFruit(0);
					}
					if (key == GameKeys.KeyI) {
						_model.setCurrentFruit(1);
					}
					if (key == GameKeys.KeyO) {
						_model.setCurrentFruit(2);
					}
					if (key == GameKeys.KeyP) {
						_model.setCurrentFruit(3);
					}
				}
		});
		_view.addViewToControllerEventListener(new ViewToControllerEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean succes)
			{
				_view.stopTimer();
				callFinishedListener(succes);
			}
		});
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
}
