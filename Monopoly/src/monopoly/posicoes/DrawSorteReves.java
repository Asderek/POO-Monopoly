package monopoly.posicoes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fila.PilhaSorteReves;
import monopoly.FrameCasa;
import monopoly.Peca;
import monopoly.SorteOuReves;
import monopoly.tabuleiro;

public class DrawSorteReves extends Casa{
	
	private List<Peca> jogadores;
	private PilhaSorteReves pilhaCartas;
	
	public DrawSorteReves(Point up, Point low, String s, List<Peca> j) {
		super(up, low, s);
		this.pilhaCartas = new PilhaSorteReves();
		populaCartas();
		this.jogadores = j;
	}
	
	private void populaCartas() {
		List<SorteOuReves> lista = new ArrayList<SorteOuReves>();
		for(int i=1; i <= 15; i++) {
			SorteOuReves carta = null;
			switch (i) {
			case 1:
				carta = new SorteOuReves(150, "sorte"+i+".jpg");
				break;
			case 2:
				carta = new SorteOuReves(100, "sorte"+i+".jpg");
				break;
			case 3:
				carta = new SorteOuReves(0, "sorte"+i+".jpg");
				carta.setPasse();
				break;
			case 4:
				carta = new SorteOuReves(80, "sorte"+i+".jpg");
				break;
			case 5:
				carta = new SorteOuReves(100, "sorte"+i+".jpg");
				break;
			case 6:
				carta = new SorteOuReves(50, "sorte"+i+".jpg");
				carta.setRecebeOutros();
				break;
			case 7:
				carta = new SorteOuReves(50, "sorte"+i+".jpg");
				break;
			case 8:
				carta = new SorteOuReves(20, "sorte"+i+".jpg");
				break;
			case 9:
				carta = new SorteOuReves(25, "sorte"+i+".jpg");
				break;
			case 10:
				carta = new SorteOuReves(200, "sorte"+i+".jpg");
				carta.setIrPartida();
				break;
			case 11:
				carta = new SorteOuReves(100, "sorte"+i+".jpg");
				break;
			case 12:
				carta = new SorteOuReves(100, "sorte"+i+".jpg");
				break;
			case 13:
				carta = new SorteOuReves(45, "sorte"+i+".jpg");
				break;
			case 14:
				carta = new SorteOuReves(50, "sorte"+i+".jpg");
				break;
			case 15:
				carta = new SorteOuReves(200, "sorte"+i+".jpg");
				break;
			default:
				break;
			}
			lista.add(carta);
		}
		for(int i=1; i <= 15; i++) {
			SorteOuReves carta = null;
			switch (i) {
			case 1:
				carta = new SorteOuReves(-25, "reves"+i+".jpg");
				break;
			case 2:
				carta = new SorteOuReves(-45, "reves"+i+".jpg");
				break;
			case 3:
				carta = new SorteOuReves(-45, "reves"+i+".jpg");
				break;
			case 4:
				carta = new SorteOuReves(-30, "reves"+i+".jpg");
				break;
			case 5:
				carta = new SorteOuReves(-40, "reves"+i+".jpg");
				break;
			case 6:
				carta = new SorteOuReves(-50, "reves"+i+".jpg");
				break;
			case 7:
				carta = new SorteOuReves(-100, "reves"+i+".jpg");
				break;
			case 8:
				carta = new SorteOuReves(-50, "reves"+i+".jpg");
				break;
			case 9:
				carta = new SorteOuReves(-30, "reves"+i+".jpg");
				break;
			case 10:
				carta = new SorteOuReves(-30, "reves"+i+".jpg");
				break;
			case 11:
				carta = new SorteOuReves(-25, "reves"+i+".jpg");
				break;
			case 12:
				carta = new SorteOuReves(0, "reves"+i+".jpg");
				carta.setIrPrisao();
				break;
			case 13:
				carta = new SorteOuReves(-100, "reves"+i+".jpg");
				break;
			case 14:
				carta = new SorteOuReves(-50, "reves"+i+".jpg");
				break;
			case 15:
				carta = new SorteOuReves(-15, "reves"+i+".jpg");
				break;
			default:
				break;
			}
			lista.add(carta);
		}
		pilhaCartas = embaralhaCartas(lista);
		
	}
	
	private PilhaSorteReves embaralhaCartas(List<SorteOuReves> lista) {
		PilhaSorteReves embaralhada = new PilhaSorteReves();
		int j=30;
		while(!lista.isEmpty()) {
			Random rand = new Random();
			int i = rand.nextInt(j);
			embaralhada.insere(lista.get(i));
			lista.remove(i);
			j--;
		}
		
		return embaralhada;
	}
	@Override
	public void AtivaCasa(Peca jogador) {
		SorteOuReves carta = pilhaCartas.remove();
	
		int efeito = carta.getEfeito();
		FrameCasa a = new FrameCasa(carta.getImage());
		a.setVisible(true);
		
		//se nao envolver nenhum valor
		if(efeito == 0) {
			if(carta.isPasse() /*&& jogador.getCasa() == 16*/) {
				jogador.setPasse(true);
			}
			if(carta.isIrPrisao()) {
				jogador.setCasa(16);
				jogador.setPosition(new Point((541+587)/2,(533+579)/2));
				jogador.setLocation(new Point((541+587)/2,(533+579)/2));
				jogador.setPreso(true);
			}
			if(carta.isIrPartida()) {
				jogador.setCasa(8);
				jogador.setPosition(new Point((541+587)/2,(2+48)/2));
				jogador.setLocation(new Point((541+587)/2,(2+48)/2));
				jogador.alterarFundos(200);
			}
		} else {
			if(!carta.isRecebeOutros()) {
				jogador.alterarFundos(efeito);
				if (efeito<0)
				{
					tabuleiro.caixinha.alteraDinheiro(efeito);
				}
			}
			else {
				for(Peca p: jogadores) {
					if(p != jogador) 
						p.alterarFundos(-efeito);
				}
				jogador.alterarFundos((jogadores.size()-1)*efeito);
			}
		}
		
		pilhaCartas.insere(carta);
	}
}

	