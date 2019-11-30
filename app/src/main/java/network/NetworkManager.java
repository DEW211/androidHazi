package network;

import data.CategoryResult;
import data.QuestionResult;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String SERVICE_URL = "https://opentdb.com";

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private Retrofit retrofit;
    private TriviaApi triviaApi;

    private NetworkManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        triviaApi = retrofit.create(TriviaApi.class);
    }

    //kategória lekérés
    public Call<CategoryResult> getCategories() {
        return triviaApi.getCategories();
    }

    //kérdések kérés
    public Call<QuestionResult> getQuestions(int amount, String category, String difficulty){
        return triviaApi.getQuestions(amount, category, difficulty.toLowerCase(), "multiple");
    }
}