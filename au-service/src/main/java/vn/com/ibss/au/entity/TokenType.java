package vn.com.ibss.au.entity;


public enum TokenType {

    /**
     * Confirm email address.
     */
    EMAIL_CONFIRMATION("EMAIL_CONFIRMATION"),

    /**
     * Confirm password reset.
     */
    PASSWORD_RESET("PASSWORD_RESET");

    private String type;

    TokenType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
