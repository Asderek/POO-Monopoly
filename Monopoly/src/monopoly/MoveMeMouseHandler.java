package monopoly;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;

import monopoly.posicoes.Casa;

public class MoveMeMouseHandler extends MouseAdapter {

	public static int xOffset;
	public static int yOffset;
	private Peca draggy;
	MovementChecker check;
	Casa[] casas;

	public void mouseReleased(MouseEvent me) {
		if (getDraggy() != null) {
			int casadest;
			int resultadoCheck;

			casadest = getDraggy().getCasa() + getDraggy().getResult(); 

			if(casadest>31)
				casadest-=32;
			
			//verifica partida
			if (getDraggy().getCasa()<8 && casadest>=8)
			{
				getDraggy().alterarFundos(200);
			}
			else if (getDraggy().getCasa()>27 && casadest>=8)
			{
				getDraggy().alterarFundos(200);
			}

			if (getDraggy().getResult()==0)
			{
				boolean set=false;
				if (getDraggy().getLocation().x<check.returnUpper(getDraggy().casa).x)
				{set=true;}
				if (getDraggy().getLocation().x>check.returnLower(getDraggy().casa).x)
				{set=true;}
				if (getDraggy().getLocation().y<check.returnUpper(getDraggy().casa).y)
				{set=true;}
				if (getDraggy().getLocation().y>check.returnLower(getDraggy().casa).y)
				{set=true;}
				if (set==true)
				{
					Random a = new Random();
					int randX;
					int randY;
					randX = a.nextInt(check.returnLower(getDraggy().casa).x - check.returnUpper(getDraggy().casa).x);
					randY = a.nextInt(check.returnLower(getDraggy().casa).y - check.returnUpper(getDraggy().casa).y);
					getDraggy().setPosition(new Point(check.returnUpper(getDraggy().casa).x + randX,check.returnUpper(getDraggy().casa).y + randY));
					getDraggy().setLocation(new Point(check.returnUpper(getDraggy().casa).x + randX,check.returnUpper(getDraggy().casa).y + randY));
				}
			}
			else
			{
				resultadoCheck = check.CheckMovement(getDraggy(), casadest);
				if (resultadoCheck != 9)
				{
					
					if (resultadoCheck != 1 && resultadoCheck != 0 && resultadoCheck != -1)
						System.exit(1);
					else
					{
						if (casadest==31 && resultadoCheck==1)
							casadest=-1;
						casadest += resultadoCheck;
						getDraggy().setCasa(casadest);
						getDraggy().setResult(0);
						casas[casadest].AtivaCasa(getDraggy());
						
					}
				}
				else
				{
					getDraggy().setPosition(new Point ((int)(check.returnUpper(getDraggy().casa).x+check.returnLower(getDraggy().casa).x)/2,(int)(check.returnUpper(getDraggy().casa).y+check.returnLower(getDraggy().casa).y)/2));
					getDraggy().setLocation(new Point ((int)(check.returnUpper(getDraggy().casa).x+check.returnLower(getDraggy().casa).x)/2,(int)(check.returnUpper(getDraggy().casa).y+check.returnLower(getDraggy().casa).y)/2));
				}


			}
		}


		setDraggy(null); 
	}


	/**
	 * Através do evento, pega o componente clicado
	 * Se o componente for uma peca, seta o atributo draggy 
	 */
	public void mousePressed(MouseEvent me) {
		JPanel comp = (JPanel) me.getComponent();
		Component child = comp.findComponentAt(me.getPoint());
		if (child instanceof Peca) {
			setDraggy((Peca) child);
		}
	}

	public void mouseDragged(MouseEvent me) {
		if (getDraggy() != null) {
			getDraggy().setLocation( me.getXOnScreen()-xOffset , me.getYOnScreen() -yOffset);
			getDraggy().setPosition(new Point(me.getXOnScreen()-xOffset,me.getYOnScreen()-yOffset));
		}
	}


	public Peca getDraggy() {
		return draggy;
	}

	public void setDraggy(Peca draggy) {
		this.draggy = draggy;
	}

	public MoveMeMouseHandler(Casa[] a) {
		super();
		check =  new MovementChecker(a);
		casas = a;
	}

}