package com.bitsfromspace.ns.api;

/**
 * @author coudeman@gmail.com
 * @since 16-Sep-15
 */
public class Credentials {
    private final String userName;
    private final String password;

    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
