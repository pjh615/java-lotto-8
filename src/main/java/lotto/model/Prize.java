package lotto.model;

import java.util.EnumMap;

public enum Prize {

    FIRST_PRIZE(6, 2_000_000_000, false),
    SECOND_PRIZE(5, 30_000_000, true),
    THRID_PRIZE(5, 1_500_000, false),
    FOURTH_PRIZE(4, 50_000, false),
    FIFTH_PRIZE(3, 5_000, false),
    MISS(0, 0, false);

    private final Integer matchCount;
    private final Integer prizeMoney;
    private final boolean isBonusMatch;

    Prize(final Integer matchCount, final Integer prizeMoney, final boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonusMatch = isBonusMatch;
    }

    public static Prize find(Integer matchCount, boolean isBonusMatch) {
        if (matchCount == 5 && isBonusMatch) {
            return SECOND_PRIZE;
        }
        for (Prize prize : Prize.values()) {
            if ((prize.matchCount == matchCount) && (prize != SECOND_PRIZE)) {
                return prize;
            }
        }
        return MISS;
    }

    public static int calculatePrize(final EnumMap<Prize, Integer> prizes) {
        return prizes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().prizeMoney * entry.getValue())
                .sum();
    }
}
