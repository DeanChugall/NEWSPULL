package club.androidx.mvc.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import club.androidx.mvc.model.api.RestApiManager;
import club.androidx.mvc.model.pojo.Articles;
import club.androidx.mvc.model.pojo.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
         newsCallBackListener.onFetchStart();
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
                                //Log.e(TAG, "newsList.size(): " + newsList.size());
                                //************************************************************
                                if (response.body().getStatus() != "null") {
                                    //Log.e(TAG, "getStatus: " + response.body().getStatus());
                                } else {
                                    response.body().setStatus("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (response.body().getTotalResults() != "null") {
                                    //Log.e(TAG, "getTotalResults: " + response.body().getTotalResults());
                                } else {
                                    response.body().setTotalResults("Nepoznati izvor");
                                }
                                //************************************************************
                                if (response.isSuccessful()) {
                                   // Log.e(TAG, "getId: " + response.body().getArticles().get(i).getSource().getId());
                                } else {
                                    response.body().getArticles().get(i).getSource().setId("Nepoznati izvor");
                                    //----------------------------------------------------------------------
                                }
                                if (response.body().getArticles().get(i).getSource().getName() != "null") {
                                    //Log.e(TAG, "SOURCE: " + response.body().getArticles().get(i).getSource().getName());
                                } else {
                                    response.body().getArticles().get(i).getSource().setName("Nepoznati izvor");
                                }
                                //************************************************************
                                if (newsList.get(i).getAuthor() != "null") {
                                   // Log.e(TAG, "getAuthor: " + newsList.get(i).getAuthor());
                                } else {
                                    newsList.get(i).setAuthor("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getTitle() != "null") {
                                    //Log.e(TAG, "getTitle: " + newsList.get(i).getTitle());
                                } else {
                                    newsList.get(i).setTitle("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getDescription() != "null") {
                                   // Log.e(TAG, "getDescription: " + newsList.get(i).getDescription());
                                } else {
                                    newsList.get(i).setDescription("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                if (newsList.get(i).getUrl() != "null") {
                                    //Log.e(TAG, "URL: " + newsList.get(i).getUrl());
                                } else {
                                    newsList.get(i).setUrl("Nepoznati izvor");
                                }
                                //----------------------------------------------------------------------
                                String urlImage = newsList.get(i).getUrlToImage();

                                Log.e(TAG, "URL TO IMAGES: " + urlImage  + "\n");
                                newsList.get(i).setUrlToImage("Nepoznati izvor \n");


                                newsList.get(i).setUrlToImage(urlImage);



                                    Log.e(TAG, "URL TO IMAGES NULLLL: " + newsList.get(i).getUrlToImage());


                                //----------------------------------------------------------------------
                                if (newsList.get(i).getPublishedAt() != "null") {
                                    //Log.e(TAG, "getPublishedAt: " + newsList.get(i).getPublishedAt());
                                } else {
                                    newsList.get(i).setPublishedAt("Nepoznati izvor");
                                }

                                newsCallBackListener.onFetchProgress(newsList);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, "on RESPONSE: " +
                                    "NullPointerException: " + e);
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




}
























