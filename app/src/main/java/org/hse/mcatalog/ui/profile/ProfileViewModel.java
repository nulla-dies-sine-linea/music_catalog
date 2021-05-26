package org.hse.mcatalog.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Сссылка на облако, избранное, плейлисты");
    }

    public void setText(String s) {
        mText.setValue(s);
    }

    public LiveData<String> getText() {
        return mText;
    }
}