package com.bitsfromspace.ns.api.types;

import com.bitsfromspace.ns.api.NsApiError;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class Iso8601ParserTest {

    @Test
    public void testNull() {
        Assert.assertEquals(0, Iso8601Parser.parsePeriodMinutes(null));
    }

    @Test
    public void testEmpty() {
        Assert.assertEquals(0, Iso8601Parser.parsePeriodMinutes(""));
    }

    @Test
    public void testMinutes() {
        Assert.assertEquals(1, Iso8601Parser.parsePeriodMinutes("PT1M"));
        Assert.assertEquals(10, Iso8601Parser.parsePeriodMinutes("PT10M"));
        Assert.assertEquals(100, Iso8601Parser.parsePeriodMinutes("PT100M"));
        Assert.assertEquals(1000, Iso8601Parser.parsePeriodMinutes("PT1000M"));
        Assert.assertEquals(10000, Iso8601Parser.parsePeriodMinutes("PT10000M"));
        Assert.assertEquals(100000, Iso8601Parser.parsePeriodMinutes("PT100000M"));
    }

    @Test
    public void testSeconds() {
        Assert.assertEquals(1, Iso8601Parser.parsePeriodMinutes("PT1S"));
        Assert.assertEquals(1, Iso8601Parser.parsePeriodMinutes("PT60S"));
        Assert.assertEquals(2, Iso8601Parser.parsePeriodMinutes("PT119S"));
        Assert.assertEquals(5, Iso8601Parser.parsePeriodMinutes("PT300S"));
        Assert.assertEquals(50, Iso8601Parser.parsePeriodMinutes("PT3000S"));
        Assert.assertEquals(51, Iso8601Parser.parsePeriodMinutes("PT3001S"));
    }

    @Test
    public void testHours() {
        Assert.assertEquals(60, Iso8601Parser.parsePeriodMinutes("PT1H"));
        Assert.assertEquals(600, Iso8601Parser.parsePeriodMinutes("PT10H"));
        Assert.assertEquals(6000, Iso8601Parser.parsePeriodMinutes("PT100H"));
        Assert.assertEquals(60000, Iso8601Parser.parsePeriodMinutes("PT1000H"));
        Assert.assertEquals(600000, Iso8601Parser.parsePeriodMinutes("PT10000H"));
        Assert.assertEquals(6000000, Iso8601Parser.parsePeriodMinutes("PT100000H"));
    }

    @Test(expected = NsApiError.class)
    public void testInvalidPostChar() {
        Iso8601Parser.parsePeriodMinutes("PT44Y");
    }

    @Test(expected = NsApiError.class)
    public void testInvalidDuration() {
        Iso8601Parser.parsePeriodMinutes("P1000M");
    }


}