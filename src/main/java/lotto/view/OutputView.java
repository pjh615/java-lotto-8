package lotto.view;

import lotto.model.Lotto;
import lotto.model.Prize;

import java.util.EnumMap;
import java.util.List;

public class OutputView {

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
        System.out.printf("3개 일치 (5,000원) - %d개\n", prizes.get(Prize.FIFTH_PRIZE));
        System.out.printf("4개 일치 (50,000원) - %d개\n", prizes.get(Prize.FOURTH_PRIZE));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", prizes.get(Prize.THRID_PRIZE));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", prizes.get(Prize.SECOND_PRIZE));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", prizes.get(Prize.FIRST_PRIZE));
    }

    public void displayProfit(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

}
