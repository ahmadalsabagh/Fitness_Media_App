package com.example.fitnessmediaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PostsAdapter extends FirestoreRecyclerAdapter <Post, PostsAdapter.PostViewHolder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostsAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {
        holder.txtContentFromPost.setText(model.getContent());
//        holder.txtLocationFromPost.setText(model.getLocation());
//        holder.txtUsernameFromPost.setText(model.getUsername());

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.posts_item, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView txtContentFromPost;
//        TextView txtLocationFromPost;
//        TextView txtUsernameFromPost;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContentFromPost = itemView.findViewById(R.id.txtPostContent);
//            txtLocationFromPost = itemView.findViewById(R.id.txtPostLocation);
//            txtUsernameFromPost = itemView.findViewById(R.id.txtPostUsername);


        }
    }
}