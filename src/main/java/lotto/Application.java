package lotto;

import lotto.controller.InputHandler;
import lotto.controller.LottoController;
import lotto.util.LottoGenerator;
import lotto.util.RandomNumberGenerator;
import lotto.view.ConsoleInputView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        InputHandler inputHandler = new InputHandler(inputView);
        RandomNumberGenerator randomNumberGenerator = new LottoGenerator();
        OutputView outputView = new OutputView();

        LottoController lottoController = new LottoController(inputHandler, randomNumberGenerator, outputView);
        lottoController.run();
    }
}
