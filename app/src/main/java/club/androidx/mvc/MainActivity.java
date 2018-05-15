package club.androidx.mvc;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import club.androidx.mvc.controller.Controller;
import club.androidx.mvc.controller.NewsCallbackListener;
import club.androidx.mvc.model.adapter.NewsAdapter;
import club.androidx.mvc.model.pojo.Articles;

public class MainActivity extends AppCompatActivity implements NewsCallbackListener{



    public static Context mContext;

    public MainActivity() {

    }

    private RecyclerView recyclerView;
    private List<Articles> mArticlesList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private Controller mController;

    private ProgressDialog pd;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();

    }

    private void configViews() {
        recyclerView = findViewById(R.id.rcv_recycle);
        mSwipeRefreshLayout = findViewById(R.id.swipe);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        newsAdapter = new NewsAdapter(mArticlesList);
        recyclerView.setAdapter(newsAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                VELICIna = 0;
                mController.startFetching();
            }
        });


    }

    @Override
    public void onFetchStart() {
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");
        pd.show();
    }
    int VELICIna = 0;
    @Override
    public void onFetchProgress(List<Articles> newsList) {


       newsAdapter.addNews(newsList.get(VELICIna++));



       // Log.e("onFetchProgress: ", " ----------------- \n\n");


    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
        pd.cancel();
    }

    @Override
    public void onFetchFailed() {
        //Log.e("onFetchFailed: ", "RAAAAAAAAAADIIIIIIIIMMMMMM !!!!");

    }
}
