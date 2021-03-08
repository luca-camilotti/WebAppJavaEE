package supersballo.yes.wow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Conta
 */
@WebServlet("/Conta")
public class Conta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int count;  // contatore visitatori
	private ArrayList<String> list;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conta() {
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
		if(request.getParameter("reset")!=null && request.getParameter("reset").equals("yes")){
			this.count=0;
			list.clear();
		}
		
		this.count++;  // incremento numero visitatori
		list.add(request.getRemoteAddr()+":"+request.getRemotePort()+" on "+new Date().toString());
		
		/* Experiment */
		
		if(request.getParameter("exp")!=null && request.getParameter("exp").equals("true")){
			
			request.setAttribute("count", count); // attributo passato per valore
			request.setAttribute("list", list);  // oggetto passato per indirizzo! Qualunque modifica venga fatta dalla pagina jsp, si riflette sull'originale
			
			// get request dispatcher
			RequestDispatcher disp = request.getRequestDispatcher("exp.jsp");
			
			// forward request to jsp file (view)
			disp.forward(request, response);
		}
		
		String htmlbegin = "<html><head><title>Count Visitors</title></head><body><h1>Welcome</h1>";
		String htmlend = "</body></html>";
		String form = "<form method='get' action='/ServletWebApp/Conta'><input type='hidden' name='reset' value='yes' /><input type='submit' value='reset'/></form>";
		// Dynamic content:
		String lista = "";
		Iterator i = list.iterator();
		while(i.hasNext()) {
			lista += "<p>"+i.next()+"</p>";
		}
		/*
		for(String s : list) {
			lista += "<p>"+s+"</p>";
		} */
		response.getWriter().println(htmlbegin+"<h3>Number of visitors: "+this.count+"</h3>"+lista+form+htmlend);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
