package loginPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	public static Connection con;
	public static Statement stmt;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/user";
	        String username = "root";
	        String password = "root";
	        con = DriverManager.getConnection(url, username, password);
	    } catch ( SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new ServletException("Error initializing database connection", e);
	    }
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		if(checkDatabase(username,password)) {
			RequestDispatcher rd=req.getRequestDispatcher("home.html");
			rd.forward(req, res);
		}
		else {
			System.out.println("person not found in the database");
			RequestDispatcher rd=req.getRequestDispatcher("signUp.html");
			rd.forward(req, res);
		}
		
	}
	
	public boolean checkDatabase(String username,String password) {
		
		try {
			String query="select * from employee where username=? and password=?";
			PreparedStatement preStatement = con.prepareStatement(query);
			preStatement.setString(0,username);
			preStatement.setString(1, password);
			ResultSet executeQuery = preStatement.executeQuery();
			if(executeQuery.next())  {
				return true;
			}
				 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return false;
	}
	public void destroy() {
	    try {
	        if (con != null && !con.isClosed()) {
	            con.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
