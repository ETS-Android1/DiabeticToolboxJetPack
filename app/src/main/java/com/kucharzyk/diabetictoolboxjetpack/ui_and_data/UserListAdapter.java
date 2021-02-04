package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

public class UserListAdapter extends ListAdapter<User, UserViewHolder> {

    protected UserListAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User current = getItem(position);
        holder.bind(current.getUsername());
    }

    static class UserDiff extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }
    }
}
