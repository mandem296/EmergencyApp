package com.example.emergency;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoresFragment extends Fragment {
    private RecyclerView storesRecyclerView;
    private ArrayList<Recipe> storesData;
    private StoresAdapter storesAdapter;

    public StoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stores, container, false);

        storesRecyclerView = rootView.findViewById(R.id.recycler_stores);
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        storesData = new ArrayList<>();
        storesAdapter = new StoresAdapter(storesData, getContext());
        storesRecyclerView.setAdapter(storesAdapter);
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(storesData, from, to);
                storesAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                storesData.remove(viewHolder.getAdapterPosition());
                storesAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(storesRecyclerView);


        return rootView;
    }

    private void initializeData() {
        String[] storeNames = getResources().getStringArray(R.array.stores_names);
        String[] storeDescriptions = getResources().getStringArray(R.array.stores_descriptions);
        TypedArray storeImages = getResources().obtainTypedArray(R.array.stores_images);

        storesData.clear();

        for (int i = 0; i < storeNames.length; i++) {
            storesData.add(new Recipe(storeImages.getResourceId(i,0), storeNames[i], storeDescriptions[i]));
        }

        storeImages.recycle();
        storesAdapter.notifyDataSetChanged();
    }
}
