package Model;

import java.sql.Date;
import java.util.ArrayList;

public class CPracownikIT {
	int ID;
	String Imie;
	String Nazwisko;
	Date ZatrudnionyOd;
	int Doswiadczenie;
	ArrayList<Integer> Umiejetnosci;
	public CPracownikIT(){}
	public CPracownikIT(String imie,String nazwisko,Date zatrudnionyod,int doswiadczenie)
	{
		Imie = imie;
		Nazwisko = nazwisko;
		ZatrudnionyOd = zatrudnionyod;
		Doswiadczenie = doswiadczenie;
	}
	public int dajID() {
		return ID;
	}
	public void ustawID(int iD) {
		ID = iD;
	}
	public String dajImie() {
		return Imie;
	}
	public void ustawImie(String imie) {
		Imie = imie;
	}
	public String dajNazwisko() {
		return Nazwisko;
	}
	public void ustawNazwisko(String nazwisko) {
		Nazwisko = nazwisko;
	}
	public Date dajZatrudnionyOd() {
		return ZatrudnionyOd;
	}
	public void ustawZatrudnionyOd(Date zatrudnionyOd) {
		ZatrudnionyOd = zatrudnionyOd;
	}
	public int dajDoswiadczenie() {
		return Doswiadczenie;
	}
	public void ustawDoswiadczenie(int doswiadczenie) {
		Doswiadczenie = doswiadczenie;
	}
	public ArrayList<Integer> dajUmiejetnosci() {
		return Umiejetnosci;
	}
	public void ustawUmiejetnosci(ArrayList<Integer> umiejetnosci) {
		Umiejetnosci = umiejetnosci;
	}
	public void dodajUmiejetnosc(int id)
	{
		if(Umiejetnosci == null) Umiejetnosci = new ArrayList<Integer>();
		Umiejetnosci.add(id);
	}
}
