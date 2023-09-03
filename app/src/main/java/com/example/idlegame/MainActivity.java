package com.example.idlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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

        // Atualiza o valor da barra de progresso
        binding.cpuProgressBar.setMax(game.getMaxCpuUsage());
        binding.cpuProgressBar.setProgress(game.getCpuUsage());

        ImageButton btn=(ImageButton)findViewById(R.id.levelupButton);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                CharSequence text = String.format("Value %s", game.getCpuUsage());
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(view.getContext(), text, duration).show();
            }
        });
    }

    // Método que atualiza o valor da barra de progresso
    private void updateProgressBar() {
        binding.cpuProgressBar.setProgress(game.getCpuUsage());

//        if (binding.cpuUsage != null) {
//            binding.cpuUsage.setText(game.getCpuUsage());
//        }
    }

    public void startViewLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                game.increaseValues();
                updateProgressBar();
            }
        }, 1000, 1000);
    }
}