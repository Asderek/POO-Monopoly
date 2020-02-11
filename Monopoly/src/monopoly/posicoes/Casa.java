package monopoly.posicoes;

import java.awt.Point;

import monopoly.Peca;
import monopoly.desenho.Draw;

public class Casa extends Draw{
	public Point UpperBound;
	public Point LowerBound;

	
	public Casa (Point up, Point low, String s)
	{
		super(s);
		this.UpperBound = up;
		this.LowerBound = low;
	}
	
	public void AtivaCasa(Peca jogador) {
	}

	public boolean pertenceCasa(Point p)
	{
		if (p.x < UpperBound.x )
			return false;
		else if (p.x > LowerBound.x)
			return false;
		else if (p.y > LowerBound.y)
			return false;
		else if (p.y < UpperBound.y)
			return false;
		return true;
	}

	
	
}
