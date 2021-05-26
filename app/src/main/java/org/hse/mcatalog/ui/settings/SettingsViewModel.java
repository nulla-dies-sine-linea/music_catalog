package org.hse.mcatalog.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Тема, размер шрифта");
    }

    public void setText(String s) {
        mText.setValue(s);
    }

    public LiveData<String> getText() {
        return mText;
    }
}