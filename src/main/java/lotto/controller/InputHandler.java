package lotto.controller;

import lotto.model.Lotto;
import lotto.util.Constants;
import lotto.util.Parser;
import lotto.view.InputView;

import java.util.HashSet;
import java.util.List;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public Integer getPurchasePrice() {
        String input = inputView.inputPurchasePrice();
        Integer purchase = Parser.convertStringToInteger(input);
        validatePositiveNumber(purchase);
        validateMultipleOfThousand(purchase);
        return purchase;
    }

    public Lotto getWinningLotto() {
        String input = inputView.inputWinningLotto();
        List<Integer> winningNumbers =
                Parser.parseByDelimiter(input, Constants.DEFAULT_DELIMITER).stream()
                .map(Parser::convertStringToInteger)
                .sorted()
                .toList();
        validateRange(winningNumbers);
        validateDuplicate(winningNumbers);
        return new Lotto(winningNumbers);
    }

    public Integer getBonusNumber(Lotto lotto) {
        String input = inputView.inputBonusNumber();
        Integer bonusNumber = Parser.convertStringToInteger(input);
        validateRange(bonusNumber);
        validateDuplicate(lotto, bonusNumber);
        return bonusNumber;
    }


    private static void validatePositiveNumber(Integer number) {
        if (number <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수로 입력하세요.");
        }
    }

    private static void validateMultipleOfThousand(Integer number) {
        if ((number % Constants.LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력하세요.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(n -> n < Constants.LOTTO_NUMBER_MIN || n > Constants.LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45까지의 숫자입니다. 올바른 범위의 수를 입력하세요.");
        }
    }

    private static void validateRange(Integer number) {
        if (number < Constants.LOTTO_NUMBER_MIN | number > Constants.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45까지의 숫자입니다. 올바른 범위의 수를 입력하세요.");
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않는 당첨 번호를 입력해주세요.");
        }
    }

    private static void validateDuplicate(Lotto lotto, Integer number) {
        if (lotto.containsNumber(number)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }
}
