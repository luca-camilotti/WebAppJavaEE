package luke.servlet.yes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo
 */
@WebServlet("/SessionDemo")
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	private int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionDemo() {
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
		count = 0;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().println("<h1>Session Demo App</h1>");
		HttpSession sessione = request.getSession(); // returns the session or creates a new one if it does not exist
		String id = sessione.getId();
		long creationTime = sessione.getCreationTime();
		long lastAccessTime = sessione.getLastAccessedTime();
		sessione.setMaxInactiveInterval(10);  // sec, session life time 
		
		try {
			int value = Integer.parseInt(sessione.getAttribute("pippo").toString());
			ArrayList<String> list = (ArrayList<String>) sessione.getAttribute("list");
			response.getWriter().println("<p>Session creation time: "+creationTime+" ("+new Date(creationTime)+") ");
			response.getWriter().println("<p>Session data: pippo ="+value+"</p>");
			response.getWriter().println("<h2>Session data: list</h2>");
			for(int i = 0; i<list.size(); i++)
				response.getWriter().println("<p>item "+i+": <b>"+list.get(i)+"</b></p>");
			list.add("count = "+count);
			count++;
		} catch(Exception e) {
		
			int value = 1250;
			response.getWriter().println("<p>Sorry, no session data.. creating one right now: pippo ="+value+"</p>");
			sessione.setAttribute("pippo", value);
			
			ArrayList<String> list = new ArrayList<String>();
			list.add("pluto");
			list.add("paperino");
			sessione.setAttribute("list", list);
		}
		
		/*
		 * Other methods:
		 * invalidate() -> destroy the session
		 * getMaxInactiveInterval() -> get the validity time
		 * setAttribute(key, value) -> add a (key, value) pair to the session data
		 * getAttribute(key) -> retrieve a value from session data
		 * removeValue(key) -> remove a value from session data
		 * */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
