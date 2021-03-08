package luke.login.session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Demo
 */
@WebServlet("/Demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String template;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//template = HtmlUtils.getHtmlfromFile(this, "template.html");
		//template = template.replace("{action}", getServletContext().getContextPath()+"/Demo");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().println(HtmlUtils.simplePage("demo", HtmlUtils.header(1, "Demo page")+ HtmlUtils.paragraph("Yes! It works!")));
		
		//String pageToRender = HtmlUtils.getHtmlfromFile(this, "logout.html");
		//pageToRender = pageToRender.replace("{username}", "Mr. Pippo MacPipp");
		
		/* Lettura Parametri */
		String emailparam = request.getParameter("email");
		String pwdparam = request.getParameter("password");
		String logoutparam = request.getParameter("logout");
		
		/* Sessione */
		HttpSession sessione = request.getSession();
		
		/* Elaborazione dati dal form */
		if(logoutparam != null && logoutparam.equalsIgnoreCase("yes")) {
			sessione.invalidate();  // logout
			sessione = request.getSession();  // default timeout should be 30 min
		}
		if(emailparam != null) {
			sessione.setAttribute("user", emailparam);
			sessione.setAttribute("logged", true);
		}
		
		
		/* Build View */
		try {
			boolean logged = false;
			if (sessione.getAttribute("logged") != null)
				logged = (boolean) sessione.getAttribute("logged");
			if (logged) { 
				/* mostra logout */ 
				String user = (String) sessione.getAttribute("user");
				
				request.setAttribute("username", user); // attributo passato per valore
						
				// get request dispatcher
				RequestDispatcher disp = request.getRequestDispatcher("logout_bootstrap.jsp");

				// forward request to jsp file (view)
				disp.forward(request, response);
			}
			/* Se l'utente non risulta autenticato, mostra login form */
			else {
				// get request dispatcher
				RequestDispatcher disp = request.getRequestDispatcher("login_bootstrap.jsp");

				// forward request to jsp file (view)
				disp.forward(request, response);
			}
			
		}  catch(Exception e) {
			e.printStackTrace();
			response.getWriter().println("<h1>ERROR</h1>");		
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
