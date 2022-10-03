package com.bix.enums;

/**
 * Enum to represent the status of Bix.
 * This includes safe terminations, runtime exceptions, timeouts, authentication status, misc. errors.
 */

/*
 * How to Read Status Codes:
 * The 1st digit of the status code indicates a general group.
 * e.g.: Status codes starting with '1' indicate a File/Resource error.
 *
 * The 2nd digit further categorizes the status into a subgroup.
 * e.g.: Status codes starting with '11' indicate that a File/Resource is not found.
 *
 * Unknown errors in each group end with a '00'.
 * e.g.: The status code for an unknown File/Resource error is '100'.
 */

public enum StatusCode {

    // 0. Safe Termination.
    SAFE_TERMINATION(0, "Safe Termination."),

    // 1. File/Resource Errors.
    UNKNOWN_RESOURCE_ERROR(100, "An unknown file/resource error has occurred."),

    // 1.1 Resource Not Found.
    CONFIG_FILE_NOT_FOUND(110, "The config.properties file could not be found."),
    VAULT_FILE_NOT_FOUND(111, "The vault.db file could not be found."),
    CONSOLE_NOT_FOUND(112, "Unable to find a system console associated with the current JVM."),

    // 1.2 Resource Access Error.
    ERROR_ACCESSING_CONFIG_FILE(120, "An error occurred while accessing the config.properties file."),
    ERROR_ACCESSING_VAULT_FILE(121, "An error occurred while accessing the vault.db file."),

    // 2. Setup Errors.
    UNKNOWN_SETUP_ERROR(200,"Bix setup process failed for an unknown reason."),

    MASTER_PASSWORD_SETUP_FAILED(210, "Master Password setup failed."),

    // 3. Authentication Failure.
    UNKNOWN_AUTHENTICATION_ERROR(300, "An unknown error occurred during User Authentication."),

    AUTHENTICATION_FAILED(310, "User Authentication Failed."),

    // 4. Idle Session Timeout.
    IDLE_SESSION_TIMEOUT(400, "Bix session terminated due to inactivity.");
    //--------------------------------------------------------------------------------------------

    private final int statusCode;
    private final String message;

    StatusCode(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() { return statusCode; }

    public String getMessage() { return message; }

} // enum StatusCode