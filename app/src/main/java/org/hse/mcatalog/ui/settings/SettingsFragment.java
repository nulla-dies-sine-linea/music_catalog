package org.hse.mcatalog.ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.hse.mcatalog.PreferenceManager;
import org.hse.mcatalog.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
//    private PreferenceManager preferenceManager;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.d("tag", "settings");
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(false);
//        preferenceManager = new PreferenceManager(this.getContext());
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Switch switchOne = (Switch) root.findViewById(R.id.switch_theme);

        switchOne.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Log.d("tag", "hh");
                        } else {

                        }
                    }
                });
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//
//        LiveData<String> liveData = settingsViewModel.getText();
//
//        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        settingsViewModel.setText(preferenceManager.getValue("name", "34"));

        return root;
    }
}