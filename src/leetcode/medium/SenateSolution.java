package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

class SenateSolution {

    public String predictPartyVictory(String senate) {
        Deque<Integer> radiants = new LinkedList<>();
        Deque<Integer> dires = new LinkedList<>();
        int length = senate.length();
        for (int i = 0; i < length; i++) {
            if (senate.charAt(i) == 'R') {
                radiants.offer(i);
            } else {
                dires.offer(i);
            }
        }

        while (!radiants.isEmpty() && !dires.isEmpty()) {
            Integer radiant = radiants.poll();
            Integer dire = dires.poll();
            if (radiant < dire) {
                radiants.offer(radiant + length);
            } else {
                dires.offer(dire + length);
            }
        }

        return radiants.isEmpty() ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        SenateSolution solution = new SenateSolution();
        System.out.println("Radiant".equals(solution.predictPartyVictory("RD")));
        System.out.println("Dire".equals(solution.predictPartyVictory("RDD")));
    }
}