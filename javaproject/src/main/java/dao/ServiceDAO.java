package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.Service;

public class ServiceDAO {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;

	public ServiceDAO() {
		connection = null;
	}

	public ServiceDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean deleteService(int serviceId) {
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String statementString = "DELETE FROM Services WHERE service_id = ?";
			ps = connection.prepareStatement(statementString);
			ps.setInt(1, serviceId);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return false;
		} finally {
			closeResources(ps, connection);
		}
	}

	public int registerService(Service service) {
		PreparedStatement ps = null;
		int result = 0;
		String statementString = "INSERT INTO Services (name, description, user_id) VALUES (?, ?, ?)";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, service.getName());
			ps.setString(2, service.getDescription());
			ps.setInt(3, service.getUser_id());
			result = ps.executeUpdate();
			if (result > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					service.setService_id(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(ps, connection);
		}
		return result;
	}

	public Service loadService(int serviceId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Service service = null;
		String statementString = "SELECT * FROM Services WHERE service_id=?";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString);
			ps.setInt(1, serviceId);
			rs = ps.executeQuery();
			if (rs.next()) {
				service = parseResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}
		return service;
	}

	public List<Service> loadServicesByUserId(int userId) {
		List<Service> services = new ArrayList<Service>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String statementString = "SELECT * FROM Services WHERE user_id=?";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				services.add(parseResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}

		return services;
	}
	
	public List<Service> loadAllServices() {
        List<Service> services = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String statementString = "SELECT * FROM Services";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            ps = connection.prepareStatement(statementString);
            rs = ps.executeQuery();

            while (rs.next()) {
                services.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(rs, ps, connection);
        }

        return services;
    }

	private Service parseResultSet(ResultSet rs) throws SQLException {
		Service service = new Service();
		service.setService_id(rs.getInt("service_id"));
		service.setName(rs.getString("name"));
		service.setDescription(rs.getString("description"));
		service.setUser_id(rs.getInt("user_id"));
		return service;
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
