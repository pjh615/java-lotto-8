package lotto.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    @Test
    void 쉼표_구분_테스트() {
        String input = "1,2,3,4,5,6";
        List<String> actual = Parser.parseByDelimiter(input, ",");
        List<String> expected = List.of("1","2","3","4","5","6") ;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 정수_변환_테스트() {
        String input = "7";
        Integer actual = Parser.convertStringToInteger(input);
        Integer expected = 7;
        assertThat(actual).isEqualTo(expected);
    }
}
