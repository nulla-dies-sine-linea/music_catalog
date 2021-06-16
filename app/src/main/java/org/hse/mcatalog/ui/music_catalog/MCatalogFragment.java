package org.hse.mcatalog.ui.music_catalog;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import android.support.v4.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.hse.mcatalog.CreatePlaylistActivity;
import org.hse.mcatalog.MainActivity;
import org.hse.mcatalog.MusicItem;
import org.hse.mcatalog.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MCatalogFragment extends Fragment {

    public RecyclerView recyclerView;
    public ItemAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        MCatalogViewModel =
//                new ViewModelProvider(this).get(MCatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music_catalog, container, false);
        setHasOptionsMenu(true);

        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePlaylistActivity.class);
                Log.d("tag", getActivity().toString());
//                intent.putExtra(ScheduleActivity.ARG_ID, group.getName());
//                intent.putExtra(ScheduleActivity.ARG_MODE, mode);
//                intent.putExtra(ScheduleActivity.ARG_TYPE, type);
//                intent.putExtra(ScheduleActivity.ARG_TIME, time);
                startActivity(intent);
                Log.d("tag", "2");
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
//
//        myFavoriteToggleButton.isChecked();


        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), 0));

        adapter = new ItemAdapter(
//                new BaseActivity.OnItemClick() {
//            public void onClick(ScheduleItem data) {
//            }
//        }
        );
        recyclerView.setAdapter(adapter);

        createCatalog(getActivity().getApplicationContext());
        return root;
    }

    public void createCatalog(Context ctx) {
        ArrayList<MusicItem> musicItemList = new ArrayList<>();
        Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor =  ctx.getContentResolver().query(allSongsUri, null, null, null, selection);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    MusicItem item = new MusicItem();
                    item.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                    item.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                    item.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                    item.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                    item.setLength(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
//                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    musicItemList.add(item);
//                    album_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
//                    int album_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
//                    int artist_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                } while (cursor.moveToNext());
            }
            cursor.close();
            adapter.setDataList(musicItemList);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        String message = "You click fragment ";
//        if(itemId == R.id.fragment_menu_search)
//        {
//            message += "Search menu";
//        }else if(itemId == R.id.fragment_menu_news)
//        {
//            message += "News menu";
//        }else if(itemId == R.id.fragment_menu_tech)
//        {
//            message += "Tech menu";
//        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        createCatalog(this);
//    }

    public final static class ItemAdapter extends
            RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MusicItem> dataList = new ArrayList<>();
//        private BaseActivity.OnItemClick onItemClick;

//        public ItemAdapter(BaseActivity.OnItemClick onItemClick) {
//            this.onItemClick = onItemClick;
//        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("tag", "initdata1");
            Context context = parent.getContext();
//            View root = inflater.inflate(R.layout.fragment_music_catalog, container, false);
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.music_item, parent, false);
            return new ViewHolder(contactView);

//                    , onItemClick

//            if (viewType == TYPE_ITEM) {
//                View contactView = inflater.inflate(R.layout.item_schedule, parent, false);
//                return new ViewHolder(contactView, context, onItemClick);
//            } else if (viewType == TYPE_HEADER) {
//                View contactView = inflater.inflate(R.layout.item_schedule_header, parent, false);
//                return new ViewHolderHeader(contactView, context, onItemClick);
//            }
//            throw new IllegalArgumentException("Invalid view type");
        }

//        public int getItemViewType(int position) {
//            ScheduleItem data = dataList.get(position);
//            if (data instanceof ScheduleItemHeader) {
//                return TYPE_HEADER;
//            }
//            return TYPE_ITEM;
//        }

        public void setDataList(List<MusicItem> list) {
            Log.d("tag", "initdata2");
            this.dataList = new ArrayList<>();
            if (dataList != null) {
                this.dataList.addAll(list);
            }
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            Log.d("tag", "initdata3");
            MusicItem data = dataList.get(position);
            ((ViewHolder) viewHolder).bind((MusicItem) data);
//            if (viewHolder instanceof ViewHolder) {
//                ((ViewHolder) viewHolder).bind((MusicItem) data);
//            } else if (viewHolder instanceof ViewHolderHeader) {
//                ((ViewHolderHeader) viewHolder).bind((ScheduleItemHeader) data);
//            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
//        private Context context;
//        private BaseActivity.OnItemClick onItemClick;

        private ImageView coverPic;
        private TextView name;
        private TextView author;

        public ViewHolder (View itemView){
//                , Context context){
//        , BaseActivity.OnItemClick onItemClick)
            super(itemView);
//            this.context = context;
//            this.onItemClick = onItemClick;
            coverPic = itemView.findViewById(R.id.music_item_cover_pic);
            name = itemView.findViewById(R.id.music_item_name);
            author = itemView.findViewById(R.id.music_item_author);
        }

        public void bind(final MusicItem data){
            Log.d("tag", "initdata4");
//            Glide.with(activity).load(data.getCoverPic()).into(coverPic);
//            coverPic.setImageResource(data.getCoverPic());
            name.setText(data.getName());
            author.setText(data.getArtist());
        }
    }
}

