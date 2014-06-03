package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ModelToControllerEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.WafModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.WafScreen;

public class WafController extends GameController
{
	private WafModel _model;
	private WafScreen _view;
	private int _triesLeft = 1;
	private boolean _whacked;
	public WafController(WafModel model, WafScreen view)
	{
		_model = model;
		_view = view;
		_whacked = false;
		_model.addModelToControllerEventListener(new ModelToControllerEventListener()
		{
			@Override
			public void gameFinished(boolean succes)
			{
				_view.stopTimer();
				callFinishedListener(succes);
			}
		});
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				switch(key)
				{
					case Key1:
						whack(1);
						break;
					case Key2:
						whack(2);
						break;
					case Key3:
						whack(3);
						break;
					case Key4:
						whack(4);
						break;
					case Key5:
						whack(5);
						break;
					case Key6:
						whack(6);
						break;
					case Key7:
						whack(7);
						break;
					case Key8:
						whack(8);
						break;
					case Key9:
						whack(9);
						break;
					case KeySpacebar:
						callFinishedListener(false);
						break;
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
		if(!_whacked)
		{
			AssetManager.Instance().playSound("Waf/whack.wav");
		}
		_whacked = ! _whacked;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(_model.whack(location))
		{
			AssetManager.Instance().playSound("Waf/ouch.wav");
			_view.stopTimer();
			callFinishedListener(true);
		}
		else
		{
			if(_triesLeft == 0)
			{
				_view.stopTimer();
				callFinishedListener(false);
			}
			else
				_triesLeft--;
		}
	}
}
