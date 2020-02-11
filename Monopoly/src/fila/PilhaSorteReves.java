package fila;

import java.util.LinkedList;
import java.util.List;

import monopoly.SorteOuReves;

public class PilhaSorteReves {
	private LinkedList<SorteOuReves> pilhaCartas = new LinkedList<SorteOuReves>();
	
	public void insere(SorteOuReves carta) {
		this.pilhaCartas.addLast(carta);
	}
	
	public SorteOuReves remove() {
		if(vazia())
			return null;
		else
			return pilhaCartas.removeFirst();
		
	}
	
	public boolean vazia() {
		return this.pilhaCartas.isEmpty();
	}
}
