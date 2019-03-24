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
	
	/**
	 * Seleziona il sottoinsieme di voti che hanno il punteggio specificato
	 * 
	 * @param punti punteggio da ricercare
	 * @return lista di {@link Voto} aventi quel punteggio (eventualmente vuota)
	 */
	public List<Voto> CercaVoti(int punti) {
		List<Voto> result = new ArrayList<>();
		for (Voto v: this.voti){
			if(v.getPunti()==punti) {
				result.add(v);
			}
		}
		return result; 
	}

}
