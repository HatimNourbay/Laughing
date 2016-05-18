package com.example.hatim.laughing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hatim on 10/05/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolderArticle>{

    private ArrayList<ArticleItem> articleList;

    public class ViewHolderArticle extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolderArticle(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public ArticleAdapter(ArrayList<ArticleItem> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ViewHolderArticle onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_layout, parent, false);

        return new ViewHolderArticle(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticle holder, int position) {
        ArticleItem article = articleList.get(position);
        holder.title.setText(article.title);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}
