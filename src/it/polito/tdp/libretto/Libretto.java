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
	 * 
	 * @param v {@link Voto} da aggiungere
	 * @return {@code false} se non è riuscito ad aggiungere il voto;
	 * 			{@code true} altrimenti.
	 */
	public boolean add(Voto v) {
		if (!this.esisteGiaVoto(v) && !this.votoConflitto(v)) {
			voti.add(v);// PATTERN DELEGATION
			return true;
		} else 
			return false;
	}

	/**
	 * Seleziona il sottoinsieme di voti che hanno il punteggio specificato
	 * 
	 * @param punti punteggio da ricercare
	 * @return lista di {@link Voto} aventi quel punteggio (eventualmente vuota)
	 */
	public List<Voto> cercaVoti(int punti) {
		List<Voto> result = new ArrayList<>();
		for (Voto v : this.voti) {
			if (v.getPunti() == punti) {
				result.add(v);
			}
		}
		return result;
	}

	/**
	 * Ricerca un {@link Voto} relativo al corso di cui è specificato il nome
	 * 
	 * @param nomeEsame nome del corso da ricercare
	 * @return il {@link Voto} corrispondente oppure {@code null} se non esistente
	 */
	public Voto cercaEsame(String nomeEsame) {
		/*
		 * for(Voto v : this.voti) { if(v.getCorso().equals(nomeEsame)) { return v; } }
		 * return null;
		 */
		Voto voto = new Voto(1, nomeEsame, null); // costruisco un oggetto voto incompleto che mi serve solo come
													// criterio di ricerca

		int pos = this.voti.indexOf(voto); // si è definito il metodo equals quindi quando vado a cercare la posizione
											// di nomeEsame, il confronto
											// è fatto sui nomi del corso (come specificato in equals) e non sugli altri
											// attributi
		if (pos == -1)
			return null;
		else
			return this.voti.get(pos);
	}

	/**
	 * Dato un {@link Voto} determina se esiste già un voto con uguale corso e
	 * punteggio.
	 * 
	 * @param v
	 * @return {@code true} se ha trovato un corso e punteggio uguali; {@code false}
	 *         se non ha trovato il corso oppure l'ha trovato con voto diverso
	 */
	public boolean esisteGiaVoto(Voto v) {
		int pos = this.voti.indexOf(v);
		if (pos == -1)
			return false;
		else {
			return v.getPunti() == this.voti.get(pos).getPunti();
		}

		/*
		 * Voto trovato = this.cercaEsame(v.getCorso()); if(trovato == null) return
		 * false; if(trovato.getPunti()==v.getPunti()) { return true; } else { return
		 * false; }
		 */
	}

	/**
	 * Dice se il {@link Voto} {@code v} è in conflitto con uno dei voti esistenti.
	 * Se il voto non esiste, non c'è conflitto. Se esiste e ha un punteggio
	 * diverso, c'è conflitto.
	 * 
	 * @param v
	 * @return {@code true} se c'è conflitto - esame esiste ed ha un punteggio
	 *         diverso; {@code false} se non c'è conflitto - il voto non esiste
	 *         oppure se esiste ma ha lo stesso punteggio.
	 */
	public boolean votoConflitto(Voto v) {
		int pos = this.voti.indexOf(v);
		if (pos == -1)
			return false;
		else {
			return v.getPunti() != this.voti.get(pos).getPunti();
		}
	}
	
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto librettoMigliorato() {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) { //aggiungo gli stessi voti del primo libretto
//			nuovo.add(v); 	copia i riferimenti agli oggetti precedenti e li mette nella nuova lista
//							quando modifico uno si modifica anche l'altro - sono proprio lo stesso oggetto!
//							quello che voglio io è una copia indipendente - devo fare una NEW con gli stessi attributi del primo oggetto
			nuovo.add(v.clone()); //creo un clone dell'oggetto precedente
		}
		for (Voto v : nuovo.voti) {
			int punti = v.getPunti();
			if(punti<24) {
				punti = punti +1;
			} 
			else if(punti <=28) {
				punti=punti+2;
			}
			v.setPunti(punti);
		}
		return nuovo;
	}
	
	public void cancellaVotiScarsi() {
		/*for(Voto v : this.voti) {  PROBLEMA di questo codice: non posso modificare una lista mentre la sto scorrendo
			if(v.getPunti()<24) {
				this.voti.remove(v); NON posso cancellare l'elemento su cui sto iterando la lista
			}
		}*/
		
		List cancellare = new ArrayList<Voto>();
		for(Voto v : this.voti) {
			if(v.getPunti()<24) {
				cancellare.add(v);
			}
		}
		this.voti.removeAll(cancellare);
	}

}
