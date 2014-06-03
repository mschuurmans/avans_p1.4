package nl.avans.essperience.models;

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
}
