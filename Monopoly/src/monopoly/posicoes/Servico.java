package monopoly.posicoes;

import java.awt.Point;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import monopoly.FrameCasa;
import monopoly.ObservableInt;
import monopoly.Peca;
import monopoly.Peca.PieceColor;
import monopoly.tabuleiro;

@SuppressWarnings("serial")
public class Servico extends Casa {

	private int preco;
	int taxa;
	Casa[] casas;
	List<Peca> jogadores;
	public Peca proprietario=null;
	private Peca hipoteca;

	public Servico(Point up, Point low, int taxa, String s, int Preco,  Casa[] casas, List<Peca> jogadores) {
		super(up, low, s);
		this.preco = Preco;
		this.taxa = taxa;
		this.casas = casas;
		this.jogadores = jogadores;
		

	}

	@Override
	public void AtivaCasa(Peca jogador) {
		FrameCasa a = new FrameCasa(this.getName());
		a.setVisible(true);

		if( proprietario == null)
			AtivaVisitante(jogador, a);
		else if (jogador.getPieceColor() != proprietario.getPieceColor())
		{
			AtivaVisitante(jogador,a );
		}
		else
		{
			AtivaProprietario();
		}
	}

	protected void AtivaProprietario()
	{
		ativaServico(proprietario);
	}
	
	protected void AtivaVisitante(Peca jogador, FrameCasa a)
	{
		if (proprietario==null)
		{
			if (hipoteca==null)
			{
				int escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de comprar essa casa?","Compra",JOptionPane.YES_NO_OPTION);

				if (escolha==JOptionPane.YES_OPTION)
				{
					ativaServico(jogador);
					jogador.alterarFundos(-getPreco());
					proprietario = jogador;

				}
			}
			else
			{
				if (jogador == hipoteca)
				{
					int escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de desHipotecar essa casa por $"+this.preco+"?","Compra",JOptionPane.YES_NO_OPTION);

					if (escolha==JOptionPane.YES_OPTION)
					{
						proprietario = jogador;
						proprietario.alterarFundos(-this.preco);
						hipoteca=null;
					}
				}
				else
				{
						String[] options = {"OK"};
						JPanel panel = new JPanel();
						JLabel lbl = new JLabel("Essa propriedade pertence à outro jogador");
						panel.add(lbl);
						int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
					
				}
			}

		}
		else {
			ativaServico(jogador);

		}
		a.dispose();
	}

	private void ativaServico(Peca jogador) {
		if (getName()=="BrotherhoodWithoutBanners.png")
		{
			ativaBrotherhood(jogador);
		}
		else if (getName()=="ArmadaDeDumbledore.png")
		{
			ativaArmada(jogador);
		}
		else if (getName()=="RebelArmy.png")
		{
			ativaExercitoRebelde(jogador);
		}
		else if (getName()=="SociedadeDoAnel.png")
		{
			ativaSociedade(jogador);
		}

	}

	private void ativaSociedade(Peca jogador) {
		ObservableInt cache = tabuleiro.caixinha;
		
		if (proprietario == null)
		{
			switch (jogadores.size()) {
			case 1:
				jogadores.get(0).alterarFundos(cache.getInt()/2);
				jogadores.get(0).alterarFundos(cache.getInt()/2);
				break;
			case 2:
				jogadores.get(0).alterarFundos(cache.getInt()/2);
				jogadores.get(1).alterarFundos(cache.getInt()/2);
				break;
			case 3:
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)==jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/2);
				}
			
				cache.setDinheiro(cache.getInt()/2);
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)!=jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/2);
				}
				cache.setDinheiro(0);

				break;
			case 4:
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)==jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/2);
				}
				cache.setDinheiro(cache.getInt()/2);
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)!=jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/3);
				}
				cache.setDinheiro(0);
				break;
			case 5:
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)==jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/2);
				}
				cache.setDinheiro(cache.getInt()/2);
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)!=jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/4);
				}
				cache.setDinheiro(0);
				break;
			case 6:
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)==jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/2);
				}
				cache.setDinheiro(cache.getInt()/2);
				for (int cont=0;cont<jogadores.size();cont++)
				{
					if(jogadores.get(cont)!=jogador)
						jogadores.get(cont).alterarFundos(cache.getInt()/5);
				}
				cache.setDinheiro(0);
				break;
			default:
				break;
			}
		}
		else if (jogador != proprietario)
		{
			if(jogador.isProtected() == false)
			{
				cache.setDinheiro(jogador.getDinheiro()*5/100);
				jogador.alterarFundos(-(jogador.getDinheiro()*5/100));
			}
			else
			{
				jogador.setProtected(false);
			}
		}
		else if (jogador == proprietario)
		{
			for (int cont=0;cont<jogadores.size();cont++)
			{
				if (jogadores.get(cont).isProtected())
				{
					jogadores.get(cont).setProtected(false);
				}
				else
				{
					jogadores.get(cont).setCasa(10);
					Point p = new Point(541+((587-541)/2),135+((180-135)/2));
					jogadores.get(cont).setLocation(p);
					jogadores.get(cont).setPosition(p);
					if (jogadores.get(cont)!=jogador)
					{
						ativaSociedade(jogadores.get(cont));
					}
				}
			}
			
		}
	
	}

	private void ativaExercitoRebelde(Peca jogador) {
		if (proprietario == null)
		{
			String IndexCasa = "a";
			int IntIndiceCasa=-1;
			do
			{
				IndexCasa = (String)JOptionPane.showInputDialog(null,"Qual Local gostaria de atacar?","Ataque");
				try
				{							
					IntIndiceCasa = Integer.parseInt(IndexCasa);
				}catch(NumberFormatException event) {
					IntIndiceCasa=-1;
				}
			}while ((IntIndiceCasa<0) || !(casas[IntIndiceCasa] instanceof Local) );
			if (casas[IntIndiceCasa] instanceof Local )
			{
				((Local)casas[IntIndiceCasa]).DestroyProperties("all");
			}
			
		}
		else if (jogador == proprietario)
		{
			String IndexCasa = "a";
			int IntIndiceCasa=-1;
			do
			{
				IndexCasa = (String)JOptionPane.showInputDialog(null,"Qual Local gostaria de atacar?","Preço");
				try
				{							
					IntIndiceCasa = Integer.parseInt(IndexCasa);
				}catch(NumberFormatException event) {
					IntIndiceCasa=-1;
				}
			}while ((IntIndiceCasa<0) && !(casas[IntIndiceCasa] instanceof Local) );
			if (casas[IntIndiceCasa] instanceof Local )
			{
				((Local)casas[IntIndiceCasa]).DestroyProperties("one");
			}
		}
		else if (jogador != proprietario)
		{
			if (jogador.isProtected())
				jogador.setProtected(false);
			else
			{
				jogador.setPreso(true);
				jogador.setJailTime(1);
			}
		}
	}

	private void ativaArmada(Peca jogador) {
		if (proprietario == null)
		{
			//TODO blah
			
		}
		else if (jogador == proprietario)
		{
			Object[] possibilities = {"Ficar Imune ao Proximo Efeito","Ganhar PasseLivre","Ganhar $200"};
			Object ret = JOptionPane.showInputDialog(null,"Quantos Jogadores?","Jogadores",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
			String escolha="";
			
			escolha = (String)ret;
			switch (escolha) {
			case "Ficar Imune ao Proximo Efeito":
				jogador.setProtected(true);
				break;
			case "Ganhar PasseLivre":
				jogador.setPasse(true);
				break;
			case "Ganhar $200":
				jogador.alterarFundos(200);
				break;
			default:
				break;
			}
			
			
		}
		else if (jogador != proprietario)
		{
			if(jogador.getPasse())
			{
				jogador.setPasse(false);
				proprietario.setPasse(true);
			}
		}
	}

	private void ativaBrotherhood(Peca jogador) {
		if (proprietario == null)
		{
			PieceColor[] possibilities = new PieceColor[jogadores.size()];
			PieceColor color;
			PieceColor CompradorColor;
			Peca Doador=null;
			ObservableInt cache = tabuleiro.caixinha;

			int i;
			for (i=0;i<jogadores.size();i++)
			{	
				color = jogadores.get(jogadores.size()-(i+1)).getPieceColor();
				possibilities[i] = color;
			}

			do{
				CompradorColor = (PieceColor)JOptionPane.showInputDialog(null,"Escolha um jogador para doar 50% do dinheiro dele\n para o tabuleiro?","Jogadores",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
				if (CompradorColor != jogador.getPieceColor())
				{

					switch (CompradorColor) {
					case VERMELHO:
						Doador = jogadores.get(jogadores.size()-1);
						break;
					case VERDE:
						Doador = jogadores.get(jogadores.size()-2);
						break;
					case AMARELO:
						Doador = jogadores.get(jogadores.size()-3);
						break;
					case AZUL:
						Doador = jogadores.get(jogadores.size()-4);
						break;
					case BRANCO:
						Doador = jogadores.get(jogadores.size()-5);
						break; 
					case PRETO:
						Doador = jogadores.get(jogadores.size()-6);
						break;

					default:
						Doador = null;
						break;
					}
					
					if (Doador.isProtected())
					{
						String[] options = {"OK"};
						JPanel panel = new JPanel();
						JLabel lbl = new JLabel("Jogador está protegido contra efeitos");
						panel.add(lbl);
						int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
					}
				}
				
			}while (CompradorColor==jogador.getPieceColor() || Doador.isProtected());

			cache.alteraDinheiro(Doador.getDinheiro()/2);;
			Doador.alterarFundos(-(Doador.getDinheiro()/2));
			jogador.alterarFundos(0);
			
			for (i=0;i<jogadores.size();i++)
			{	
				jogadores.get(i).setProtected(false);
			}
			
		}
		else if (jogador == proprietario)
		{
			PieceColor[] possibilities = new PieceColor[jogadores.size()];
			PieceColor color;
			PieceColor CompradorColor;
			Peca Doador=null;

			int i;
			for (i=0;i<jogadores.size();i++)
			{	
				color = jogadores.get(jogadores.size()-(i+1)).getPieceColor();
				possibilities[i] = color;
			}

			do{
				CompradorColor = (PieceColor)JOptionPane.showInputDialog(null,"Escolha um jogador para doar 50% do dinheiro dele\n para o tabuleiro?","Jogadores",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
				if (CompradorColor != jogador.getPieceColor())
				{

					switch (CompradorColor) {
					case VERMELHO:
						Doador = jogadores.get(jogadores.size()-1);
						break;
					case VERDE:
						Doador = jogadores.get(jogadores.size()-2);
						break;
					case AMARELO:
						Doador = jogadores.get(jogadores.size()-3);
						break;
					case AZUL:
						Doador = jogadores.get(jogadores.size()-4);
						break;
					case BRANCO:
						Doador = jogadores.get(jogadores.size()-5);
						break; 
					case PRETO:
						Doador = jogadores.get(jogadores.size()-6);
						break;

					default:
						Doador = null;
						break;
					}
					
					if (Doador.isProtected())
					{
						String[] options = {"OK"};
						JPanel panel = new JPanel();
						JLabel lbl = new JLabel("Jogador está protegido contra efeitos");
						panel.add(lbl);
						int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
					}
				}
			}while (CompradorColor==jogador.getPieceColor() || Doador.isProtected());

			int doacao = (int)(Doador.getDinheiro()/10);
			Doador.alterarFundos(-doacao);
			jogador.alterarFundos(doacao);

			for (i=0;i<jogadores.size();i++)
			{	
				jogadores.get(i).setProtected(false);
			}
			
		}
		else if (jogador != proprietario)
		{
			if (jogador.isProtected())
			{
				jogador.setProtected(false);
			}
			else
			{
				int doacao = (int)(jogador.getDinheiro()*0.05);
				jogador.alterarFundos(-doacao);
				proprietario.alterarFundos(doacao);
			}
		}
	}

	public int getPreco() {
		return preco;
	}

	public Peca getHipotecario() {
		return hipoteca;
	}

	public void setHipotecario(Peca hipoteca) {
		this.hipoteca = hipoteca;
	}


}
