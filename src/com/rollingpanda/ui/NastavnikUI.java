package com.rollingpanda.ui;

import java.util.List;

import com.rollingpanda.dao.NastavnikDAO;
import com.rollingpanda.model.Nastavnik;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.utils.PomocnaKlasa;

public class NastavnikUI {

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
				ispisiSveNastavnike();
				break;
			case 2:
				unosNovogNastavnika();
				break;
			case 3:
				izmenaPodatakaONastavniku();
				break;
			case 4:
				brisanjePodatakaONastavniku();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa nastavnicima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Nastavnika");
		System.out.println("\tOpcija broj 2 - unos novog Nastavnika");
		System.out.println("\tOpcija broj 3 - izmena Nastavnika");
		System.out.println("\tOpcija broj 4 - brisanje Nastavnika");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}


	public static void ispisiSveNastavnike() {
		List<Nastavnik> sviNastavnici = NastavnikDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-5s %-20s %-20s %-20s %-20s", 
				"id", 
				"ime", 
				"prezime", 
				"zvanje", 
				"predmeti"); System.out.println();
		System.out.println("===== ==================== ==================== ==================== ====================");
		for (Nastavnik itNastavnik: sviNastavnici) {
			System.out.printf("%-5s %-20s %-20s %-20s", 
					itNastavnik.getId(), 
					itNastavnik.getIme(), 
					itNastavnik.getPrezime(), 
					itNastavnik.getZvanje()); System.out.println();
			for (Predmet itPredmet: itNastavnik.getPredmeti()) {
				System.out.printf("%-5s %-20s %-20s %-20s %-20s", 
						"", "", "", "", 
						itPredmet.getNaziv()); System.out.println();
			}
			System.out.println("----- -------------------- -------------------- -------------------- --------------------");
		}
	}

	public static Nastavnik pronadjiNastavnika() {
		Nastavnik retVal = null;
		System.out.print("Unesi id nastavnika:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = NastavnikDAO.getNastavnikByID(ApplicationUI.getConn(), id);
		if (retVal == null)
			System.out.println("Nastavnik sa ID-em " + id
					+ " ne postoji u evidenciji");
		return retVal;
	}

	public static void unosNovogNastavnika() {
		System.out.print("Unesi ime:");
		String stIme = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String stPrezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi zvanje:");
		String stZvanje = PomocnaKlasa.ocitajTekst();

		Nastavnik nast = new Nastavnik(0, stIme, stPrezime, stZvanje);
		NastavnikDAO.add(ApplicationUI.getConn(), nast);
	}


	public static void izmenaPodatakaONastavniku() {
		Nastavnik nast = pronadjiNastavnika();
		if (nast != null) {
			System.out.print("Unesi ime:");
			String stIme = PomocnaKlasa.ocitajTekst();
			nast.setIme(stIme);

			System.out.print("Unesi prezime:");
			String stPrezime = PomocnaKlasa.ocitajTekst();
			nast.setPrezime(stPrezime);
	
			System.out.print("Unesi zvanje:");
			String stZvanje = PomocnaKlasa.ocitajTekst();
			nast.setZvanje(stZvanje);

			NastavnikDAO.update(ApplicationUI.getConn(), nast);
		}
	}

	public static void brisanjePodatakaONastavniku() {
		Nastavnik nast = pronadjiNastavnika();
		if (nast != null) {
			NastavnikDAO.delete(ApplicationUI.getConn(), nast.getId());
		}
	}

}
