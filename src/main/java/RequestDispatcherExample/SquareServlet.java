package RequestDispatcherExample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SquareServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int c=(int)req.getAttribute("c");
		PrintWriter out=res.getWriter();
		out.println("The sum of two number is"+c);
		out.println("The square of the result is"+(c*c));
		
	}
}
