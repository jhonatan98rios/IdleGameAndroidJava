package com.example.idlegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private int money;
    private int profitPerUser;
    private int numberOfUsers;
    private int cpuUsage;

    private int cpuUpgradePrice;
    private int maxCpuUsage;

    public Game() {
        // Initialize values
        money = 0;
        profitPerUser = 1;
        numberOfUsers = 1;

        cpuUsage = 0;
        maxCpuUsage = 500;
        cpuUpgradePrice = 100;
    }

    public void increaseValues() {

        Random random = new Random();

        // Generate a random value to increase numberOfUsers and profitPerUser
        int numberOfUsersPercentage = numberOfUsers / 100;
        int increaseUsers = random.nextInt((numberOfUsersPercentage * 10) + 1) + 1;

        // Increase numberOfUsers
        numberOfUsers += increaseUsers;

        // update the value money with profitPerUser and numberOfUsers
        money += profitPerUser * numberOfUsers;

        // Increase the value of cpuUsage with numberOfUsers
        cpuUsage += numberOfUsers;

        // Check if cpuUsage is bigger than the maxCpuUsage
        if (cpuUsage > maxCpuUsage) {
            cpuUsage = maxCpuUsage;
        }
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getCpuUsage() < getMaxCpuUsage()) {
                    increaseValues();
                }
            }
        }, 2000, 2000);
    }

    public void increaseMaxCpuUsage() {
        // Generate a random value to increase numberOfUsers and profitPerUser
        Random random = new Random();
        int maxCpuUsagePercentage = maxCpuUsage / 100;
        int randomCpuUsageIncrease = (maxCpuUsagePercentage * random.nextInt(380)) + (maxCpuUsagePercentage * 20);
        maxCpuUsage += randomCpuUsageIncrease;
    }

    public void increaseProfitPerUser() {
        Random random = new Random();
        int increase = random.nextInt(3) + 1;
        profitPerUser += increase;
    }

    public void increaseCpuUpgradePrice() {
        cpuUpgradePrice += cpuUpgradePrice / 2;
    }

    public void subtractMoney(int value) {
        money -= value;
    }

    public int getMoney() {
        return money;
    }

    public int getProfitPerUser() {
        return profitPerUser;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public int getCpuUsage() {
        return cpuUsage;
    }

    public int getMaxCpuUsage() {
        return maxCpuUsage;
    }

    public int getCpuUpgradePrice() {
        return cpuUpgradePrice;
    }
}
