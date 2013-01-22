import java.sql.*;
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
	model.dajPracownikowIT();
	model.dodajPracownika(new CPracownikIT("kazik","krosman",new Date(123454),30) );
	System.out.println("Application End");
  }
}