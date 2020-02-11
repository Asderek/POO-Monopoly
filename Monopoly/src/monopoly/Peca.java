package monopoly;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import monopoly.desenho.Draw;

@SuppressWarnings("serial")
public class Peca  extends Draw {

	public enum PieceColor {
		VERMELHO,
		VERDE,
		AZUL,
		AMARELO,
		PRETO,
		BRANCO;
		
		public URL getURL() {
			String s = null;
			switch (this) {
			case VERMELHO:
				s = "pecavermelha.png"; 
				break;
			case VERDE:
				s = "pecaverde.png"; 
				break;
			case AMARELO:
				s = "pecaamarela.png"; 
				break;
			case AZUL:
				s = "pecaazul.png"; 
				break;
			case BRANCO:
				s = "pecabranca.png"; 
				break;
			case PRETO:
				s = "pecapreta.png"; 
				break;

			default:
				break;
			}
			
			return getClass().getClassLoader().getResource(s);
		}
		
	};
		
	Image img;
	private PieceColor pieceColor;
	int result;
	int casa;
	private ObservableInt dinheiro= new ObservableInt(1000);
	private Point position;
	private Map<String, Integer> mapaCasas;
	private boolean passe = false;
	private boolean preso = false;
	public int timeInJail;
	private boolean isProtected;
	
	
	public Peca ( PieceColor pieceColor ){	
		this.setPieceColor(pieceColor);
		mapaCasas = new HashMap<String,Integer>();
		mapaCasas.put("Roxo", 0);
		mapaCasas.put("Verde", 0);
		mapaCasas.put("Laranja", 0);
		mapaCasas.put("Azul", 0);
		mapaCasas.put("Vermelho", 0);
		mapaCasas.put("Amarelo", 0);
		init();
		
	}

	@Override
	public URL getURL() {
		return getPieceColor().getURL();
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public void setPieceColor(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	public void setResult(int i)
	{
		if(preso==false)
			result = i;
	}

	public int getResult()
	{
		return result;
	}
	
	public void setCasa(int i)
	{
		casa = i;
	}

	public int getCasa()
	{
		return casa;
	}
	
	public void setPosition(Point p)
	{
		position = p;
	}
	
	public Point getPosition()
	{
		return position;
	}

	public int getDinheiro() {
		return dinheiro.getInt();
	}

	public void alterarFundos(int dinheiro) {
		
		this.dinheiro.alteraDinheiro(dinheiro);
		
	}
	
public void setDinheiro(int dinheiro) {
		
		this.dinheiro.setDinheiro(dinheiro);
		
	}

	public Map<String, Integer> getMapaCasas() {
		return mapaCasas;
	}

	public void setMapaCasas(Map<String, Integer> mapaCasas) {
		this.mapaCasas = mapaCasas;
	}

	public void setPasse(boolean b) {
		this.passe  = b;
	}
	
	public boolean getPasse() {
		return this.passe;
	}

	public boolean isPreso() {
		return preso;
	}

	public void setPreso(boolean preso) {
		this.preso = preso;
		timeInJail = 0;
	}
	
	public void setObserver(ObserverLabel a)
	{
		dinheiro.addObserver(a);
	}
	
	public ObservableInt getObserver()
	{
		return dinheiro;
	}

	public void setJailTime(int i)
	{
		timeInJail = i;
	}
	
	public void increaseJailTime() {
		timeInJail++;
		if (timeInJail>=3)
		{
			if (casa == 16  && isPreso()==true)
			{
				int escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de pagar $50 pela sua Liberdade?","Bail",JOptionPane.YES_NO_OPTION);
				if (escolha==JOptionPane.YES_OPTION)
				{
					setPreso(false);
					this.alterarFundos(-50);
				}
			}
			else
			{
				setPreso(false);
			}
		}
		
	}

	public boolean isProtected() {
		return isProtected;
	}

	public void setProtected(boolean state) {
		this.isProtected = state;
	}

}
