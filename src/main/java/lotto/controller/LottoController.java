package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.util.RandomNumberGenerator;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class LottoController {
    private final InputHandler inputHandler;
    private final RandomNumberGenerator randomNumberGenerator;
    private final OutputView outputView;


    public LottoController(InputHandler inputHandler, RandomNumberGenerator randomNumberGenerator, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.randomNumberGenerator = randomNumberGenerator;
        this.outputView = outputView;
    }

    public void run() {
        Integer purchasePrice = inputHandler.getPurchasePrice();
        int lottoCount = purchasePrice / 1000;
        outputView.displayLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generateRandomNumbers(1, 45, 6)));
        }
        outputView.displayMyLotto(lottos);

        Lotto winningLotto = inputHandler.getWinningLotto();
        Integer bonusNumber = inputHandler.getBonusNumber(winningLotto);

        EnumMap<Prize, Integer> prizeStatistics = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            prizeStatistics.put(prize, 0);
        }
        for (Lotto lotto : lottos) {
            Integer matchCount = lotto.matchNumber(winningLotto);
            boolean isBonusMatch = lotto.containsNumber(bonusNumber);
            Prize foundPrize = Prize.find(matchCount, isBonusMatch);
            prizeStatistics.merge(foundPrize, 1, Integer::sum);
        }
        outputView.displayStatistics(prizeStatistics);
        double profitRate = (double) Prize.calculatePrize(prizeStatistics) / purchasePrice * 100;
        outputView.displayProfit(profitRate);
    }
}
