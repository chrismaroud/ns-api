package com.bitsfromspace.ns.api.rest;

import com.bitsfromspace.ns.api.Credentials;
import com.bitsfromspace.ns.api.NsApiError;
import com.bitsfromspace.ns.api.io.HttpTransport;
import com.bitsfromspace.ns.api.utils.IoUtils;
import com.bitsfromspace.ns.api.utils.StringUtils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class NsRestSessionImpl implements NsRestSession {

    public final static String REST_BASE_URL = "http://webservices.ns.nl/";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final HttpTransport httpTransport;
    private final String apiBaseUrl;
    private final JaxbContextCache jaxbCache;
    private final String authorizationHeader;

    public NsRestSessionImpl(HttpTransport httpTransport, Credentials credentials) {
        this(httpTransport, credentials, REST_BASE_URL);
    }

    public NsRestSessionImpl(HttpTransport httpTransport, Credentials credentials, String apiBaseUrl) {
        this.httpTransport = httpTransport;
        this.apiBaseUrl = apiBaseUrl;
        jaxbCache = new JaxbContextCache();
        authorizationHeader = IoUtils.getAuthorizationHeaderValue(credentials.getUserName(), credentials.getPassword());
    }

    @Override
    public <T> RestCall<T> call(Class<T> responseClass, final String serviceName) {
        final Unmarshaller unmarshaller = jaxbCache.createUnmarshaller(responseClass);
        return new RestCall<T>() {
            private final Map<String, String> parameters = new HashMap<>();

            @Override
            public RestCall<T> setParameter(String name, Object value) {
                String valueString = encode(value);
                if (valueString != null) {
                    parameters.put(name, valueString);
                }
                return this;
            }

            @Override
            public T execute() {
                try (InputStream in = httpTransport.get(apiBaseUrl + serviceName, parameters, singletonMap(AUTHORIZATION_HEADER, authorizationHeader))) {
                    //noinspection unchecked
                    String response = IoUtils.readFully(in);
                    try {
                        //noinspection unchecked
                        return (T) unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));// on-error response code == 200 :(
                    } catch (JAXBException jaxB) {
                        String error = StringUtils.readBetween(response, "<message>", "</message>");
                        //noinspection ConstantConditions
                        if (error != null && !error.isEmpty()) {
                            throw new NsApiError(error, jaxB);
                        }
                        throw jaxB;
                    }
                } catch (IOException | JAXBException ex) {
                    throw new NsApiError(ex);
                }
            }
        };
    }

    private String encode(Object paramValue) {
        if (paramValue == null) {
            return null;
        }
        return paramValue.toString();
    }
}
