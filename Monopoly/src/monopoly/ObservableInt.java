package monopoly;

import java.util.Observable;

public class ObservableInt extends Observable {
	int dinheiro;
	
	public ObservableInt(int din) {
		this.dinheiro = din;
	}
	
	public void alteraDinheiro(int din)
	{
		this.dinheiro+=din;
		setChanged();
		notifyObservers();
		
	}
	
	public void setDinheiro(int din)
	{
		this.dinheiro=din;
		setChanged();
		notifyObservers();
		
	}
	
	public int getInt()
	{
		return dinheiro;
	}
	
	@Override
	public String toString() {
	
		return ""+dinheiro;
	}
	
}
