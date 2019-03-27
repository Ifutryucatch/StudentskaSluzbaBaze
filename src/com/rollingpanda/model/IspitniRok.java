package com.rollingpanda.model;

import java.sql.Date;

public class IspitniRok {

	protected int id;
	protected String naziv;
	protected Date pocetak;
	protected Date kraj;

	public IspitniRok(int id, String naziv, Date pocetak, Date kraj) {
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	@Override
	public String toString() {
		return "IspitniRok [id=" + id + ", naziv=" + naziv + ", pocetak=" + pocetak + ", kraj=" + kraj + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof IspitniRok)) return false;

		IspitniRok that = (IspitniRok) obj;
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

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

}
