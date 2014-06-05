package nl.avans.essperience.models;

import java.util.List;

import nl.avans.essperience.entities.Score;
import nl.avans.essperience.utils.Utils;

public class MenuModel extends GameModel
{
	private boolean _leftFoot = false;
	private boolean _rightFoot = false;
	
	public MenuModel()
	{
		
	}
	
	public void setLeftFoot(boolean value)
	{
		_leftFoot = value;
	}
	
	public void setRightFoot(boolean value)
	{
		_rightFoot = value;
	}
	
	public boolean getLeftFoot()
	{
		return _leftFoot;
	}
	
	public boolean getRightFoot()
	{
		return _rightFoot;
	}
	
	public List<Score> getTopScores(int amount)
	{
		return Utils.getTopScores(amount);
	}
}
