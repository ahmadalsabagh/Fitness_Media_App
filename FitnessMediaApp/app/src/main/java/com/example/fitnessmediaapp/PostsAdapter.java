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
    private final LinkedList<Post> mPostsList;
    private LayoutInflater mInflater;

    public PostsAdapter(Context context, LinkedList<Post> postsList) {

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
        Post mCurrent = mPostsList.get(position);
        holder.postContentView.setText(mCurrent.getContent());
        holder.postLocationView.setText("location: " + mCurrent.getLocation());
        holder.postTimeView.setText("time: " + mCurrent.getTime());

    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }


    private void getLayoutPosition() {
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        public final TextView postContentView;
        public final TextView postTimeView;
        public final TextView postLocationView;
        final PostsAdapter mAdapter;
        public PostsViewHolder(View itemView, PostsAdapter adapter) {
            super(itemView);
            postContentView = itemView.findViewById(R.id.txtContent);
            postTimeView = itemView.findViewById(R.id.txtTime);
            postLocationView = itemView.findViewById(R.id.txtLocation);
            this.mAdapter = adapter;

        }
    }
}