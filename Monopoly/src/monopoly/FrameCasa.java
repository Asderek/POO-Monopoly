package monopoly;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import monopoly.Peca.PieceColor;
import monopoly.desenho.Draw;
import monopoly.posicoes.Casa;
import monopoly.posicoes.Local;
import monopoly.posicoes.Servico;

public class FrameCasa extends JFrame {


	final JPanel Panel = new JPanel();
	final JPanel basic = new JPanel();
	Draw mostra=null;
	final JFrame esta = this;
	List<Peca> jogadores;
	Casa[] casas;

	public FrameCasa(String s) {
		initUI(s);
	}

	public FrameCasa(Casa a, Peca jogadorAtual, List<Peca> jogadores) {
		compraEVendas(a, jogadorAtual, jogadores);
	}

	public FrameCasa(Casa[] a, List<Peca> jogadores, Peca jogadorAtual) {
		casas = a; this.jogadores = jogadores;
		DEBUGMenu(a, jogadores, jogadorAtual);
	}

	private void initUI(String s) {


		basic.setLayout(null);
		mostra = new Draw(s);


		Panel.setLayout(null);
		Panel.setPreferredSize(new Dimension(mostra.getImage().getWidth(null), mostra.getImage().getHeight(null)));

		Panel.add(mostra);
		mostra.setBounds(0, 0, mostra.getImage().getWidth(null), mostra.getImage().getHeight(null));

		pack();

		basic.add(Panel);
		Panel.setBounds(0, 0, mostra.getImage().getWidth(null), mostra.getImage().getHeight(null));
		basic.setSize(Panel.getWidth(),Panel.getHeight());
		this.add(basic);
		basic.setBounds(0, 0, basic.getWidth(), basic.getHeight());

		/*Essa Parte seta as configurações do Tabuleiro*/

		setTitle("Jogo Banco Imobiliário");
		setSize(basic.getWidth(),basic.getHeight()+25);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(0, 0);


	}

	private void compraEVendas (Casa a, Peca jogadorAtual, List<Peca> jogadores) {
		initUI(a.getName());
		JButton venda = new JButton("Vender");
		final Servico aux;

		if (a instanceof Servico)
		{
			aux = (Servico)a;

			venda.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {	
				}
				public void mouseEntered(MouseEvent e) {	
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					Object possibilities[] = new Object[jogadores.size()+1];
					PieceColor color;

					int i;
					for (i=0;i<jogadores.size();i++)
					{	
						color = jogadores.get(jogadores.size()-(i+1)).getPieceColor();
						possibilities[i] = (Object)color;
					}
					possibilities[i] = (Object)"Hipoteca";

					if (aux.proprietario == jogadorAtual)
					{
						Object CompradorColor = JOptionPane.showInputDialog(null,"Para quem voce gostaria de vender?","Jogadores",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
						if ((String)CompradorColor == "Hipoteca")
						{

							int resp = JOptionPane.showConfirmDialog(
									null,
									"Você gostaria de hipotecar essa casa por $"+(aux.getPreco())/2+"?",
									"Hipoteca",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (resp == JOptionPane.YES_OPTION)
							{
								aux.setHipotecario(jogadorAtual);
								aux.proprietario = null;
								jogadorAtual.alterarFundos(aux.getPreco()/2);
							}
						}
						else if ((PieceColor)CompradorColor != jogadorAtual.getPieceColor())
						{
							String input = "a";
							int Preço=-1;
							do
							{
								input = (String)JOptionPane.showInputDialog(null,"Por quanto gostaria de vender","Preço");
								try
								{							
									Preço = Integer.parseInt(input);
								}catch(NumberFormatException event) {
									Preço=-1;
								}
							}while (Preço<0);

							int resp = JOptionPane.showConfirmDialog(
									null,
									"Jogador "+CompradorColor+"; aceitas pagar $"+Preço+" pela propriedade?",
									"Compra",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);

							if (resp==0 )
							{							
								Peca Comprador;
								switch ((PieceColor)CompradorColor) {
								case VERMELHO:
									Comprador = jogadores.get(jogadores.size()-1);
									break;
								case VERDE:
									Comprador = jogadores.get(jogadores.size()-2);
									break;
								case AMARELO:
									Comprador = jogadores.get(jogadores.size()-3);
									break;
								case AZUL:
									Comprador = jogadores.get(jogadores.size()-4);
									break;
								case BRANCO:
									Comprador = jogadores.get(jogadores.size()-5);
									break; 
								case PRETO:
									Comprador = jogadores.get(jogadores.size()-6);
									break;

								default:
									Comprador = null;
									break;
								}

								Comprador.alterarFundos(-Preço);
								jogadorAtual.alterarFundos(Preço);

								if (aux instanceof Local)
								{
									String cor = ((Local) a).getCor();
									Comprador.getMapaCasas().put(cor, (Comprador.getMapaCasas().get(cor)+1));
									jogadorAtual.getMapaCasas().put(cor, (jogadorAtual.getMapaCasas().get(cor)-1));
								}
								aux.proprietario = Comprador;
								esta.dispose();

							}
						}
					}

				}
			});

			this.setLayout(null);
			this.add(venda);
			venda.setBounds(5,a.getImage().getHeight(null)+5,75,20);
			venda.setVisible(true);

			if (aux.proprietario != null)
			{
				JLabel dono = new JLabel("Dono: "+aux.proprietario.getPieceColor());
				this.add(dono);
				dono.setBounds(81, a.getImage().getHeight(null)+5, 120, 20);
			}

			JButton compra = new JButton("Compra");

			compra.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {	
				}
				public void mouseEntered(MouseEvent e) {	
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					if (aux.proprietario != null)
					{
						if (aux.proprietario != jogadorAtual)
						{
							String input = "a";
							int Preço=-1;
							do
							{
								input = (String)JOptionPane.showInputDialog(null,"Por quanto gostaria de comprar","Preço");
								try
								{							
									Preço = Integer.parseInt(input);
								}catch(NumberFormatException event) {
									Preço=-1;
								}
							}while (Preço<0);

							int resp = JOptionPane.showConfirmDialog(
									null,
									"Jogador "+aux.proprietario.getPieceColor()+"; aceitas receber $"+Preço+" pela propriedade?",
									"Compra",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);

							if (resp==0 )
							{							
								aux.proprietario.alterarFundos(+Preço);
								jogadorAtual.alterarFundos(-Preço);

								if (aux instanceof Local)
								{
									String cor = ((Local) a).getCor();
									jogadorAtual.getMapaCasas().put(cor, (jogadorAtual.getMapaCasas().get(cor)+1));
									aux.proprietario.getMapaCasas().put(cor, (aux.proprietario.getMapaCasas().get(cor)-1));
								}
								aux.proprietario = jogadorAtual;		
								esta.dispose();
							}
						}

					}
				}
			});


			this.add(compra);
			compra.setBounds(180,a.getImage().getHeight(null)+5,80,20);
			compra.setVisible(true);

			setSize(a.getImage().getWidth(null)+5,a.getImage().getHeight(null)+55);
		}
	}

	private void DEBUGMenu(Casa[] a, List<Peca> jogadores, Peca jogadorAtual) {


		JButton setResult = new JButton("SetResult");
		JButton alteraFundos = new JButton("AlteraFundos");
		JButton setFundos = new JButton("SetFundos");
		JButton compraCasa = new JButton("CompraCasa");

		MouseListener res = CriadorDeMouse("SetResult", jogadorAtual);
		MouseListener alter = CriadorDeMouse("AlteraFundos", jogadorAtual);
		MouseListener fundos = CriadorDeMouse("SetFundos", jogadorAtual);
		MouseListener compra = CriadorDeMouse("CompraCasa", jogadorAtual);

		setResult.addMouseListener(res);
		alteraFundos.addMouseListener(alter);
		setFundos.addMouseListener(fundos);
		compraCasa.addMouseListener(compra);

		JPanel basic = new JPanel();
		basic.setLayout(new GridLayout(3,2));
		basic.add(setResult);
		basic.add(alteraFundos);
		basic.add(setFundos);
		basic.add(compraCasa);

		pack();
		this.add(basic);

		setTitle("Debug Menu");
		setSize(240,150);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(0, 0);
	}

	public MouseListener CriadorDeMouse(String s, Peca jogador)
	{
		if (s == "SetResult")
		{
			MouseListener res = new MouseListener() {				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub


					//VERMELHO = jogadores.get(jogadores.size()-1);
					//VERDE = jogadores.get(jogadores.size()-2);
					//AMARELO = jogadores.get(jogadores.size()-3);
					//AZUL = jogadores.get(jogadores.size()-4);
					//BRANCO = jogadores.get(jogadores.size()-5);
					//PRETO = jogadores.get(jogadores.size()-6);

					String input = "a";
					int result=-1;
					do
					{
						input = (String)JOptionPane.showInputDialog(null,"Qual resultado gostaria de inserir","DEBUG Resultado");
						try
						{							
							result = Integer.parseInt(input);
						}catch(NumberFormatException event) {
							result=-1;
						}
					}while (result<0 || result >32);

					jogador.setResult(result);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			};
			return res;
		}
		else if (s == "AlteraFundos")
		{
			MouseListener alter = new MouseListener() {				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					String input = "a";
					int result=-9999;
					do
					{
						input = (String)JOptionPane.showInputDialog(null,"Quanto dinheiro gostaria de adicionar ao Jogador "+jogador.getPieceColor(),"DEBUG Fundos");
						try
						{							
							result = Integer.parseInt(input);
						}catch(NumberFormatException event) {
							result=-9999;
						}
					}while (result<=-9999);

					jogador.alterarFundos(result);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			};
			return alter;
		}
		else if (s == "SetFundos")
		{
			MouseListener funds = new MouseListener() {				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					String input = "a";
					int result=-9999;
					do
					{
						input = (String)JOptionPane.showInputDialog(null,"Para qual valor gostaria de mudar o saldo do Jogador "+jogador.getPieceColor(),"DEBUG Saldo");
						try
						{							
							result = Integer.parseInt(input);
						}catch(NumberFormatException event) {
							result=-9999;
						}
					}while (result<=-9999);

					jogador.setDinheiro(result);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			};
			return funds;
		}
		else if (s == "CompraCasa")
		{
			MouseListener compra = new MouseListener() {				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub


					//VERMELHO = jogadores.get(jogadores.size()-1);
					//VERDE = jogadores.get(jogadores.size()-2);
					//AMARELO = jogadores.get(jogadores.size()-3);
					//AZUL = jogadores.get(jogadores.size()-4);
					//BRANCO = jogadores.get(jogadores.size()-5);
					//PRETO = jogadores.get(jogadores.size()-6);

					String input = "a";
					int result=-1;
					do
					{
						input = (String)JOptionPane.showInputDialog(null,"Qual resultado gostaria de inserir","DEBUG Resultado");
						try
						{							
							result = Integer.parseInt(input);
						}catch(NumberFormatException event) {
							result=-1;
						}
					}while (result<0 || result >31);

					if(casas[result] instanceof Servico)
					{
						casas[result].AtivaCasa(jogador);
						((Servico)casas[result]).proprietario = jogador;
					}
					else 
					{

						String[] options = {"OK"};
						JPanel panel = new JPanel();
						JLabel lbl = new JLabel("Casa não é instanceof Serviço");
						panel.add(lbl);
						int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			};
			return compra;
		}
		else
			return null;
	}

}
