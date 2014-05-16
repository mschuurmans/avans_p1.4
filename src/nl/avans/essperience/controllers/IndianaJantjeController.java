package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.IndianaJantjeModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.IndianaJantjeScreen;

public class IndianaJantjeController extends GameController
{
	private IndianaJantjeModel _model;
	private IndianaJantjeScreen _view;
	private boolean _debug = false;
	private boolean keyA = true;
	private boolean keyD = true;

	public IndianaJantjeController(IndianaJantjeScreen view, IndianaJantjeModel model)
	{
		_model = model;
		_view = view;

		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				if(_debug) {
					System.out.println("IndianaJantjeController : key has been pressed " + key);
				}
				if (key == GameKeys.KeyA) {
					keyA = true;
				}
				if (key == GameKeys.KeyD) {
					keyD = true;
				}
				if (key == GameKeys.None) {
					keyD = false;
					keyA = false;
				}
				setPos();
			}

			public void keyReleased(GameKeys key) {
				if(_debug){
					//System.out.println("IndianaJantjeController : key has been released " + key);
				}
				if (key == GameKeys.KeyA) {
					keyA = false;
				}
				if (key == GameKeys.KeyD) {
					keyD = false;
				}
				setPos();
			}
		});

		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
		
		_view.addViewToControllerEventListener(new ViewToControllerEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean moreGames)
			{
				//System.out.println("received event in controller. currentKey = " + _currentKey);
				if (moreGames) {
					switch (((IndianaJantjeScreen)_view).getSide()) {
					case 0:
						if (!keyA && keyD){
							((IndianaJantjeScreen)_view).next();
						} else {
							if (((IndianaJantjeScreen)_view).getDead()) {
								callFinishedListener(false);
							} else {
								((IndianaJantjeScreen)_view).fail();
							}
						}
						break;
					case 1:
						if (keyA && !keyD){
							((IndianaJantjeScreen)_view).next();
						} else {
							if (((IndianaJantjeScreen)_view).getDead()) {
								callFinishedListener(false);
							} else {
								((IndianaJantjeScreen)_view).fail();
							}
						}
						break;
					}
				} else {
					callFinishedListener(true);
				}
			}
		});
		
		
	}
	
	private void setPos() {
		if (keyA && keyD) {
			_model.setCurrentPosition(1);
		} else if (keyA) {
			_model.setCurrentPosition(0);
		} else if (keyD) {
			_model.setCurrentPosition(2);
		} else {
			_model.setCurrentPosition(1);	
		}
	}
}
