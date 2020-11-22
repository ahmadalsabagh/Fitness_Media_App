package com.example.fitnessmediaapp;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CustomExerciseListAdapter extends FirestoreRecyclerAdapter <CustomExerciseList, CustomExerciseListAdapter.CustomListHolder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustomExerciseListAdapter(@NonNull FirestoreRecyclerOptions<CustomExerciseList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomListHolder holder, int position, @NonNull CustomExerciseList model) {
        holder.customExercise.setText(model.getExerciseName());
    }

    @NonNull
    @Override
    public CustomListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customexercise_item, parent, false);
        return new CustomListHolder(view);
    }

    class CustomListHolder extends RecyclerView.ViewHolder{

        TextView customExercise;

        public CustomListHolder(@NonNull View itemView) {
            super(itemView);
            customExercise = itemView.findViewById(R.id.custom_word);

        }
    }
}