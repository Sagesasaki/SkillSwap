package servlets;

import com.google.gson.Gson;
import dao.RequestDAO;
import dao.BarterDAO;
import model.Barter;
import model.Request;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/updateRequestStatus")
public class UpdateRequestStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDAO requestDAO = new RequestDAO();
        BarterDAO barterDAO = new BarterDAO();
        int requestId = Integer.parseInt(request.getParameter("request_id"));
        String status = request.getParameter("status");


        // Update the request status
        boolean isUpdated = requestDAO.updateRequestStatus(requestId, status);

        // Prepare and send the JSON response
        String jsonResponse;
        if (isUpdated) {
        	Map<String, Boolean> responseMap = new HashMap<>();
            responseMap.put("success", true);
            jsonResponse = new Gson().toJson(responseMap);

            // If the request is accepted, create a new barter entry
            if ("accepted".equals(status)) {
                // Retrieve the request object
                Request req = requestDAO.getRequestById(requestId);
                System.out.println("we've made it here");
                if (req != null) {
                    // Create and insert the new Barter object
                    Barter barter = new Barter();
                    barter.setIs_complete(false); // initial state
                    barter.setService_id1(req.getOffered_service_id());
                    barter.setService_id2(req.getRequested_service_id());
                    boolean barterAdded = barterDAO.insertBarter(barter);
                    if (!barterAdded) {
                        System.out.println("Barter creation failed");
                    }
                } else {
                    System.out.println("Request not found");
                }
            }
        } else {
        	Map<String, Boolean> responseMap = new HashMap<>();
            responseMap.put("success", false);
            jsonResponse = new Gson().toJson(responseMap);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
