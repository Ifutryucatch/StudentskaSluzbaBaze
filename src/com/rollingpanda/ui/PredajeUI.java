package com.rollingpanda.ui;

import com.rollingpanda.dao.PredajeDAO;
import com.rollingpanda.model.Nastavnik;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.utils.PomocnaKlasa;

public class PredajeUI {
	private static void ispisiMenu() {
		System.out.println("Rad sa predavanjima nastavnika - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje nastavnik predaje");
		System.out.println("\tOpcija broj 2 - nastavnici koji predaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje nastavnika na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje nastavnika sa predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}

	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiPredmeteZaNastavnika();
				break;
			case 2:
				ispisiNastavnikeZaPredmet();
				break;
			case 3:
				dodajNastavnikaNaPredmet();
				break;
			case 4:
				ukloniNastavnikaSaPredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void ispisiPredmeteZaNastavnika() {
		Nastavnik nastavnik = NastavnikUI.pronadjiNastavnika();
		if (nastavnik != null) {
			System.out.println();
			System.out.printf("%-10s %-20s", 
					"id", 
					"naziv"); System.out.println();
			System.out.println("========== ====================");
			for (Predmet itPredmet: PredajeDAO.getPredmetiByNastavnkID(ApplicationUI.getConn(), nastavnik.getId())) {
				System.out.printf("%-10s %-20s", 
						itPredmet.getId(), 
						itPredmet.getNaziv()); System.out.println();
			}
			System.out.println("---------- --------------------");
		}
	}
	
	private static void ispisiNastavnikeZaPredmet() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if (predmet != null) {
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-20s", 
					"id", 
					"ime", 
					"prezime", 
					"zvanje"); System.out.println();
			System.out.println("========== ==================== ==================== ====================");
			for (Nastavnik itNastavnik: PredajeDAO.getNastavniciByPredmetID(ApplicationUI.getConn(), predmet.getId())) {
				System.out.printf("%-10s %-20s %-20s %-20s", 
						itNastavnik.getId(), 
						itNastavnik.getIme(), 
						itNastavnik.getPrezime(), 
						itNastavnik.getZvanje()); System.out.println();
			}
		}
	}

	private static void dodajNastavnikaNaPredmet() {
		Nastavnik nastavnik = NastavnikUI.pronadjiNastavnika();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if (nastavnik != null && predmet != null) {
			PredajeDAO.add(ApplicationUI.getConn(), nastavnik.getId(), predmet.getId());
		}
	}
	
	private static void ukloniNastavnikaSaPredmeta() {
		Nastavnik nastavnik = NastavnikUI.pronadjiNastavnika();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if (nastavnik != null && predmet != null) {
			PredajeDAO.delete(ApplicationUI.getConn(), nastavnik.getId(), predmet.getId());
		}
	}
}
