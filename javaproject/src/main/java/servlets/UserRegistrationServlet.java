package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (result == 1) {
            int userId = newUser.getUser_id();
            System.out.println("userID: " + userId);
            request.getSession().setAttribute("user", newUser);
            String jsonResponse = "{\"userId\": " + userId + ", \"name\": \"" + newUser.getName() + "\"}";
            response.getWriter().write(jsonResponse);

        } else {
            response.getWriter().write("{\"error\": \"Username is taken\"}");
        }
    }
}
