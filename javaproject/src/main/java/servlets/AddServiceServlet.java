package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ServiceDAO;
import model.Service;

@WebServlet("/addService")
public class AddServiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int user_id = Integer.parseInt(request.getParameter("userid"));

            ServiceDAO sDao = new ServiceDAO();
            Service service = new Service();
            service.setName(name);
            service.setUser_id(user_id);
            service.setDescription(description);

            sDao.registerService(service);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (NumberFormatException e) {
            // Send error response for invalid user ID format
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"Invalid user ID format.\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"Error adding service: " + e.getMessage() + "\"}");
        }
    }
}
