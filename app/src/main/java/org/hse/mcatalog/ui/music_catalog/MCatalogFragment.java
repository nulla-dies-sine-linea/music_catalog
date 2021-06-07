package org.hse.mcatalog.ui.music_catalog;

import android.content.Context;
import android.os.Bundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

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
    private MCatalogViewModel MCatalogViewModel;

//    public MainActivity activity = (MainActivity) getParentFragment().getActivity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MCatalogViewModel =
                new ViewModelProvider(this).get(MCatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music_catalog, container, false);
        setHasOptionsMenu(true);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        MCatalogViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

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

        initData();
        return root;
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
//        Log.d("tag", "4");
//        super.onCreate(savedInstanceState);
//        Log.d("tag", "5");
////        setContentView(R.layout.activity_schedule);
//        recyclerView = (RecyclerView)getParentFragment().getActivity().findViewById(R.id.recycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(activity, 0));
//        adapter = new ItemAdapter(
////                new BaseActivity.OnItemClick() {
////            public void onClick(ScheduleItem data) {
////            }
////        }
//        );
//        recyclerView.setAdapter(adapter);
//
//        initData();
//    }
//
    private void initData() {
        Log.d("tag", "initdata");
        List<MusicItem> list = new ArrayList<>();

        MusicItem item = new MusicItem();
        item.setName("Track 1");
        item.setAuthor("Author 1");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 2");
        item.setAuthor("Author 2");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 3");
        item.setAuthor("Author 3");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 4");
        item.setAuthor("Author 4");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 5");
        item.setAuthor("Author 5");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 6");
        item.setAuthor("Author 6");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 7");
        item.setAuthor("Author 7");
        list.add(item);

        item = new MusicItem();
        item.setName("Track 8");
        item.setAuthor("Author 8");
        list.add(item);

        adapter.setDataList(list);
    }
//        mainViewModel.getTimeTableTeacherByDate(date).observe(this, new Observer<List<TimeTableWithTeacherEntity>>() {
//            @Override
//            public void onChanged(@org.jetbrains.annotations.Nullable List<TimeTableWithTeacherEntity> entitylist) {
//
//                List<ScheduleItem> list = new ArrayList<>();
//
//                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", Locale.forLanguageTag("ru"));
//                SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, dd MMMM", myDateFormatSymbols);
//                SimpleDateFormat sdf3 = new SimpleDateFormat("w", Locale.forLanguageTag("ru"));
//                SimpleDateFormat sdf4 = new SimpleDateFormat("u", Locale.getDefault());
//
//
//                for (TimeTableWithTeacherEntity entity: entitylist){
//
//                    if (type == BaseActivity.ScheduleType.DAY && sdf2.format(entity.timeTableEntity.timeStart).compareTo(sdf2.format(date)) == 0){
//                        ScheduleItem item = new ScheduleItem();
//                        Log.d("tag", "1");
//                        item.setStart(sdf1.format(entity.timeTableEntity.timeStart));
//                        item.setEnd(sdf1.format(entity.timeTableEntity.timeEnd));
//                        item.setType(entity.timeTableEntity.type == 0? "Лекция": "Практическое занятие");
//                        item.setName(entity.timeTableEntity.subjName);
//                        item.setPlace(String.format("%s, %s", entity.timeTableEntity.corp, entity.timeTableEntity.cabinet));
//                        item.setTeacher(entity.teacherEntity.fio);
//                        list.add(item);
//                    }
//
//                    if (type == BaseActivity.ScheduleType.WEEK &&
//                            Integer.parseInt(sdf3.format(entity.timeTableEntity.timeStart)) == Integer.parseInt(sdf3.format(date)) &&
//                            Integer.parseInt(sdf4.format(entity.timeTableEntity.timeStart)) >= Integer.parseInt(sdf4.format(date))){
//
//                        ScheduleItemHeader header = new ScheduleItemHeader();
//                        header.setTitle(sdf2.format(entity.timeTableEntity.timeStart));
//                        list.add(header);
//
//                        ScheduleItem item = new ScheduleItem();
//                        item.setStart(sdf1.format(entity.timeTableEntity.timeStart));
//                        item.setEnd(sdf1.format(entity.timeTableEntity.timeEnd));
//                        item.setType(entity.timeTableEntity.type == 0? "Лекция": "Практическое занятие");
//                        item.setName(entity.timeTableEntity.subjName);
//                        item.setPlace(String.format("%s, %s", entity.timeTableEntity.corp, entity.timeTableEntity.cabinet));
//                        item.setTeacher(entity.teacherEntity.fio);
//                        list.add(item);
//                    }
//                }
//                adapter.setDataList(list);
//            }
//        });

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
            author.setText(data.getAuthor());
        }
    }
}

