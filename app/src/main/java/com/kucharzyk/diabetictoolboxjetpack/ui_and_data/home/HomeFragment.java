package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.util.List;

public class HomeFragment extends Fragment {

    private TextView userWelcome;

    private HomeViewModel homeViewModel;
    public static User currentUser;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        navController = NavHostFragment.findNavController(this);

        userWelcome = root.findViewById(R.id.home_welcome_user_textView);
        CardView updateUserInformationCard = root.findViewById(R.id.home_user_cardView);
        CardView changeUserPasswordCard = root.findViewById(R.id.home_changePassword_cardView);
        CardView mealCard = root.findViewById(R.id.home_food_cardView);
        CardView exercisesCard = root.findViewById(R.id.home_exercises_cardView);
        CardView measurementsCard = root.findViewById(R.id.home_measurements_cardView);
        CardView diaryCard = root.findViewById(R.id.home_diary_cardView);


        mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationFood();
                navController.navigate(action);
            }
        });

        exercisesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationExercises();
                navController.navigate(action);
            }
        });

        measurementsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationGlycemia();
                navController.navigate(action);
            }
        });

        diaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToNavigationDiary();
                navController.navigate(action);
            }
        });

        updateUserInformationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToUpdateUserInformationFragment(currentUser);
                navController.navigate(action);
            }
        });

        changeUserPasswordCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomeFragmentDirections.actionNavigationHomeToChangePasswordFragment(currentUser);
                navController.navigate(action);
            }
        });

        final Observer<List<User>> appUsersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                currentUser = users.get(0);
                setUserWelcome(currentUser.getUsername());
            }
        };

        homeViewModel.getAllApplicationUsers().observe(getViewLifecycleOwner(), appUsersObserver);
        return root;
    }

    private void setUserWelcome(String username){
        String welcome = "Welcome " + username;
        userWelcome.setText(welcome);
    }

}