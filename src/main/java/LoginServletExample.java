

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
import java.sql.Statement;

@WebServlet("/LoginServletExample")
public class LoginServletExample extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement preStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);

            String usernameParam = req.getParameter("username");
            String passwordParam = req.getParameter("password");
         // Get the session from the request
            HttpSession session = req.getSession();
            String query = "select * from employee where username=? and password=?";
            preStatement = con.prepareStatement(query);
            preStatement.setString(1, usernameParam);
            preStatement.setString(2, passwordParam);

            ResultSet executeQuery = preStatement.executeQuery();

            if (executeQuery.next()) {
            	// Set session attributes
                session.setAttribute("username", usernameParam);
                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                rd.forward(req, res);
            } else {
                System.out.println("Person not found in the database");
                RequestDispatcher rd = req.getRequestDispatcher("signUp.html");
                rd.forward(req, res);
            }

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
