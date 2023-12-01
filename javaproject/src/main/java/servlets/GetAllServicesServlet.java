package servlets;

import com.google.gson.Gson;
import dao.ServiceDAO;
import model.Service;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listAllServices")
public class GetAllServicesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            ServiceDAO serviceDAO = new ServiceDAO();
            List<Service> services = serviceDAO.loadAllServices();

      
            String json = new Gson().toJson(services);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }
    }
}
