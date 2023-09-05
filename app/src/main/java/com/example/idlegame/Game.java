package com.example.idlegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private long money;
    private int profitPerUser;
    private int numberOfUsers;

    public CpuMetrics cpuMetrics;
    public RamMetrics ramMetrics;

    private Random random;

    public Game() {
        // Initialize values
        money = 0;
        profitPerUser = 1;
        numberOfUsers = 1;

        cpuMetrics = new CpuMetrics();
        ramMetrics = new RamMetrics();

        random = new Random();
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cpuMetrics.usage < cpuMetrics.maxUsage &&
                    ramMetrics.usage < ramMetrics.maxUsage) {
                    increaseValues();
                } else {
                    decreaseValues();
                }
            }
        }, 0, 100);
    }

    public void increaseValues() {

        // Generate a random value to increase numberOfUsers and profitPerUser
        int numberOfUsersPercentage = numberOfUsers / 100;
        int increaseUsers = random.nextInt((numberOfUsersPercentage * 10) + 1) + 1;

        // Increase numberOfUsers
        numberOfUsers += increaseUsers;

        // update the value money with profitPerUser and numberOfUsers
        int debtors = random.nextInt(numberOfUsers + 1);
        money += (profitPerUser * numberOfUsers) - debtors;

        // Increase the value of cpuMetrics.usage with numberOfUsers
        cpuMetrics.usage += increaseUsers + random.nextInt(numberOfUsers);
        ramMetrics.usage += increaseUsers + random.nextInt(numberOfUsers);

        // Check if cpuMetrics.usage is bigger than the cpuMetrics.maxUsage
        if (cpuMetrics.usage > cpuMetrics.maxUsage) {
            cpuMetrics.usage = cpuMetrics.maxUsage;
        }

        if (ramMetrics.usage > ramMetrics.maxUsage) {
            ramMetrics.usage = ramMetrics.maxUsage;
        }
    }

    public void decreaseValues() {
        if (numberOfUsers > 0) {
            int decreaseUsers = random.nextInt((numberOfUsers / 10) + 1) + 1;
            money -= profitPerUser * numberOfUsers;
            numberOfUsers -= decreaseUsers;
            cpuMetrics.usage -= decreaseUsers;
            ramMetrics.usage -= decreaseUsers;
        }
    }

    // Money Methods
    public void increaseProfitPerUser() {
        profitPerUser += 1;
    }

    public void subtractMoney(int value) {
        money -= value;
    }

    public long getMoney() {
        return money;
    }

    public int getProfitPerUser() {
        return profitPerUser;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

}
