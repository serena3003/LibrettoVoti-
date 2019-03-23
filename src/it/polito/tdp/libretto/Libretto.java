package it.polito.tdp.libretto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Libretto {
	
	private List<Voto> voti;
	
	public Libretto() {
		this.voti = new ArrayList<>();
	}
	
	/**
	 * Aggiunge un nuovo voto al libretto, in ordine di inserimento
	 * @param v
	 */
	public void add(Voto v) { //PATTERN DELEGATION
		voti.add(v);
	}

}
