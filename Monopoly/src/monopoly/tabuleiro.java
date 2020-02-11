package monopoly;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import monopoly.Peca.PieceColor;
import monopoly.desenho.Draw;
import monopoly.desenho.DrawResult;
import monopoly.posicoes.Casa;
import monopoly.posicoes.DrawSorteReves;
import monopoly.posicoes.Local;
import monopoly.posicoes.Servico;
import monopoly.posicoes.Special;

@SuppressWarnings("serial")
public class tabuleiro extends JFrame {


	final JPanel top = new JPanel();
	static PieceColor turno = PieceColor.VERMELHO;
	static String s="Vermelho";
	final Peca verm = new Peca(PieceColor.VERMELHO);
	final Peca verde = new Peca(PieceColor.VERDE);
	final Peca amarelo = new Peca(PieceColor.AMARELO);
	final Peca azul = new Peca(PieceColor.AZUL);
	final Peca branco = new Peca(PieceColor.BRANCO);
	final Peca preto = new Peca(PieceColor.PRETO);
	DrawResult res1;
	DrawResult res2;
	Casa[] casa;
	final JLabel saldo = new JLabel("Saldo: ");

	final ObserverLabel ObserverAzul = new ObserverLabel(azul.getObserver(), saldo);
	final ObserverLabel ObserverPreto = new ObserverLabel(preto.getObserver(), saldo);
	final ObserverLabel ObserverBranco = new ObserverLabel(branco.getObserver(), saldo);
	final ObserverLabel ObserverVerde = new ObserverLabel(verde.getObserver(), saldo);
	final ObserverLabel ObserverAmarelo = new ObserverLabel(amarelo.getObserver(), saldo);
	final ObserverLabel ObserverVermelho = new ObserverLabel(verm.getObserver(), saldo);
	
	
	
	

	static FrameCasa DEBUGFrame;
	List<Peca> jogadores = new ArrayList<Peca>();

	public static ObservableInt caixinha = new ObservableInt(0);
	JLabel saldoCaixinha = new JLabel("Saldo Caixinha: ");
	private ObserverLabel caixinhaLabel = new ObserverLabel(caixinha, saldoCaixinha);
	

	public tabuleiro(){ 
	}
	public tabuleiro(int i)
	{
		initUI(i);
	}

	private void initUI(final int i) {

		final JPanel basic = new JPanel();
		final JPanel bottom = new JPanel();	
		//basic.setLayout(new BoxLayout(basic,BoxLayout.Y_AXIS));
		basic.setLayout(null);

		final Draw tab = new Draw("tabuleiro.png");
		JButton debugMenu = new JButton("Debug Menu");


		caixinha.addObserver(caixinhaLabel);
		
		top.setLayout(null);
		top.setPreferredSize(new Dimension(tab.getImage().getWidth(null), tab.getImage().getHeight(null)));

		

		//popula lista de jogadores

		switch (i) {
		case 6:
			jogadores.add(preto);
		case 5:
			jogadores.add(branco);
		case 4:
			jogadores.add(azul);
		case 3:
			jogadores.add(amarelo);
		case 2:
			jogadores.add(verde);
		case 1:
			jogadores.add(verm);
			break;
		default:
			break;
		}
		
		final ObservableInt qtdJogadores = new ObservableInt(i);
		final ObserverEndGame ObserverJogadores = new ObserverEndGame(qtdJogadores);
		qtdJogadores.addObserver(ObserverJogadores);

		/*Inicializa o vetor de casas*/

		casa = new Casa[32];
		{
			casa[0] = new Special(new Point(2,2), new Point (48,48), "barbante.jpg") {

				@Override
				public void NewAtivaCasa(Peca jogador) {
					jogador.setCasa(16);
					jogador.setPosition(new Point((541+587)/2,(533+579)/2));
					jogador.setLocation(new Point((541+587)/2,(533+579)/2));
					jogador.setPreso(true);

				}
			};
			//					UpperBound			LowBound		 nome do arquivo   taxa   $compra    $imovel     CorCasa
			casa[1] = new Local(new Point(70,2), new Point (115,48), "Tatooine.png",     10,     60,          50,      "Amarelo");	
			casa[2] = new Servico(new Point(137,2), new Point (183,48), 40, "RebelArmy.png", 400, casa, jogadores);
			casa[3] = new Local(new Point(204,2), new Point (250,48), "Coast City.png", 26, 180, 100, "Roxo");	//Coast
			casa[4] = new DrawSorteReves(new Point(272,2), new Point (317,48), "barbante.jpg", jogadores);
			casa[5] = new Local(new Point(339,2), new Point (385,48), "Gotham City.png", 33, 250, 100, "Roxo");	//Gotham
			casa[6] = new DrawSorteReves(new Point(406,2), new Point (452,48), "barbante.jpg", jogadores);
			casa[7] = new Local(new Point(473,2), new Point (519,48), "Metropolis.png", 30, 230, 100, "Roxo");	//Metropolis
			casa[8] = new Casa(new Point(541,2), new Point (587,48), "barbante.jpg");



			casa[9] = new Local(new Point(541,70), new Point (587,114), "The Shire.png", 30, 230, 150, "Verde");	//Shire
			casa[10] = new Servico(new Point(541,135), new Point (587,180), 40, "SociedadeDoAnel.png", 400, casa, jogadores);
			casa[11] = new Local(new Point(541,201), new Point (587,246), "Isengard.png", 38, 280, 150, "Verde");	//Isengard
			casa[12] = new DrawSorteReves(new Point(541,267), new Point (587,312), "barbante.jpg", jogadores);
			casa[13] = new Local(new Point(541,333), new Point (587,380), "Hogsmead.png", 45, 350, 200, "Laranja");	//Hogsmead
			casa[14] = new Servico(new Point(541,400), new Point (587,446), 40, "ArmadaDeDumbledore.png", 400, casa, jogadores);
			casa[15] = new Local(new Point(541,467), new Point (587,513), "Hogwarts.png", 50, 400, 200, "Laranja");	//Hogwarts


			casa[16] = new Casa(new Point(541,533), new Point (587,579), "barbante.jpg");

			casa[17] = new Local(new Point(473,533), new Point (520,579), "New York.png", 18, 100, 75, "Azul");	//Hell's Kitchen
			casa[18] = new Special(new Point(406,533), new Point (453,579), "barbante.jpg") {		
				@Override
				public void NewAtivaCasa(Peca jogador) {
					jogador.alterarFundos(-200);
				}
			};
			casa[19] = new Local(new Point(339,533), new Point (386,579), "Asgard.png", 23, 130, 75, "Azul");		//Asgard
			casa[20] = new DrawSorteReves(new Point(271,533), new Point (318,579), "barbante.jpg", jogadores);
			casa[21] = new Local(new Point(204,533), new Point (250,579), "Winterfell.png", 22, 120, 90, "Vermelho");	//Winterfell
			casa[22] = new Servico(new Point(137,533), new Point (183,579), 40, "BrotherhoodWithoutBanners.png", 400, casa, jogadores);
			casa[23] = new Local(new Point(69,533), new Point (116,579), "King's Landing.png", 18, 100, 90, "Vermelho");	//King's Landing	
			casa[24] = new Special(new Point(2,533), new Point (49,579), "barbante.jpg") {

				@Override
				public void NewAtivaCasa(Peca jogador) {
					jogador.alterarFundos(caixinha.getInt());
					caixinha.setDinheiro(0);
				}
			};



			casa[25] = new Local(new Point(2,467), new Point (49,513), "Braavos.png", 30, 230, 90, "Vermelho");	//Braavos
			casa[26] = new DrawSorteReves(new Point(2,400), new Point (49,447), "barbante.jpg", jogadores);
			casa[27] = new Local(new Point(2,333), new Point (49,380), "Dorne.png", 26, 180, 90, "Vermelho");	//Dorne
			casa[28] = new Special(new Point(2,267), new Point (49,312), "barbante.jpg") {	
				@Override
				public void NewAtivaCasa(Peca jogador) {
					jogador.alterarFundos(200);
				}
			};
			casa[29] = new Local(new Point(2,201), new Point (49,247), "Coruscant.png", 18, 100, 50, "Amarelo");	//Coruscant
			casa[30] = new DrawSorteReves(new Point(2,135), new Point (49,181), "barbante.jpg", jogadores);
			casa[31] = new Local(new Point(2,69), new Point (49,114), "Naboo.png", 15, 80, 50, "Amarelo");	//Naboo				
		}

		final MoveMeMouseHandler handler = new MoveMeMouseHandler(casa);

		switch (i) {
		case 6:
			preto.setOpaque(false);
			top.add(preto);
			preto.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2),preto.getImage().getWidth(null), preto.getImage().getHeight(null));		
			preto.addMouseListener(handler);
			preto.addMouseMotionListener(handler);
			preto.setCasa(8);
			preto.setObserver(ObserverPreto);
		case 5:
			branco.setOpaque(false);
			top.add(branco);
			branco.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2),branco.getImage().getWidth(null), branco.getImage().getHeight(null));		
			branco.addMouseListener(handler);
			branco.addMouseMotionListener(handler);
			branco.setCasa(8);
			branco.setObserver(ObserverBranco);
		case 4:
			azul.setOpaque(false);
			top.add(azul);
			azul.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2),azul.getImage().getWidth(null), azul.getImage().getHeight(null));		
			azul.addMouseListener(handler);
			azul.addMouseMotionListener(handler);
			azul.setCasa(8);
			azul.setObserver(ObserverAzul);
		case 3:
			amarelo.setOpaque(false);
			top.add(amarelo);
			amarelo.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2), amarelo.getImage().getWidth(null), amarelo.getImage().getHeight(null));
			amarelo.addMouseListener(handler);
			amarelo.addMouseMotionListener(handler);
			amarelo.setCasa(8);
			amarelo.setObserver(ObserverAmarelo);
		case 2:
			verde.setOpaque(false);
			top.add(verde);
			verde.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2), verde.getImage().getWidth(null), verde.getImage().getHeight(null));
			verde.addMouseListener(handler);
			verde.addMouseMotionListener(handler);
			verde.setCasa(8);
			verde.setObserver(ObserverVerde);
		case 1:
			verm.setOpaque(false);
			top.add(verm);
			verm.setBounds(MoveMeMouseHandler.xOffset+(tab.getImage().getWidth(null)/2),MoveMeMouseHandler.yOffset+(tab.getImage().getHeight(null)/2), verm.getImage().getWidth(null), verm.getImage().getHeight(null));
			verm.addMouseListener(handler);
			verm.addMouseMotionListener(handler);
			verm.setCasa(8);
			verm.setObserver(ObserverVermelho);
			break;

		default:
			break;
		}

		top.add(saldoCaixinha);
		saldoCaixinha.setBounds((tab.getImage().getWidth(null)/2)-70, tab.getImage().getHeight(null)/2, 150, 20);
		
		top.add(tab);
		tab.setBounds(0, 0, tab.getImage().getWidth(null), tab.getImage().getHeight(null));

		pack();

		bottom.setLayout(null);
		//		bottom.setBounds(0, top.getHeight(), top.getWidth(), 200);

		

		basic.add(top);
		top.setBounds(0, 0, tab.getImage().getWidth(null), tab.getImage().getHeight(null));
		basic.add(bottom);
		bottom.setBounds(0, top.getHeight(), top.getWidth(), 120);
		basic.setSize(top.getWidth(),top.getHeight()+bottom.getHeight());
		this.add(basic);
		basic.setBounds(0, 0, basic.getWidth(), basic.getHeight());
		
		
		
		/*Esta parte do codigo cuida da passagem dos turnos*/
		final JLabel turn = new JLabel("Turno:"+s);
		final JButton Pass = new JButton("Pass");

		MouseListener passar = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) 
			{
				MoveMeMouseHandler.xOffset = (int) top.getLocationOnScreen().getX();
				MoveMeMouseHandler.yOffset = (int) top.getLocationOnScreen().getY();
				if (DEBUGFrame!= null)
					DEBUGFrame.dispose();
				
				
				
				int Vermelho, Verde, Amarelo, Azul, Branco, Preto;
				Vermelho = jogadores.size()-1;
				Verde = jogadores.size()-2;
				Amarelo = jogadores.size()-3;
				Azul = jogadores.size()-4;
				Branco = jogadores.size()-5;
				Preto = jogadores.size()-6;
				
				if (turno == jogadores.get(Vermelho).getPieceColor())
				{
					jogadores.get(Vermelho).increaseJailTime();
					if (jogadores.size()>1)
					{
						turno = jogadores.get(Verde).getPieceColor();
						s = "Turno: "+jogadores.get(Verde).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Verde).getDinheiro());
						this.usarPasse(jogadores.get(Verde));
					}
					else
					{
						this.usarPasse(jogadores.get(Vermelho));
					}
					jogadores.get(Vermelho).setResult(0);
					if (jogadores.get(Vermelho).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Vermelho))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Vermelho))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Vermelho);
						top.remove(Vermelho);
						top.repaint();
						top.revalidate();
						
						qtdJogadores.alteraDinheiro(-1);
						
					}
				}
				
				else if (turno == jogadores.get(Verde).getPieceColor())
				{
					jogadores.get(Verde).increaseJailTime();
					if (jogadores.size()>2)
					{
						turno = jogadores.get(Amarelo).getPieceColor();
						s = "Turno: "+jogadores.get(Amarelo).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Amarelo).getDinheiro());
						this.usarPasse(jogadores.get(Amarelo));
					}
					else
					{
						turno = jogadores.get(Vermelho).getPieceColor();
						s = "Turno: "+jogadores.get(Vermelho).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Vermelho).getDinheiro());
						this.usarPasse(jogadores.get(Vermelho));
					}
					jogadores.get(Verde).setResult(0);
					if (jogadores.get(Verde).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Verde))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Verde))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Verde);
						top.remove(Verde);
						top.repaint();
						top.revalidate();
						qtdJogadores.alteraDinheiro(-1);
					}
				}
				else if (turno == jogadores.get(Amarelo).getPieceColor())
				{
					jogadores.get(Amarelo).increaseJailTime();
					if (jogadores.size()>3)
					{
						turno = jogadores.get(Azul).getPieceColor();
						s = "Turno: "+jogadores.get(Azul).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Azul).getDinheiro());
						this.usarPasse(jogadores.get(Azul));
					}
					else
					{
						turno = jogadores.get(Vermelho).getPieceColor();
						s = "Turno: "+jogadores.get(Vermelho).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Vermelho).getDinheiro());
						this.usarPasse(jogadores.get(Vermelho));
					}
					jogadores.get(Amarelo).setResult(0);
					if (jogadores.get(Amarelo).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Amarelo))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Amarelo))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Amarelo);
						top.remove(Amarelo);
						top.repaint();
						top.revalidate();
						qtdJogadores.alteraDinheiro(-1);
					}
				}
				else if (turno == jogadores.get(Azul).getPieceColor())
				{
					jogadores.get(Azul).increaseJailTime();
					if (jogadores.size()>4)
					{
						turno = jogadores.get(Branco).getPieceColor();
						s = "Turno: "+jogadores.get(Branco).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Branco).getDinheiro());
						this.usarPasse(jogadores.get(Branco));
					}
					else
					{
						turno = jogadores.get(Vermelho).getPieceColor();
						s = "Turno: "+jogadores.get(Vermelho).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Vermelho).getDinheiro());
						this.usarPasse(jogadores.get(Vermelho));
					}
					jogadores.get(Azul).setResult(0);
					if (jogadores.get(Azul).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Azul))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Azul))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Azul);
						top.remove(Azul);
						top.repaint();
						top.revalidate();
						qtdJogadores.alteraDinheiro(-1);
					}
				}
				else if (turno == jogadores.get(Branco).getPieceColor())
				{
					jogadores.get(Branco).increaseJailTime();
					if (jogadores.size()>5)
					{
						turno = jogadores.get(Preto).getPieceColor();
						s = "Turno: "+jogadores.get(Preto).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Preto).getDinheiro());
						this.usarPasse(jogadores.get(Preto));
					}
					else
					{
						turno = jogadores.get(Vermelho).getPieceColor();
						s = "Turno: "+jogadores.get(Vermelho).getPieceColor().toString();
						turn.setText(s);
						saldo.setText("Saldo: "+jogadores.get(Vermelho).getDinheiro());
						this.usarPasse(jogadores.get(Vermelho));
					}
					jogadores.get(Branco).setResult(0);
					if (jogadores.get(Branco).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Branco))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Branco))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Branco);
						top.remove(Branco);
						top.repaint();
						top.revalidate();
						qtdJogadores.alteraDinheiro(-1);
					}
				}
				else if (turno == jogadores.get(Preto).getPieceColor())
				{
					jogadores.get(Preto).increaseJailTime();
					turno = jogadores.get(Vermelho).getPieceColor();
					s = "Turno: "+jogadores.get(Vermelho).getPieceColor().toString();
					turn.setText(s);
					saldo.setText("Saldo: "+jogadores.get(Vermelho).getDinheiro());
					this.usarPasse(jogadores.get(Vermelho));
					jogadores.get(Preto).setResult(0);
					if (jogadores.get(Preto).getDinheiro() <= 0)
					{
						for (int i=0;i<casa.length;i++)
						{
							if (casa[i] instanceof Servico)
							{
								if (((Servico)casa[i]).proprietario == jogadores.get(Preto))
								{
									((Servico)casa[i]).proprietario = null;
								}
								if (((Servico)casa[i]).getHipotecario() == jogadores.get(Preto))
								{
									((Servico)casa[i]).setHipotecario(null);
								}
							}
						}
						jogadores.remove(Preto);
						top.remove(Preto);
						top.repaint();
						top.revalidate();
						qtdJogadores.alteraDinheiro(-1);
					}
				}
				if (bottom.getComponentCount()>5){
					bottom.remove(res1);
					bottom.remove(res2);
					bottom.revalidate();
					bottom.repaint();
				}
			}
			private void usarPasse(Peca jogador) {
				if(jogador.isPreso() && jogador.getPasse())
				{
					int escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de usar seu passe livre?","Saida Livre",JOptionPane.YES_NO_OPTION);
					if (escolha==JOptionPane.YES_OPTION)
					{
						jogador.setPreso(false);
						jogador.setPasse(false);
					}
				}

			}
		};

		saldo.setText("Saldo: "+verm.getDinheiro());
		Pass.addMouseListener(passar);
		bottom.add(Pass);
		Pass.setBounds(490, 50, 100, 30);

		tab.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2)
				{
					for (Casa a: casa)
					{
						Point click;
						Point OnScreen;
						OnScreen = e.getLocationOnScreen();
						click = new Point(MoveMeMouseHandler.xOffset,MoveMeMouseHandler.yOffset);
						OnScreen.x -= click.x;
						OnScreen.y -= click.y;
						FrameCasa frame;
						if(a instanceof Servico)
						{
							if (a.pertenceCasa(OnScreen)==true)
							{
								switch (turno) {
								case VERMELHO:
									frame = new FrameCasa(a, verm, jogadores);
									break;
								case VERDE:
									frame = new FrameCasa(a, verde, jogadores);
									break;
								case AMARELO:
									frame = new FrameCasa(a, amarelo, jogadores);
									break;
								case AZUL:
									frame = new FrameCasa(a,azul,jogadores);
									break;
								case BRANCO:
									frame = new FrameCasa(a,branco,jogadores);
									break;
								case PRETO:
									frame = new FrameCasa(a,preto,jogadores);
									break;

								default:
									frame= null;
									break;
								}
								frame.setVisible(true);
							}
						}
					}
				}
			}
		});


		bottom.add(turn);
		turn.setBounds(490,5,100,10);

		bottom.add(saldo);
		saldo.setBounds(490, 30, 100, 10);

		/*Esta parte do codigo cuida da rolagem dos dados*/

		final Draw die = new Draw("dados.png");
		bottom.add(die);
		die.setBounds(140,5,100,100);

		MouseListener dice = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) 
			{
				Random rand = new Random();

				bottom.removeAll();
				bottom.add(Pass);
				bottom.add(turn);
				bottom.add(saldo);
				bottom.add(debugMenu);

				Pass.setBounds(490, 50, 100, 30);
				turn.setBounds(490,5,100,10);
				saldo.setBounds(490, 30, 100, 10);
				debugMenu.setBounds(380, 50, 100, 30);

				bottom.add(die);
				die.setBounds(140,5,100,100);

				int a = rand.nextInt(6);
				a++;
				res1 = new DrawResult(a);
				bottom.add(res1);
				res1.setBounds(0, 5, res1.getImage().getWidth(null), res1.getImage().getHeight(null));

				int b = rand.nextInt(6);
				b++;

				res2 = new DrawResult(b);
				bottom.add(res2);	
				res2.setBounds(res1.getImage().getWidth(null)+2, 5, res1.getImage().getWidth(null), res1.getImage().getHeight(null));


				switch (turno) {
				case VERMELHO:
					if (a==b)
						verm.setPreso(false);
					verm.setResult(a+b);

					break;
				case VERDE:
					if (a==b)
						verde.setPreso(false);
					verde.setResult(a+b);
					break;
				case AMARELO:
					if (a==b)
						amarelo.setPreso(false);
					amarelo.setResult(a+b);
					break;
				case AZUL:
					if (a==b)
						azul.setPreso(false);
					azul.setResult(a+b); 
					break;
				case BRANCO:
					if (a==b)
						branco.setPreso(false);
					branco.setResult(a+b);
					break;
				case PRETO:
					if (a==b)
						preto.setPreso(false);
					preto.setResult(a+b);
					break;

				default:
					break;
				}

				bottom.revalidate();


			}
		};

		die.addMouseListener(dice);




		/*Essa Parte seta as configurações do Tabuleiro*/

		setTitle("Jogo Banco Imobiliário");
		setSize(basic.getWidth()+20,basic.getHeight());
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		MouseListener DEBUGMenu = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) 
			{
				Peca aux;
				switch (turno) {
				case VERMELHO:
					aux = verm;
					break;
				case VERDE:
					aux = verde;
					break;
				case AMARELO:
					aux = amarelo;
					break;
				case AZUL:
					aux = azul;
					break;
				case BRANCO:
					aux = branco;
					break;
				case PRETO:
					aux = preto;
					break;

				default:
					aux = verm;
					break;
				}

				DEBUGFrame = new FrameCasa(casa, jogadores, aux);
				DEBUGFrame.setVisible(true);
			}
		};



		debugMenu.addMouseListener(DEBUGMenu);
		bottom.add(debugMenu);
		debugMenu.setBounds(380, 50, 100, 30);


	}

}

