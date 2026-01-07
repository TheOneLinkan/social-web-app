package se.jensen.linkan.springboot.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException forId(Long id) {
        return new UserNotFoundException("Post med " + id + " hittades inte.");
    }
}
