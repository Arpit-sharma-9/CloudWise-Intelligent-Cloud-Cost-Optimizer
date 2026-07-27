package com.cloudwise.exception;

import java.time.LocalDateTime;

/**
 * ErrorResponse is the standard JSON body returned whenever an exception
 * is thrown by the application.
 *
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "timestamp": "2026-07-27T10:00:00",
 *   "status": 404,
 *   "message": "Resource not found with id: 99"
 * }
 * </pre>
 */
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
