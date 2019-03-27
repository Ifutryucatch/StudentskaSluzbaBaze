package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;

public class PohadjaDAO {

	public static List<Predmet> getPredmetiByStudentID(Connection conn, int studentID) {
		List<Predmet> predmetiKojeStudentPohadja = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT predmet_id FROM pohadja WHERE student_id = " + studentID;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int predmetID = rset.getInt(1);

				Predmet predmet = PredmetDAO.getPredmetByID(conn, predmetID);
				predmetiKojeStudentPohadja.add(predmet);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return predmetiKojeStudentPohadja;
	}
	
	public static List<Student> getStudentiByPredmetID(Connection conn, int id) {
		List<Student> studentiKojiPohadjajuPredmet = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT student_id FROM pohadja WHERE predmet_id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int studentID = rset.getInt(1);

				Student student = StudentDAO.getStudentByID(conn, studentID);
				studentiKojiPohadjajuPredmet.add(student);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return studentiKojiPohadjajuPredmet;
	}
	
	public static boolean add(Connection conn, int studentID, int predmetID){
		Statement stmt = null;
		try {
			String query = "INSERT INTO pohadja (student_id, predmet_id) VALUES (" + studentID + ", " + predmetID + ")";

			stmt = conn.createStatement();
			return stmt.executeUpdate(query) == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	
	public static boolean delete(Connection conn, int studentID, int predmetID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM pohadja WHERE student_id = " + studentID + " AND predmet_id = " + predmetID;

			stmt = conn.createStatement();
			return stmt.executeUpdate(query) == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean deletePohadjanjaStudenta(Connection conn, int studentID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM pohadja WHERE student_id = " + studentID;

			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean deletePohadjanjaPredmeta(Connection conn, int predmetID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM pohadja WHERE predmet_id = " + predmetID;

			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	// update svih pohadjanja jednog studenta
	public static boolean update(Connection conn, Student student) {
		try {
			conn.setAutoCommit(false); // iskljucivanje automatske transakcije (pri cemu je svaki upit bio transakcija za sebe)
			conn.commit(); // pocetak transakcije

			// obrisati prethodna pohadjaja
			boolean uspeh = deletePohadjanjaStudenta(conn, student.getId());
			if (!uspeh) // prekid u slucaju neuspelog brisanja
				throw new Exception("Brisanje nije uspelo!");

			// za svaki predmet ovog studenta dodati po jedno pohadjanje
			for (Predmet predmet : student.getPredmeti()) {
				uspeh = add(conn, student.getId(), predmet.getId());
				if (!uspeh) // prekid u slucaju neuspelog dodavanja
					throw new Exception("Dodavanje nije uspelo!");
			}

			conn.commit(); // kraj transakcije
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			try {conn.rollback();} catch (SQLException ex1) { ex1.printStackTrace(); } // vratiti bazu u stanje pre pocetka transakcije
		} finally {
			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();} // ukljuciti automatsku tranaskciju
		}

		return false;
	}
	
	// update svih pohadjanja jednog predmeta
	public static boolean update(Connection conn, Predmet predmet){
		try {
			conn.setAutoCommit(false); // iskljucivanje automatske transakcije (pri cemu je svaki upit bio transakcija za sebe)
			conn.commit(); // pocetak transakcije

			// obrisati prethodna pohadjaja
			boolean uspeh = deletePohadjanjaPredmeta(conn, predmet.getId());
			if (!uspeh) // prekid u slucaju neuspelog brisanja
				throw new Exception("Brisanje nije uspelo!");

			// za svakog studenta ovog predmeta dodati po jedno pohadjanje
			for (Student student : predmet.getStudenti()) {
				uspeh = add(conn, student.getId(), predmet.getId());
				if (!uspeh) // prekid u slucaju neuspelog dodavanja
					throw new Exception("Dodavanje nije uspelo!");
			}

			conn.commit(); // kraj transakcije
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			try {conn.rollback();} catch (SQLException ex1) { ex1.printStackTrace(); } // vratiti bazu u stanje pre pocetka transakcije
		} finally {
			try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();} // ukljuciti automatsku tranaskciju
		}

		return false;
	}
}
