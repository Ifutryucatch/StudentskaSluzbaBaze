package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.IspitniRok;



public class IspitniRokDAO {
	
	public static IspitniRok getIspitniRokByID(Connection conn, int id) {
		IspitniRok ispitniRok = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT naziv, pocetak, kraj FROM ispitni_rokovi WHERE rok_id = " + id;

			stmt = conn.prepareStatement(query);
			rset = stmt.executeQuery(query);
			
			if (rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);
				Date pocetak = rset.getDate(index++);
				Date kraj = rset.getDate(index++);
				ispitniRok = new IspitniRok(id, naziv, pocetak, kraj);
			}
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return ispitniRok;
	}
	
	public static List<IspitniRok> getAll(Connection conn) {
		List<IspitniRok> ispitniRokovi = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT rok_id, naziv, pocetak, kraj FROM ispitni_rokovi";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date pocetak = rset.getDate(index++);
				Date kraj = rset.getDate(index++);

				IspitniRok ispitniRok = new IspitniRok(id, naziv, pocetak, kraj);
				ispitniRokovi.add(ispitniRok);
			}
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return ispitniRokovi;
	}

	public static boolean add(Connection conn, IspitniRok ispitniRok) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES (?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, ispitniRok.getNaziv());
			pstmt.setDate(index++, ispitniRok.getPocetak());
			pstmt.setDate(index++, ispitniRok.getKraj());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Connection conn, IspitniRok ispitniRok) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE ispitni_rokovi SET naziv = ?, pocetak = ?, kraj = ? WHERE rok_id = " + ispitniRok.getId();

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, ispitniRok.getNaziv());
			pstmt.setDate(index++, ispitniRok.getPocetak());
			pstmt.setDate(index++, ispitniRok.getKraj());

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
			String query = "DELETE FROM ispitni_rokovi WHERE rok_id = " + id;

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
