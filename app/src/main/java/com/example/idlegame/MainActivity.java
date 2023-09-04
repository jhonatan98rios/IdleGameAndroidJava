package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
        if (game.getMoney() >= game.getCpuUpgradePrice()) {
            game.increaseMaxCpuUsage();
            game.increaseProfitPerUser();
            game.subtractMoney(game.getCpuUpgradePrice());
            game.increaseCpuUpgradePrice();
            updateTextViews();
            updateHeader();
        }
    }

    public void buyMaxRamUpgrade() {
        if (game.getMoney() >= game.getRamUpgradePrice()) {
            game.increaseMaxRamUsage();
            game.increaseProfitPerUser();
            game.subtractMoney(game.getRamUpgradePrice());
            game.increaseRamUpgradePrice();
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
        binding.cpuProgressBar.setMax(game.getMaxCpuUsage());
        binding.cpuProgressBar.setProgress(game.getCpuUsage());

        binding.ramProgressBar.setMax(game.getMaxRamUsage());
        binding.ramProgressBar.setProgress(game.getRamUsage());
    }

    private void updateTextViews() {
        binding.cpuUpgradePriceText.setText(String.valueOf(game.getCpuUpgradePrice()));
        binding.ramUpgradePriceText.setText(String.valueOf(game.getRamUpgradePrice()));
    }

    public void startViewLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (game.getCpuUsage() <= game.getMaxCpuUsage() &&
                    game.getRamUsage() <= game.getMaxRamUsage()) {
                    ViewLoop();
                }
            }
        }, 0, 200);
    }

    public void ViewLoop() {
        updateHeader();
        updateProgressBar();
    }
}