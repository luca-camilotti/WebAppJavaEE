package supersballo.yes.wow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContaJsp
 */
@WebServlet("/ContaJsp")
public class ContaJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count;  // contatore visitatori
	private ArrayList<String> list;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContaJsp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.count = 0;
		list = new ArrayList<String>();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		this.count++;  // incremento numero visitatori
		list.add(request.getRemoteAddr()+":"+request.getRemotePort()+" on "+new Date().toString());
		
		request.setAttribute("count", count); // attributo passato per valore
		request.setAttribute("list", list);  // oggetto passato per indirizzo! Qualunque modifica venga fatta dalla pagina jsp, si riflette sull'originale
		
		// get request dispatcher
		RequestDispatcher disp = request.getRequestDispatcher("conta.jsp");

		// forward request to jsp file (view)
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
