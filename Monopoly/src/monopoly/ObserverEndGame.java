package monopoly;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ObserverEndGame implements Observer {
	ObservableInt qtdJogadores;
	
	public ObserverEndGame(ObservableInt jogadores) {
		qtdJogadores = jogadores;
	}
	@Override
	public void update(Observable jogadores, Object arg1) {
		if (jogadores==qtdJogadores)
		{
			if (qtdJogadores.getInt() <= 1)
			{
				String[] options = {"OK"};
				JPanel panel = new JPanel();
				JLabel lbl = new JLabel("Parabens você é o vencedor");
				panel.add(lbl);
				int selectedOption = JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
			}
		}
	}

}
