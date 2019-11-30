package Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseQuestionDao {

    @Query("SELECT * FROM questions")
    List<DatabaseQuestion> getAll();

    @Insert
    void insertAll(DatabaseQuestion... questions);
}
