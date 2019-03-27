package com.rollingpanda.model;

import java.util.ArrayList;
import java.util.List;

public class Nastavnik {

	protected int id;
	protected String ime;
	protected String prezime;
	protected String zvanje;

	protected List<Predmet> predmeti = new ArrayList<>();

	public Nastavnik(int id, String ime, String prezime, String zvanje) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", zvanje=" + zvanje
				+ ", predmeti=" + predmeti + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Predmet)) return false;

		Nastavnik that = (Nastavnik) obj;
		return that.id == this.id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public int getId() {
		return id;
	}

}
