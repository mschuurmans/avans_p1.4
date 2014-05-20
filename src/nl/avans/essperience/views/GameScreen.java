package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.RedButtonModel;

public abstract class GameScreen extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 517082358948978120L;
	
	protected GameModel _gameModel;
	protected ViewToControllerEventListener _listener = null;
	protected Timer _timer;
	
	public GameScreen(GameModel model)
	{
		this._gameModel = model;
		this.setFocusable(true);
		_timer = new Timer(60, this);
		_timer.start(); 
	}
	
	public void addTimeBar(Graphics g) 
	{

		double timeBarSize = Main.GAME.getWidth() * _gameModel.getTimeRemaining();
		g.fillRect(0, 0, (int) timeBarSize, 20);
	}
	
	public void addViewToControllerEventListener(ViewToControllerEventListener event)
	{
		_listener = event;
	}
	
	public void setKeyboardListener(KeyListener listener)
	{
		this.addKeyListener(listener);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		update();
		
		repaint();
	}
	
	public abstract void update();
	public void stopTimer()
	{
		_timer.stop();
	}
}
