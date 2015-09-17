package com.bitsfromspace.ns.api.utils;


import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class IoUtilsTest {

    @Test
    public void testReadFully() {
        ByteArrayInputStream in = new ByteArrayInputStream("Hello world!".getBytes());
        assertEquals("Hello world!", IoUtils.readFully(in));

        // now test something that exceeds the initial buffer:
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            stringBuilder.append("Hello World!\n");
        }
        String read = IoUtils.readFully(new ByteArrayInputStream(stringBuilder.toString().getBytes()));

        String[] lines = read.split("\n");
        assertEquals(10_000, lines.length);
        for (String line : lines) {
            assertEquals("Hello World!", line);
        }

    }

}