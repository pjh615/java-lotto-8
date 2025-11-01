package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static List<String> parseByDelimiter(final String input, final String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .toList();
    }

    public static Integer convertStringToInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] " + input + " 는 숫자가 아닙니다.");
        }
    }
}
