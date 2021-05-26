package org.hse.mcatalog.ui.music_catalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.hse.mcatalog.R;

public class MCatalogFragment extends Fragment {

    private MCatalogViewModel MCatalogViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MCatalogViewModel =
                new ViewModelProvider(this).get(MCatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music_catalog, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        MCatalogViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}