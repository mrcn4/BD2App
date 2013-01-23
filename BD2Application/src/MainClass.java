import java.sql.*;
import java.util.ArrayList;

import Model.*;
public class MainClass {


  public static void main(String[] args)
      throws ClassNotFoundException, SQLException
  {
	System.out.println("Application Start");
	String url =
			"jdbc:oracle:thin:kkrosman/kkrosman@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";
	CDAO model = new CDAO();
	try {
		model.polacz(url, "kkrosman", "kkrosman");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	model.dajUslugi();
	model.dajPracownikowIT();
	CPracownikIT tmpp =new CPracownikIT("kazik","krosman",new Date(123454),30) ;
	ArrayList<Integer> tmp = new ArrayList<Integer>();
	tmp.add(2);
	tmp.add(5);
	tmpp.ustawUmiejetnosci(tmp);
	model.dodajPracownika(tmpp);
	
	
	tmpp =new CPracownikIT("bialy","nygas",new Date(123454),30) ;
	tmpp.ustawID(50);
	
	model.modyfikujPracownikaIT(tmpp);
	
	model.usunPracownika(tmpp);
	System.out.println("Application End");
  }
}