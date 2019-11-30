package Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class DatabaseQuestion {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "correct_answer")
    public String correctAnswer;

    @ColumnInfo(name = "incorrect_answer1")
    public String incorrectAnswer1;

    @ColumnInfo(name = "incorrect_answer2")
    public String incorrectAnswer2;

    @ColumnInfo(name = "incorrect_answer3")
    public String incorrectAnswer3;

    public DatabaseQuestion(String question, String correctAnswer, String incorrectAnswer1, String incorrectAnswer2, String incorrectAnswer3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer1 = incorrectAnswer1;
        this.incorrectAnswer2 = incorrectAnswer2;
        this.incorrectAnswer3 = incorrectAnswer3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswer1() {
        return incorrectAnswer1;
    }

    public void setIncorrectAnswer1(String incorrectAnswer1) {
        this.incorrectAnswer1 = incorrectAnswer1;
    }

    public String getIncorrectAnswer2() {
        return incorrectAnswer2;
    }

    public void setIncorrectAnswer2(String incorrectAnswer2) {
        this.incorrectAnswer2 = incorrectAnswer2;
    }

    public String getIncorrectAnswer3() {
        return incorrectAnswer3;
    }

    public void setIncorrectAnswer3(String incorrectAnswer3) {
        this.incorrectAnswer3 = incorrectAnswer3;
    }
}
