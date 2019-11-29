package hu.bme.aut.triviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fragments.DifficultyFragment;

public class WinActivity extends AppCompatActivity implements DifficultyFragment.DifficultyFragmentInterface{

    private Button btnNewGame;
    private Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        btnNewGame = findViewById(R.id.newGameFromWin);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CategorySelectActivity.class);
                startActivity(intent);
            }
        });
        btnMain = findViewById(R.id.backToMainFromWin);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onOptionsFragmentResult(String difficulty) {

    }
}
