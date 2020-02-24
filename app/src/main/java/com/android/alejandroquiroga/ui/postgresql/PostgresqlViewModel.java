package com.android.alejandroquiroga.ui.postgresql;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostgresqlViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostgresqlViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is postgresql fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}