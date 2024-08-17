package io.nerdbyteslns.productcatalogservice.enums;

public enum DefaultHttpMessages {
    SUCCESS("Success"),
    CREATED("Created"),
    ACCEPTED("Accepted"),
    BAD_REQUEST("Bad Request"),
    FAILED_DEPENDENCY("Failed Dependency"),
    NOT_FOUND("Not Found"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    CONFLICT("Conflict");

    private final String message;

    DefaultHttpMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
