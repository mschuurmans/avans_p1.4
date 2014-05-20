package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.SimonGameScreen;

public class SimonGameController extends GameController
{
	private SimonGameModel _model;
	private SimonGameScreen _view;
	private boolean _debug = false;
	private boolean keyU = true;
	private boolean keyI = true;
	private boolean keyO = true;
	private boolean keyP = true;
	
	
	public SimonGameController(SimonGameModel model, SimonGameScreen view)
	{
		this._model = model;
		this._view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
				public void keyPressed(GameKeys key)
				{
					if(_debug) {
						System.out.println("IndianaJantjeController : key has been pressed " + key);
					}
					if (key == GameKeys.KeyU) {
						keyU = true;
					}
					if (key == GameKeys.KeyI) {
						keyI = true;
					}
					if (key == GameKeys.KeyO) {
						keyO = true;
					}
					if (key == GameKeys.KeyP) {
						keyP = true;
					}
					if (key == GameKeys.None) {
						keyU = false;
						keyI = false;
						keyO = false;
						keyP = false;
					}
					setPos();
				}
				
				public void keyReleased(GameKeys key) {
					if(_debug){
						//System.out.println("IndianaJantjeController : key has been released " + key);
					}
					if (key == GameKeys.KeyU) {
						keyU = false;
					}
					if (key == GameKeys.KeyI) {
						keyI = false;
					}
					if (key == GameKeys.KeyO) {
						keyO = false;
					}
					if (key == GameKeys.KeyP) {
						keyP = false;
					}
					setPos();
				}
		});
		_view.addViewToControllerEventListener(new ViewToControllerEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean succes)
			{
				callFinishedListener(succes);
			}
		});
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	
	private void setPos() {
		if (keyU) {
			_model.setCurrentFruit(0);
		} else if (keyI) {
			_model.setCurrentFruit(1);
		} else if (keyO) {
			_model.setCurrentFruit(2);
		} else if (keyP) {
			_model.setCurrentFruit(3);
		} else {
			_model.setCurrentFruit(-1);
		}
	}
	
}
