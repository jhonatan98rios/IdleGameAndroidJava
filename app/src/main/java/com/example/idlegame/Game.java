package com.example.idlegame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private int money;
    private int profitPerUser;
    private int numberOfUsers;
    private int cpuUsage;
    private int maxCpuUsage;

    public Game() {
        // Inicialize os valores iniciais
        money = 0;
        profitPerUser = 1;
        numberOfUsers = 0;
        cpuUsage = 10;
        maxCpuUsage = 100;
    }

    public void increaseValues() {
        Random random = new Random();

        // Generate a random value to increase numberOfUsers and profitPerUser
        int increaseUsers = 1; //random.nextInt(10) + 1;
        int increaseProfitPerUser = 1; //random.nextInt(5) + 1;

        // Increase numberOfUsers e profitPerUser
        numberOfUsers += increaseUsers;
        profitPerUser += increaseProfitPerUser;

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
                increaseValues();
            }
        }, 1000, 1000);
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
}
