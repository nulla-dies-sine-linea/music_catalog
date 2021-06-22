package org.hse.mcatalog.ui.profile;

import android.content.Context;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.hse.mcatalog.MainActivity;
import org.hse.mcatalog.MusicItem;
import org.hse.mcatalog.PreferenceManager;
import org.hse.mcatalog.R;
import org.hse.mcatalog.ui.music_catalog.MCatalogFragment;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private PreferenceManager preferenceManager;
    private EditText name;
    private EditText link;
    private TextView name_nav_header;
    private TextView link_nav_header;

    public RecyclerView recyclerView;
    public ItemAdapter adapter;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.d("tag", "profile");
//        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(false);
//        preferenceManager = new PreferenceManager(this.getContext());
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        name = root.findViewById(R.id.name);
        NavigationView navigationView = getParentFragment().getActivity().findViewById(R.id.nav_view);
        name_nav_header = navigationView.getHeaderView(0).findViewById(R.id.username_nav_title);

        link = root.findViewById(R.id.cloudStorageLink);
        link_nav_header = navigationView.getHeaderView(0).findViewById(R.id.link_nav_title);

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
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        profileViewModel.setText(preferenceManager.getValue("link", ""));
        link.post(new Runnable() {
            @Override
            public void run() {
                link.setSelection(link.length());
            }
        });
        link.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                link_nav_header.setText(s.toString());
                preferenceManager.saveValue("link", link.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recyclerView = root.findViewById(R.id.recycleViewFav);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), 0));

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        showFav(getActivity().getApplicationContext());
        return root;
    }

    public void showFav(Context ctx) {
        ArrayList<MusicItem> musicItemList = new ArrayList<>();
        Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor =  ctx.getContentResolver().query(allSongsUri, null, null, null, selection);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
//                do {
                    MusicItem item = new MusicItem();
                    item.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                    item.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                    item.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                    item.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                    item.setLength(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                    musicItemList.add(item);
//                } while (cursor.moveToNext());
            }
            cursor.close();
            adapter.setDataList(musicItemList);
        }
    }

    public final static class ItemAdapter extends
            RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MusicItem> dataList = new ArrayList<>();

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.fav_item, parent, false);
            return new ViewHolder(contactView);
        }

        public void setDataList(List<MusicItem> list) {
            this.dataList = new ArrayList<>();
            if (dataList != null) {
                this.dataList.addAll(list);
            }
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            MusicItem data = dataList.get(position);
            ((ViewHolder) viewHolder).bind((MusicItem) data);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        private TextView name;
        private TextView artist;

        public ViewHolder (View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.music_item_name);
            artist = itemView.findViewById(R.id.music_item_author);
        }

        public void bind(final MusicItem data){
            name.setText(data.getName());
            artist.setText(data.getArtist());
        }
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