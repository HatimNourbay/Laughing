package com.example.hatim.laughing;

import android.content.Context;
import android.content.Intent;
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
        public TextView creator;
        public TextView description;
        public String url;



        public ViewHolderArticle(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.artTitle);
            creator = (TextView) view.findViewById(R.id.artCreator);
            description = (TextView) view.findViewById(R.id.artDescription);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webIntent = new Intent(v.getContext(),DetailArticleActivity.class);
                    webIntent.putExtra("lien",url);
                    v.getContext().startActivity(webIntent);
                }
            });

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
        holder.creator.setText(article.creator);
        holder.description.setText(article.description);
        holder.url = article.link;
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}
