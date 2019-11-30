package hu.bme.aut.triviapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import Database.Database;
import Database.DatabaseQuestion;
import data.Question;
import data.QuestionResult;
import network.NetworkManager;

import static org.apache.commons.text.StringEscapeUtils.unescapeHtml4;

public class QuestionActivity extends AppCompatActivity  {

    private boolean isGame = true;

    private Database db;

    private Button btn_answer1;
    private Button btn_answer2;
    private Button btn_answer3;
    private Button btn_answer4;
    private TextView tvQuestion;

    List<DatabaseQuestion> question1 = null;

    private ImageButton btn_save;

    private int noQuestions = 0;
    private int currenNo;

    private List<Question> questions = null;
    private Question currentQuestion = null;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btn_save = findViewById(R.id.btnSave);

        tvQuestion = findViewById(R.id.tvQuestion);
        btn_answer1 = findViewById(R.id.question1);
        btn_answer2 = findViewById(R.id.question2);
        btn_answer3 = findViewById(R.id.question3);
        btn_answer4 = findViewById(R.id.question4);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "questions").fallbackToDestructiveMigration().build();
        //List<DatabaseQuestion> question1 = db.databaseQuestionDao().getAll();




        Intent intent = getIntent();
        final String isNew = intent.getStringExtra("isnew");

        if(isNew.equals("1")) {
            final String difficulty = intent.getStringExtra("difficulty");
            final String[] IDs = intent.getStringArrayExtra("categories");
            //kérdések lekérése, eventlistenrek beállítása, ha rossz a válasz, akkor lose screen, a jó akkor következő kérdés, ha csillag akkor adatb mentés és egy toast, ami szól róla
            //minden kategóriára 2 db kérdés
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    for (String id : IDs) {
                        try {
                            QuestionResult result = NetworkManager.getInstance().getQuestions(2, id, difficulty).execute().body();
                            if (questions == null) {
                                questions = new ArrayList<>();
                            }
                            for (Question question : result.getResults()) {
                                questions.add(question);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    return true;
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (success) {
                        noQuestions = questions.size();
                        currenNo = 1;
                        game();
                    }
                }
            }.execute();
        }else{
            //db
            new AsyncTask<Void, Void, List<DatabaseQuestion>>(){
                @Override
                protected List<DatabaseQuestion> doInBackground(Void... voids) {
                    List<DatabaseQuestion> questonsFromDb= db.databaseQuestionDao().getAll();
                    return questonsFromDb;
                }

                @Override
                protected void onPostExecute(List<DatabaseQuestion> questionsFromDb) {
                    if(questions == null){
                        questions = new ArrayList<>();
                    }
                    for (DatabaseQuestion databaseQuestion : questionsFromDb) {
                        List<String> incorrectAnswers = new ArrayList<>();
                        incorrectAnswers.add(databaseQuestion.incorrectAnswer1);
                        incorrectAnswers.add(databaseQuestion.incorrectAnswer2);
                        incorrectAnswers.add(databaseQuestion.incorrectAnswer3);
                        questions.add(new Question(databaseQuestion.getQuestion(), databaseQuestion.correctAnswer, incorrectAnswers));

                    }
                    noQuestions = questions.size();
                    currenNo = 1;
                    game();
                    //Toast.makeText(getBaseContext(), "Questions loaded!", Toast.LENGTH_SHORT).show();

                }
            }.execute();
        }







    }

    public void game() {
        currenNo++;
        tvQuestion.setText(unescapeHtml4(questions.get(0).getQuestion()));
        btn_answer1.setText(unescapeHtml4(questions.get(0).getCorrectAnswer()));
        btn_answer2.setText(unescapeHtml4(questions.get(0).getIncorrectAnswers().get(0)));
        btn_answer3.setText(unescapeHtml4(questions.get(0).getIncorrectAnswers().get(1)));
        btn_answer4.setText(unescapeHtml4(questions.get(0).getIncorrectAnswers().get(2)));
        currentQuestion = questions.get(0);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {
                        db.databaseQuestionDao().insertAll(new DatabaseQuestion(currentQuestion.getQuestion(), currentQuestion.getCorrectAnswer(), currentQuestion.getIncorrectAnswers().get(0),currentQuestion.getIncorrectAnswers().get(1), currentQuestion.getIncorrectAnswers().get(2)));
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        Toast.makeText(getBaseContext(), "Question saved!", Toast.LENGTH_SHORT).show();
                    }
                }.execute();


            }
        });

        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //következő kérdés vagy victory screen
                if(currenNo <= noQuestions){
                    questions.remove(0);
                    game();
                }else{
                    Intent intent = new Intent(v.getContext(), WinActivity.class);
                    startActivity(intent);
                }
            }
        });
        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoseActivity.class);
                startActivity(intent);
            }
        });
        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoseActivity.class);
                startActivity(intent);
            }
        });
        btn_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoseActivity.class);
                startActivity(intent);
            }
        });

    }








}
