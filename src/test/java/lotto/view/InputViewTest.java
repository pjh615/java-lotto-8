package lotto.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InputViewTest implements InputView {
    private final Queue<String> purchasePrices = new LinkedList<>();
    private final Queue<String> winningLottos = new LinkedList<>();
    private final Queue<String> bonusNumbers = new LinkedList<>();

    public void setPurchasePrice(String... purchasePrice) {
        purchasePrices.addAll(List.of(purchasePrice));
    }

    public void setWinningLotto(String... winningLotto) {
        winningLottos.addAll(List.of(winningLotto));
    }

    public void setBonusNumber(String... bonusNumber) {
        bonusNumbers.addAll(List.of(bonusNumber));
    }

    @Override
    public String inputPurchasePrice() {
        return purchasePrices.poll();
    }

    public String inputWinningLotto() {
        return winningLottos.poll();
    }

    public String inputBonusNumber() {
        return bonusNumbers.poll();
    }
}
