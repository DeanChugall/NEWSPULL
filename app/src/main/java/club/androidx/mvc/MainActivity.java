package club.androidx.mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import club.androidx.mvc.controller.Controller;
import club.androidx.mvc.controller.NewsCallbackListener;
import club.androidx.mvc.model.pojo.Articles;
import club.androidx.mvc.model.pojo.News;

public class MainActivity extends AppCompatActivity implements NewsCallbackListener{

    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = new Controller(MainActivity.this);
        mController.startFetching();
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(List<Articles> newsList) {

        Log.e("onFetchProgress: ", "----------------- \n");


    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed() {
        Log.e("onFetchFailed: ", "RAAAAAAAAAADIIIIIIIIMMMMMM !!!!");

    }
}
