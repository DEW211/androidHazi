package network;

import data.CategoryResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TriviaApi {

    //kategória
    @GET("api_category.php")
    Call<CategoryResult> getCategories();

    //kérdések

}
