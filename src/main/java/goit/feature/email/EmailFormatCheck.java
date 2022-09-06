package goit.feature.email;

public class EmailFormatCheck {
    public static boolean isTheEmailCorrect(String email) {
        String regexp = "[A-Za-z\\d!#$%&'*+/=?^_`.{|}~-]+@[a-z\\d]+.[a-z\\d]+";
        return email.matches(regexp);
    }
}
