package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private Parser() {}

    public static List<String> parseByDelimiter(final String input, final String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .toList();
    }

    public static Integer convertStringToInteger(final String input) {
        return Integer.parseInt(input);
    }
}
