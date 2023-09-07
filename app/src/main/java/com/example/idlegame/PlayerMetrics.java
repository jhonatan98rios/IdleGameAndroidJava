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
        double expenses = money >= 100000 ? profit * 0.5 :
                          money >= 10000 ? profit * 0.3 :
                          money >= 5000 ? profit * 0.2 :
                          profit * 0.1;

        // update the value money with profitPerUser and numberOfUsers
        int debtors = random.nextInt(numberOfUsers + 1);
        money += (long)((profit - debtors) - expenses);
    }

    public void subtractMoney(int value) {
        money -= value;
    }

    public void increaseProfitPerUserRandom() {
        double randomNumber = random.nextDouble();
        if (randomNumber >= 0.75) {
            profitPerUser += 1;
        }
    }

    public void increaseProfitPerUser(int value) {
        profitPerUser += value;
    }
}
