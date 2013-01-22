import java.sql.*;

public class MainClass {

        public static void selectPlayers(Connection conn, String name) throws
SQLException {
                PreparedStatement randomSelect = null;
            String randomSelectString = "select name_,surname,pref_foot,wage from players where name_ LIKE ? AND surname LIKE ?";
            randomSelect = conn.prepareStatement(randomSelectString);
            randomSelect.setString(1, name);
            randomSelect.setString(2, "Z%");
            randomSelect.execute();

            ResultSet rset  = randomSelect.getResultSet();

            while (rset.next()) {
                System.out.print (rset.getString(1) + "\t");
                System.out.print (rset.getString(2) + "\t");
                System.out.print (rset.getString(3)+ "\t");
                System.out.println (rset.getString(4));
            }
        }

        public static void updatePlayersWages(Connection conn){
                PreparedStatement randomUpdate = null;
            String randomUpdateString = "update players set wage=wage*0.9 where name_ LIKE ? AND surname LIKE ?";

            try {
                        randomUpdate = conn.prepareStatement(randomUpdateString);

                    randomUpdate.setString(1, "K%");
                    randomUpdate.setString(2, "Z%");
                    randomUpdate.executeUpdate();
                    conn.commit(); //Just do it!

                } catch (SQLException e) {
                        System.err.println("Transaction failed.");
                if (conn != null) {
                    try {
                        System.err.println("Transaction is being rolled back");
                        conn.rollback();
                    } catch(SQLException excep) {
                        System.out.println("Rollback failed. You should panic.");
                        e.printStackTrace();
                    }
                }

                }

        }

        public static void insertPlayer(Connection conn){
                PreparedStatement randomUpdate = null;
            String randomUpdateString = "INSERT INTO PLAYERS VALUES(991,'Adam','Parewicz',16453,'rwm','r','MLN');";

            try {
                        randomUpdate = conn.prepareStatement(randomUpdateString);

                    randomUpdate.execute();
                    conn.commit(); //Just do it!

                } catch (SQLException e) {
                        System.err.println("Transaction failed.");
                if (conn != null) {
                    try {
                        System.err.println("Transaction is being rolled back");
                        conn.rollback();
                    } catch(SQLException excep) {
                        System.out.println("Rollback failed. You should panic.");
                        e.printStackTrace();
                    }
                }

                }

        }

        public static void updatePlayersWagesInvalid(Connection conn){
                PreparedStatement randomUpdate = null;
            String randomUpdateString = "update players set waage=wage*2 where name_ LIKE ? AND surname LIKE ?";

            try {
                        randomUpdate = conn.prepareStatement(randomUpdateString);

                    randomUpdate.setString(1, "K%");
                    randomUpdate.setString(2, "Z%");
                    randomUpdate.executeUpdate();
                    conn.commit(); //Just do it!

                } catch (SQLException e) {
                        System.err.println("Transaction failed.");
                if (conn != null) {
                    try {
                        System.err.println("Transaction is being rolled back");
                        conn.rollback();
                    } catch(SQLException excep) {
                        System.out.println("Rollback failed. You should panic.");
                        e.printStackTrace();
                    }
                }

                }

        }


        public static void updatePlayerFootInvalid(Connection conn){
                PreparedStatement randomUpdate = null;
                PreparedStatement randomUpdate2 = null;
                String randomUpdateString = "update players set wage=wage*1.1 where name_ LIKE ? AND surname LIKE ?";
            String randomUpdateString2 = "update players set PREF_FOOT='n' where name_ LIKE ? AND surname LIKE ?";

            try {
                        randomUpdate = conn.prepareStatement(randomUpdateString);
                        randomUpdate2 = conn.prepareStatement(randomUpdateString2);

                        randomUpdate.setString(1, "K%");
                    randomUpdate.setString(2, "Z%");

                    randomUpdate2.setString(1, "K%");
                    randomUpdate2.setString(2, "Z%");

                    randomUpdate.executeUpdate();
                    randomUpdate2.executeUpdate();

                    conn.commit(); //Just do it!

                } catch (SQLException e) {
                        System.err.println("Transaction failed.");
                if (conn != null) {
                    try {
                        System.err.println("Transaction is being rolled back");
                        conn.rollback();
                    } catch(SQLException excep) {
                        System.out.println("Rollback failed. You should panic.");
                        e.printStackTrace();
                    }
                }

                }

        }
  public static void main(String[] args)
      throws ClassNotFoundException, SQLException
  {
	System.out.println("Application Start");
	String url =
			"jdbc:oracle:thin:kkrosman/kkrosman@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";
	Connection conn = DriverManager.getConnection(url,"kkrosman","kkrosman");
    conn.setAutoCommit(false);
    System.out.println("Połączenie poprawne? " + conn.isValid(50));
    /*
    System.out.println("\nPoprawny update:");
    selectPlayers(conn,"K%");
    updatePlayersWages(conn);
    selectPlayers(conn,"K%");

    System.out.println("\nWadliwy update:");
    selectPlayers(conn,"K%");
    updatePlayersWagesInvalid(conn);
    selectPlayers(conn,"K%");
    */
    selectPlayers(conn,"K%");
    updatePlayerFootInvalid(conn);
    selectPlayers(conn,"K%");

    System.out.println("");
    //insertPlayer(conn);

    /*Statement stmt = conn.createStatement();
    ResultSet rset =
         stmt.executeQuery("select name_,surname,pref_foot from players");

    while (rset.next()) {
         System.out.println (rset.getString(2));
    }
    stmt.close();*/
    System.out.println ("\nRequiestat in pace.");
  }
}