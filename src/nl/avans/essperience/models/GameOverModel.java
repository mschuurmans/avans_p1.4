package nl.avans.essperience.models;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.Utils;

public class GameOverModel extends GameModel
{
	public static final int ENTERID = -1;
	public static final int LEFTSHIFTID = -2;
	public static final int RIGHTSHIFTID = -3;
	public static final int SPACEBARID = -4;
	
	private int _score;
	private String _name;
	private char _selectedChar;
	private char _nextChartoDisplay;
	
	//keyboard
	private char[] _charArray = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
	private String _shiftString = (char)0x21E7 + "Shift";
	private String _returnString = "Enter" + (char)0x23CE;
	private int _selectedKeyID = 5;
	private boolean _shiftEnabled = true;
	
	public GameOverModel()
	{
		startCursorThread();
		
		this._name = "";
//		Utils.addHighScore(name, score);
	}
	
	public void updateData()
	{
		_score = Main.GAME.getScore();
		_name = Main.GAME.getPlayerName();	
	}
	
	public void submitHighscore()
	{
		Utils.addHighScore(_name, _score);
	}
	
	/**
	 * purges the String name that has been created in the gameOverScreen to the Main.GAME
	 */
	public void setPlayerName()
	{
		Main.GAME.setPlayerName(_name);
	}
	
	public void addCharactertoLocalName(char c)
	{
		this._name += c;
	}
	
	public String getName()
	{
		return this._name;
	}
	
	public char getSelectedChar()
	{
		return this._selectedChar;
	}
	
	public char getNextChartoDisplay()
	{
		return this._nextChartoDisplay;
	}
	
	public char[] getKeyboardCharacters()
	{
		return this._charArray;
	}
	
	public String getShiftString()
	{
		return this._shiftString;
	}
	
	public String getReturnString()
	{
		return this._returnString;
	}
	
	public int getSelectedKey()
	{
		return this._selectedKeyID;
	}
	
	public void setSelectedKey(int id)
	{
		this._selectedKeyID = id;
	}
	
	public void toggleShift()
	{
		this._shiftEnabled = !_shiftEnabled;
	}
	
	public boolean getShiftEnabled()
	{
		return this._shiftEnabled;
	}
	
	public void backSpaceName()
	{
		if( !_name.isEmpty())
		{
			_name = _name.substring(0, _name.length() -1);
		}
	}
	
	public void startCursorThread()
	{
		Thread t = new Thread(new Runnable()
		{
			int sleepTime = 80;

			@Override
			public void run() 
			{
				while(true)
				{

					for( int i = 0; i < 10; i ++)
					{
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						//update the charArray
						if(_shiftEnabled)
							_charArray = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
						else
							_charArray = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
						//set selectedChar to display an underscore ( this is the cursor)
						_nextChartoDisplay = '_';
					}
					
					for( int i = 0; i < 10; i ++)
					{
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						//update charArray
						if(_shiftEnabled)
							_charArray = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
						else
							_charArray = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
						
						//set selectedChar to display
						if(_selectedKeyID >= 0 && _selectedKeyID < 26)
						{
							_selectedChar = _charArray[_selectedKeyID];
							_nextChartoDisplay = _selectedChar;
						}
					}
					
				}
				
			}
			
		});
		t.start();
	}
	
}
