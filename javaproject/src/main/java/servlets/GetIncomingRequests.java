package servlets;

import com.google.gson.Gson;
import dao.RequestDAO;
import model.Request;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/incomingRequests")
public class GetIncomingRequests extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8833068841077337345L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user ID from session or however you are storing the current user
        int userId = Integer.parseInt(request.getParameter("user_id")); // Example, adjust as needed

        RequestDAO requestDAO = new RequestDAO();
        List<Request> incomingRequests = requestDAO.viewIncomingRequests(userId);

        String jsonResponse = new Gson().toJson(incomingRequests);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
