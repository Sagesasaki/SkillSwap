package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Review;

public class ReviewDAO {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public ReviewDAO() {
        connection = null;
    }

    public ReviewDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertReview(Review review) {
        PreparedStatement ps = null;
        int result = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String query = "INSERT INTO reviews (reviewer_id, reviewed_id, review_text, rating) VALUES (?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, review.getReviewer_id());
            ps.setInt(2, review.getReviewed_id());
            ps.setString(3, review.getReview());
            ps.setInt(4, review.getRating());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(ps, connection);
        }
        return result > 0;
    }

    public boolean updateReview(Review review) {
        PreparedStatement ps = null;
        int result = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String query = "UPDATE reviews SET review_text = ?, rating = ? WHERE reviewer_id = ? AND reviewed_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, review.getReview());
            ps.setInt(2, review.getRating());
            ps.setInt(3, review.getReviewer_id());
            ps.setInt(4, review.getReviewed_id());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(ps, connection);
        }
        return result > 0;
    }

    public List<Review> getReviewsByUserId(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Review> reviews = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/skillswap?user=root&password=root");
            String query = "SELECT * FROM reviews WHERE reviewed_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                reviews.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            closeResources(rs, ps, connection);
        }
        return reviews;
    }

    private Review parseResultSet(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setReviewer_id(rs.getInt("reviewer_id"));
        review.setReviewed_id(rs.getInt("reviewed_id"));
        review.setReview(rs.getString("review_text"));
        review.setRating(rs.getInt("rating"));
        return review;
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
