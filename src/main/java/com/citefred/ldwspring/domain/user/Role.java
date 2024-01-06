package com.citefred.ldwspring.domain.user;

public enum Role {

    GUEST(Authority.GUEST),
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String GUEST = "ROLE_GUEST";
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}