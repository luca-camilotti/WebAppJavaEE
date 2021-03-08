package supersballo.yes.wow;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import luke.database.ok.*;

/**
 * Servlet implementation class DBazienda
 */
@WebServlet("/DBazienda")
public class DBazienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean connected;  // indica se la connessione è attiva
    private DbHelper helper;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBazienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		helper = new DbHelper();
		connected = helper.connect();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		if(connected) {
			ArrayList<Dipendente> list = helper.getDipendenti();
			
			request.setAttribute("list", list);  // oggetto passato per indirizzo! Qualunque modifica venga fatta dalla pagina jsp, si riflette sull'originale
			
			// get request dispatcher
			RequestDispatcher disp = request.getRequestDispatcher("dipendente.jsp");

			// forward request to jsp file (view)
			disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
