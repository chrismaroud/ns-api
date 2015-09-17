package com.bitsfromspace.ns.api.rest;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public interface NsRestSession {

    <T> RestCall<T> call(Class<T> responseClass, String serviceName);
}
