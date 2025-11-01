package lotto.controller;

import lotto.model.Lotto;
import lotto.view.InputViewTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputHandlerTest {
    private InputHandler inputHandler;
    private InputViewTest inputViewTest;

    @BeforeEach
    void setUp() {
        inputViewTest = new InputViewTest();
        inputHandler = new InputHandler(inputViewTest);
    }

    @Test
    void 구입금액_기능_테스트() {
        inputViewTest.setPurchasePrice("2000");
        Integer purchasePrice = inputHandler.getPurchasePrice();
        assertThat(purchasePrice).isEqualTo(2000);
    }

    @Test
    void 구입금액_음수_예외() {
        inputViewTest.setPurchasePrice("-1000");
        assertThatThrownBy(() -> inputHandler.getPurchasePrice())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 구입금액_배수_예외() {
        inputViewTest.setPurchasePrice("1234");
        assertThatThrownBy(() -> inputHandler.getPurchasePrice())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨번호_기능_테스트() {
        inputViewTest.setWinningLotto("1,2,3,4,5,6");
        Lotto expected = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lotto winningLotto = inputHandler.getWinningLotto();
        assertThat(winningLotto.matchNumber(expected)).isEqualTo(6);
    }

    @Test
    void 당첨번호_범위_예외() {
        inputViewTest.setWinningLotto("1,2,3,4,5,46");
        assertThatThrownBy(() -> inputHandler.getWinningLotto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨번호_중복_예외() {
        inputViewTest.setWinningLotto("1,1,3,4,5,6");
        assertThatThrownBy(() -> inputHandler.getWinningLotto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스번호_기능_테스트() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        inputViewTest.setBonusNumber("7");
        Integer bonusNumber = inputHandler.getBonusNumber(winningLotto);
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    void 보너스번호_범위_예외() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        inputViewTest.setBonusNumber("0");
        assertThatThrownBy(() -> inputHandler.getBonusNumber(winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스번호_중복_예외() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        inputViewTest.setBonusNumber("6");
        assertThatThrownBy(() -> inputHandler.getBonusNumber(winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
