package com.rollingpanda.ui;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.rollingpanda.dao.IspitniRokDAO;
import com.rollingpanda.model.IspitniRok;
import com.rollingpanda.utils.PomocnaKlasa;

public class IspitniRokUI {

	public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy.");
	
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
				ispisiSveIspitneRokove();
				break;
			case 2:
				unosNovogIspitnogRoka();
				break;
			case 3:
				izmenaPodatakaOIspitnomRoku();
				break;
			case 4:
				brisanjePodatakaOIspitnomRoku();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	public static void ispisiMenu() {
		System.out.println("Rad sa ispitnim rokovima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Ispitnih rokova");
		System.out.println("\tOpcija broj 2 - unos novog Ispitnog roka");
		System.out.println("\tOpcija broj 3 - izmena Ispitnog Roka");
		System.out.println("\tOpcija broj 4 - brisanje Ispitnog Roka");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	public static void ispisiSveIspitneRokove() {
		List<IspitniRok> sviIspitniRokovi = IspitniRokDAO.getAll(ApplicationUI.getConn());

		System.out.println();
		System.out.printf("%-10s %-20s %-15s %-15s", 
				"id", 
				"naziv", 
				"pocetak", 
				"kraj"); System.out.println();
		System.out.println("========== ==================== =============== ===============");
		for (IspitniRok itIspitniRok: sviIspitniRokovi) {
			System.out.printf("%-10s %-20s %-15s %-15s", 
					itIspitniRok.getId(), 
					itIspitniRok.getNaziv(), 
					DATE_FORMAT.format(itIspitniRok.getPocetak()), 
					DATE_FORMAT.format(itIspitniRok.getKraj())); System.out.println();
			System.out.println("---------- -------------------- --------------- ---------------");
		}
	}
	

	public static IspitniRok pronadjiIspitniRok() {
		IspitniRok retVal = null;
		System.out.print("Unesi id ispitnog roka:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = IspitniRokDAO.getIspitniRokByID(ApplicationUI.getConn(), id);
		if (retVal == null)
			System.out.println("Ispitni rok sa ID-em " + id
					+ " ne postoji u evidenciji");
		return retVal;
	}
	

	public static void unosNovogIspitnogRoka() {
		System.out.print("Naziv:");
		String naziv = PomocnaKlasa.ocitajTekst();	
		try {
			System.out.print("Pocetak (dd.MM.yyyy.): ");
			Date pocetak = new Date(DATE_FORMAT.parse(PomocnaKlasa.ocitajTekst()).getTime());
			System.out.print("Kraj (dd.MM.yyyy.): ");
			Date kraj = new Date(DATE_FORMAT.parse(PomocnaKlasa.ocitajTekst()).getTime());

			IspitniRok ispRok = new IspitniRok(0, naziv, pocetak, kraj);
			IspitniRokDAO.add(ApplicationUI.getConn(), ispRok);
		} catch (ParseException e) {
			System.out.println("Greska u formatu!");
		}
	}

	public static void izmenaPodatakaOIspitnomRoku() {
		IspitniRok ispRok = pronadjiIspitniRok();
		if (ispRok != null){
			System.out.print("Unesi novi naziv:");
			String naziv = PomocnaKlasa.ocitajTekst();
			try {
				System.out.print("Unesi novi pocetak (dd.MM.yyyy.): ");
				Date pocetak = new Date(DATE_FORMAT.parse(PomocnaKlasa.ocitajTekst()).getTime());
				System.out.print("Unesi novi kraj (dd.MM.yyyy.): ");
				Date kraj = new Date(DATE_FORMAT.parse(PomocnaKlasa.ocitajTekst()).getTime());

				ispRok.setNaziv(naziv);
				ispRok.setPocetak(pocetak);
				ispRok.setKraj(kraj);
				IspitniRokDAO.update(ApplicationUI.getConn(), ispRok);
			} catch (ParseException e) {
				System.out.println("Greska u formatu!");
			}
		}
	}

	public static void brisanjePodatakaOIspitnomRoku(){
		IspitniRok ispRok = pronadjiIspitniRok();
		if (ispRok != null){
			IspitniRokDAO.delete(ApplicationUI.getConn(), ispRok.getId());
		}
	}	
}
