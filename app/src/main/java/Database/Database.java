package Database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {DatabaseQuestion.class}, version = 2)
public abstract class Database extends RoomDatabase {


    public abstract DatabaseQuestionDao databaseQuestionDao();
}
