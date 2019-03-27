package com.rollingpanda.ui;

import java.util.List;

import com.rollingpanda.dao.PredmetDAO;
import com.rollingpanda.model.Nastavnik;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;
import com.rollingpanda.utils.PomocnaKlasa;

public class PredmetUI {

	public static void menu(){
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
				ispisiSvePredmete();
				break;
			case 2:
				ispisiSvePredmeteSortiranePoNazivu();
				break;
			case 3:
				unosNovogPredmeta();
				break;
			case 4:
				izmenaPodatakaOPredmetu();
				break;
			case 5:
				brisanjePodatakaOPredmetu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	public static void ispisiMenu() {
		System.out.println("Rad sa predmetima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Predmeta");
		System.out.println("\tOpcija broj 2 - ispis svih Predmeta sortiranih po nazivu");
		System.out.println("\tOpcija broj 3 - unos novog Predmeta");
		System.out.println("\tOpcija broj 4 - izmena naziva Predmeta");
		System.out.println("\tOpcija broj 5 - brisanje Predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	

	public static void ispisiSvePredmete() {
		List<Predmet> sviPredmeti = PredmetDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", 
				"id", 
				"naziv", 
				"studenti", 
				"", 
				"", 
				"nastavnici", 
				"", 
				""); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ==================== ==================== ==================== ====================");
		for (Predmet itPredmet: sviPredmeti) {
			System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
					itPredmet.getId(), 
					itPredmet.getNaziv(), 
					"", 
					"", 
					"",
					"",
					"",
					""); System.out.println();

			for (Student itStudent: itPredmet.getStudenti()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
						"",
						"",
						itStudent.getIndeks(),
						itStudent.getIme(), 
						itStudent.getPrezime()); System.out.println();
			}
			for (Nastavnik itNastavnik: itPredmet.getNastavnici()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", 
						"",
						"",
						"",
						"",
						"",
						itNastavnik.getIme(),
						itNastavnik.getPrezime(), 
						itNastavnik.getZvanje()); System.out.println();
			}
			System.out.println("---------- -------------------- -------------------- -------------------- -------------------- -------------------- -------------------- --------------------");
		}
	}

	public static void ispisiSvePredmeteSortiranePoNazivu() {
		List<Predmet> sviPredmeti = PredmetDAO.getAllSortedByNaziv(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", 
				"id", 
				"naziv", 
				"studenti", 
				"", 
				"", 
				"nastavnici", 
				"", 
				""); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ==================== ==================== ==================== ====================");
		for (Predmet itPredmet: sviPredmeti) {
			System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
					itPredmet.getId(), 
					itPredmet.getNaziv(), 
					"", 
					"", 
					"",
					"",
					"",
					""); System.out.println();

			for (Student itStudent: itPredmet.getStudenti()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
						"",
						"",
						itStudent.getIndeks(),
						itStudent.getIme(), 
						itStudent.getPrezime()); System.out.println();
			}
			for (Nastavnik itNastavnik: itPredmet.getNastavnici()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", 
						"",
						"",
						"",
						"",
						"",
						itNastavnik.getIme(),
						itNastavnik.getPrezime(), 
						itNastavnik.getZvanje()); System.out.println();
			}
			System.out.println("---------- -------------------- -------------------- -------------------- -------------------- -------------------- -------------------- --------------------");
		}
	}
	

	public static Predmet pronadjiPredmet() {
		Predmet retVal = null;
		System.out.print("Unesi id predmeta:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiPredmet(id);
		if (retVal == null)
			System.out.println("Predmet sa id-om " + id
					+ " ne postoji u evidenciji");
		return retVal;
	}


	public static Predmet pronadjiPredmet(int id) {
		Predmet retVal = PredmetDAO.getPredmetByID(ApplicationUI.getConn(), id);
		return retVal;
	}
	

	public static void unosNovogPredmeta() {
		System.out.print("Naziv:");
		String prNaziv= PomocnaKlasa.ocitajTekst();
		
		Predmet pred = new Predmet(0, prNaziv);
		PredmetDAO.add(ApplicationUI.getConn(), pred);
	}
	

	public static void izmenaPodatakaOPredmetu() {
		Predmet pred = pronadjiPredmet();
		if(pred != null){
			System.out.print("Unesi novi naziv :");
			String prNaziv = PomocnaKlasa.ocitajTekst();
			pred.setNaziv(prNaziv);
			PredmetDAO.update(ApplicationUI.getConn(), pred);
		}
	}


	public static void brisanjePodatakaOPredmetu(){
		Predmet pred  = pronadjiPredmet();
		if(pred != null){
			PredmetDAO.delete(ApplicationUI.getConn(), pred.getId());
		}
	}	
}
