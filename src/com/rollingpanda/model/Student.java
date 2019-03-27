package com.rollingpanda.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

	protected int id;
	protected String ime;
	protected String prezime;
	protected String grad;
	protected String indeks;

	protected List<IspitnaPrijava> prijave = new ArrayList<>();
	protected List<Predmet> predmeti = new ArrayList<>();

	public Student(int id, String indeks, String ime, String prezime, String grad) {
		this.id = id;
		this.indeks = indeks;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", grad=" + grad + ", indeks=" + indeks
				+ ", prijave=" + prijave + ", predmeti=" + predmeti + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Student)) return false;

		Student that = (Student) obj;
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getIndeks() {
		return indeks;
	}

	public void setIndeks(String indeks) {
		this.indeks = indeks;
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

	public List<IspitnaPrijava> getPrijave() {
		return prijave;
	}

}
