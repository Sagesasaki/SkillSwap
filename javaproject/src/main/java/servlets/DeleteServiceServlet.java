package servlets;

import dao.ServiceDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteService")
public class DeleteServiceServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));

        ServiceDAO serviceDAO = new ServiceDAO();
        boolean success = serviceDAO.deleteService(serviceId);

        if (success) {
        	response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            // Handle the case where deletion fails, e.g., send an error message
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"Unable to add service.\"}");
        }
    }
}
