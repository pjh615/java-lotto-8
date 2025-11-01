package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.service.LottoService;
import lotto.view.OutputView;

import java.util.EnumMap;
import java.util.List;

public class LottoController {
    private final LottoService lottoService;
    private final OutputView outputView;


    public LottoController(LottoService lottoService, OutputView outputView) {
        this.lottoService = lottoService;
        this.outputView = outputView;
    }

    public void run() {
        Integer purchasePrice = lottoService.getPurchasePrice();
        Integer lottoCount = lottoService.calculateLottoCount(purchasePrice);
        outputView.displayLottoCount(lottoCount);

        List<Lotto> lottos = lottoService.generateLottos(lottoCount);
        outputView.displayMyLotto(lottos);

        Lotto winningLotto = lottoService.getWinningLotto();
        Integer bonusNumber = lottoService.getBonusNumber(winningLotto);

        EnumMap<Prize, Integer> prizeStatistics = lottoService.getPrizeCount(lottos, winningLotto, bonusNumber);
        outputView.displayStatistics(prizeStatistics);
        double profitRate = lottoService.calculateProfitRate(prizeStatistics, purchasePrice);
        outputView.displayProfit(profitRate);
    }
}
