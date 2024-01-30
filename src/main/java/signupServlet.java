

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class signupServlet
 */
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con = null;
        PreparedStatement preStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);
         // Get the session from the request
            HttpSession session = req.getSession();
            String usernameParam = req.getParameter("username");
            int ageParam=Integer.parseInt(req.getParameter("age"));
            String emailParam=req.getParameter("email");
            String passwordParam = req.getParameter("password");

            String query="insert into employee values(?,?,?,?)";
            PreparedStatement prstmt = con.prepareStatement(query);
            prstmt.setString(1, usernameParam);
            prstmt.setInt(2, ageParam);
            prstmt.setString(3, emailParam);
            prstmt.setString(4, passwordParam);
            int executeUpdate = prstmt.executeUpdate();
         // Set session attributes
            session.setAttribute("username", usernameParam);
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, res);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Error initializing database connection", e);
        } finally {
            try {
                if (preStatement != null) {
                    preStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
