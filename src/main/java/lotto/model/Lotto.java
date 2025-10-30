package lotto.model;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        int start = numbers.getFirst();
        int end = numbers.getLast();
        if (start < 1 || end > 45 ) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45까지의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if(numbers.size() != new HashSet<>(numbers).size()){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되어선 안됩니다.");
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
