package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.Nastavnik;
import com.rollingpanda.model.Predmet;

public class NastavnikDAO {
	
	public static Nastavnik getNastavnikByID(Connection conn, int id) {
		Nastavnik nastavnik = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT ime, prezime, zvanje FROM nastavnici WHERE nastavnik_id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int index = 1;
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);

				List<Predmet> predmetiKojePohadja = PredajeDAO.getPredmetiByNastavnkID(conn, id);

				nastavnik = new Nastavnik(id, ime, prezime, zvanje);
				nastavnik.getPredmeti().addAll(predmetiKojePohadja);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return nastavnik;
	}
	
	public static List<Nastavnik> getAll(Connection conn) {
		List<Nastavnik> nastavnici = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT nastavnik_id, ime, prezime, zvanje FROM nastavnici";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);

				List<Predmet> predmetiKojePohadja = PredajeDAO.getPredmetiByNastavnkID(conn, id);

				Nastavnik nastavnik = new Nastavnik(id, ime, prezime, zvanje);
				nastavnik.getPredmeti().addAll(predmetiKojePohadja);
				nastavnici.add(nastavnik); 
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return nastavnici;
	}

	public static boolean add(Connection conn, Nastavnik nastavnik) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO nastavnici (ime, prezime, zvanje) VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, nastavnik.getIme());
			pstmt.setString(index++, nastavnik.getPrezime());
			pstmt.setString(index++, nastavnik.getZvanje());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Connection conn, Nastavnik nastavnik) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE nastavnici SET ime = ?, prezime = ?, zvanje = ? WHERE nastavnik_id = " + nastavnik.getId();

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, nastavnik.getIme());
			pstmt.setString(index++, nastavnik.getPrezime());
			pstmt.setString(index++, nastavnik.getZvanje());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean delete(Connection conn, int id) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM nastavnici WHERE nastavnik_id = " + id;

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

}
