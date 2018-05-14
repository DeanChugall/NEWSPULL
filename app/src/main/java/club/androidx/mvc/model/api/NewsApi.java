package club.androidx.mvc.model.api;

import java.util.List;

import club.androidx.mvc.model.pojo.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {


    @GET("top-headlines?country=rs&apiKey=a26922e96e604e14970efcf9a0cffe83")
    Call<News> getNews();

}


