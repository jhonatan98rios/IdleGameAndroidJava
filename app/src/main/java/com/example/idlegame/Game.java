package com.example.idlegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public PlayerMetrics playerMetrics;

    public CpuMetrics cpuMetrics;
    public RamMetrics ramMetrics;
    public HardDiskMetrics hardDiskMetrics;
    public NetworkMetrics networkMetrics;

    public Services services;

    private Random random;

    public Game() {
        playerMetrics = new PlayerMetrics();
        cpuMetrics = new CpuMetrics();
        ramMetrics = new RamMetrics();
        hardDiskMetrics = new HardDiskMetrics();
        networkMetrics = new NetworkMetrics();

        services = new Services();

        random = new Random();
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executionInterval();
            }
        }, 0, 1000);
    }

    public void executionInterval() {
        if (cpuMetrics.usage < cpuMetrics.maxUsage &&
            ramMetrics.usage < ramMetrics.maxUsage &&
            hardDiskMetrics.usage < hardDiskMetrics.maxUsage &&
            networkMetrics.usage < networkMetrics.maxUsage) {
            increaseValues();
        } else {
            decreaseValues();
        }
    }

    public void increaseValues() {
        // Generate a random value to increase numberOfUsers and profitPerUser
        int numberOfUsersPercentage = playerMetrics.numberOfUsers / 100;
        int increaseUsers = random.nextInt((numberOfUsersPercentage * 2) + 1) + 1;

        playerMetrics.increaseValues(increaseUsers);

        // Increase the value of cpuMetrics.usage with numberOfUsers
        cpuMetrics.increaseUsage(increaseUsers + random.nextInt(playerMetrics.numberOfUsers));
        ramMetrics.increaseUsage(increaseUsers + random.nextInt(playerMetrics.numberOfUsers));
        hardDiskMetrics.increaseUsage(increaseUsers + random.nextInt(playerMetrics.numberOfUsers));
        networkMetrics.increaseUsage(increaseUsers + random.nextInt(playerMetrics.numberOfUsers));
    }

    public void decreaseValues() {
        if (playerMetrics.numberOfUsers > 0 && playerMetrics.money > 0) {
            int decreaseUsers = random.nextInt((playerMetrics.numberOfUsers / 2) + 1);

            int userLoss = playerMetrics.profitPerUser * decreaseUsers;
            int usageLoss = random.nextInt(playerMetrics.profitPerUser * playerMetrics.numberOfUsers);
            int damage = userLoss + usageLoss;

            playerMetrics.numberOfUsers -= decreaseUsers;
            playerMetrics.money -= damage;

            cpuMetrics.usage -= decreaseUsers + random.nextInt(cpuMetrics.usage / 10);
            ramMetrics.usage -= decreaseUsers + random.nextInt(ramMetrics.usage / 10);
            hardDiskMetrics.usage -= decreaseUsers + random.nextInt(hardDiskMetrics.usage / 10);
            networkMetrics.usage -= decreaseUsers + random.nextInt(networkMetrics.usage / 10);
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
