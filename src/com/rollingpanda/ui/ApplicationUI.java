package com.rollingpanda.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.rollingpanda.utils.PomocnaKlasa;

public class ApplicationUI {

	private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/studentskasluzba?useSSL=false", 
					"root", 
					"root");
		} catch (Exception ex) {
			System.out.println("Neuspela konekcija na bazu!");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String[] args)  {
		int odluka = -1;
		while (odluka != 0) {
			ApplicationUI.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				StudentUI.menu();
				break;
			case 2:
				NastavnikUI.menu();
				break;
			case 3:
				PredmetUI.menu();
				break;
			case 4:
				IspitniRokUI.menu();
				break;
			case 5:
				PohadjaUI.menu();
				break;
			case 6:
				PredajeUI.menu();
				break;
			case 7:
				IspitnaPrijavaUI.menu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void ispisiMenu() {
		System.out.println("Studentska Sluzba - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Rad sa studentima");
		System.out.println("\tOpcija broj 2 - Rad sa nastavnicima");
		System.out.println("\tOpcija broj 3 - Rad sa predmetima");
		System.out.println("\tOpcija broj 4 - Rad sa ispitnim rokovima");
		System.out.println("\tOpcija broj 5 - Rad sa pohadjanjem predmeta");
		System.out.println("\tOpcija broj 6 - Rad sa predavanjem nastavnika");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama studenta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

	public static Connection getConn() {
		return conn;
	}
	
}
