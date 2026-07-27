package com.cloudwise.exception;

/**
 * Custom exception thrown when a cloud resource is not found in the database.
 *
 * <p>This is caught by the GlobalExceptionHandler and converted into a
 * clean 404 JSON response.</p>
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
