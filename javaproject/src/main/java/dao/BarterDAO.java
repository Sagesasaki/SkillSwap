package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Barter;
import model.BarterInfo;

public class BarterDAO {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;

	public BarterDAO() {
		connection = null;
	}

	public BarterDAO(Connection connection) {
		this.connection = connection;
	}

	public List<BarterInfo> getBartersForUser(int userId) {
		List<BarterInfo> barters = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String sql = "SELECT " + "    b.is_complete, " + "    u1.name AS user_name, "
					+ "    u2.name AS other_user_name, " + "    s1.name AS user_service_name, "
					+ "    s2.name AS other_user_service_name " + "FROM barters AS b "
					+ "    JOIN services AS s1 ON b.service_id_1 = s1.service_id "
					+ "    JOIN services AS s2 ON b.service_id_2 = s2.service_id "
					+ "    JOIN users AS u1 ON s1.user_id = u1.user_id "
					+ "    JOIN users AS u2 ON s2.user_id = u2.user_id " + "WHERE u1.user_id = ? OR u2.user_id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, userId);

			rs = ps.executeQuery();

			while (rs.next()) {
				BarterInfo barter = new BarterInfo();
				barter.setIsComplete(rs.getBoolean("is_complete"));
				barter.setUserName(rs.getString("user_name"));
				barter.setOtherUserName(rs.getString("other_user_name"));
				barter.setUserServiceName(rs.getString("user_service_name"));
				barter.setOtherUserServiceName(rs.getString("other_user_service_name"));

				barters.add(barter);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}

		return barters;
	}

	public boolean insertBarter(Barter barter) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String query = "INSERT INTO barters (is_complete, service_id_1, service_id_2) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, barter.getIs_complete());
			ps.setInt(2, barter.getService_id1());
			ps.setInt(3, barter.getService_id2());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(ps, connection);
		}
		return result > 0;
	}

	public boolean updateBarterStatus(int serviceId1, int serviceId2, boolean isComplete) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String query = "UPDATE barters SET is_complete = ? WHERE service_id_1 = ? AND service_id_2 = ?";
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, isComplete);
			ps.setInt(2, serviceId1);
			ps.setInt(3, serviceId2);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(ps, connection);
		}
		return result > 0;
	}

	public Barter getBarter(int serviceId1, int serviceId2) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Barter barter = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String query = "SELECT * FROM barters WHERE service_id_1 = ? AND service_id_2 = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, serviceId1);
			ps.setInt(2, serviceId2);
			rs = ps.executeQuery();
			if (rs.next()) {
				barter = parseResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}
		return barter;
	}

	public List<Barter> getAllBartersForUser(int userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Barter> barters = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			String query = "SELECT * FROM barters WHERE service_id_1 IN (SELECT service_id FROM services WHERE user_id = ?) OR service_id_2 IN (SELECT service_id FROM services WHERE user_id = ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				barters.add(parseResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}
		return barters;
	}

	private Barter parseResultSet(ResultSet rs) throws SQLException {
		Barter barter = new Barter();
		barter.setIs_complete(rs.getBoolean("is_complete"));
		barter.setService_id1(rs.getInt("service_id_1"));
		barter.setService_id2(rs.getInt("service_id_2"));
		return barter;
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
