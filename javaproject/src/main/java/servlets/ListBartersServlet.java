package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BarterDAO;
import model.BarterInfo;

@WebServlet("/listBarters")
public class ListBartersServlet extends HttpServlet {

	
	private static final long serialVersionUID = 4269663722972780339L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve user ID from request
		int userId = Integer.parseInt(request.getParameter("user_id"));

		// Get the list of barters for the user
		BarterDAO barterDAO = new BarterDAO();
		List<BarterInfo> barters = barterDAO.getBartersForUser(userId);

		String jsonResponse = new Gson().toJson(barters);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse);
	}
}
