package lotto.service;

import lotto.controller.InputHandler;
import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class LottoService {
    private final InputHandler inputHandler;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoService(InputHandler inputHandler, RandomNumberGenerator randomNumberGenerator) {
        this.inputHandler = inputHandler;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Integer getPurchasePrice() {
        while (true) {
            try {
                return inputHandler.getPurchasePrice();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Lotto getWinningLotto() {
        while (true) {
            try {
                return inputHandler.getWinningLotto();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Integer getBonusNumber(Lotto lotto) {
        while (true) {
            try {
                return inputHandler.getBonusNumber(lotto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Integer calculateLottoCount(Integer purchasePrice) {
        return purchasePrice / 1000;
    }

    public List<Lotto> generateLottos(Integer lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generateRandomNumbers(1, 45, 6)));
        }
        return lottos;
    }

    public EnumMap<Prize, Integer> getPrizeCount(List<Lotto> lottos, Lotto winningLotto, Integer bonusNumber) {
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
        return prizeStatistics;
    }

    public double calculateProfitRate(EnumMap<Prize, Integer> prizeStatistics, Integer purchasePrice) {
        return (double) Prize.calculatePrize(prizeStatistics) / purchasePrice * 100;
    }
}
