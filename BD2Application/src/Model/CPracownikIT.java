package Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CPracownikIT {
	Integer ID;
	String Imie;
	String Nazwisko;
	Date ZatrudnionyOd;
	Integer Doswiadczenie;
	ArrayList<Integer> Umiejetnosci;
	
	public CPracownikIT() {
		Umiejetnosci = new ArrayList<Integer>();
	}
	public Integer dajID() {
		return ID;
	}
	public void ustawID(Integer iD) {
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
	public Integer dajDoswiadczenie() {
		return Doswiadczenie;
	}
	public void ustawDoswiadczenie(Integer doswiadczenie) {
		Doswiadczenie = doswiadczenie;
	}
	public ArrayList<Integer> dajUmiejetnosci() {
		return Umiejetnosci;
	}
	public void ustawUmiejetnosci(ArrayList<Integer> umiejetnosci) {
		Umiejetnosci = umiejetnosci;
	}
	public void dodajUmiejetnosc(Integer id)
	{
		if(Umiejetnosci == null) Umiejetnosci = new ArrayList<Integer>();
		Umiejetnosci.add(id);
	}
	
	@Override
	public String toString() {
		String toStringString = Imie + " " + Nazwisko + "; zatrudniony od: " + ZatrudnionyOd;
		toStringString+= "posiada doświadczenie: " + Doswiadczenie;
		toStringString+= "; posiada umiejetnosci: ";
		for (Integer value : Umiejetnosci) {
			toStringString += value + "\n";
		}
		return toStringString;
	}
}