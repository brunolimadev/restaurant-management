package br.com.fiap.restaurantmanagement.domain.entities;

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
}
