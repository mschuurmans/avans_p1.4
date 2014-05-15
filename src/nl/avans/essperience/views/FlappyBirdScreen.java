package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.events.CollisionDetectedEventListener;
import nl.avans.essperience.events.FlappyBirdFinishedListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FlappyBirdModel;

public class FlappyBirdScreen extends GameScreen
{
	// Images
	
	
	private static final long serialVersionUID = -2013215913618586135L;
	
	public FlappyBirdScreen(FlappyBirdModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		
		((FlappyBirdModel)_gameModel).addCollisionListener(new CollisionDetectedEventListener()
		{
			
			@Override
			public void collisionDetected() 
			{
				_timer.stop();
				if (_listener != null)
					_listener.sendGamefinishedEvent(false);		
			}
		});
		
		((FlappyBirdModel)_gameModel).addFlappyFinishedListener(new FlappyBirdFinishedListener()
		{	
			@Override
			public void flappyFinishedListener() 
			{
				_timer.stop();
				if(_listener != null)
					_listener.sendGamefinishedEvent(true);		
			}
		});
	}
	
	
	
	@Override
	public void update()
	{
		_gameModel.update();
	}
	

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		
		FlappyBirdModel model = (FlappyBirdModel)_gameModel;
		
		int backgroundWidth = model.getBackground().getWidth(null);
		
		for(int i = 0; (i * backgroundWidth) < screenWidth; i++)
		{
			g.drawImage(model.getBackground(), i * backgroundWidth, 0, backgroundWidth, screenHeight, null);
		}
		
		model.getPipeTop().draw(g);
		model.getPipeBottom().draw(g);
		
		model.getPlayer().draw(g);
	}

}
