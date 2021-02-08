package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

class UserViewHolder extends RecyclerView.ViewHolder {
    private final TextView userItemView;

    private UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text){
        userItemView.setText(text);
    }

    static UserViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(view);
    }


    }
