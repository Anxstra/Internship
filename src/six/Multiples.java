package six;

import java.util.stream.IntStream;

public class Multiples {

    public int solution(int number) {
        return IntStream.range(1, number)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .sum();
    }

    public int solutionWithoutStream(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if(i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Multiples multiples = new Multiples();
        System.out.println(23 == multiples.solution(10));
        System.out.println(23 == multiples.solutionWithoutStream(10));
    }
}
