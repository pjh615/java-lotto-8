package lotto.util;

import java.util.List;

public interface RandomNumberGenerator {
    List<Integer>  generateRandomNumbers(int startInclusive, int endInclusive, int count);
}
