package com.rollingpanda.ui;

import java.util.List;

import com.rollingpanda.dao.StudentDAO;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;
import com.rollingpanda.utils.PomocnaKlasa;


public class StudentUI {

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
				ispisiSveStudente();
				break;
			case 2:
				unosNovogStudenta();
				break;
			case 3:
				izmenaPodatakaOStudentu();
				break;
			case 4:
				brisanjePodatakaOStudentu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiMenu() {
		System.out.println("Rad sa studentima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Studenata");
		System.out.println("\tOpcija broj 2 - unos novog Studenta");
		System.out.println("\tOpcija broj 3 - izmena Studenta");
		System.out.println("\tOpcija broj 4 - brisanje Studenta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}


	public static void ispisiSveStudente() {
		List<Student> sviStudenti = StudentDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
				"indeks", 
				"ime", 
				"prezime", 
				"grad", 
				"predmeti"); System.out.println();
		System.out.println("========== ==================== ==================== ==================== ====================");
		for (Student itStudent: sviStudenti) {
			System.out.printf("%-10s %-20s %-20s %-20s", 
					itStudent.getIndeks(), 
					itStudent.getIme(), 
					itStudent.getPrezime(), 
					itStudent.getGrad()); System.out.println();
			for (Predmet itPredmet: itStudent.getPredmeti()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s", 
						"", "", "", "", 
						itPredmet.getNaziv()); System.out.println();
			}
			System.out.println("---------- -------------------- -------------------- -------------------- --------------------");
		}
	}

	public static Student pronadjiStudenta() {
		Student retVal = null;
		System.out.print("Unesi indeks studenta:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		retVal = pronadjiStudenta(stIndex);
		if (retVal == null)
			System.out.println("Student sa indeksom " + stIndex
					+ " ne postoji u evidenciji");
		return retVal;
	}

	public static Student pronadjiStudenta(String stIndex) {
		Student retVal = StudentDAO.getStudentByIndeks(ApplicationUI.getConn(),
				stIndex);
		return retVal;
	}

	public static void unosNovogStudenta() {
		System.out.print("Unesi indeks:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		stIndex = stIndex.toUpperCase();
		while (pronadjiStudenta(stIndex) != null) {
			System.out.println("Student sa indeksom " + stIndex
					+ " vec postoji");
			stIndex = PomocnaKlasa.ocitajTekst();
		}
		System.out.print("Unesi ime:");
		String stIme = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String stPrezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi grad:");
		String stGrad = PomocnaKlasa.ocitajTekst();

		Student st = new Student(0, stIndex, stIme, stPrezime, stGrad);
		StudentDAO.add(ApplicationUI.getConn(), st);
	}

	public static void izmenaPodatakaOStudentu() {
		Student st = pronadjiStudenta();
		if (st != null) {
			System.out.print("Unesi novi indeks :");
			String stIndeks = PomocnaKlasa.ocitajTekst();
			st.setIndeks(stIndeks);

			System.out.print("Unesi ime :");
			String stIme = PomocnaKlasa.ocitajTekst();
			st.setIme(stIme);

			System.out.print("Unesi prezime:");
			String stPrezime = PomocnaKlasa.ocitajTekst();
			st.setPrezime(stPrezime);
	
			System.out.print("Unesi grad:");
			String stGrad = PomocnaKlasa.ocitajTekst();
			st.setGrad(stGrad);

			StudentDAO.update(ApplicationUI.getConn(), st);
		}
	}

	public static void brisanjePodatakaOStudentu() {
		Student st = pronadjiStudenta();
		if (st != null) {
			StudentDAO.delete(ApplicationUI.getConn(), st.getId());
		}
	}

}
