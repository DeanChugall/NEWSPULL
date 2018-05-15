package club.androidx.mvc.model.api;

import club.androidx.mvc.MainActivity;
import club.androidx.mvc.model.utilities.Constants;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager {

    private NewsApi newsApi;

    public NewsApi getNewsApi() {
        if (newsApi == null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(MainActivity.mContext.getCacheDir(), cacheSize);


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsApi = retrofit.create(NewsApi.class);

        }

        return newsApi;
    }


}
