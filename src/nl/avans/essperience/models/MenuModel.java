package nl.avans.essperience.models;

import java.util.List;

import nl.avans.essperience.controllers.LightController;
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
		switchLight();
		_leftFoot = value;
	}
	
	public void setRightFoot(boolean value)
	{
		switchLight();
		_rightFoot = value;
	}
	
	private void switchLight()
	{
		if(_leftFoot || _rightFoot)
			LightController.Instance().writeData((char)101);
		else
			LightController.Instance().writeData((char)103);
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
