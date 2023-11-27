package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.User;
public class UserDAO {
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
		String statementString = "INSERT IGNORE INTO Users" +
                " (username, password, name) VALUES " +
                " ( ?, ?, ?)";
        try {
        	// Establishing connection
        	connection = DriverManager
            .getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
        	//Preparing a statement
            PreparedStatement preparedStatement = connection.prepareStatement(statementString);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            // No insertion happened because the username is already in the table
            if(result < 1) {
            	System.out.println("Username taken.");
            }
        } catch (SQLException e) {
        	System.out.println ("SQLException: " + e.getMessage());
        } finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
        return result;
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

