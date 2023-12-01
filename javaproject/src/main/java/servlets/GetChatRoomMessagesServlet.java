package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import model.ChatRoomMessage;
import dao.ChatRoomMessageDAO;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/getChatRoomMessages")
public class GetChatRoomMessagesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChatRoomMessageDAO messagesDao = new ChatRoomMessageDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ChatRoomMessage> messages = messagesDao.loadAllMessages();
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
		        .create();
		String messagesJson = gson.toJson(messages);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(messagesJson);
	}

	public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
		private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		@Override
		public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
			return new JsonPrimitive(formatter.format(localDateTime));
		}

		@Override
		public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			return LocalDateTime.parse(json.getAsString(), formatter);
		}
	}
}
