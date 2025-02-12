package org.example;

public class CustomException extends Throwable {
    private String message;

    public CustomException(String message) {
        this.message = message;
    }
}
