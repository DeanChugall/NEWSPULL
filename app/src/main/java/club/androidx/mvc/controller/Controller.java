package club.androidx.mvc.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import club.androidx.mvc.model.api.RestApiManager;
import club.androidx.mvc.model.pojo.Articles;
import club.androidx.mvc.model.pojo.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static club.androidx.mvc.model.utilities.FixUrlToImageExtension.fixUrl;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();

    private NewsCallbackListener newsCallBackListener;
    private RestApiManager restApiManager;

    private List<Articles> newsList;


    public Controller(NewsCallbackListener newsCallBackListener) {
        this.newsCallBackListener = newsCallBackListener;
        this.restApiManager = new RestApiManager();
    }

    public void startFetching() {
        // newsCallBackListener.onFetchStart();
        Call<News> call = restApiManager.getNewsApi().getNews();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: received information: " + response.body().toString());

                Log.e(TAG, "JSON: -- >> " + new Gson().toJson(response));
                try {
                    if (response.isSuccessful() && response.body() != null) {

                        try {

                            newsList = response.body().getArticles();


                            for (int i = 0; i < newsList.size(); i++) {

                                //************************************************************
                                if (response.body().getStatus() != "null") {
                                    Log.e(TAG, "getStatus: " + response.body().getStatus());
                                } else {
                                    response.body().setStatus("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (response.body().getTotalResults() != "null") {
                                    Log.e(TAG, "getTotalResults: " + response.body().getTotalResults());
                                } else {
                                    response.body().setTotalResults("Nepoznati izvor");
                                }
                                //************************************************************
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "getId: " + response.body().getArticles().get(i).getSource().getId());
                                } else {
                                    response.body().getArticles().get(i).getSource().setId("Nepoznati izvor");
                                    //----------------------------------------------------------------------
                                }
                                if (response.body().getArticles().get(i).getSource().getName() != "null") {
                                    Log.e(TAG, "SOURCE: " + response.body().getArticles().get(i).getSource().getName());
                                } else {
                                    response.body().getArticles().get(i).getSource().setName("Nepoznati izvor");
                                }
                                //************************************************************
                                if (newsList.get(i).getAuthor() != "null") {
                                    Log.e(TAG, "getAuthor: " + newsList.get(i).getAuthor());
                                } else {
                                    newsList.get(i).setAuthor("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getTitle() != "null") {
                                    Log.e(TAG, "getTitle: " + newsList.get(i).getTitle());
                                } else {
                                    newsList.get(i).setTitle("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getDescription() != "null") {
                                    Log.e(TAG, "getDescription: " + newsList.get(i).getDescription());
                                } else {
                                    newsList.get(i).setDescription("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getUrl() != "null") {
                                    Log.e(TAG, "URL: " + newsList.get(i).getUrl());
                                } else {
                                    newsList.get(i).setUrl("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getUrlToImage() != "null") {
                                    Log.e(TAG, "URL TO IMAGES: " + newsList.get(i).getUrlToImage());
                                    //Log.e(TAG, "URL TO IMAGES: " + fixUrl(newsList.get(i).getUrlToImage()));
                                } else {
                                    newsList.get(i).setUrlToImage("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getPublishedAt() != "null") {
                                    Log.e(TAG, "getPublishedAt: " + newsList.get(i).getPublishedAt());
                                } else {
                                    newsList.get(i).setPublishedAt("Nepoznati izvor");
                                }


                               /* if (!newsList.get(i).getTitle().isEmpty() || !newsList.get(i).getAuthor().isEmpty() || !newsList.get(i).getDescription().isEmpty()
                                        || !newsList.get(i).getUrlToImage().isEmpty()) {
                                    Log.e(TAG, "on RESPONSE: \n" +
                                            "getTitle: " + notNullNewsString(newsList.get(i).getTitle()) + "\n" +
                                            "getStatus: " + notNullNewsString(response.body().getStatus()) + "\n" +
                                            "SOURCE: " + notNullNewsString(response.body().getArticles().get(i).getSource().getName()) + "\n" +
                                            "URL TO IMAGES: " + notNullNewsString(newsList.get(i).getUrlToImage()) + "\n" +
                                            "URL TO IMAGES: " + notNullNewsString(fixUrl(newsList.get(i).getUrlToImage())) + "\n" +
                                            "getStatus: " + response.body().getTotalResults()
                                    );
                                }*/


                                newsCallBackListener.onFetchProgress(newsList);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "on RESPONSE: " +
                                    "getTitle: " + e);
                        }
                    }

                } catch (Exception e) {
                    newsCallBackListener.onFetchFailed();
                    Log.e(TAG, e.getLocalizedMessage());
                }
                newsCallBackListener.onFetchComplete();

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: received information: " + t.toString());
            }
        });


    }

    private String notNullNewsString(String news) {

        if (news.isEmpty())
            return "Nema sadrzaja !";

        return news;
    }


}
























