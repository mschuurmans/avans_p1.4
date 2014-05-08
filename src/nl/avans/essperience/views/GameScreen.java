package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import nl.avans.essperience.models.GameModel;

public abstract class GameScreen extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 517082358948978120L;
	
	private GameModel _gameModel;
	
	public GameScreen(GameModel model)
	{
		this._gameModel = model;
		this.setFocusable(true);
		new Timer(30, this).start();
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
	public abstract void paintComponent(Graphics g);
	
	public void drawLives(Graphics g)
	{
		
	}
}
