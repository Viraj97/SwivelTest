package com.example.swiveltest.ui.home;

import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.swiveltest.R;
import com.github.ybq.android.spinkit.style.Pulse;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Loading");
    }

    public LiveData<String> getText() {
        return mText;
    }
}