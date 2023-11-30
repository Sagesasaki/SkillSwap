package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.User;
public class UserDAO {
	static {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	private Connection connection;
	public UserDAO() {
		connection = null;
	}
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	public int registerUser(User user) {
	    PreparedStatement ps = null;
	    int result = 0;
	    String statementString = "INSERT IGNORE INTO Users (username, password, name) VALUES (?, ?, ?)";
	    ResultSet generatedKeys = null;
	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
	        ps = connection.prepareStatement(statementString, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getName());

	        result = ps.executeUpdate();

	        if (result > 0) {
	            generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                user.setUser_id(generatedKeys.getInt(1));
	                System.out.println(user.getUser_id());
                }
	        } else {
	            System.out.println("Username taken.");
	        }
	    } catch (SQLException e) {
	        System.out.println("SQLException: " + e.getMessage());
	    } finally {
	        closeResources(generatedKeys, ps, connection);
	    }
	    return result;
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
	public User loadUser(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String statementString = "SELECT * FROM Users WHERE username=?"+
		" AND password=?";
		try {
			
			connection = DriverManager
		            .getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
			ps = connection.prepareStatement(statementString);
			ps.setString(1, username); 
			ps.setString(2, password); 
			rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
				if(counter > 1) {
					System.out.println("More than one user with these credential found.");
				}
				else {
					user = parseResultSet(rs);
				}
			}
		} 
		catch (SQLException e) {
        	System.out.println ("SQLException: " + e.getMessage());
        } finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.out.println("in load");
				System.out.println("sqle: " + sqle.getMessage());
			}
        }
		return user;
		//https://www.baeldung.com/jdbc-resultset 2 lines 11/24
	}
	
	
	private User parseResultSet(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUser_id(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		return user;
	}
//	public static void main(String[] args) {
//		User user = new User();
//		user.setName("Bob Bobson");
//		user.setPassword("pass");
//		user.setUser_id(1);
//		user.setUsername("username");
//		//DBConnector.startConnection();
//		UserDAO uDao = new UserDAO();
//		uDao.registerUser(user);
//		User user2 = uDao.loadUser("username", "pass");
//		System.out.println(user2.getUser_id());
//		System.out.println(user2.getUsername());
//		System.out.println(user2.getPassword());
//		System.out.println(user2.getName());
//	}
	
}

