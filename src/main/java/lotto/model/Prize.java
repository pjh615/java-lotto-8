package lotto.model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Prize {

    FIRST_PRIZE(6, 2_000_000_000, false, "6개 일치"),
    SECOND_PRIZE(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD_PRIZE(5, 1_500_000, false, "5개 일치"),
    FOURTH_PRIZE(4, 50_000, false, "4개 일치"),
    FIFTH_PRIZE(3, 5_000, false, "3개 일치"),
    MISS(0, 0, false, "낙첨");

    private final Integer matchCount;
    private final Integer prizeMoney;
    private final boolean isBonusMatch;
    private final String description;

    Prize(final Integer matchCount, final Integer prizeMoney, final boolean isBonusMatch, final String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonusMatch = isBonusMatch;
        this.description = description;
    }

    public static Prize find(Integer matchCount, boolean isBonusMatch) {
        if (SECOND_PRIZE.matchCount.equals(matchCount) && isBonusMatch) {
            return SECOND_PRIZE;
        }
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount.equals(matchCount) && prize != SECOND_PRIZE)
                .findFirst()
                .orElse(MISS);
    }

    public static int calculatePrize(final EnumMap<Prize, Integer> prizes) {
        return prizes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().prizeMoney * entry.getValue())
                .sum();
    }

    public String getStatisticsMessage() {
        return String.format("%s (%,d원)", this.description, this.prizeMoney);
    }
}
