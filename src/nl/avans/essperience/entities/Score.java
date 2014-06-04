package nl.avans.essperience.entities;

import com.google.gson.annotations.SerializedName;

public class Score 
{
	@SerializedName("name")
	private String _name;
	
	@SerializedName("score")
	private int _score;
	
	public void setName(String value)
	{
		_name = value;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public void setScore(int value)
	{
		_score = value;
	}
	
	public int getScore()
	{
		return _score;
	}
}
