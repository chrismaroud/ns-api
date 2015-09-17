package com.bitsfromspace.ns.api.io;

import com.bitsfromspace.ns.api.NsApiError;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static java.net.URLEncoder.encode;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public class HttpTransportImpl implements HttpTransport {

    private final static String UTF8 = "UTF8";

    @Override
    public InputStream get(String baseUrl, Map<String, String> parameters, Map<String, String> headers) {
        String url = buildUrl(baseUrl, parameters);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            for (Map.Entry<String, String> header : headers.entrySet()) {
                if (header.getValue() != null) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }
            if (connection.getResponseCode() < 200 || connection.getResponseCode() > 299) {
                throw new NsApiError(connection.getResponseCode(), connection.getResponseMessage());
            }
            return connection.getInputStream();
        } catch (IOException ioex) {
            throw new NsApiError(ioex);
        }
    }

    private String buildUrl(String baseUrl, Map<String, String> parameters) {

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