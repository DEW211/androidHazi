package network;

import data.CategoryResult;
import okhttp3.OkHttpClient;
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
    private TriviaApi weatherApi;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(TriviaApi.class);
    }

    //kategória lekérés
    public Call<CategoryResult> getCategories() {
        return weatherApi.getCategories();
    }

    //kérdések kérés
}