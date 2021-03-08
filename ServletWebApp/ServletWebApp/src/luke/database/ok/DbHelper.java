package luke.database.ok;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DbHelper {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// new driver name: com.mysql.cj.jdbc.Driver
	//private static final String DBurl = "jdbc:mysql://localhost:3306/azienda";
	private static final String DBurl = "jdbc:mysql://remotemysql.com:3306/hGiaOgy3x7";
	private static final String user = "hGiaOgy3x7";
	private static final String pwd = "THbDSSME4U";
	
	private Connection con;
	
	public DbHelper() {
		//super();
		con = null;
	}
	
	public boolean connect() {
		try 
	    {
	      Class.forName(DRIVER);
	      System.out.println("Driver Connector/J trovato!");
	    }
	    catch (ClassNotFoundException e)
	    {
	      System.out.println("WARNING: driver Connector/J NON trovato!");
	      //System.exit(1);  //quit
	      return false;
	    }
		try
		{
			con = DriverManager.getConnection(DBurl, user, pwd);
			/*
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", pwd);
			props.setProperty("ssl","false");
			con = DriverManager.getConnection(DBurl, props); */
		}
		catch (Exception e)
		{
			System.out.println("Errore di connessione a "+ DBurl);
			e.printStackTrace();
			//System.exit(1);  //quit
			return false;
		}
		return true;
	}
	
	public void disconnect() throws SQLException {
		if(con != null)
			con.close();
	}
	
	public ArrayList<Dipendente> getDipendenti() {
		String query = "SELECT * FROM dipendente";
		
		ResultSet res = null;
		Statement sql = null;
	    try
	    {	    	
	    	sql = con.createStatement();	    	
	    	res = sql.executeQuery(query);	    
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di esecuzione comando SQL: "+ query);
	    	e.printStackTrace();
	    	//System.exit(1);  //quit
	    	return null;
	    }
	    ArrayList<Dipendente> list = new ArrayList<Dipendente>();
	    
	    try {
			while(res.next())
			{
				Dipendente d = new Dipendente(res.getInt(1), res.getString(2), res.getString(3),
						res.getInt(4), res.getString(5), res.getString(6), res.getInt(7));
				list.add(d);
			}
		} catch (SQLException e) {
			System.out.println("Errore di lettura risultato query ");
			e.printStackTrace();
		}
	    return list;
		
	}

}
