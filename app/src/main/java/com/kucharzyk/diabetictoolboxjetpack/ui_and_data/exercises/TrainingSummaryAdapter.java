package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;

import java.util.ArrayList;
import java.util.List;

public class TrainingSummaryAdapter extends RecyclerView.Adapter<TrainingSummaryAdapter.TrainingSummaryViewHolder>{

    private List<Exercise> trainingSummary = new ArrayList<>();
    private TrainingSummaryAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {

        void onEditExerciseClick(int position);

        void onDeleteExerciseClick(int position);
    }


    public void setOnItemClickListener(TrainingSummaryAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static class TrainingSummaryViewHolder extends RecyclerView.ViewHolder {

        private final TextView mExerciseName;
        private final TextView mCaloriesBurnedValue;
        private final TextView mCarbohydrateExchangersUsed;
        private final TextView mDuration;

        public TrainingSummaryViewHolder(@NonNull View itemView, TrainingSummaryAdapter.OnItemClickListener listener) {
            super(itemView);
            mExerciseName = itemView.findViewById(R.id.textView_example_exercise_name);
            mCaloriesBurnedValue = itemView.findViewById(R.id.textView_example_exercise_calories_burned_value);
            mCarbohydrateExchangersUsed = itemView.findViewById(R.id.textView_example_exercise_carbohydrate_exchangers_used_value);
            mDuration = itemView.findViewById(R.id.textView_example_exercise_duration_value);

            ImageView mEditExerciseImage = itemView.findViewById(R.id.imageView_edit_example_exercise);
            ImageView mDeleteExerciseImage = itemView.findViewById(R.id.imageView_delete_example_exercise);

            mEditExerciseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditExerciseClick(position);
                        }
                    }
                }
            });

            mDeleteExerciseImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteExerciseClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public TrainingSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_exercise_summary, parent, false);
        TrainingSummaryViewHolder trainingSummaryViewHolder = new TrainingSummaryAdapter.TrainingSummaryViewHolder(view, mOnItemClickListener);
        return trainingSummaryViewHolder;
    }

    private Double quickHelper(Exercise exercise) {
        return Globals.calculateCaloriesBurned(exercise.getExerciseDuration(),
                exercise.getMetabolicEquivalentOfTask(), 68.0);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingSummaryViewHolder holder, int position) {
        Exercise currentExercise = trainingSummary.get(position);
        holder.mExerciseName.setText(currentExercise.getExerciseName());
        holder.mCaloriesBurnedValue.setText(Globals.REAL_FORMATTER.format(quickHelper(currentExercise)));
        holder.mCarbohydrateExchangersUsed.setText(Globals.REAL_FORMATTER.format(quickHelper(currentExercise) / 4 / 12));
        holder.mDuration.setText(Globals.REAL_FORMATTER.format(currentExercise.getExerciseDuration()));
    }

    @Override
    public int getItemCount() {
        return trainingSummary.size();
    }

    public void setExercises(List<Exercise> trainingSummary) {
        this.trainingSummary = trainingSummary;
        notifyDataSetChanged();
    }


    public Exercise getExercise(int position) {return trainingSummary.get(position);}
}
