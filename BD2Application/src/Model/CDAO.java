package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Window.Type;
import java.sql.*;

import oracle.jdbc.internal.OracleTypes;
public class CDAO implements IModel{

	private Connection conn = null;
	@Override
	public ArrayList<CPracownikIT> dajPracownikowIT() {
		
		String selectQuery = 
				"SELECT PRACOWNIK.ID as id,PRACOWNIK.IMIE as imie,PRACOWNIK.NAZWISKO as nazwisko," +
				"PRACOWNIK.ZATRUDNIONY_OD as zatr, PRACOWNIK.DOSWIADCZENIE as doswiadczenie," +
				"PRACOWNIKIT_UMIEJETNOSCI.USLUGA_ID as usluga_id "+
				"FROM PRACOWNIK, PRACOWNIKIT, PRACOWNIKIT_UMIEJEtNOSCI "+
				"WHERE PRACOWNIK.ID=PRACOWNIKIT.PRACOWNIK_ID AND PRACOWNIKIT.PRACOWNIK_ID=PRACOWNIKIT_UMIEJETNOSCI.USLUGA_ID " +
				"AND PRACOWNIKIT.PRACOWNIK_ID = PRACOWNIK.ID "+
				"ORDER BY PRACOWNIK.ID ASC, PRACOWNIKIT_UMIEJETNOSCI.USLUGA_ID DESC";
		ResultSet rs = null;
		try {
			conn.setAutoCommit(true);
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			rs = ps.executeQuery();

			ArrayList<CPracownikIT> pracLista =  new ArrayList<CPracownikIT>();
			int lastId = -1;

			CPracownikIT tmpPracownik  = null;
			while(rs.next())
			{
				int currId = rs.getInt("id");
				if(currId != lastId)
				{
					if(tmpPracownik!=null)
						pracLista.add(tmpPracownik);
					tmpPracownik  = new CPracownikIT();
				}
				tmpPracownik.ustawID(rs.getInt("id"));
				tmpPracownik.ustawImie(rs.getString("imie"));
				tmpPracownik.ustawNazwisko(rs.getString("nazwisko"));
				tmpPracownik.ustawZatrudnionyOd(rs.getDate("zatr"));
				tmpPracownik.dodajUmiejetnosc(rs.getInt("usluga_id"));
				
				
			}
			return pracLista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean modyfikujPracownikaIT(CPracownikIT PracownikDoModyfikacji) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean usunPracownika(CPracownikIT PracownikDoUsuniecia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean dodajPracownika(CPracownikIT PracownikDoDodania) {

		//String url2 = "INSERT INTO PRACOWNIKIT VALUES(PRACOWNIK_AUTOINCREMENT.nextval,?,?,?,?)";
		//String url3 = "call TESTP('test123')";
		String test1 ="{? = call DODAJPRACOWNIKA(?,?,?,?)}";
		String url2  = "INSERT INTO PRACOWNIKIT_UMIEJETNOSCI VALUES(?,?);";
		try {

			conn.setAutoCommit(false);
			CallableStatement proc = conn.prepareCall(test1);
			
			proc.setString(2, PracownikDoDodania.dajImie());
			proc.setString(3, PracownikDoDodania.dajNazwisko());
			proc.setDate(4, PracownikDoDodania.dajZatrudnionyOd());
			proc.setInt(5, PracownikDoDodania.dajDoswiadczenie());
			proc.registerOutParameter(1, OracleTypes.INTEGER);; //what a fucking trick
			
			proc.execute();
			int id = proc.getInt(1);
			proc.close();
			
			ArrayList<Integer> umiejetnosci =  PracownikDoDodania.dajUmiejetnosci();
			try
			{
			Iterator<Integer> i = umiejetnosci.iterator();
			while(i.hasNext())
			{
				int id_uslugi = i.next();
				PreparedStatement ps =conn.prepareStatement(url2);
				ps.setInt(1, id);
				ps.setInt(2, id_uslugi);
				ps.executeUpdate();
			}
			}catch(NullPointerException e){}
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void polacz(String url, String username, String password) throws Exception {
		
		try {
			if(conn!=null)
				conn.close();
				conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn = null;
				throw new Exception("Cannot connect to Database"+e.getMessage());
			
		}
	}

	@Override
	public void rozlacz() {
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		conn = null;
	}

}
