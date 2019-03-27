package com.rollingpanda.model;

import java.util.ArrayList;
import java.util.List;

public class Predmet {

	private int id;
	private String naziv;
	
	private List<Student> studenti = new ArrayList<>();
	private List<Nastavnik> nastavnici = new ArrayList<>();
	
	public Predmet(int id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Predmet [id=" + id + ", naziv=" + naziv + ", studenti=" + studenti + ", nastavnici=" + nastavnici + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Predmet)) return false;

		Predmet that = (Predmet) obj;
		return that.id == this.id;
	}

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public List<Nastavnik> getNastavnici() {
		return nastavnici;
	}
	
}
