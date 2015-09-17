package com.bitsfromspace.ns.api.rest;

import com.bitsfromspace.ns.api.NsApiError;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */

public class JaxbContextCache {

    private final Map<Class<?>, JAXBContext> classToContextMap;
    private final ReadWriteLock rwLock;

    public JaxbContextCache() {
        rwLock = new ReentrantReadWriteLock();
        classToContextMap = new HashMap<>();
    }

    public Unmarshaller createUnmarshaller(Class<?> type) {
        JAXBContext context;

        rwLock.readLock().lock();
        try {
            context = classToContextMap.get(type);
        } finally {
            rwLock.readLock().unlock();
        }
        if (context == null) {
            rwLock.writeLock().lock();
            try {
                context = classToContextMap.get(type);
                if (context == null) {
                    context = JAXBContext.newInstance(type);
                    classToContextMap.put(type, context);
                }
            } catch (JAXBException jaxb) {
                throw new NsApiError(jaxb);
            } finally {
                rwLock.writeLock().unlock();
            }
        }
        try {
            return context.createUnmarshaller();
        } catch (JAXBException jaxb) {
            throw new NsApiError(jaxb);
        }
    }
}
