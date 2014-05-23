package nl.avans.essperience.controllers;

import java.util.ArrayList;

import nl.avans.essperience.entities.simon.FruitPiece;
import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.FopsScreen;
import wiiusej.wiiusejevents.physicalevents.IREvent;

public class FopsController extends GameController
{
	private FopsModel _model;
	private FopsScreen _view;
	private double _cursorX;
	private double _cursorY;
	private int _XOFFSETALLOWED = 10;
	private int _YOFFSETALLOWED = 10;
	
	public FopsController(FopsModel model, FopsScreen view)
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			@Override
			public void keyPressed(GameKeys key)
			{
				if (key == GameKeys.WiiB)
				{
					if (_model.getBullets() > 0)
					{
						_model.bulletFired();
						AssetManager.Instance().playSound("Fops/gun shot.wav");
						checkHits();
					}
					else
					{
						AssetManager.Instance().playSound("Fops/gun empty.wav");	
					}
				}
			}
			
			@Override
			public void wiimoteIREvent(IREvent e)
			{
				_cursorX = e.getX();
				_cursorY = e.getY();
				//ir event of the wiimote
			}
		});
		
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	private void checkHits() {
		ArrayList<FruitPiece> fruits = _model.getFruits();
		for (int i = 0; i < fruits.size(); i++)
		{
			float fruitX = fruits.get(i).getPosition().getX();
			float fruitY = fruits.get(i).getPosition().getY();
			if (	_cursorX > fruitX - _XOFFSETALLOWED &&
					_cursorX < fruitX + _YOFFSETALLOWED &&
					_cursorY > fruitY - _XOFFSETALLOWED &&
					_cursorY < fruitY + _YOFFSETALLOWED)
			{
				_model.fruitHit(i);
			}
		}
	}
}
