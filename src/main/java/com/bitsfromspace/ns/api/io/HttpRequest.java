package com.bitsfromspace.ns.api.io;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public interface HttpRequest {
    HttpRequest addParameter(String key, String value);

    HttpRequest addHeader(String key, String value);

    String get();
}
