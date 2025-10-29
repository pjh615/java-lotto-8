package lotto.controller;

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

    public List<Integer> getWinningLotto() {
        String input = inputView.inputWinningLotto();
        List<Integer> winningLotto = Parser.parseByDelimiter(input, ",").stream()
                .map(Parser::convertStringToInteger)
                .sorted()
                .toList();
        validateRange(winningLotto);
        validateDuplicate(winningLotto);
        return winningLotto;
    }

    public Integer getBonusNumber(List<Integer> numbers) {
        String input = inputView.inputBonusNumber();
        Integer bonusNumber = Parser.convertStringToInteger(input);
        validatePositiveNumber(bonusNumber);
        validateDuplicate(numbers, bonusNumber);
        return bonusNumber;
    }


    private static void validatePositiveNumber(Integer number) {
        if (number <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수로 입력하세요.");
        }
    }

    private static void validateMultipleOfThousand(Integer number) {
        if ((number % 1000) != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력하세요.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        int start = numbers.getFirst();
        int end = numbers.getLast();

        if (start < 1 | start > 45 | end < 1 | end > 45) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45까지의 숫자입니다. 올바른 범위의 수를 입력하세요.");
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않는 당첨 번호를 입력해주세요.");
        }
    }

    private static void validateDuplicate(List<Integer> numbers, Integer number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }
}
