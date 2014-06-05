package nl.avans.essperience.models;

public class ScoreModel extends GameModel
{
	private int _score;
	private int _randomScore;
	
	public int getScore()
	{
		return _score;
	}
	
	public int getRandomScore()
	{
		return _randomScore;
	}
	
	public void update()
	{
		_randomScore = (int) ((Math.random() * 60) - 30)+100;
	}
	
	public void resetScore()
	{
		_score = 0;
	}
	
	public void scoreCount()
	{
		if (_randomScore > 0)
		{
			if (_randomScore < 10)
			{
				_score += _randomScore;
				_randomScore = 0;
			}
			else
			{
				_score += 10;
				_randomScore -= 10;
			}
		}
		else if (_randomScore < 0)
		{
			if (_randomScore > -10)
			{
				_score -= _randomScore;
				_randomScore = 0;
			}
			else
			{
				_score -= 10;
				_randomScore += 10;
			}
		}
	}
}
