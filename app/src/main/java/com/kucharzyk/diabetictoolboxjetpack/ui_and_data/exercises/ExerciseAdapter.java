package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises = new ArrayList<>();
    private List<Exercise> exercisesFull;
    private ExerciseAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddExerciseClick(int position);

        void onDeleteExerciseClick(int position);
    }

    public void setOnItemClickListener(ExerciseAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{

        public TextView exerciseName;

        public TextView mProductCarbsValue;
        public TextView mProductFatValue;
        public TextView mProductProteinsValue;
        public TextView mProductCarbsExchangerValue;
        public TextView mProductFatExchangerValue;

        public ImageView addExerciseImage;
        public ImageView deleteExerciseImage;
        public ImageView editExerciseImage;

        public ExerciseViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_example_text_name);
        }


    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_exercise, parent, false);
        ExerciseViewHolder exerciseViewHolder = new ExerciseViewHolder(view, onItemClickListener);
        return exerciseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise currentExercise = exercises.get(position);
        holder.exerciseName.setText(currentExercise.getExerciseName());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
