package monopoly.posicoes;

import java.awt.Point;
import java.util.Map;

import javax.swing.JOptionPane;

import monopoly.FrameCasa;
import monopoly.Peca;

public class Local extends Servico{

	int NumCasas=0;
	boolean Hotel=false;
	private String cor;
	int precoImovel;

	public Local(Point up, Point low, String s, int taxa, int precoCompra, int precoImovel ,String cor) {
		super(up, low, taxa, s, precoCompra, null, null);
		this.setCor(cor);
		this.precoImovel = precoImovel;
	}

	@Override
	protected void AtivaVisitante(Peca jogador, FrameCasa a) {
		int debito = 0;
		if(Hotel==true) {
			debito+=taxa*10;
		}
		debito += taxa*NumCasas;
		debito += taxa;
		

		proprietario.alterarFundos(debito);
		jogador.alterarFundos(-debito);

		a.dispose();
	}

	@Override
	protected void AtivaProprietario() {
		Map<String,Integer> mapaCasas = proprietario.getMapaCasas();
		int qtdCasa = mapaCasas.get(this.getCor());
		switch (getCor()) {
		case "Vermelho":
			if(qtdCasa==4)
				comprarImovel();
			break;
		case "Verde":
			if(qtdCasa==2)
				comprarImovel();
			break;
		case "Roxo":
			if(qtdCasa==3)
				comprarImovel();
			break;
		case "Laranja":
			if(qtdCasa==2)
				comprarImovel();
			break;
		case "Amarelo":
			if(qtdCasa==3)
				comprarImovel();
			break;
		case "Azul":
			if(qtdCasa==2)
				comprarImovel();
			break;


		default:
			break;
		}
	}

	@Override
	public void AtivaCasa(Peca jogador) {
		FrameCasa a = new FrameCasa(this.getName());
		a.setVisible(true);
		if(proprietario == null) {
			super.AtivaVisitante(jogador,a );
			if(proprietario!=null)
			{
				Map<String,Integer> mapaCasas = proprietario.getMapaCasas();
				mapaCasas.put(getCor(), mapaCasas.get(getCor())+1);
			}
		}
		else if (jogador.getPieceColor() != proprietario.getPieceColor())
		{
			AtivaVisitante(jogador,a );
		}
		else
		{
			AtivaProprietario();
		}
	}

	private void comprarImovel() {
		int escolha;
		
		if(NumCasas<4) {
			if(proprietario.getDinheiro()>precoImovel) {
				escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de comprar um imovel?","Compra",JOptionPane.YES_NO_OPTION);
				if (escolha==JOptionPane.YES_OPTION)
				{
					NumCasas++;
					proprietario.alterarFundos(-precoImovel);
				}
			}
		}
		else if (Hotel==false){
			if(proprietario.getDinheiro()>precoImovel) {
				escolha = JOptionPane.showConfirmDialog(null, "Voce gostaria de comprar um hotel?","Compra",JOptionPane.YES_NO_OPTION);
				if (escolha==JOptionPane.YES_OPTION)
				{
					proprietario.alterarFundos(-precoImovel);
					Hotel = true;
				}
			}
		}
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public void DestroyProperties(String s)
	{
		if (s=="all")
		{
			Hotel=false;
			NumCasas=0;
		}
		if (s!="all")
		{
			if (Hotel==true)
				Hotel=false;
			else
			{
				if (NumCasas>=1)
				{
					NumCasas--;
				}
			}
		}
	}

}



