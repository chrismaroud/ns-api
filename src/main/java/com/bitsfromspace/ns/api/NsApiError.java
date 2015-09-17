package com.bitsfromspace.ns.api;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public class NsApiError extends RuntimeException {
    private final int code;

    public NsApiError(String message) {
        this(0, message);
    }

    public NsApiError(String message, Throwable cause) {
        super(message, cause);
        code = 0;
    }

    public NsApiError(Throwable cause) {
        super(cause);
        this.code = 0;
    }

    public NsApiError(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
