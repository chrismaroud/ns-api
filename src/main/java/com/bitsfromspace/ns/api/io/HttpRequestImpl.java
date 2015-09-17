package com.bitsfromspace.ns.api.io;

import com.bitsfromspace.ns.api.NsApiError;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.bitsfromspace.ns.api.utils.IoUtils.readFully;
import static java.net.URLEncoder.encode;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public class HttpRequestImpl implements HttpRequest {

    private final static String UTF8 = "UTF8";

    private final String baseUrl;
    private final Map<String, String> headers;
    private final Map<String, String> parameters;

    public HttpRequestImpl(String baseUrl) {
        this.baseUrl = baseUrl;
        headers = new HashMap<>();
        parameters = new HashMap<>();
    }

    @Override
    public HttpRequest addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public HttpRequest addParameter(String key, String value) {
        parameters.put(key, value);
        return this;
    }

    @Override
    public String get() {
        String url = buildUrl();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            for (Map.Entry<String, String> header : headers.entrySet()) {
                if (header.getValue() != null) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }
            if (connection.getResponseCode() < 200 || connection.getResponseCode() > 299) {
                try (InputStream err = connection.getErrorStream()) {
                    throw new NsApiError(connection.getResponseCode(), readFully(err));
                }
            }
            try (InputStream in = connection.getInputStream()) {
                return readFully(in);
            }
        } catch (IOException ioex) {
            throw new NsApiError(ioex);
        }
    }

    private String buildUrl() {

        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            if (parameter.getValue() == null) {
                continue;
            }
            try {
                queryString.append(queryString.length() == 0 ? '?' : '&')
                        .append(encode(parameter.getValue(), UTF8))
                        .append('=')
                        .append(encode(parameter.getValue(), UTF8));
            } catch (UnsupportedEncodingException uee) {
                throw new NsApiError(uee);
            }

        }
        return baseUrl + queryString.toString();
    }
}
