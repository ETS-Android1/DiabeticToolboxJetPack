package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user.UserViewModel;

import java.util.Objects;


public class UpdateUserInformationFragment extends Fragment {

    private static final String TAG = "UpdateUserInformationFragment";

    private TextInputLayout userName;
    private TextInputLayout userWeight;

    private UserViewModel userViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View child = inflater.inflate(R.layout.fragment_update_user_information, container, false);
        navController = NavHostFragment.findNavController(this);
        userName = child.findViewById(R.id.home_fragment_update_username_textInputLayout);
        userWeight = child.findViewById(R.id.home_fragment_update_weight_textInputLayout);
        Button backButton = child.findViewById(R.id.home_fragment_cancel_update_button);
        Button updateButton = child.findViewById(R.id.home_fragment_update_user_button);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUsername = Objects.requireNonNull(userName.getEditText()).getText().toString();
                String newUserWeight = Objects.requireNonNull(userWeight.getEditText()).getText().toString();
                userViewModel.updateUsername(newUsername);
                userViewModel.updateUserWeight(Double.parseDouble(newUserWeight));
                navController.navigateUp();
            }
        });

        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        User currentUser = UpdateUserInformationFragmentArgs.fromBundle(getArguments()).getParcelizedUser();
        Objects.requireNonNull(userName.getEditText()).setText(currentUser.getUsername());
        Objects.requireNonNull(userWeight.getEditText()).setText(currentUser.getWeight().toString());
    }
}