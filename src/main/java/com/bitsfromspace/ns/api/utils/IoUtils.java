package com.bitsfromspace.ns.api.utils;

import com.bitsfromspace.ns.api.NsApiError;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class IoUtils {

    private IoUtils() {
    }

    public static String readFully(InputStream in) {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            char[] buff = new char[4096];
            StringBuilder result = new StringBuilder();

            for (int read = reader.read(buff); read != -1; read = reader.read(buff)) {
                result.append(buff, 0, read);
            }
            return result.toString();
        } catch (IOException ioEx) {
            throw new NsApiError(ioEx);
        }
    }

    public static String getAuthorizationHeaderValue(String username, String password) {
        return "Basic " + DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());
    }
}
