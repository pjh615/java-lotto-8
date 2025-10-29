package lotto.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    private final RandomNumberGenerator randomNumberGenerator = new LottoGenerator();

    @Test
    void 기능_테스트() {
        int startInclusive = 1;
        int endInclusive = 45;
        int count = 6;

        List<Integer> numbers = randomNumberGenerator.generateRandomNumbers(startInclusive, endInclusive, count);

        assertThat(numbers).hasSize(count);
        assertThat(numbers).allMatch(num -> num >= startInclusive && num <= endInclusive);
        assertThat(numbers.stream().distinct().toList()).hasSameElementsAs(numbers);
    }
}
