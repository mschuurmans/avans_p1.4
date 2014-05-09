package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.IndianaJantjeModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.IndianaJantjeScreen;

public class IndianaJantjeController extends GameController
{
	private IndianaJantjeModel _model;
	private IndianaJantjeScreen _view;
	private boolean _debug = true;
	
	public IndianaJantjeController(IndianaJantjeScreen view, IndianaJantjeModel model)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				if(_debug)
					System.out.println("IndianaJantjeController : key has been pressed" + key);
			}
			public void keyReleased(GameKeys key) {
				if(_debug)
					System.out.println("IndianaJantjeController : key has been released" + key);
				switch (((IndianaJantjeScreen)_view).getSide()) {
				case 0:
					if (key == GameKeys.KeyA){
						callFinishedListener(true);
					} else {
						callFinishedListener(false);
					}
					break;
				case 1:
					if (key == GameKeys.KeyA || key == GameKeys.KeyD){
						callFinishedListener(true);
					} else {
						callFinishedListener(false);
					}
					break;
				case 2:
					if (key == GameKeys.KeyD){
						callFinishedListener(true);
					} else {
						callFinishedListener(false);
					}
					break;
				}
			}
		});
		
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
}
