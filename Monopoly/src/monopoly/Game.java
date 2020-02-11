package monopoly;

import javax.swing.JOptionPane;

public class Game {
	private static Game Singleton_Game;
	 
	private Game(){}
	public static Game createGame()
	{
		if (Singleton_Game==null)
		{
			Singleton_Game = new Game();
		}
		return Singleton_Game;
	}
	
	public void startGame(){		
		Object[] possibilities = {1, 2, 3, 4, 5, 6};
		Object ret = JOptionPane.showInputDialog(null,"Quantos Jogadores?","Jogadores",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
		int i=0;
		if (ret != null)
			 i = (int)ret;
		
		if (i>0)
		{
			tabuleiro a = new tabuleiro(i);
			a.setVisible(true);
			MoveMeMouseHandler.xOffset = (int) a.top.getLocationOnScreen().getX();
			MoveMeMouseHandler.yOffset = (int) a.top.getLocationOnScreen().getY();
		}
	}
}
