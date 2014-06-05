package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.GameOverModel;
import nl.avans.essperience.utils.AssetManager;
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
				if(key == GameKeys.KeySpacebar)
				{
					submitScore(false);
				}
				
				if(key == GameKeys.KeyU || key == GameKeys.KeyI || key == GameKeys.KeyO || key == GameKeys.KeyP)
				{
					String s = "Unknown Soldier";
					
					switch(key)
					{
					case KeyU: s = "Im a filthy Banana"; break;
					case KeyI: s = "Angry Orange"; break;
					case KeyO: s = "AppleF*g"; break;
					case KeyP: s = "Grow a Pear"; break;
					}
					
					_model.setLocalName(s);
					_model.setShiftEnabled(false);
					_model.setSelectedKey(GameOverModel.ENTERID);
				}
				
				if(key == GameKeys.WiiA)
				{
					// add the selected character to the model.localName
					int selectedKey = _model.getSelectedKey();
					if ( (selectedKey >= -20) && (selectedKey < -10) )
					{
						int sizeList = _model.getHighscoreQuickList().size();
						String name = _model.getHighscoreQuickList().get(sizeList - (selectedKey + 20 + 1));
						_model.setLocalName(name);
						_model.setShiftEnabled(false);
						_model.setSelectedKey(GameOverModel.ENTERID);
					}
					else if(selectedKey >= 0) //if the keyID is 0 or above 0. enter the corresponding character to the name
					{
						int keyID = _model.getSelectedKey();
						_model.addCharactertoLocalName(_model.getKeyboardCharacters()[keyID]);

						if(_model.getName().length() == 1)
							_model.setShiftEnabled(false); // after entering the first character. toggle the shift off.
					}
					else if(selectedKey == GameOverModel.ENTERID) //if the keyID == enterID (-1) and the A is pressed. Submit Score. and continue game
						submitScore(true);
					else if(selectedKey == GameOverModel.LEFTSHIFTID || selectedKey == GameOverModel.RIGHTSHIFTID ) // if the keyID is -2 or -3 shift has been pressed
						_model.toggleShift();
					else if( selectedKey == GameOverModel.SPACEBARID) // if the keyID is -4. the spacebar has been clicked
						_model.addCharactertoLocalName(' ');
						
				}
				else if(key == GameKeys.WiiB)
				{
					_model.toggleShift();
				}
				else if(key == GameKeys.WiiMinus)
				{
					_model.backSpaceName();
				}
				else if (key == GameKeys.WiiPlus)
				{
					_model.addCharactertoLocalName(' ');
				}
				else if (key == GameKeys.WiiHome)
				{
					_model.setSelectedKey(GameOverModel.ENTERID);
				}
				else if (key == GameKeys.WiiUp)
				{
					moveHandUp();
				}
				else if (key == GameKeys.WiiRight)
				{
					moveHandRight();
				}
				else if (key == GameKeys.WiiDown)
				{
					moveHandDown();
				}
				else if (key == GameKeys.WiiLeft)
				{
					moveHandLeft();
				}
				
			}
		});
				
		InputController.Instance().setMotionDetecting(false);
		_view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	/*
	 * this method handles the way the hand moves over the keyboard when the WiiRight button is pressed.
	 * it consits of a huge if/else statement that in its condition checks where the hand is. and in its body moves the hand to the position it should
	 * @author Jack
	 */
	private void moveHandUp()
	{
		int sk = _model.getSelectedKey();
		
		if ( (sk >= -20) && (sk < -10) ) //when the quickselectionbar is selected and button up is pressed. go to the spacebar
		{
			_model.setSelectedKey(GameOverModel.SPACEBARID);
		}
		else if( (sk >= 0) && (sk <= 9) ) //when on the upper row. go to the spacebar
		{
			_model.setSelectedKey((int)(sk/1.5) - 20);
		}
		else if (sk == GameOverModel.SPACEBARID) // when the spacebar is selected. go to the 'z' key
		{
			_model.setSelectedKey(19);
		}
		else if ( (sk >= 10) && (sk <= 18)) //when on the second row. go to keyID - 10. (row up)
		{
			_model.setSelectedKey(sk - 10);
		}
		else if ( (sk >= 19) && (sk <= 25) ) //when on the lower row. go to the middle row
		{
			_model.setSelectedKey(sk - 8);
		}
		else if ( sk == GameOverModel.LEFTSHIFTID) //from left shift to 'a' key
		{
			_model.setSelectedKey(10);
		}
		else if ( sk == GameOverModel.RIGHTSHIFTID) //from right shift to 'm' key
		{
			_model.setSelectedKey(18);
		}
		else if ( sk == GameOverModel.ENTERID)
		{
			_model.setSelectedKey(GameOverModel.RIGHTSHIFTID);
		}
		
	}
	
	/*
	 * this method handles the way the hand moves over the keyboard when the WiiRight button is pressed.
	 * it consits of a huge if/else statement that in its condition checks where the hand is. and in its body moves the hand to the position it should
	 * @author Jack
	 */
	private void moveHandRight()
	{
		int sk = _model.getSelectedKey();
		
		// this switch checks where the hand should move

		if (sk == (AssetManager.Instance().getHighscoreQuickList().size() -1) -20)
		{
			_model.setSelectedKey(-20);
		}
		else if ( (sk >= -20) && (sk < -10))
		{
			_model.setSelectedKey(sk + 1);
		}
		else if( (sk >= 0 ) && (sk != 9) && (sk != 18) && (sk != 25) ) //when the hand is >= 0 and not on 9, 18 or 25. the hand gets +1 so it moves to the char on the right
		{
			_model.setSelectedKey(sk +1);
		}
		else if ( (sk == 9) || (sk == 18) ) //when the selectedKey value == 9 or 18 its either on 'p' or 'l' so it will move to the enter key
		{
			_model.setSelectedKey(GameOverModel.ENTERID);
		}
		else if ( sk == 25 ) //when the hand is on the m key it will move to the RIGHT SHIFT key
		{
			_model.setSelectedKey(GameOverModel.RIGHTSHIFTID);
		}
		else if (sk == GameOverModel.RIGHTSHIFTID) //when on the right shift. go to the left shift
		{
			_model.setSelectedKey(GameOverModel.LEFTSHIFTID);
		}
		else if (sk == GameOverModel.LEFTSHIFTID) //when on the left shift go to the 'z' key
		{
			_model.setSelectedKey(19);
		}
		else if (sk == GameOverModel.ENTERID) //when on the Enter key. switch to the 'q' key
		{
			_model.setSelectedKey(0);
		}
	}
	
	private void moveHandDown()
	{
		int sk = _model.getSelectedKey();
		
		if ( (sk >= -20) && (sk < -10))
		{
			_model.setSelectedKey((int) (sk + 20 + (sk + 20)/1.5) );
		}
		else if( (sk >= 0) && (sk <= 8) ) //when on the upper row add + 10
		{
			_model.setSelectedKey(sk + 10);
		}
		else if (sk == 9)
		{
			_model.setSelectedKey(GameOverModel.ENTERID);
		}
		else if (sk == GameOverModel.SPACEBARID) // when the spacebar is selected. go to the quickSelectionBar
		{
			_model.setSelectedKey(-20);
		}
		else if (sk == 10) //if the 'a' key is selected add 9 instead of 8 for the selector to end up at 'z' instead of 'l'
		{
			_model.setSelectedKey(GameOverModel.LEFTSHIFTID);
		}
		else if (sk == 18) // from the 'l' key to the rightShift
		{
			_model.setSelectedKey(GameOverModel.RIGHTSHIFTID);
		}
		else if ( (sk > 10) && (sk <= 17)) //when on the second row. go to keyID + n. (row down)
		{
			_model.setSelectedKey(sk + 8);
		}
		else if ( (sk >= 19) && (sk <= 25) ) //when on the lower row. go to the spaceBar
		{
			_model.setSelectedKey(GameOverModel.SPACEBARID);
		}
		else if ( sk == GameOverModel.LEFTSHIFTID) //from left shift to spacbar
		{
			_model.setSelectedKey(GameOverModel.SPACEBARID);
		}
		else if ( sk == GameOverModel.RIGHTSHIFTID) //from right shift to spacebar
		{
			_model.setSelectedKey(GameOverModel.SPACEBARID);
		}
		else if ( sk == GameOverModel.ENTERID)
		{
			_model.setSelectedKey(GameOverModel.RIGHTSHIFTID);
		}
	}
	
	private void moveHandLeft()
	{
		int sk = _model.getSelectedKey();

		if ( (sk > -20) && (sk < -10))
		{
			_model.setSelectedKey(sk - 1);
		}
		else if (sk == -20)
		{
			int listSize = AssetManager.Instance().getHighscoreQuickList().size();
			_model.setSelectedKey(sk + (listSize -1) );
		}
		else if( (sk > 0 ) && (sk != 10) && (sk != 19) ) //when the hand is > 0 and not on 10 or 20. the hand gets -1 so it moves to the char on the left
		{
			_model.setSelectedKey(sk -1);
		}
		else if ( (sk == 0) || (sk == 10)) //when the key is on the 'q' or 'a' key. go to the enter
		{
			_model.setSelectedKey(GameOverModel.ENTERID);
		}
		else if ( sk == 19 ) // when on the 'z' key. move to the left shift
		{
			_model.setSelectedKey(GameOverModel.LEFTSHIFTID);
		}
		else if (sk == GameOverModel.RIGHTSHIFTID) //when on the right shift. go to the 'm' key
		{
			_model.setSelectedKey(25);
		}
		else if (sk == GameOverModel.LEFTSHIFTID) //when on the left shift go to the RIGHTSHIFTID key
		{
			_model.setSelectedKey(GameOverModel.RIGHTSHIFTID);
		}
		else if (sk == GameOverModel.ENTERID) //when on the Enter key. switch to the 'p' key
		{
			_model.setSelectedKey(9);
		}
	}
	
	public void start()
	{
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				for( int i = 0; i < 10; i ++)
				{
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
//				submitScore();
			}
			
		});
		t.start();
	}

	//gets called from line: 30 when a name has been entered
	private void submitScore(boolean withScore)
	{
		if(withScore)
		{
			_model.setPlayerName(); //this sets the local player name to be updated to the Main.GAME
			_model.updateData();
			_model.submitHighscore();
		}
		
		_view.stopTimer();
		if(_finishedListener != null)
			_finishedListener.microGameFinishedEvent(true);
	}
}
