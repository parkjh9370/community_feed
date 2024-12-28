package main.auth.domain;

import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private final String emailText;

    public Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is not valid.");
        }

        if (isNotValidEmailString(email)) {
            throw new IllegalArgumentException("Email is not valid.");
        }

        return new Email(email);
    }

    public String getEmailText() {
        return emailText;
    }

    private static boolean isNotValidEmailString(String emailText) {
        return !pattern.matcher(emailText).matches();
    }
}
