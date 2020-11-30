package com.example.fitnessmediaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseListHolder> {
    private final LinkedList<String> mExerciseList;
    private final LinkedList<String> mDescriptionList;
    private final LinkedList<Integer> mImageList;
    //private LayoutInflater mInflater;
    private Context mContext;

    public ExerciseListAdapter(Context context, LinkedList<String> wordList,LinkedList<String> descriptionList, LinkedList<Integer> imageList){
        this.mContext = context;
        this.mExerciseList = wordList;
        this.mDescriptionList = descriptionList;
        this.mImageList = imageList;
    }

    @NonNull
    @Override
    public ExerciseListAdapter.ExerciseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exerciselist_item, parent, false);
        ExerciseListHolder holder = new ExerciseListHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ExerciseListAdapter.ExerciseListHolder holder, final int position) {
        String mCurrent = mExerciseList.get(position);
        final int imageCurrent = mImageList.get(position);
        final String descriptionCurrent = mDescriptionList.get(position);
        holder.wordItemView.setText(mCurrent);
        //holder.imageItemView.setImageResource(R.drawable.barbell);
        holder.imageItemView.setImageResource(imageCurrent);

        holder.parentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExerciseListActivity.class);
                intent.putExtra("exercise_description", descriptionCurrent);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    class ExerciseListHolder extends RecyclerView.ViewHolder{
        public final TextView wordItemView;
        public final ImageView imageItemView;
        LinearLayout parentBox;

        public ExerciseListHolder(View itemView){
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            imageItemView = itemView.findViewById(R.id.exerciseIconImg);
            parentBox = itemView.findViewById(R.id.parent_box);

        }

    }
}
