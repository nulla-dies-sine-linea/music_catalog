package org.hse.mcatalog.ui.music_catalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MCatalogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MCatalogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Список");
    }

    public LiveData<String> getText() {
        return mText;
    }
}