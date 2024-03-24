package br.com.fiap.restaurantmanagement.domain.entities;

import java.util.Objects;

/**
 * This class represents a comment
 */
public class Comment {
    private String userName;
    private String comment;

    public Comment(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }

	@Override
	public int hashCode() {
		return Objects.hash(comment, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(userName, other.userName);
	}
    
    
}
