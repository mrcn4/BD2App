package Model;

import java.sql.Date;
import java.util.ArrayList;

public class CPracownikIT {
	int ID;
	String Imie;
	String Nazwisko;
	Date ZatrudnionyOd;
	int Doswiadczenie;
	ArrayList<Boolean> Umiejetnosci;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getImie() {
		return Imie;
	}
	public void setImie(String imie) {
		Imie = imie;
	}
	public String getNazwisko() {
		return Nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		Nazwisko = nazwisko;
	}
	public Date getZatrudnionyOd() {
		return ZatrudnionyOd;
	}
	public void setZatrudnionyOd(Date zatrudnionyOd) {
		ZatrudnionyOd = zatrudnionyOd;
	}
	public int getDoswiadczenie() {
		return Doswiadczenie;
	}
	public void setDoswiadczenie(int doswiadczenie) {
		Doswiadczenie = doswiadczenie;
	}
	public ArrayList<Boolean> getUmiejetnosci() {
		return Umiejetnosci;
	}
	public void setUmiejetnosci(ArrayList<Boolean> umiejetnosci) {
		Umiejetnosci = umiejetnosci;
	}
}
