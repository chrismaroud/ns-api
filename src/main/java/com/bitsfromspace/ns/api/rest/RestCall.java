package com.bitsfromspace.ns.api.rest;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public interface RestCall<T> {

    RestCall<T> setParameter(String name, Object value);

    T execute();

}
