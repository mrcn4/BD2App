package Model;

import java.util.ArrayList;
import java.sql.*;
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

		String url = "INSERT INTO PRACOWNIK VALUES(PRACOWNIK_AUTOINCREMENT.nextval,?,?,?,?)";
		try {
			
			PreparedStatement ps = conn.prepareStatement(url);
			ps.setString(1, PracownikDoDodania.dajImie());
			ps.setString(2, PracownikDoDodania.dajNazwisko());
			ps.setDate(3, PracownikDoDodania.dajZatrudnionyOd());
			ps.setInt(4, PracownikDoDodania.dajDoswiadczenie());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
