package network;

import data.CategoryResult;
import data.QuestionResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApi {

    //kategória
    @GET("api_category.php")
    Call<CategoryResult> getCategories();

    //kérdések
    @GET("api.php")
    Call<QuestionResult> getQuestions(
            @Query("amount") int amount,
            @Query("category") String category,
            @Query("difficulty") String difficulty,
            @Query("type") String type
    );
}
