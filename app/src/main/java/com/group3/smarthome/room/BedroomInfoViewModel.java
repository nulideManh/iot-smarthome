package com.group3.smarthome.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BedroomInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BedroomInfoViewModel() {
        mText = new MutableLiveData<>();

    }
    public LiveData<String> getText() {
        return mText;
    }
}
