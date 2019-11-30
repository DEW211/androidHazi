package hu.bme.aut.triviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import fragments.DifficultyFragment;

public class MainActivity extends AppCompatActivity implements DifficultyFragment.DifficultyFragmentInterface {

    private Button startGameButton;
    private Button loadSavedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = findViewById(R.id.btn_newGame);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DifficultyFragment().show(getSupportFragmentManager(), DifficultyFragment.TAG);
            }
        });

        loadSavedButton = findViewById(R.id.btn_loadGame);

        loadSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                intent.putExtra("isnew", "0");
                startActivity(intent);
            }
        });

    }



    @Override
    public void onOptionsFragmentResult(String difficulty) {
        //itt kell menteni valahova hogy milyen nehézség kell majd
        Intent intent = new Intent(this, CategorySelectActivity.class);
        intent.putExtra("difficulty", difficulty);
        String vmi = "1";
        intent.putExtra("isnew", vmi);
        startActivity(intent);
    }
}
