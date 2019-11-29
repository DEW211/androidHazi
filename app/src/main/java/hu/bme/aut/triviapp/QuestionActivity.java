package hu.bme.aut.triviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity  {

    private Button btn_answer1;
    private Button btn_answer2;
    private Button btn_answer3;
    private Button btn_answer4;
    private TextView tvQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvQuestion = findViewById(R.id.tvQuestion);
        btn_answer1 = findViewById(R.id.question1);
        btn_answer2 = findViewById(R.id.question2);
        btn_answer3 = findViewById(R.id.question3);
        btn_answer4 = findViewById(R.id.question4);
        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WinActivity.class);
                startActivity(intent);
            }
        });
        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoseActivity.class);
                startActivity(intent);
            }
        });





    }








}
