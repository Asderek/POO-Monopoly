package monopoly;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class ObserverLabel implements Observer{

	private ObservableInt ov = null;
	JLabel saldo;
	String s;
	
	public ObserverLabel(ObservableInt dinheiro, JLabel saldo) {
		this.ov = dinheiro;
		this.saldo = saldo;
		s = saldo.getText();
		
	}
	
	@Override
	public void update(Observable dinheiro, Object obj) {
		if (dinheiro == ov)
		{
			//saldo.setText("Saldo: "+((ObservableInt) dinheiro).getDinheiro());
			saldo.setText(s+dinheiro);
		}

		
	}
	

}
