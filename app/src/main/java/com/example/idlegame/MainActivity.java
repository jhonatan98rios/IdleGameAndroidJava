package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
        ImageButton maxCpuUpgradeBtn=(ImageButton)findViewById(R.id.cpuLevelupButton);
        ImageButton maxRamUpgradeBtn=(ImageButton)findViewById(R.id.ramLevelupButton);

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
    }

    public void buyMaxCpuUpgrade() {
        if (game.getMoney() >= game.cpuMetrics.upgradePrice) {
            game.cpuMetrics.increaseMaxUsage();
            game.increaseProfitPerUser();
            game.subtractMoney(game.cpuMetrics.upgradePrice);
            game.cpuMetrics.increaseUpgradePrice();

            updateTextViews();
            updateHeader();
        }
    }

    public void buyMaxRamUpgrade() {
        if (game.getMoney() >= game.ramMetrics.upgradePrice) {

            game.ramMetrics.increaseMaxUsage();

            game.increaseProfitPerUser();
            game.subtractMoney(game.ramMetrics.upgradePrice);
            game.ramMetrics.increaseUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    // Update header data
    private void updateHeader() {
        String str_money = String.valueOf(game.getMoney());
        binding.moneyText.setText(str_money);

        String str_profit_per_user = String.valueOf(game.getProfitPerUser());
        binding.profitPerUserText.setText(str_profit_per_user);

        String str_number_of_users = String.valueOf(game.getNumberOfUsers());
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


    private void updateTextViews() {
        binding.cpuUpgradePriceText.setText(String.valueOf(game.cpuMetrics.upgradePrice));
        binding.ramUpgradePriceText.setText(String.valueOf(game.ramMetrics.upgradePrice));
    }

    public void startViewLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (game.cpuMetrics.usage <= game.cpuMetrics.maxUsage &&
                    game.ramMetrics.usage <= game.ramMetrics.maxUsage) {
                    ViewLoop();
                }

                if (game.getNumberOfUsers() == 0) {
                    Toast.makeText(getApplicationContext(), "Perdeste", Toast.LENGTH_LONG).show();
                }
            }
        }, 0, 100);
    }

    public void ViewLoop() {
        updateHeader();
        updateProgressBar();
    }
}