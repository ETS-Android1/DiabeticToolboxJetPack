package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.glycemia;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Glycemia;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary.DiaryEntryViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class GlycemiaFragment extends Fragment {
    public static final String TAG = "GlycemiaFragment";

    private GlycemiaViewModel glycemiaViewModel;
    private DiaryEntryViewModel diaryEntryViewModel;
    private LocalDateTime currentDateTime;

    private TextView mGlycemiaValue;
    private TextInputLayout mGlycemiaValueLayout;
    private TextInputEditText currentDateEditText;
    private TextInputEditText currentTimeEditText;
    private FloatingActionButton mSaveMeasurementButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        glycemiaViewModel = new ViewModelProvider(requireActivity()).get(GlycemiaViewModel.class);

        View root =  inflater.inflate(R.layout.glycemia_fragment, container, false);

        mGlycemiaValueLayout = root.findViewById(R.id.glycemia_glycemia_textInputLayout);
        mGlycemiaValue = root.findViewById(R.id.glycemia_value_textInputEditText);
        currentDateEditText = root.findViewById(R.id.glycemia_date_textInputEditText);
        currentTimeEditText = root.findViewById(R.id.glycemia_time_textInputEditText);
        mSaveMeasurementButton = root.findViewById(R.id.glycemia_button_save);

        mSaveMeasurementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDateString = Objects.requireNonNull(currentDateEditText.getText()).toString();
                String currentTimeString = Objects.requireNonNull(currentTimeEditText.getText()).toString();
                String currentDateTimeString = currentDateString + " " + currentTimeString;
                currentDateTime = LocalDateTime.parse(currentDateTimeString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


                if (mGlycemiaValueLayout.getError() == null && mGlycemiaValue.getText() != null) {
                    glycemiaViewModel.insertDiaryEntry(new DiaryEntry(currentDateTime.toLocalDate()));
                    glycemiaViewModel.insertMeasurement(new Glycemia(currentDateTime, Integer.parseInt(mGlycemiaValue.getText().toString())));
                    Objects.requireNonNull(mGlycemiaValueLayout.getEditText()).getText().clear();
                    Toast.makeText(requireContext(), "Measurement was successfully saved!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(requireContext(), "Glycemia value is invalid!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mGlycemiaValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (count > 0){
                    if(Integer.parseInt(charSequence.toString()) < 20 || Integer.parseInt(charSequence.toString()) > 600){
                        mGlycemiaValueLayout.setError("Podaj wartość z przedziału 20-600");
                    }
                    else if(Integer.parseInt(charSequence.toString()) > 20 || Integer.parseInt(charSequence.toString()) < 600){
                        mGlycemiaValueLayout.setError(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if(Integer.parseInt(editable.toString()) < 20 || Integer.parseInt(editable.toString()) > 600){
                        mGlycemiaValueLayout.setError("Podaj wartość z przedziału 20-600");
                    }
                } catch (NumberFormatException e) {
                    Log.e(TAG, "afterTextChanged: glycemiaValueEditText cleared", e);
                }

            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        currentDateTime = LocalDateTime.now();
        currentDateEditText.setText(currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        currentTimeEditText.setText(currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}