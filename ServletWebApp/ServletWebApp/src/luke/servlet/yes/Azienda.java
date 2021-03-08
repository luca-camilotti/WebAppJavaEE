package luke.servlet.yes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;  // JDBC
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Azienda
 */
@WebServlet("/Azienda")
public class Azienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private Connection conn = null;
	private String URL_myDB = "jdbc:mysql://localhost:3306/azienda";
	private String dbuser="root"; 
	private String dbpassw="";
	private String htmlStart;
	private String htmlEnd;
	private String form;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Azienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		// TODO Auto-generated method stub
		super.init(config);
		this.context = getServletContext();
		htmlStart = "<html><head><title>MySQL JDBC</title>"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"table.css\">"
				+ "</head><body><h1>MySQL JDBC</h1>";
		htmlEnd = "</body></html>";
		form = "<form action=\""+this.context.getContextPath()+"/Azienda\" method=\"post\">" + 
				"	<div class=\"box\">" + 
				"	<!--fieldset-->" + 
				"		<legend>Add a record</legend><ul>" + 
				"		<li><label for=\"nome\">Nome:</label></li><li><input type=\"text\" name=\"nome\" placeholder=\"nome\" /></li>" + 
				"<li><label for=\"cognome\">Cognome:</label></li><li><input type=\"text\" name=\"cognome\" placeholder=\"cognome\" /></li>" + 
				"<li><label for=\"stipendio\">Stipendio:</label></li><li><input type=\"text\" name=\"stipendio\" placeholder=\"stipendio\" /></li>" + 
				"<li><label for=\"funzione\">Funzione:</label></li><li><input type=\"text\" name=\"funzione\" placeholder=\"funzione\" /></li>" + 
				"<li><label for=\"filiale\">Filiale:</label></li><li><input type=\"text\" name=\"filiale\" placeholder=\"filiale\" /></li>" + 
				"<li><label for=\"livello\">Livello:</label></li><li><input type=\"text\" name=\"livello\" placeholder=\"livello\" /></li>"	+
				"<!--/fieldset-->" + 
				"	<li><input type=\"submit\" name=\"inserisci\"  value=\"Register\" /></li>" + 
				"	</ul></div>" + 
				"	</form>";
		String DRIVER = "com.mysql.jdbc.Driver";
	    try 
	    {
	      Class.forName(DRIVER);
	      System.out.println("Driver Connector/J trovato!");
	    }
	    catch (ClassNotFoundException e)
	    {
	      System.out.println("WARNING: driver Connector/J NON trovato!");
	      System.out.println("Error! Driver Connector/J trovato!");
	    }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String invia = request.getParameter("inserisci");
		String elimina = request.getParameter("elimina");
		if(elimina != null) {
			elimina(elimina);
		}
		if(invia != null) {
			out.println("Inserimento: "+ request.getParameter("nome")+" "+request.getParameter("cognome")+" "+request.getParameter("stipendio")+" "+request.getParameter("funzione")+" "+request.getParameter("filiale")+" "+ request.getParameter("livello"));
			inserisci(request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("stipendio"), request.getParameter("funzione"), request.getParameter("filiale"), request.getParameter("livello"));
		}
		
		out.println(htmlStart);		
		ResultSet res = queryDipendenti();
		
		// Show results
	    try {
	    	
	    	out.println("<h1>Tabella Dipendenti</h1>");
	    	out.println("<table><tr><th>ID</th><th>nome</th><th>cognome</th><th>stipendio</th><th>funzione</th><th>filiale</th><th>livello</th><th>Action</th></tr>");
			while(res.next())
			{
				String id = res.getString(1); // primo campo -> indice 1
				String nome = res.getString(2);
				String cognome = res.getString(3);
				int stipendio = res.getInt(4);
				String funzione = res.getString(5);
				String filiale = res.getString(6);
				int livello = res.getInt(7);
				System.out.println(id+") "+nome+" "+cognome+": "+funzione);
				out.println("<tr><td>"+id+"</td><td>"+nome+"</td><td>"+cognome+"</td><td>"+stipendio+"</td><td>"+funzione+"</td><td>"+filiale+"</td><td>"+livello+"</td><td> <a href=\'"+this.context.getContextPath()+"/Azienda?elimina="+id+"\'>elimina</a></td></tr>");
			}
			out.println("</table>");
		} catch (SQLException e) {
			System.out.println("Errore di lettura risultato query ");
			e.printStackTrace();
		}
	    out.println(form);
	    out.println(htmlEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/* DB management methods */
	private boolean elimina(String id) {
		boolean retVal = false;
		try
	    {
	    	conn = DriverManager.getConnection(URL_myDB, dbuser, dbpassw);
	    	
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di apertura connessione");
	    	conn = null;
	    }

		String deleteCommand = "DELETE FROM dipendente WHERE id = "+id;

		try
	    {
	    	Statement cmd = conn.createStatement();
	    	//cmd.execute(insertCommand);
	    	cmd.execute(deleteCommand);
	    	
	    	retVal = true;
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di eliminazione");
	    	//System.exit(1);  //quit
	    }
		
		
		return retVal;
	}
	
	private boolean inserisci(String nome, String cognome, String stipendio, String funzione, String filiale, String livello) {
		boolean retVal = false;
		try
	    {
	    	conn = DriverManager.getConnection(URL_myDB, dbuser, dbpassw);
	    	
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di apertura connessione");
	    	conn = null;
	    }
		
		int stipendioInt;
		try {
			stipendioInt = Integer.parseInt(stipendio);
		}
		catch(Exception e) {
			stipendioInt = 0;
		}
		int livelloInt;
		try {
			livelloInt = Integer.parseInt(livello);
		}
		catch(Exception e) {
			livelloInt = 0;
		}
		/* inserimento nel database */
		// Insert
	    String insertCommand = "INSERT INTO dipendente (nome, cognome, stipendio, funzione, filiale, livello) VALUES (\'"+nome+"\', \'"+cognome+"\', "+stipendioInt+", \'"+funzione+"\' , \'"+filiale+"\', "+livelloInt+");";
	    System.out.println(insertCommand);
	    // Delete
	    
	    //String deleteCommand = "DELETE FROM dipendente WHERE nome = \'pippo\'";
	    
	    
	    try
	    {
	    	Statement cmd = conn.createStatement();
	    	//cmd.execute(insertCommand);
	    	cmd.executeUpdate(insertCommand);
	    	retVal = true;
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di inserimento");
	    	//System.exit(1);  //quit
	    } 
	    if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return retVal;
	}
	
	private ResultSet queryDipendenti() 
	{
		try
	    {
	    	conn = DriverManager.getConnection(URL_myDB, dbuser, dbpassw);
	    	
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di apertura connessione");
	    	conn = null;
	    }
		ResultSet res = null;
	    try
	    {
	    	Statement sql = conn.createStatement();
	    	res = sql.executeQuery("SELECT * FROM dipendente");
	    	System.out.println("Ho eseguito il comando SQL: "+ "SELECT * FROM dipendente");
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di esecuzione comando SQL: "+ "SELECT * FROM dipendente");
	    	//System.exit(1);  //quit
	    }
	    /*
	    if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		return res;		
	}

}
