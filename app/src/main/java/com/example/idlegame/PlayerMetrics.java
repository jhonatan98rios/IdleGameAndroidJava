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

        // update the value money with profitPerUser and numberOfUsers
        int debtors = random.nextInt(numberOfUsers + 1);
        money += (profitPerUser * numberOfUsers) - debtors;
    }

    public void subtractMoney(int value) {
        money -= value;
    }

    public void increaseProfitPerUser() {
        profitPerUser += 1;
    }
}
