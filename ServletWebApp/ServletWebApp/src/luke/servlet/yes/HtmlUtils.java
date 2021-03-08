package luke.servlet.yes;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.Scanner;

/* Usage:
     inside a servlet, call 
     HtmlUtils.getHtml(this, filename);
*/
public class HtmlUtils {
	
	public static String getHtmlfromFile(HttpServlet servlet, String filename) {
		ServletContext servletContext = servlet.getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String name = contextPath + filename;						
		File f = new File(name);
		
		if(!f.exists()  || !f.isFile())
			return "<h2>Error: the file "+name+" does not exist!</h2>";
		
		try {
			Scanner input = new Scanner(f);
			String text = "";
			while (input.hasNextLine()) {
				text += input.nextLine();
				//System.out.println(line);
			}
			input.close();
			return text;
		}  catch (Exception ex) {
			ex.printStackTrace();
			return "<h2>Error: Exception trying to read file "+name+" !</h2>";
		}
	}	
	public static String header(int n, String text) {
		if(n>6) n=6;
		if(n<1) n=1;
		return "<h"+n+">"+text+"</h"+n+">";
	}
	public static String paragraph(String text) {
		return "<p>"+text+"</p>";
	}
	public static String simplePage(String title, String body) {
		return "<!DOCTYPE html><html><head><title>"+title
				+"</title></head><body>"+body+"</body></html>";
	}
	
}