

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")

public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the JWT token from the request header
        String token = request.getHeader("Authorization");

        // Validate the token
        boolean isValidToken = AuthenticationService.validateToken(token);

        // Send a response based on token validity
        if (isValidToken) {
            String username = AuthenticationService.getUsernameFromToken(token);
            out.println("<h1>Welcome, " + username + "!</h1>");
        } else {
            out.println("<h1>Please sign in or sign up</h1>");
            out.println("<a href='signin.html'>Sign In</a> | <a href='signup.html'>Sign Up</a>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle sign-in and sign-up requests here
    }
}