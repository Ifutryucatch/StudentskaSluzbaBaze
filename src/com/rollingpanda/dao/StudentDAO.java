package com.rollingpanda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rollingpanda.model.Predmet;
import com.rollingpanda.model.Student;

public class StudentDAO {
	
	public static Student getStudentByID(Connection conn, int id) {
		Student student = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT indeks, ime, prezime, grad FROM studenti WHERE student_id = " + id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int index = 1;
				String indeks = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				
				student = new Student(id, indeks, ime, prezime, grad);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return student;
	}
	
	public static Student getStudentByIndeks(Connection conn, String indeks) {
		Student student = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT student_id, ime, prezime, grad FROM studenti WHERE indeks = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, indeks);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				
				student = new Student(id, indeks, ime, prezime, grad);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return student;
	}
	
	public static List<Student> getAll(Connection conn) {
		List<Student> studenti = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String indeks = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);

				List<Predmet> predmetiKojePohadja = PohadjaDAO.getPredmetiByStudentID(conn, id);

				Student student = new Student(id, indeks, ime, prezime, grad);
				student.getPredmeti().addAll(predmetiKojePohadja);
				studenti.add(student); 
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return studenti;
	}

	public static boolean add(Connection conn, Student student) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO studenti (indeks, ime, prezime, grad) VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, student.getIndeks());
			pstmt.setString(index++, student.getIme());
			pstmt.setString(index++, student.getPrezime());
			pstmt.setString(index++, student.getGrad());

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Connection conn, Student student) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE studenti SET indeks = ?, ime = ?, prezime = ?, grad = ? WHERE student_id = " + student.getId();

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, student.getIndeks());
			pstmt.setString(index++, student.getIme());
			pstmt.setString(index++, student.getPrezime());
			pstmt.setString(index++, student.getGrad());

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
			String query = "DELETE FROM studenti WHERE student_id = " + id;

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
