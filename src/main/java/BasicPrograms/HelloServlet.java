package BasicPrograms;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello Servlet World</h1>");
        out.println("</body>");
        out.println("</html>");

	}
}
