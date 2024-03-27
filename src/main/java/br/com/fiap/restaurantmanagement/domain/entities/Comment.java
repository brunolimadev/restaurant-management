package br.com.fiap.restaurantmanagement.domain.entities;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents a comment
 */
public class Comment {

	private Long userId;

	private String comment;

	private Long idRestaurant;

	private LocalDateTime createAt;

	private Long rating;

	public Comment(Long userId, String comment, Long idRestaurant, LocalDateTime createAt, Long rating) {

		if (Objects.isNull(comment) || comment.isEmpty() || comment.length() < 2) {
			throw new IllegalArgumentException("Comment cannot be null or empty");
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("UserId cannot be null");
		}

		if (Objects.isNull(idRestaurant)) {
			throw new IllegalArgumentException("IdRestaurant cannot be null");
		}

		if (Objects.isNull(rating) || rating > 5 || rating < 1) {
			throw new IllegalArgumentException("The rating cannot be null and must be between 1 and 5");
		}

		this.userId = userId;
		this.comment = comment;
		this.idRestaurant = idRestaurant;
		this.createAt = createAt;
		this.rating = rating;
	}

	public Long getUserId() {
		return userId;
	}

	public String getComment() {
		return comment;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public Long getRating() {
		return rating;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment1 = (Comment) o;
		return Objects.equals(userId, comment1.userId) && Objects.equals(comment, comment1.comment) && Objects.equals(idRestaurant, comment1.idRestaurant) && Objects.equals(createAt, comment1.createAt) && Objects.equals(rating, comment1.rating);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, comment, idRestaurant, createAt, rating);
	}
}




