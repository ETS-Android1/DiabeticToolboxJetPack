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
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user.UserViewModel;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class ChangePasswordFragment extends Fragment {

    private static final String TAG = "ChangePasswordFragment";

    private TextInputLayout currentPassword;
    private TextInputLayout newPassword;

    private UserViewModel userViewModel;
    private NavController navController;
    private User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View child = inflater.inflate(R.layout.fragment_change_password, container, false);
        navController = NavHostFragment.findNavController(this);
        currentPassword = child.findViewById(R.id.home_fragment_changePassword_current_password_textInputLayout);
        newPassword = child.findViewById(R.id.home_fragment_changePassword_new_password_textInputLayout);
        Button backButton = child.findViewById(R.id.home_fragment_cancel_password_change_button);
        Button updateButton = child.findViewById(R.id.home_fragment_update_password_button);
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
                String currentUserPassword = Objects.requireNonNull(currentPassword.getEditText()).getText().toString();
                String newUserPassword = Objects.requireNonNull(newPassword.getEditText()).getText().toString();
                try {
                    if (Globals.getMessageDigest("SHA-256", currentUserPassword).equals(currentUser.getPasswordHash())){
                        userViewModel.updateUserPassword(Globals.getMessageDigest("SHA-256", newUserPassword));
                        navController.navigateUp();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currentUser = ChangePasswordFragmentArgs.fromBundle(getArguments()).getParcelizedUser();
    }
}