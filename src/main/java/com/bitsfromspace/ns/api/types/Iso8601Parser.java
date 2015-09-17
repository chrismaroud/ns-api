package com.bitsfromspace.ns.api.types;

import com.bitsfromspace.ns.api.NsApiError;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class Iso8601Parser {

    private final static DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private final static Lock dateFormatLock = new ReentrantLock();

    private Iso8601Parser() {
    }

    public static Date parseDate(String iso8601Date) {
        if (iso8601Date == null) {
            return null;
        }

        dateFormatLock.lock();
        try {
            return iso8601Format.parse(iso8601Date);
        } catch (ParseException pe) {
            throw new NsApiError(pe);
        } finally {
            dateFormatLock.unlock();
        }
    }

    public static int parsePeriodMinutes(String iso8601Value) {

        if (iso8601Value == null || iso8601Value.isEmpty()) {
            return 0;
        }

        if (!iso8601Value.startsWith("PT") || iso8601Value.length() < 4) {
            throw new NsApiError("Could not parse ISO8601 value: " + iso8601Value);
        }

        int numValue;
        try {
            numValue = Integer.parseInt(iso8601Value.substring(2, iso8601Value.length() - 1));
        } catch (NumberFormatException nfe) {
            throw new NsApiError("Could not parse ISO8601 value: " + iso8601Value, nfe);
        }

        switch (iso8601Value.charAt(iso8601Value.length() - 1)) {
            case 'M':
                return numValue;
            case 'S':
                return (int) Math.ceil(numValue / 60d);
            case 'H':
                return 60 * numValue;
            default:
                throw new NsApiError("Could not parse ISO8601 value: " + iso8601Value);
        }

    }
}
