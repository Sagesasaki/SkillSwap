package model;

public class Review {
	private int reviewer_id;
	private int reviewed_id;
	private String review_text;
	private int rating;
	public Review(){
		reviewer_id = 0;
		reviewed_id = 0;
		review_text = null;
		rating = 0;
	}
	public int getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		this.reviewer_id = reviewer_id;
	}
	public int getReviewed_id() {
		return reviewed_id;
	}
	public void setReviewed_id(int reviewed_id) {
		this.reviewed_id = reviewed_id;
	}
	public String getReview() {
		return review_text;
	}
	public void setReview(String review_text) {
		this.review_text = review_text;
	}
	public int getRating() {
			return rating;
	}
	public void setRating(int rating) {
			this.rating = rating;
	}	
}
