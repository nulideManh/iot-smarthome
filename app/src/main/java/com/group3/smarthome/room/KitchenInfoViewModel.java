package com.group3.smarthome.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KitchenInfoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public KitchenInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("PM2.5");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
