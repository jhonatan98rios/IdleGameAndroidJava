package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.idlegame.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private Game game;
    private ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla o layout usando databinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Cria uma instância do objeto Game
        game = new Game();

        game.startGameLoop();
        startViewLoop();

        // Update the value of progress bar
        updateHeader();
        updateProgressBar();
        updateTextViews();

        // Add methods to the clickable elements
        addEventListener();
    }

    public void addEventListener() {
        LinearLayout maxCpuUpgradeBtn=binding.cpuLevelupButton;
        LinearLayout maxRamUpgradeBtn=binding.ramLevelupButton;
        LinearLayout maxHardDiskUpgradeBtn=binding.harddiskLevelupButton;
        LinearLayout maxNetworkUpgradeBtn=binding.networkLevelupButton;

        maxCpuUpgradeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                buyMaxCpuUpgrade();
            }
        });

        maxRamUpgradeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                buyMaxRamUpgrade();
            }
        });

        maxHardDiskUpgradeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                buyMaxHardDiskUpgrade();
            }
        });

        maxNetworkUpgradeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                buyMaxNetworkUpgrade();
            }
        });
    }

    public void buyMaxCpuUpgrade() {
        if (game.getMoney() >= game.cpuMetrics.upgradePrice) {
            game.cpuMetrics.increaseMaxUsage();
            game.playerMetrics.increaseProfitPerUser();
            game.playerMetrics.subtractMoney(game.cpuMetrics.upgradePrice);
            game.cpuMetrics.increaseUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    public void buyMaxRamUpgrade() {
        if (game.getMoney() >= game.ramMetrics.upgradePrice) {
            game.ramMetrics.increaseMaxUsage();
            game.playerMetrics.increaseProfitPerUser();
            game.playerMetrics.subtractMoney(game.ramMetrics.upgradePrice);
            game.ramMetrics.increaseUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    public void buyMaxHardDiskUpgrade() {
        if (game.getMoney() >= game.hardDiskMetrics.upgradePrice) {
            game.hardDiskMetrics.increaseMaxUsage();
            game.playerMetrics.increaseProfitPerUser();
            game.playerMetrics.subtractMoney(game.hardDiskMetrics.upgradePrice);
            game.hardDiskMetrics.increaseUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    public void buyMaxNetworkUpgrade() {
        if (game.getMoney() >= game.networkMetrics.upgradePrice) {
            game.networkMetrics.increaseMaxUsage();
            game.playerMetrics.increaseProfitPerUser();
            game.playerMetrics.subtractMoney(game.networkMetrics.upgradePrice);
            game.networkMetrics.increaseUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    // Update header data
    private void updateHeader() {
        String str_money = formatValue(game.getMoney());
        binding.moneyText.setText(str_money);

        String str_profit_per_user = formatValue(game.getProfitPerUser());
        binding.profitPerUserText.setText(str_profit_per_user);

        String str_number_of_users = formatValue(game.getNumberOfUsers());
        binding.numberOfUsersText.setText(str_number_of_users);
    }

    // Update progress bar
    private void updateProgressBar() {
        binding.cpuProgressBar.setMax(game.cpuMetrics.maxUsage);
        binding.cpuProgressBar.setProgress(game.cpuMetrics.usage);
        updateCpuProgressDrawable();

        binding.ramProgressBar.setMax(game.ramMetrics.maxUsage);
        binding.ramProgressBar.setProgress(game.ramMetrics.usage);
        updateRamProgressDrawable();

        binding.harddiskProgressBar.setMax(game.hardDiskMetrics.maxUsage);
        binding.harddiskProgressBar.setProgress(game.hardDiskMetrics.usage);
        updateHardDriveProgressDrawable();

        binding.networkProgressBar.setMax(game.networkMetrics.maxUsage);
        binding.networkProgressBar.setProgress(game.networkMetrics.usage);
        updateNetworkProgressDrawable();
    }

    private void updateCpuProgressDrawable() {
        Drawable drawable;

        if (game.cpuMetrics.usage >= game.cpuMetrics.maxUsage * 0.75) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_progress_bg);
        } else if (game.cpuMetrics.usage >= game.cpuMetrics.maxUsage * 0.5) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_progress_bg);
        } else {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_progress_bg);
        }
        binding.cpuProgressBar.setProgressDrawable(drawable);
    }

    private void updateRamProgressDrawable() {
        Drawable drawable;

        if (game.ramMetrics.usage >= game.ramMetrics.maxUsage * 0.75) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_progress_bg);
        } else if (game.ramMetrics.usage >= game.ramMetrics.maxUsage * 0.5) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_progress_bg);
        } else {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_progress_bg);
        }
        binding.ramProgressBar.setProgressDrawable(drawable);
    }

    private void updateHardDriveProgressDrawable() {
        Drawable drawable;

        if (game.hardDiskMetrics.usage >= game.hardDiskMetrics.maxUsage * 0.75) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_progress_bg);
        } else if (game.hardDiskMetrics.usage >= game.hardDiskMetrics.maxUsage * 0.5) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_progress_bg);
        } else {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_progress_bg);
        }
        binding.harddiskProgressBar.setProgressDrawable(drawable);
    }

    private void updateNetworkProgressDrawable() {
        Drawable drawable;

        if (game.networkMetrics.usage >= game.networkMetrics.maxUsage * 0.75) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.red_progress_bg);
        } else if (game.networkMetrics.usage >= game.networkMetrics.maxUsage * 0.5) {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellow_progress_bg);
        } else {
            drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.green_progress_bg);
        }
        binding.networkProgressBar.setProgressDrawable(drawable);
    }

    private void updateTextViews() {
        binding.cpuUpgradePriceText.setText(formatValue(game.cpuMetrics.upgradePrice));
        binding.ramUpgradePriceText.setText(formatValue(game.ramMetrics.upgradePrice));
        binding.harddiskUpgradePriceText.setText(formatValue(game.hardDiskMetrics.upgradePrice));
        binding.networkUpgradePriceText.setText(formatValue(game.networkMetrics.upgradePrice));
    }

    public void startViewLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (game.cpuMetrics.usage <= game.cpuMetrics.maxUsage &&
                    game.ramMetrics.usage <= game.ramMetrics.maxUsage &&
                    game.hardDiskMetrics.usage <= game.hardDiskMetrics.maxUsage &&
                    game.networkMetrics.usage <= game.networkMetrics.maxUsage) {
                    ViewLoop();
                }

                if (game.getNumberOfUsers() == 0) {
                    Toast.makeText(getApplicationContext(), "Perdeste", Toast.LENGTH_LONG).show();
                }
            }
        }, 0, 300);
    }

    public String formatValue(long number) {

        String abbreviation = "";

        if (number >= 10_000_000_000L) {
            abbreviation = number / 1_000_000_000 + "B";
        } else if (number >= 10_000_000) {
            abbreviation = number / 1_000_000 + "M";
        } else if (number >= 10_000) {
            abbreviation = number / 1_000 + "k";
        } else {
            abbreviation = String.valueOf(number);
        }

        return abbreviation;
    }

    public void ViewLoop() {
        updateHeader();
        updateProgressBar();
    }
}