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
	private boolean _debug = true;
	private GameKeys _currentKey;

	public IndianaJantjeController(IndianaJantjeScreen view, IndianaJantjeModel model)
	{
		_model = model;
		_view = view;

		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				if(_debug)
					System.out.println("IndianaJantjeController : key has been pressed " + key);
				if (_currentKey == key) {
					_currentKey = null;
					_model.setCurrentPosition(0);
					System.out.println("Changing position to: CENTER" );
				}
			}

			public void keyReleased(GameKeys key) {
				if(_debug)
					System.out.println("IndianaJantjeController : key has been released " + key);
				_currentKey = key;
				if (_currentKey == GameKeys.KeyA) {
					_model.setCurrentPosition(2);
					System.out.println("Changing position to: RIGHT" );
				} else if (_currentKey == GameKeys.KeyD) {
					_model.setCurrentPosition(1);
					System.out.println("Changing position to: LEFT" );
				}
			}
		});

		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
		
		_view.addViewToControllerEventListener(new ViewToControllerEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean moreGames)
			{
				System.out.println("received event in controller. currentKey = " + _currentKey);
				if (moreGames) {
					switch (((IndianaJantjeScreen)_view).getSide()) {
					case 0:
						if (_currentKey == GameKeys.KeyA){
							((IndianaJantjeScreen)_view).next();
						} else {
							callFinishedListener(false);
						}
						break;
					case 1:
						if (_currentKey == GameKeys.KeyD){
							((IndianaJantjeScreen)_view).next();
						} else {
							callFinishedListener(false);
						}
						break;
					}
				} else {
					callFinishedListener(true);
				}
			}
		});
	}
}
