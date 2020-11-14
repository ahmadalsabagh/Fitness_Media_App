package com.example.fitnessmediaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>  {
    private final LinkedList<String> mPostsList;
    private LayoutInflater mInflater;

    public PostsAdapter(Context context, LinkedList<String> postsList) {

        mInflater = LayoutInflater.from(context);
        this.mPostsList = postsList;
    }

    @NonNull
    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.posts_item,
                parent, false);
        return new PostsViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostsViewHolder holder, int position) {
        String mCurrent = mPostsList.get(position);
        holder.postsItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }


    private void getLayoutPosition() {
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        public final TextView postsItemView;
        final PostsAdapter mAdapter;
        public PostsViewHolder(View itemView, PostsAdapter adapter) {
            super(itemView);
            postsItemView = itemView.findViewById(R.id.txtDummy1);
            this.mAdapter = adapter;

        }

    }
}