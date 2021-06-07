package org.hse.mcatalog.ui.profile;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.android.material.navigation.NavigationView;

import org.hse.mcatalog.MainActivity;
import org.hse.mcatalog.PreferenceManager;
import org.hse.mcatalog.R;
import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private PreferenceManager preferenceManager;
    private EditText name;
    private TextView name_nav_header;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.d("tag", "profile");
//        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(false);
//        preferenceManager = new PreferenceManager(this.getContext());
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("tag", "profile1");
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        name = root.findViewById(R.id.name);
        Log.d("tag", "profile2");
        NavigationView navigationView = getParentFragment().getActivity().findViewById(R.id.nav_view);
        name_nav_header = navigationView.getHeaderView(0).findViewById(R.id.username_nav_title);
        Log.d("tag", "profile3");

        LiveData<String> liveData = profileViewModel.getText();

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                name.setText(s);
            }
        });

        preferenceManager = new PreferenceManager(root.getContext());

        profileViewModel.setText(preferenceManager.getValue("name", ""));

        name.post(new Runnable() {
            @Override
            public void run() {
                name.setSelection(name.length());
            }
        });

        name.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                name_nav_header.setText(s.toString());
                preferenceManager.saveValue("name", name.getText().toString());
//                profileViewModel.setText(preferenceManager.getValue("name", ""));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        Log.d("tag", "profile4");
        return root;
    }

//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        preferenceManager.saveValue("name", name.getText().toString());
//        name_nav_header.setText(name.getText().toString());
//        name_nav_header.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//  }
}