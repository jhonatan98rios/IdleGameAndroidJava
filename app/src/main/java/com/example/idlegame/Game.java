package com.example.idlegame;

import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private long money;
    private int profitPerUser;
    private int numberOfUsers;
    private int cpuUsage;
    private int cpuUpgradePrice;
    private int maxCpuUsage;

    private int ramUsage;
    private int maxRamUsage;
    private int ramUpgradePrice;

    private Random random;

    public Game() {
        // Initialize values
        money = 0;
        profitPerUser = 1;
        numberOfUsers = 1;

        cpuUsage = 1;
        maxCpuUsage = 500;
        cpuUpgradePrice = 100;

        ramUsage = 1;
        maxRamUsage = 500;
        ramUpgradePrice = 100;

        random = new Random();
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getCpuUsage() < getMaxCpuUsage() &&
                    getRamUsage() < getMaxRamUsage()) {
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

        // Increase the value of cpuUsage with numberOfUsers
        cpuUsage += increaseUsers + random.nextInt(numberOfUsers);
        ramUsage += increaseUsers + random.nextInt(numberOfUsers);

        // Check if cpuUsage is bigger than the maxCpuUsage
        if (cpuUsage > maxCpuUsage) {
            cpuUsage = maxCpuUsage;
        }

        if (ramUsage > maxRamUsage) {
            ramUsage = maxRamUsage;
        }
    }

    public void decreaseValues() {
        if (numberOfUsers > 0) {
            int decreaseUsers = random.nextInt((numberOfUsers / 10) + 1) + 1;
            money -= profitPerUser * numberOfUsers;
            numberOfUsers -= decreaseUsers;
            cpuUsage -= decreaseUsers;
            ramUsage -= decreaseUsers;

        }
    }

    // CPU Methods
    public void increaseMaxCpuUsage() {
        // Generate a random value to increase numberOfUsers and profitPerUser
        int maxCpuUsagePercentage = maxCpuUsage / 100;
        int randomMaxCpuUsageIncrease = (maxCpuUsagePercentage  * random.nextInt(180)) + (maxCpuUsagePercentage * 20);
        maxCpuUsage += randomMaxCpuUsageIncrease;
    }

    public void increaseCpuUpgradePrice() {
        cpuUpgradePrice += cpuUpgradePrice * 1.3;
    }

    // Ram Methods
    public void increaseMaxRamUsage() {
        // Generate a random value to increase numberOfUsers and profitPerUser
        int maxRamUsagePercentage = maxRamUsage / 100;
        int randomMaxRamUsageIncrease = (maxRamUsagePercentage * random.nextInt(180)) + (maxRamUsagePercentage * 20);
        maxRamUsage += randomMaxRamUsageIncrease;
    }

    public void increaseRamUpgradePrice() { ramUpgradePrice += ramUpgradePrice * 1.3; }

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

    public int getCpuUsage() {
        return cpuUsage;
    }

    public int getMaxCpuUsage() {
        return maxCpuUsage;
    }

    public int getCpuUpgradePrice() {
        return cpuUpgradePrice;
    }

    public int getRamUsage() {
        return ramUsage;
    }

    public int getMaxRamUsage() {
        return maxRamUsage;
    }

    public int getRamUpgradePrice() {
        return ramUpgradePrice;
    }
}
