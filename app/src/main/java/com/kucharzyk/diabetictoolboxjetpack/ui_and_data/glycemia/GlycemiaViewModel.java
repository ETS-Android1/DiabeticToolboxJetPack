package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.glycemia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Glycemia;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.GlycemiaRepository;

public class GlycemiaViewModel extends AndroidViewModel {

    private final GlycemiaRepository glycemiaRepository;

    public GlycemiaViewModel(@NonNull Application application) {
        super(application);

        glycemiaRepository = new GlycemiaRepository(application);
    }

    public void insert(Glycemia glycemiaMeasurement){
        glycemiaRepository.insert(glycemiaMeasurement);
    }

    public void delete(Glycemia glycemiaMeasurement) {
        glycemiaRepository.delete(glycemiaMeasurement);
    }

    public void update(Glycemia glycemiaMeasurement) {
        glycemiaRepository.update(glycemiaMeasurement);
    }
}