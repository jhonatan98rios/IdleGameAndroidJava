package com.example.idlegame;

import java.util.Random;

public class AbstractMetrics {

    private Random random;
    public int upgradePrice;
    public int maxUsage;
    public int usage;

    public AbstractMetrics() {
        usage = 1;
        maxUsage = 500;
        upgradePrice = 100;
        random = new Random();
    }

    public void increaseMaxUsage() {
        int maxUsagePercentage = maxUsage / 100;
        int randomMaxCpuUsageIncrease = (maxUsagePercentage  * random.nextInt(180)) + (maxUsagePercentage * 20);
        maxUsage += randomMaxCpuUsageIncrease;
    }

    public void increaseUpgradePrice() {
        upgradePrice += upgradePrice * 1.3;
    }

    public int getUsage() {
        return usage;
    }

    public int getMaxUsage() {
        return maxUsage;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }
}
