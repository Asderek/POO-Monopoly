package monopoly;

public class SorteOuReves 
{
	private int efeito;
	private boolean passeprisao = false;
	private boolean irprisao = false;
	private boolean recebeOutros = false;
	private boolean irPartida = false;
	private String image;
	
	public SorteOuReves(int i, String image)
	{
		this.efeito = i;
		this.image = image;
	}

	public int getEfeito() {
		return efeito;
	}

	public void setEfeito(int efeito) {
		this.efeito = efeito;
	}

	public boolean isPasse() {
		return passeprisao;
	}

	public void setPasse() {
		this.passeprisao = true;
	}

	public boolean isIrPrisao() {
		return irprisao;
	}

	public void setIrPrisao() {
		this.irprisao = true;
	}

	public boolean isRecebeOutros() {
		return recebeOutros;
	}

	public void setRecebeOutros() {
		this.recebeOutros = true;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isIrPartida() {
		return irPartida;
	}

	public void setIrPartida() {
		this.irPartida = true;
	}
	
}
	