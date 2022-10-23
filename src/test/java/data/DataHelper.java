package data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Cards {
        private String cardNumber;
    }

    public static Cards getClientCard1(AuthInfo authInfo) {
        return new Cards("5559 0000 0000 0001");
    }

    public static Cards getClientCard2(AuthInfo authInfo) {
        return new Cards("5559 0000 0000 0002");
    }

    public static Cards getWrongClientCard() {
        return new Cards("5559 0000 0000 000");
    }

}
