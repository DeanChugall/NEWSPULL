package club.androidx.mvc.controller;

import java.util.List;

import club.androidx.mvc.model.pojo.Articles;
import club.androidx.mvc.model.pojo.News;

public interface NewsCallbackListener {

    void onFetchStart();
    //void onFetchProgress(Flower flower);
    void onFetchProgress(List<Articles> newsList);
    void onFetchComplete();
    void onFetchFailed();

}
