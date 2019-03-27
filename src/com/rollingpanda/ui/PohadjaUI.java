package com.rollingpanda.ui;

import java.util.List;

import com.rollingpanda.dao.PohadjaDAO;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;
import com.rollingpanda.utils.PomocnaKlasa;

public class PohadjaUI {
	private static void ispisiMenu() {
		System.out.println("Rad sa predmetima studenta - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje student pohadja");
		System.out.println("\tOpcija broj 2 - studenti koji pohadjaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje studenta na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje studenta sa predmeta");
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
				ispisiPredmeteZaStudenta();
				break;
			case 2:
				ispisiStudenteZaPredmet();
				break;
			case 3:
				dodajStudentaNaPredmet();
				break;
			case 4:
				ukloniStudentaSaPredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void ispisiPredmeteZaStudenta() {

		Student student = StudentUI.pronadjiStudenta();
		if (student != null) {
			List<Predmet> predmeti = PohadjaDAO.getPredmetiByStudentID(
					ApplicationUI.getConn(), student.getId());

			System.out.println();
			System.out.printf("%-10s %-20s",
					"id",
					"naziv"); System.out.println();
			System.out.println("========== ====================");
			for (Predmet itPredmet: predmeti) {
				System.out.printf("%-10s %-20s", 
						itPredmet.getId(),
						itPredmet.getNaziv()); System.out.println();
			}
		}
	}
	
	private static void ispisiStudenteZaPredmet() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if (predmet != null) {
			List<Student> studenti = PohadjaDAO.getStudentiByPredmetID(
					ApplicationUI.getConn(), predmet.getId());
	
			System.out.println();
			System.out.printf("%-10s %-20s %-20s %-20s", 
					"indeks", 
					"ime", 
					"prezime", 
					"grad"); System.out.println();
			System.out.println("========== ==================== ==================== ====================");
			for (Student itStudent: studenti) {
				System.out.printf("%-10s %-20s %-20s %-20s", 
						itStudent.getIndeks(), 
						itStudent.getIme(), 
						itStudent.getPrezime(), 
						itStudent.getGrad()); System.out.println();
			}
		}
	}

	private static void dodajStudentaNaPredmet() {

		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if (student != null && predmet != null) {
			PohadjaDAO.add(ApplicationUI.getConn(), student.getId(), predmet.getId());
		}
	}
	
	private static void ukloniStudentaSaPredmeta() {

		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if (student != null && predmet != null) {
			PohadjaDAO.delete(ApplicationUI.getConn(), student.getId(), predmet.getId());
		}
	}
}
