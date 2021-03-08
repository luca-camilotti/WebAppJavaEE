package luke.servlet.yes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyCookieSet
 */
@WebServlet("/MyCookieSet")
public class MyCookieSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private String html;
	private String form;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCookieSet() {
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
		this.html = "<html><head><title>MySetCookie</title><link rel=\"stylesheet\" type=\"text/css\" href=\"table.css\"></head><body><h1>Set a Cookie</h1>";
		this.form = "<form action=\""+this.context.getContextPath()+"/MyCookieSet\" method=\"post\">" + 
				"	<div class=\"box\">" + 
				"	<!--fieldset-->" + 
				"		<legend>Add a cookie</legend><ul>" + 
				"		<li><label for=\"name\">Name:</label></li><li><input type=\"text\" name=\"name\" placeholder=\"name\" /></li>" + 
				"<li><label for=\"value\">Value:</label></li><li><input type=\"text\" name=\"value\" placeholder=\"value\" /></li>" + 
				"<!--/fieldset-->" + 
				"	<li><input type=\"submit\" name=\"set\"  value=\"Register\" /></li>" + 
				"	</ul></div>" + 
				"	</form>";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String set = request.getParameter("set");
		if (set != null)  {
			String name = request.getParameter("name");
			String value = request.getParameter("value");
			if(name != null && value != null) {
				Cookie mycookie = new Cookie(name, value);
				mycookie.setMaxAge(240); // validita' in secondi
				mycookie.setPath(this.context.getContextPath()+"/MyCookieSet");  // tell the browser to set this cookie for a specific resource
				//mycookie.setDomain("luke.servlet.yes");  // cookie will be sent only to that particular domain requests
				response.addCookie(mycookie);
				response.sendRedirect(this.context.getContextPath()+"/MyCookieSet");
			}
		}
		out.println(html);
		out.println("<h3>Cookies found:</h3><div>");
		Cookie[] cookies =request.getCookies();
		if(cookies != null && cookies.length > 0)
			for(int i=0; i<cookies.length; i++) {
				out.println(cookies[i].getName()+": "+cookies[i].getValue()+"<br />");
							
			}
		out.println("</div>"+form+"</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
