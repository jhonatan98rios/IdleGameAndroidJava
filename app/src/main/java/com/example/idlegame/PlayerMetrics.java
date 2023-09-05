package com.example.idlegame;

import java.util.Random;

public class PlayerMetrics {

    public long money;
    public int profitPerUser;
    public int numberOfUsers;
    private Random random;

    public PlayerMetrics() {
        money = 0;
        profitPerUser = 1;
        numberOfUsers = 1;

        random = new Random();
    }

    public void increaseValues(int increaseUsers) {

        // Increase numberOfUsers
        numberOfUsers += increaseUsers;
        long profit = profitPerUser * numberOfUsers;
        double expenses = profit * 0.2;

        // update the value money with profitPerUser and numberOfUsers
        int debtors = random.nextInt(numberOfUsers + 1);
        money += (long)((profit - debtors) - expenses);
    }

    public void subtractMoney(int value) {
        money -= value;
    }

    public void increaseProfitPerUser() {
        double randomNumber = random.nextDouble();
        if (randomNumber >= 0.9) {
            profitPerUser += 2;
        } else if (randomNumber >= 0.5) {
            profitPerUser += 1;
        }
    }
}
