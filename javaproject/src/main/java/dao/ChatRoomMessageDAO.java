package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatRoomMessage;

public class ChatRoomMessageDAO {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;

	public ChatRoomMessageDAO() {
		connection = null;
	}

	public ChatRoomMessageDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean insertMessage(ChatRoomMessage message) {
		PreparedStatement ps = null;
		boolean result = false;
		String statementString = "INSERT INTO chatroom_messages (sender_id, message_text) VALUES (?, ?)";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString);
			ps.setInt(1, message.getSenderId());
			ps.setString(2, message.getMessageText());
			int rowsAffected = ps.executeUpdate();
			result = rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(ps, connection);
		}
		return result;
	}

	public List<ChatRoomMessage> loadAllMessages() {
		List<ChatRoomMessage> messages = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String statementString = "SELECT chatroom_messages.*, users.name AS sender_name " + "FROM chatroom_messages "
				+ "JOIN users ON chatroom_messages.sender_id = users.user_id "
				+ "ORDER BY chatroom_messages.timestamp ASC";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString);
			rs = ps.executeQuery();

			while (rs.next()) {
				messages.add(parseResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}

		return messages;
	}

	private ChatRoomMessage parseResultSet(ResultSet rs) throws SQLException {
		ChatRoomMessage message = new ChatRoomMessage();
		message.setMessageId(rs.getInt("message_id"));
		message.setSenderId(rs.getInt("sender_id"));
		message.setMessageText(rs.getString("message_text"));
		message.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime()); // Convert Timestamp to LocalDateTime
		message.setSender(rs.getString("sender_name")); // Set the sender's name
		return message;
	}

	private void closeResources(AutoCloseable... resources) {
		for (AutoCloseable resource : resources) {
			try {
				if (resource != null) {
					resource.close();
				}
			} catch (Exception e) {
				System.out.println("Error closing resource: " + e.getMessage());
			}
		}
	}
}
