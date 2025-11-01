package lotto.view;

public class InputViewTest implements InputView {
    private String purchasePrice;
    private String winningLotto;
    private String bonusNumber;

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setWinningLotto(String winningLotto) {
        this.winningLotto = winningLotto;
    }

    public void setBonusNumber(String bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    @Override
    public String inputPurchasePrice() {
        return purchasePrice;
    }

    public String inputWinningLotto() {
        return winningLotto;
    }

    public String inputBonusNumber() {
        return bonusNumber;
    }
}
