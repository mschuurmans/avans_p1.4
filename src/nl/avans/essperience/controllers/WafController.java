package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.WafModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.WafScreen;

public class WafController extends GameController
{
	private WafModel _model;
	private WafScreen _view;
	
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
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
}
