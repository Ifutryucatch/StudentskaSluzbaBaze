package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.Nastavnik;
import com.rollingpanda.model.Predmet;


public class PredajeDAO {

	public static List<Predmet> getPredmetiByNastavnkID(Connection conn, int nastavnikID) {
		List<Predmet> predmetiKojeNastavnikPredaje = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT predmet_id FROM predaje WHERE nastavnik_id = " + nastavnikID;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int predmetID = rset.getInt(1);

				Predmet predmet = PredmetDAO.getPredmetByID(conn, predmetID);
				predmetiKojeNastavnikPredaje.add(predmet);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return predmetiKojeNastavnikPredaje;
	}
	
	public static List<Nastavnik> getNastavniciByPredmetID(Connection conn, int id) {
		List<Nastavnik> nastavniciKojiPredajuPredmet = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT nastavnik_id FROM predaje WHERE predmet_id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int studentID = rset.getInt(1);

				Nastavnik nastavnik = NastavnikDAO.getNastavnikByID(conn, studentID);
				nastavniciKojiPredajuPredmet.add(nastavnik);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return nastavniciKojiPredajuPredmet;
	}
	
	public static boolean add(Connection conn, int nastavnikID, int predmetID){
		Statement stmt = null;
		try {
			String query = "INSERT INTO predaje (nastavnik_id, predmet_id) VALUES (" + nastavnikID + ", " + predmetID + ")";

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
	
	
	public static boolean delete(Connection conn, int nastavnikID, int predmetID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM predaje WHERE nastavnik_id = " + nastavnikID + " AND predmet_id = " + predmetID;

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
