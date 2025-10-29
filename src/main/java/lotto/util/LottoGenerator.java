package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.random.RandomGenerator;

public class LottoGenerator implements RandomNumberGenerator {

    public List<Integer> generateRandomNumbers(int startInclusive, int endInclusive, int count) {
        return Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
    }
}
