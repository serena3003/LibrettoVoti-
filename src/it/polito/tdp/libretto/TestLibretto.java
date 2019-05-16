package it.polito.tdp.libretto;

import java.time.LocalDate;
import java.util.List;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib = new Libretto();
		
		lib.add(new Voto(30, "Analisi I", LocalDate.of(2017, 1, 15)));
		lib.add(new Voto(21, "Analisi II", LocalDate.of(2018, 1, 25)));
		lib.add(new Voto(25, "Fisica I", LocalDate.of(2017, 6, 10)));
		lib.add(new Voto(28, "Fisica II", LocalDate.of(2018, 9, 3)));
		lib.add(new Voto(18, "Geometria", LocalDate.of(2017, 9, 1)));
		lib.add(new Voto(20, "Economia", LocalDate.of(2018, 1, 28)));
		lib.add(new Voto(25, "Ricerca Operativa", LocalDate.of(2018, 6, 5)));
		lib.add(new Voto(24, "Complementi di Economia", LocalDate.of(2018, 2, 15)));
		lib.add(new Voto(25, "Logistica", LocalDate.of(2019, 2, 1)));
		lib.add(new Voto(27, "Programmazione a Oggetti", LocalDate.of(2019, 1, 25)));
		
		List<Voto> venticinque = lib.cercaVoti(25);
		System.out.println(venticinque);
		
		Voto a1 = lib.cercaEsame("Analisi I");
		Voto a3 = lib.cercaEsame("Analisi III");
		System.out.println(a1);
		System.out.println(a3);
		
		Voto giusto = new Voto(18, "Geometria", LocalDate.now());
		Voto falso = new Voto (28, "Geometria", LocalDate.of(2017, 9, 1));
		Voto mancante = new Voto(30, "Merendine", LocalDate.now());
		System.out.format("Il voto %s è %s\n", giusto.toString(),lib.esisteGiaVoto(giusto));
		System.out.format("Il voto %s è %s\n", falso.toString(),lib.esisteGiaVoto(falso));
		System.out.format("Il voto %s è %s\n", mancante.toString(),lib.esisteGiaVoto(mancante));
		
		System.out.format("Il voto %s è in conflitto %s\n", giusto.toString(),lib.votoConflitto(giusto));
		System.out.format("Il voto %s è in conflitto %s\n", falso.toString(),lib.votoConflitto(falso));
		System.out.format("Il voto %s è in conflitto %s\n", mancante.toString(),lib.votoConflitto(mancante));
		
		System.out.println(lib.add(giusto));
		System.out.println(lib.add(falso));
		System.out.println(lib.add(mancante));
		
		System.out.println(lib.toString());
		
		System.out.println("***Migliora il libretto***");
		Libretto migliore = lib.librettoMigliorato();
		System.out.println(migliore.toString());
		
		migliore.cancellaVotiScarsi();
		System.out.println(migliore.toString());
}

}