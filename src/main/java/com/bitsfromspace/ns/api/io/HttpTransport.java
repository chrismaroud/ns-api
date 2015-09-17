package com.bitsfromspace.ns.api.io;

import java.io.InputStream;
import java.util.Map;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public interface HttpTransport {

    InputStream get(String baseUrl, Map<String, String> parameters, Map<String, String> headers);
}
