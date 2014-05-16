package nl.avans.essperience.models;

import nl.avans.essperience.entities.waf.Fardoes;

public class WafModel extends GameModel
{
	private Fardoes _fardoes;
	
	public WafModel()
	{
		_fardoes = new Fardoes(1, 75,100); //TODO change to image w/h
	}
	
	/**
	 * whacks and returns a boolean if it was a hit or not.
	 * @param location location where the whack was.
	 * @return
	 */
	public boolean whack(int location)
	{
		if(location == _fardoes.getLocation())
			return true;
		else
			return false;
	}
	
	public Fardoes getFardoes()
	{
		return _fardoes;
	}
	
	@Override
	public void update()
	{
		// update method
	}
}
