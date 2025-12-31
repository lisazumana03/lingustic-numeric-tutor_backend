package za.co.lzinc.heriplay.domain.feedback;

import jakarta.persistence.*;
import za.co.lzinc.heriplay.domain.authentication.User;

import java.io.Serializable;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private int rating;
    private String description;
    @ManyToOne
    private User user;

    protected Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.description = builder.description;
        this.user = builder.user;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {
        private int reviewId;
        private int rating;
        private String description;
        private User user;

        public Builder setReviewId(int reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.description = review.description;
            this.user = review.user;
            return this;
        }

        public Review build() {
            return new Review(this);
        }

    }

}
