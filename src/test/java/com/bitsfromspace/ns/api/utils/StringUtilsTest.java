package com.bitsfromspace.ns.api.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chris
 * @since 23-09-15.
 */
public class StringUtilsTest {

    @Test
    public void test() {
        assertEquals("hi!", StringUtils.readBetween("hello <world>hi!</world>dkewjdlkwejd", "<world>", "</world>"));
        assertEquals("", StringUtils.readBetween("hello <world>hi!</worl>dkewjdlkwejd", "<world>", "</world>"));
        assertEquals("", StringUtils.readBetween("hello <orld>hi!</world>dkewjdlkwejd", "<world>", "</world>"));
        assertEquals("", StringUtils.readBetween("", "<world>", "</world>"));
        assertEquals("", StringUtils.readBetween(null, "<world>", "</world>"));
    }

}