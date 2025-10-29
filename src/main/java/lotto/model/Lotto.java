package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    public void printNumbers() {
        System.out.println(numbers);
    }

    public Integer matchNumber(final Lotto lotto) {
        return Math.toIntExact(lotto.numbers.stream().filter(this.numbers::contains).count());
    }
}
