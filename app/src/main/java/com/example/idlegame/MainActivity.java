package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;

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

        ImageButton btn=(ImageButton)findViewById(R.id.levelupButton);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                CharSequence text = String.format("Value %s", game.getCpuUsage());
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(view.getContext(), text, duration).show();
            }
        });
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
    }

    public void startViewLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (game.getCpuUsage() <= game.getMaxCpuUsage()) {
                    ViewLoop();
                }
            }
        }, 1000, 1000);
    }

    public void ViewLoop() {
        updateHeader();
        updateProgressBar();
    }
}