package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/registerUser")
public class UserRegistrationServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setName(name);

		UserDAO userDao = new UserDAO();

		int result = userDao.registerUser(newUser);

		if (result == 1) {
			request.getSession().setAttribute("user", newUser);
			response.sendRedirect("201_explorePage.html");
		} else {
			response.sendRedirect("201_signup.html?error=Username is taken");
		}
	}
}
