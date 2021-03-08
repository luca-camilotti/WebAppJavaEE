package luke.servlet.yes;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Count
 */
@WebServlet("/Count/*")
public class Count extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int count;  // contatore da incrementare ad ogni request ricevuta
	
	
	private ArrayList<String> list;  // memorizza la lista delle request (ip, porta, timestamp)
	private ServletContext context;  // per il path alle risorse della web app
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Count() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    /* init viene eseguita una volta sola alla prima request effettuata alla servlet */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.count = 0;
		
		this.context = getServletContext();	// initialize web app context	
		list = new ArrayList<String>();
	}
	
	/* Url Parser */
	public static String[] parseFullUrl(HttpServletRequest req) throws Exception {
	    String pathAfterContext = req.getRequestURI().substring(
	        req.getContextPath().length() + req.getServletPath().length() + 1);
	    ArrayList<String> res = new ArrayList<String>();
	    for (String val : pathAfterContext.split("/")) {
	        res.add(URLDecoder.decode(val, "UTF-8"));
	    }
	    String query = req.getQueryString();
	    if (query!=null) {
	        for (String val : query.split("&")) {
	            res.add(URLDecoder.decode(val, "UTF-8"));
	        }
	    }
	    return res.toArray(new String[0]);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.count++;
		
		list.add(request.getRemoteAddr()+":"+request.getRemotePort()+" on "+new Date().toString());
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String htmlbegin = "<html><head><title>Count Visitors</title></head><body><h1>Welcome</h1>";
		String htmlend = "</body></html>";
		String form = "<form method='get' action='"+this.context.getContextPath()+"/Count'><input type='hidden' name='reset' value='yes' /><input type='submit' value='reset'/></form>";
		String visitors = "";
		if(request.getParameter("reset")!=null && request.getParameter("reset").equalsIgnoreCase("yes")) {
			this.count = 0;
			list.clear();
			response.sendRedirect(this.context.getContextPath()+"/Count");
		}		
		
		Iterator i = list.iterator();
		
		int j=1;
		while(i.hasNext()) {
			visitors+= "<p>visitor n. "+j+": "+i.next()+"</p>";
			j++;
		}
		String path = "<p>ContextPath: "+request.getContextPath()+"</p>";
		path += "<p>ServletPath: "+request.getServletPath()+"</p>";
		
		String[] ulrArray; //double pi = Math.PI; double d = Math.cos(x);
		try {
			ulrArray = parseFullUrl(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ulrArray = new String[1];
			ulrArray[0] = "exception"; 
			e.printStackTrace();
		}
		String prtn =""; //int n = prtn.length();
		for (String s : ulrArray) {
			prtn += s+", ";
		}
		response.getWriter().println(htmlbegin+form+"<h3>visitors: "+this.count+"</h3>"+visitors+"<h4>"+path+prtn+"</h4>"+htmlend);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
