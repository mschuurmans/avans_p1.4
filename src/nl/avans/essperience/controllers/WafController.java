package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.WafModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.WafScreen;

public class WafController extends GameController
{
	private WafModel _model;
	private WafScreen _view;
	private int _triesLeft = 1;
	
	public WafController(WafModel model, WafScreen view)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				switch(key)
				{
					default:
						break;
				}
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
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	public void whack(int location)
	{
		if(_model.whack(location))
			callFinishedListener(true);
		else
		{
			if(_triesLeft == 0)
				callFinishedListener(false);
			else
				_triesLeft--;
		}
	}
}
