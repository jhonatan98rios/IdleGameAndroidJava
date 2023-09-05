package com.example.idlegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public PlayerMetrics playerMetrics;

    public CpuMetrics cpuMetrics;
    public RamMetrics ramMetrics;

    public HardDiskMetrics hardDiskMetrics;

    private Random random;

    public Game() {
        playerMetrics = new PlayerMetrics();
        cpuMetrics = new CpuMetrics();
        ramMetrics = new RamMetrics();
        hardDiskMetrics = new HardDiskMetrics();

        random = new Random();
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executionInterval();
            }
        }, 0, 300);
    }

    public void executionInterval() {
        if (cpuMetrics.usage < cpuMetrics.maxUsage &&
            ramMetrics.usage < ramMetrics.maxUsage &&
            hardDiskMetrics.usage < hardDiskMetrics.maxUsage) {
            increaseValues();
        } else {
            decreaseValues();
        }
    }

    public void increaseValues() {
        // Generate a random value to increase numberOfUsers and profitPerUser
        int numberOfUsersPercentage = playerMetrics.numberOfUsers / 100;
        int increaseUsers = random.nextInt((numberOfUsersPercentage * 10) + 1) + 1;
        int increaseHardwareUsage = increaseUsers + random.nextInt(playerMetrics.numberOfUsers);

        playerMetrics.increaseValues(increaseUsers);

        // Increase the value of cpuMetrics.usage with numberOfUsers
        cpuMetrics.increaseUsage(increaseHardwareUsage);
        ramMetrics.increaseUsage(increaseHardwareUsage);
        hardDiskMetrics.increaseUsage(increaseHardwareUsage);
    }

    public void decreaseValues() {
        if (playerMetrics.numberOfUsers > 0) {
            int decreaseUsers = random.nextInt((playerMetrics.numberOfUsers / 10) + 1) + 1;
            playerMetrics.money -= playerMetrics.profitPerUser * playerMetrics.numberOfUsers;
            playerMetrics.numberOfUsers -= decreaseUsers;

            cpuMetrics.usage -= decreaseUsers;
            ramMetrics.usage -= decreaseUsers;
            hardDiskMetrics.usage -= decreaseUsers;
        }
    }

    public long getMoney() {
        return playerMetrics.money;
    }

    public int getProfitPerUser() {
        return playerMetrics.profitPerUser;
    }

    public int getNumberOfUsers() {
        return playerMetrics.numberOfUsers;
    }

}
