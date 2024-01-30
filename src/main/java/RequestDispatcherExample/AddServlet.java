package RequestDispatcherExample;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		int a=Integer.parseInt(req.getParameter("number1"));
		int b=Integer.parseInt(req.getParameter("number2"));
		int c=a+b;
		req.setAttribute("c", c);
		RequestDispatcher rd=req.getRequestDispatcher("squareServlet");
		rd.forward(req, res);
	}
}
