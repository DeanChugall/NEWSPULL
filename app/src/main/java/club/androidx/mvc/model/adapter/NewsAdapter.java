package club.androidx.mvc.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.List;

import club.androidx.mvc.R;
import club.androidx.mvc.model.pojo.Articles;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private static String TAG = NewsAdapter.class.getCanonicalName();

    private List<Articles> mNews;

    public NewsAdapter(List<Articles> articles) {
        mNews = articles;
    }

    public void addNews(Articles articles) {
        mNews.add(articles);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Articles currentNews = mNews.get(position);
        holder.title.setText(currentNews.getTitle());

        String path = currentNews.getUrlToImage();
        Log.e("PATH", "PUTANJA: " + path);
        if (path != null) {

            Picasso.get().load(currentNews.getUrlToImage()).into(holder.image);
        }









    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image;

        public Holder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.flowerName);
            image = itemView.findViewById(R.id.flowerImage);
        }


    }
}


















