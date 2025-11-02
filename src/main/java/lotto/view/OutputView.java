package lotto.view;

import lotto.model.Lotto;
import lotto.model.Prize;

import java.util.EnumMap;
import java.util.List;

public class OutputView {
    private static final List<Prize> DISPLAY_PRIZES = List.of(
            Prize.FIRST_PRIZE,
            Prize.SECOND_PRIZE,
            Prize.THRID_PRIZE,
            Prize.FOURTH_PRIZE,
            Prize.FIFTH_PRIZE
    );

    public void displayLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void displayMyLotto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void displayStatistics(EnumMap<Prize, Integer> prizes) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Prize prize : DISPLAY_PRIZES) {
            System.out.printf("%s - %d개\n",
                    prize.getStatisticsMessage(),
                    prizes.get(prize)
            );
        }
    }

    public void displayProfit(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

}
