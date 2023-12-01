package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Request;

public class RequestDAO {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	Connection connection;
    public RequestDAO() {
        connection = null;
    }

    public boolean addRequest(Request request) {
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String sql = "INSERT INTO requests (user_id, offered_service_id, requested_service_id, request_text, status) VALUES (?, ?, ?, ?, 'pending')";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, request.getUser_id());
            ps.setInt(2, request.getOffered_service_id());
            ps.setInt(3, request.getRequested_service_id());
            ps.setString(4, request.getRequest_text());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps);
            closeResources(connection);
        }
    }

    public boolean deleteRequest(int requestId) {
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String sql = "DELETE FROM requests WHERE request_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, requestId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps);
            closeResources(connection);
        }
    }

    public List<Request> viewOutgoingRequests(int userId) {
        List<Request> requests = new ArrayList<Request>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String sql = "SELECT * FROM requests WHERE user_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(rs);
            closeResources(ps);
            closeResources(connection);
        }
        return requests;
    }

    public List<Request> viewIncomingRequests(int userId) {
        List<Request> requests = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String sql = "SELECT r.* FROM requests r JOIN services s ON r.requested_service_id = s.service_id WHERE s.user_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(rs);
            closeResources(ps);
            closeResources(connection);
        }
        return requests;
    }


    public boolean updateRequestStatus(int requestId, String status) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String sql = "UPDATE requests SET status = ? WHERE request_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, requestId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            closeResources(ps);
            closeResources(connection);
        }
    }

    private void closeResources(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.out.println("Error closing resource: " + e.getMessage());
            }
        }
    }

    private Request parseResultSet(ResultSet rs) throws SQLException {
        Request request = new Request();
        request.setUser_id(rs.getInt("user_id"));
        request.setOffered_service_id(rs.getInt("offered_service_id"));
        request.setRequested_service_id(rs.getInt("requested_service_id"));
        request.setRequest_text(rs.getString("request_text"));
        request.setStatus(rs.getString("status"));
        return request;
    }

}
