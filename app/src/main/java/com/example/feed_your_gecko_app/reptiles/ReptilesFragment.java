package com.example.feed_your_gecko_app.reptiles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feed_your_gecko_app.database.AppDatabase;
import com.example.feed_your_gecko_app.database.relations.UserReptiles;
import com.example.feed_your_gecko_app.database.tables.UserReptile;
import com.example.feedyourgeckoapp.R;
import com.example.feedyourgeckoapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class ReptilesFragment extends Fragment {

    ReptilesViewAdapter reptilesViewAdapter;
    public RecyclerView recyclerView;
    public List<ReptileListItem> reptilesArrayList = new ArrayList<>();
    public FragmentHomeBinding binding;
    Context context;
    AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        db = AppDatabase.getDatabase(context);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerView);

        getAllPlants();
        initAdapter();

        return root;
    }
    public void initAdapter() {
        reptilesViewAdapter = new ReptilesViewAdapter(reptilesArrayList, getActivity());
        recyclerView.setAdapter(reptilesViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reptilesViewAdapter.notifyItemChanged(0, reptilesArrayList.size());
    }
    void getAllPlants(){
        List<UserReptiles> listDb = db.dao_userReptile().getAllUserReptiles();
        reptilesArrayList.clear();
        for(int i = 0; i < listDb.size(); i ++){
            reptilesArrayList.add(new ReptileListItem(listDb.get(i)
            ));
        }
        reptilesArrayList.add(null);
    }
    @Override
    public void onResume() {
        super.onResume();
        getAllPlants();
        reptilesViewAdapter.notifyItemChanged(0, reptilesArrayList.size());
    }
}