package com.example.idlegame;

import java.util.Random;

public class AbstractMetrics {

    private Random random;
    public int upgradePrice;
    public int maxUsage;
    public int usage;

    public AbstractMetrics() {
        usage = 1;
        maxUsage = 700;
        upgradePrice = 100;
        random = new Random();
    }

    public void increaseUsage(int increaseHardwareUsage) {
        usage += increaseHardwareUsage;

        if (usage > maxUsage) {
            usage = maxUsage;
        }
    }

    public void increaseMaxUsage() {
        int maxUsagePercentage = maxUsage / 100;
        int randomMaxUsageIncrease = (maxUsagePercentage  * random.nextInt(230)) + (maxUsagePercentage * 30);
        maxUsage += randomMaxUsageIncrease;
    }

    public void increaseUpgradePrice() {
        upgradePrice += upgradePrice * 1.5;
    }
}
