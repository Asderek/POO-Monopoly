package monopoly.posicoes;

import java.awt.Point;

import monopoly.Peca;

public abstract class Special extends Casa {

	public Special(Point up, Point low, String s) {
		super(up, low, s);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void NewAtivaCasa (Peca jogador );
	
	@Override
	public void AtivaCasa(Peca jogador) {
		NewAtivaCasa(jogador);
	}
	
}
