package servlets;

import com.google.gson.Gson;
import dao.RequestDAO;
import model.Request;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addRequest")
public class AddRequestServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDAO requestDAO = new RequestDAO();

		int userId = Integer.parseInt(request.getParameter("user_id"));
		int offeredServiceId = Integer.parseInt(request.getParameter("offered_service_id"));
		int requestedServiceId = Integer.parseInt(request.getParameter("requested_service_id"));
		String requestText = request.getParameter("request_text");
		System.out.println(requestText);

		// Create a new Request object
		Request newRequest = new Request();
		newRequest.setUser_id(userId);
		newRequest.setOffered_service_id(offeredServiceId);
		newRequest.setRequested_service_id(requestedServiceId);
		newRequest.setRequest_text(requestText);

		boolean isAdded = requestDAO.addRequest(newRequest);

		// Prepare the response
		Map<String, Boolean> jsonResponse = new HashMap<>();
        jsonResponse.put("success", isAdded);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(jsonResponse));
	}
}
