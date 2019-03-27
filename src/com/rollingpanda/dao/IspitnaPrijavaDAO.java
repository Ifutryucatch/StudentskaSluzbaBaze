package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.IspitnaPrijava;
import com.rollingpanda.model.IspitniRok;
import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;


public class IspitnaPrijavaDAO {

	public static List<IspitnaPrijava> getPrijaveByStudent(Connection conn, Student student) {
		List<IspitnaPrijava> ispitnePrijaveStudenta = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT predmet_id, rok_id, teorija, zadaci FROM ispitne_prijave WHERE student_id = " + student.getId();

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int predmetID = rset.getInt(index++);
				int rokID = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);

				Predmet predmet = PredmetDAO.getPredmetByID(conn, predmetID);
				IspitniRok ispRok = IspitniRokDAO.getIspitniRokByID(conn, rokID);

				IspitnaPrijava ispPrijava = new IspitnaPrijava(predmet, student, ispRok, teorija, zadaci);
				ispitnePrijaveStudenta.add(ispPrijava);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return ispitnePrijaveStudenta;
	}

	public static List<IspitnaPrijava> getPrijaveByPredmet(Connection conn, Predmet predmet) {
		List<IspitnaPrijava> ispitnePrijaveZaPredmet = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT student_id, rok_id, teorija, zadaci FROM ispitne_prijave WHERE predmet_id = " + predmet.getId();

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int studentID = rset.getInt(index++);
				int rokID = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);

				Student student = StudentDAO.getStudentByID(conn, studentID);
				IspitniRok ispRok = IspitniRokDAO.getIspitniRokByID(conn, rokID);

				IspitnaPrijava ispPrijava = new IspitnaPrijava(predmet, student, ispRok, teorija, zadaci);
				ispitnePrijaveZaPredmet.add(ispPrijava);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return ispitnePrijaveZaPredmet;
	}
	
	public static List<IspitnaPrijava> getPrijaveByIspRok(Connection conn, IspitniRok ispRok) {
		List<IspitnaPrijava> ispitnePrijaveURoku = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT student_id, predmet_id, teorija, zadaci FROM ispitne_prijave WHERE rok_id = " + ispRok.getId();

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int studentID = rset.getInt(index++);
				int predmetID = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);

				Student student = StudentDAO.getStudentByID(conn, studentID);
				Predmet predmet = PredmetDAO.getPredmetByID(conn, predmetID);

				IspitnaPrijava ispPrijava = new IspitnaPrijava(predmet, student, ispRok, teorija, zadaci);
				ispitnePrijaveURoku.add(ispPrijava);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return ispitnePrijaveURoku;
	}
	
	public static boolean add(Connection conn, int studentID, int predmetID, int rokID, int teorija, int zadaci) {
		Statement stmt = null;
		try {
			String query = "INSERT INTO ispitne_prijave (student_id, predmet_id, rok_id, teorija, zadaci) "
					+ "VALUES (" + studentID + ", " + predmetID + ", " + rokID + ", " + teorija + ", " + zadaci + ")";

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

	public static boolean update(Connection conn, int studentID, int predmetID, int rokID, int teorija, int zadaci) {
		Statement stmt = null;
		try {
			String query = "UPDATE ispitne_prijave SET teorija = " + teorija + ", zadaci = " + zadaci + 
					" WHERE student_id = " + studentID + 
					" AND predmet_id = " + predmetID + 
					" AND rok_id = " + rokID;

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
	
	public static boolean delete(Connection conn, int studentID, int predmetID, int rokID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM ispitne_prijave WHERE student_id = " + studentID + " AND predmet_id = " + predmetID + " AND rok_id = " + rokID;

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
