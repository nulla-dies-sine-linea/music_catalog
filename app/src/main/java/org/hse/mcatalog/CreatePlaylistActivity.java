package org.hse.mcatalog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.hse.mcatalog.ui.music_catalog.MCatalogFragment;
import org.hse.mcatalog.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ItemAdapter adapter;
    public Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_playlist_activity);

        Log.d("tag", "1");

        done = findViewById(R.id.button_done);
        done.setOnClickListener(v ->
        {
//            Toast.makeText("Playlist created", )
//                    make("Replace with your own action", Snackbar.LENGTH_LONG).show();
//            Intent intent = new Intent(this, MCatalogFragment.class);
////                intent.putExtra(ScheduleActivity.ARG_ID, group.getName());
////                intent.putExtra(ScheduleActivity.ARG_MODE, mode);
////                intent.putExtra(ScheduleActivity.ARG_TYPE, type);
////                intent.putExtra(ScheduleActivity.ARG_TIME, time);
//            startActivity(intent);
        });


        recyclerView = findViewById(R.id.recycleViewPlaylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 0));

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        showTracks(this);
    }

    public void showTracks(Context ctx) {
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
                musicItemList.add(item);
                } while (cursor.moveToNext());
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

            View contactView = inflater.inflate(R.layout.playlist_item, parent, false);
            return new ProfileFragment.ViewHolder(contactView);
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
            ((ProfileFragment.ViewHolder) viewHolder).bind((MusicItem) data);
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
}
