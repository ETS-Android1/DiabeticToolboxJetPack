package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.glycemia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.kucharzyk.diabetictoolboxjetpack.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlycemiaFragment extends Fragment {

    private GlycemiaViewModel glycemiaViewModel;
    private LocalDateTime currentDateTime;

    private TextView mGlycemiaValue;
    private TextInputEditText currentDateEditText;
    private TextInputEditText currentTimeEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        glycemiaViewModel = new ViewModelProvider(requireActivity()).get(GlycemiaViewModel.class);
        View root =  inflater.inflate(R.layout.glycemia_fragment, container, false);

        mGlycemiaValue = root.findViewById(R.id.glycemia_textView);
        currentDateEditText = root.findViewById(R.id.glycemia_date_textInputEditText);
        currentTimeEditText = root.findViewById(R.id.glycemia_time_textInputEditText);

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