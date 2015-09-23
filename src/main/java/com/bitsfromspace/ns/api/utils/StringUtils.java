package com.bitsfromspace.ns.api.utils;

/**
 * @author chris
 * @since 23-09-15.
 */
public class StringUtils {
    private StringUtils() {
    }

    public static String readBetween(String in, String beginTag, String endTag) {
        if (in == null || in.isEmpty()) {
            return "";
        }

        int startPos = in.indexOf(beginTag) + beginTag.length();
        if (startPos < beginTag.length()) {
            return "";
        }

        int endPos = in.indexOf(endTag, startPos);
        if (endPos == -1) {
            return "";
        }
        return in.substring(startPos, endPos);
    }
}
