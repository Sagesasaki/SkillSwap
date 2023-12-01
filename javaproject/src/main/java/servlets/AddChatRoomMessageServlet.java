package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.ChatRoomMessageDAO;
import model.ChatRoomMessage;

@WebServlet("/addChatRoomMessage")
public class AddChatRoomMessageServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 628152165040394250L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int senderId = Integer.parseInt(request.getParameter("senderId"));
            String messageText = request.getParameter("messageText");

            ChatRoomMessageDAO crmDao = new ChatRoomMessageDAO();
            ChatRoomMessage message = new ChatRoomMessage();
            message.setSenderId(senderId);
            message.setMessageText(messageText);

            boolean insertResult = crmDao.insertMessage(message);

            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("success", insertResult);
            if (insertResult) {
                jsonResponse.put("messageId", message.getMessageId());
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(jsonResponse));

        } catch (NumberFormatException e) {
            // Send error response for invalid sender ID format
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Invalid sender ID format.");
            response.getWriter().write(new Gson().toJson(errorResponse));

        } catch (Exception e) {
            // Send error response for any other exceptions
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Error adding message: " + e.getMessage());
            response.getWriter().write(new Gson().toJson(errorResponse));
        }
    }
}
