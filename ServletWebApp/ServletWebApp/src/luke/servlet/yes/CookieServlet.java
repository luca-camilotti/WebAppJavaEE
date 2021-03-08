package luke.servlet.yes;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie
 */
@WebServlet("/Cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		boolean cookieSet = false;
		String cookieName = "pippo";
		String cookieValue = "pluto";
		response.getWriter().println("<h1>Cookie Demo App</h1>");
		Cookie[] cookies =request.getCookies();
		if(cookies != null && cookies.length > 0)
			for(int i=0; i<cookies.length; i++) {
				response.getWriter().println(cookies[i].getName()+": "+cookies[i].getValue());
				if(cookies[i].getName().equals(cookieName))
					cookieSet = true;			
			}
		if(!cookieSet) {
			Cookie mycookie = new Cookie(cookieName, cookieValue);
			mycookie.setMaxAge(10); // validita' in secondi
			mycookie.setPath(this.context.getContextPath()+"/Cookie");  // tell the browser to set this cookie for a specific resource
			//mycookie.setDomain("luke.servlet.yes");  // cookie will be sent only to that particular domain requests
			response.addCookie(mycookie);
			response.getWriter().println("Setting up Cookie "+cookieName+"!");
		}
		else {
			response.getWriter().println("OK! Cookie "+cookieName+" found!");
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
