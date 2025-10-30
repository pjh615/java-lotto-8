package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeTest {

    @Test
    void 로또_꼴등_보너스_번호_매칭_실패_테스트() {
        Integer matchCount = 2;
        boolean isBonusMatch = false;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.MISS);
    }

    @Test
    void 로또_꼴등_보너스_번호_매칭_성공_테스트() {
        Integer matchCount = 2;
        boolean isBonusMatch = true;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.MISS);
    }

    @Test
    void 로또_5등_보너스_번호_매칭_실패_테스트() {
        Integer matchCount = 3;
        boolean isBonusMatch = false;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.FIFTH_PRIZE);
    }

    @Test
    void 로또_5등_보너스_번호_매칭_성공_테스트() {
        Integer matchCount = 3;
        boolean isBonusMatch = true;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.FIFTH_PRIZE);
    }


    @Test
    void 로또_4등_보너스_번호_매칭_실패_테스트() {
        Integer matchCount = 4;
        boolean isBonusMatch = false;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.FOURTH_PRIZE);
    }

    @Test
    void 로또_4등_보너스_번호_매칭_성공_테스트() {
        Integer matchCount = 4;
        boolean isBonusMatch = true;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.FOURTH_PRIZE);
    }

    @Test
    void 로또_3등_테스트() {
        Integer matchCount = 5;
        boolean isBonusMatch = false;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.THRID_PRIZE);
    }

    @Test
    void 로또_2등_테스트() {
        Integer matchCount = 5;
        boolean isBonusMatch = true;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.SECOND_PRIZE);
    }

    @Test
    void 로또_1등_테스트() {
        Integer matchCount = 6;
        boolean isBonusMatch = false;
        assertThat(Prize.find(matchCount, isBonusMatch)).isEqualTo(Prize.FIRST_PRIZE);
    }

    @Test
    void 상금_계산_테스트() {
        EnumMap<Prize, Integer> prizes = new EnumMap<>(Prize.class);

        prizes.put(Prize.SECOND_PRIZE, 2);
        prizes.put(Prize.THRID_PRIZE, 3);
        prizes.put(Prize.FOURTH_PRIZE, 2);
        prizes.put(Prize.FIFTH_PRIZE, 3);
        Integer actual = Prize.calculatePrize(prizes);
        Integer expected = (2 * 30_000_000) + (3 * 1_500_000) + (2 * 50_000) + (3 * 5_000);

        assertThat(actual).isEqualTo(expected);
    }
}
