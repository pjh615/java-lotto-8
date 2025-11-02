package lotto.service;

import lotto.controller.InputHandler;
import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.util.LottoGenerator;
import lotto.util.RandomNumberGenerator;
import lotto.view.InputViewTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    private LottoService lottoService;
    private InputHandler inputHandler;
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        InputViewTest inputView = new InputViewTest();
        inputHandler = new InputHandler(inputView);
        randomNumberGenerator = new LottoGenerator();
        lottoService = new LottoService(inputHandler, randomNumberGenerator);
    }

    @Test
    void 로또_개수_계산_테스트() {
        Integer purchasePrice = 4000;
        Integer actual = lottoService.calculateLottoCount(purchasePrice);
        Integer expected = 4;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또_생성_시_개수_테스트() {
        Integer lottoCount = 4;
        List<Lotto> lottos = lottoService.generateLottos(lottoCount);
        assertThat(lottos).hasSize(lottoCount);

        for (Lotto lotto : lottos) {
            assertThat(lotto).isNotNull();
            assertThat(lotto.matchNumber(lotto)).isEqualTo(6);
        }
    }

    @Test
    void 로또_당첨_계산_테스트() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 44)),
                new Lotto(List.of(1, 2, 3, 4, 41, 42)),
                new Lotto(List.of(2, 3, 4, 5, 42, 45)),
                new Lotto(List.of(3, 4, 5, 6, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Integer bonusNumber = 7;

        EnumMap<Prize, Integer> prizeCount = lottoService.getPrizeCount(lottos, winningLotto, bonusNumber);

        assertThat(prizeCount.get(Prize.FIFTH_PRIZE)).isEqualTo(1);
        assertThat(prizeCount.get(Prize.FOURTH_PRIZE)).isEqualTo(3);
        assertThat(prizeCount.get(Prize.THRID_PRIZE)).isEqualTo(1);
        assertThat(prizeCount.get(Prize.SECOND_PRIZE)).isEqualTo(1);
        assertThat(prizeCount.get(Prize.FIRST_PRIZE)).isEqualTo(1);
    }

    @Test
    void 수익률_계산_테스트() {
        EnumMap<Prize, Integer> prizeStatistics = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            prizeStatistics.put(prize, 0);
        }
        prizeStatistics.put(Prize.FIFTH_PRIZE, 1);
        Integer purchasePrice = 4000;
        double actual = lottoService.calculateProfitRate(prizeStatistics, purchasePrice);
        double expected = (double) 5_000 / purchasePrice * 100;
        assertThat(actual).isEqualTo(expected);
    }
}
