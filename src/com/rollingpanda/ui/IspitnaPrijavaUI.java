package com.rollingpanda.ui;

import com.rollingpanda.dao.IspitnaPrijavaDAO;
import com.rollingpanda.model.IspitnaPrijava;
import com.rollingpanda.model.IspitniRok;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;
import com.rollingpanda.utils.PomocnaKlasa;

public class IspitnaPrijavaUI {
	private static void ispisiMenu() {
		System.out.println("Rad sa ispitnim prijavama studenta - opcije:");
		System.out.println("\tOpcija broj 1 - ispitne prijave studenta");
		System.out.println("\tOpcija broj 2 - ispitne prijave za predmet");
		System.out.println("\tOpcija broj 3 - ispitne prijave u roku");
		System.out.println("\tOpcija broj 4 - dodavanje ispitne prijave");
		System.out.println("\tOpcija broj 5 - izmena ispitne prijave");
		System.out.println("\tOpcija broj 6 - uklanjanje ispitne prijave");
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
				ispisiIspitnePrijaveZaStudenta();
				break;
			case 2:
				ispisiIspitnePrijaveZaPredmet();
				break;
			case 3:
				ispisiIspitnePrijaveZaRok();
				break;
			case 4:
				dodajIspitnuPrijavu();
				break;
			case 5:
				izmeniIspitnuPrijavu();
				break;
			case 6:
				ukloniIspitnuPrijavu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void ispisiIspitnePrijaveZaStudenta() {
		Student student = StudentUI.pronadjiStudenta();
		if (student != null) {
			System.out.println();
			System.out.printf("%-10s %-20s %-10s %-20s %-10s %-10s %-10s %-10s", 
					"predmet", 
					"", 
					"rok", 
					"", 
					"", 
					"", 
					"teorija", 
					"zadaci"); System.out.println();
			System.out.println("========== ==================== ========== ==================== ========== ========== ========== ==========");
			for (IspitnaPrijava itIspPrijava: IspitnaPrijavaDAO.getPrijaveByStudent(ApplicationUI.getConn(), student)) {
				System.out.printf("%-10s %-20s %-10s %-20s %-10s %-10s %-10s %-10s", 
						itIspPrijava.getPredmet().getId(), 
						itIspPrijava.getPredmet().getNaziv(), 
						itIspPrijava.getRok().getId(), 
						itIspPrijava.getRok().getNaziv(), 
						IspitniRokUI.DATE_FORMAT.format(itIspPrijava.getRok().getPocetak()), 
						IspitniRokUI.DATE_FORMAT.format(itIspPrijava.getRok().getKraj()), 
						itIspPrijava.getTeorija(), 
						itIspPrijava.getZadaci()); System.out.println();
			}
			System.out.println("---------- -------------------- ---------- -------------------- ---------- ---------- ---------- ----------");
		}
	}
	
	private static void ispisiIspitnePrijaveZaPredmet() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if (predmet != null) {
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-10s %-20s %-10s %-10s %-10s %-10s", 
					"student", 
					"", 
					"", 
					"rok", 
					"", 
					"", 
					"", 
					"teorija", 
					"zadaci"); System.out.println();
			System.out.println("========== ==================== ==================== ========== ==================== ========== ========== ========== ==========");
			for (IspitnaPrijava itIspPrijava: IspitnaPrijavaDAO.getPrijaveByPredmet(ApplicationUI.getConn(), predmet)) {
				System.out.printf("%-10s %-20s %-20s %-10s %-20s %-10s %-10s %-10s %-10s", 
						itIspPrijava.getStudent().getIndeks(), 
						itIspPrijava.getStudent().getIme(), 
						itIspPrijava.getStudent().getPrezime(), 
						itIspPrijava.getRok().getId(), 
						itIspPrijava.getRok().getNaziv(), 
						IspitniRokUI.DATE_FORMAT.format(itIspPrijava.getRok().getPocetak()), 
						IspitniRokUI.DATE_FORMAT.format(itIspPrijava.getRok().getKraj()), 
						itIspPrijava.getTeorija(), 
						itIspPrijava.getZadaci()); System.out.println();
			}
			System.out.println("---------- -------------------- -------------------- ---------- -------------------- ---------- ---------- ---------- ----------");
		}
	}

	private static void ispisiIspitnePrijaveZaRok() {
		IspitniRok ispRok = IspitniRokUI.pronadjiIspitniRok();
		if (ispRok != null) {
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-10s %-20s %-10s %-10s", 
					"student", 
					"", 
					"", 
					"predmet", 
					"", 
					"teorija", 
					"zadaci"); System.out.println();
			System.out.println("========== ==================== ==================== ========== ==================== ========== ==========");
			for (IspitnaPrijava itIspPrijava: IspitnaPrijavaDAO.getPrijaveByIspRok(ApplicationUI.getConn(), ispRok)) {
				System.out.printf("%-10s %-20s %-20s %-10s %-20s %-10s %-10s", 
						itIspPrijava.getStudent().getIndeks(), 
						itIspPrijava.getStudent().getIme(), 
						itIspPrijava.getStudent().getPrezime(), 
						itIspPrijava.getPredmet().getId(), 
						itIspPrijava.getPredmet().getNaziv(), 
						itIspPrijava.getTeorija(), 
						itIspPrijava.getZadaci()); System.out.println();
			}
			System.out.println("---------- -------------------- -------------------- ---------- -------------------- ---------- ----------");
		}
	}
	
	private static void dodajIspitnuPrijavu() {
		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		IspitniRok ispRok = IspitniRokUI.pronadjiIspitniRok();

		System.out.println();
		System.out.println("Bodovi sa teorije: ");
		int teorija = PomocnaKlasa.ocitajCeoBroj();
		System.out.println("Bodovi za zadatke: ");
		int zadaci = PomocnaKlasa.ocitajCeoBroj();
		
		if (student != null && predmet != null && ispRok != null) {
			IspitnaPrijavaDAO.add(ApplicationUI.getConn(), student.getId(), predmet.getId(), ispRok.getId(), teorija, zadaci);
		}
	}

	private static void izmeniIspitnuPrijavu() {
		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		IspitniRok ispRok = IspitniRokUI.pronadjiIspitniRok();

		System.out.println();
		System.out.println("Novi bodovi sa teorije: ");
		int teorija = PomocnaKlasa.ocitajCeoBroj();
		System.out.println("Novi bodovi za zadatke: ");
		int zadaci = PomocnaKlasa.ocitajCeoBroj();
		
		if (student != null && predmet != null && ispRok != null) {
			IspitnaPrijavaDAO.update(ApplicationUI.getConn(), student.getId(), predmet.getId(), ispRok.getId(), teorija, zadaci);
		}
	}

	private static void ukloniIspitnuPrijavu() {
		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		IspitniRok ispRok = IspitniRokUI.pronadjiIspitniRok();
		
		if (student != null && predmet != null && ispRok != null) {
			IspitnaPrijavaDAO.delete(ApplicationUI.getConn(), student.getId(), predmet.getId(), ispRok.getId());
		}
	}

}
