package com.rollingpanda.model;

public class IspitnaPrijava {

	protected Predmet predmet;
	protected IspitniRok rok;
	protected Student student;

	protected int teorija;
	protected int zadaci;

	public IspitnaPrijava(Predmet predmet, Student student, IspitniRok rok, int teorija, int zadaci) {
		this.predmet = predmet;
		this.student = student;
		this.rok = rok;
		this.teorija = teorija;
		this.zadaci = zadaci;
	}

	@Override
	public String toString() {
		return "IspitnaPrijava [predmet=" + predmet + ", rok=" + rok + ", student=" + student + ", teorija=" + teorija
				+ ", zadaci=" + zadaci + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof IspitnaPrijava)) return false;

		IspitnaPrijava that = (IspitnaPrijava) obj;
		return that.predmet.equals(this.predmet) && that.rok.equals(this.rok) && that.student.equals(this.student);
	}

	public int sracunajOcenu() {
		int bodovi = teorija + zadaci;

		int ocena;
		if (bodovi >= 95)
			ocena = 10;
		else if (bodovi >= 85)
			ocena = 9;
		else if (bodovi >= 75)
			ocena = 8;
		else if (bodovi >= 65)
			ocena = 7;
		else if (bodovi >= 55)
			ocena = 6;
		else
			ocena = 5;

		return ocena;
	}
	
	public Student getStudent() {
		return student;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public IspitniRok getRok() {
		return rok;
	}

	public int getTeorija() {
		return teorija;
	}

	public void setTeorija(int teorija) {
		this.teorija = teorija;
	}

	public int getZadaci() {
		return zadaci;
	}

	public void setZadaci(int zadaci) {
		this.zadaci = zadaci;
	}

}
