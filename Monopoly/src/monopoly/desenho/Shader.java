package monopoly.desenho;

import java.awt.Point;

import javax.swing.JPanel;

public class Shader extends Draw{

	public Point UpperBound;
	public Point LowerBound;
	
	public Shader(Point a, Point b, String s) {
		super(s);
		this.UpperBound = a;
		this.LowerBound = b;
		
	}
}
