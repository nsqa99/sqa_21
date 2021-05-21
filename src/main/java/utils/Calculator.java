package utils;

import java.util.ArrayList;

public class Calculator {
	public static ArrayList<Integer> calculateAmountLevels(int amount) {
        ArrayList<Integer> levels = new ArrayList<>();
        if (amount > 50) {
            if (amount > 100) {
                int count = 3;
                for (int i = 0; i < 2; i++) {
                    levels.add(50);
                    amount -= 50;
                }
                while (amount > 100 && count > 0) {
                    levels.add(100);
                    amount -= 100;
                    count--;
                }
                levels.add(amount);
            } else {
                levels.add(50);
                levels.add(amount - 50);
            }
        } else if (amount>=0) {
            levels.add(amount);
        }
        return levels;
    }
}
