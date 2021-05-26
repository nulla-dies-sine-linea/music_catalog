package org.hse.mcatalog.ui.profile;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.hse.mcatalog.PreferenceManager;
import org.hse.mcatalog.R;
import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private PreferenceManager preferenceManager;
    private EditText name;
    private TextView name_nav_header;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        preferenceManager = new PreferenceManager(this.getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        name = root.findViewById(R.id.name);
        name_nav_header = root.findViewById(R.id.username_nav_title);

        LiveData<String> liveData = profileViewModel.getText();

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                name.setText(s);
            }
        });

        profileViewModel.setText(preferenceManager.getValue("name", ""));

        name.post(new Runnable() {
            @Override
            public void run() {
                name.setSelection(name.length());
            }
        });

        name_nav_header.post(new Runnable() {
            @Override
            public void run() {
                name_nav_header.setText(preferenceManager.getValue("name", ""));
            }
        });

        return root;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        preferenceManager.saveValue("name", name.getText().toString());
    }
}