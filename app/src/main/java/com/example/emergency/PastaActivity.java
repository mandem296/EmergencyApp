package com.example.emergency;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class PastaActivity extends AppCompatActivity {
    private RecyclerView pastaRecyclerView;
    private ArrayList<Recipe> pastaRecipeData;
    private RecipeAdapter pastaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_white);

        pastaRecyclerView = findViewById(R.id.recycler_pasta);
        pastaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pastaRecipeData = new ArrayList<>();
        pastaAdapter = new RecipeAdapter(pastaRecipeData, this);
        pastaRecyclerView.setAdapter(pastaAdapter);
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(pastaRecipeData, from, to);
                pastaAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pastaRecipeData.remove(viewHolder.getAdapterPosition());
                pastaAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(pastaRecyclerView);
    }

    private void initializeData() {
        String[] pastaTitles = getResources().getStringArray(R.array.pasta_titles);
        String[] pastaDescriptions = getResources().getStringArray(R.array.pasta_descriptions);
        TypedArray pastaImages = getResources().obtainTypedArray(R.array.pasta_images);

        pastaRecipeData.clear();

        for (int i = 0; i < pastaTitles.length; i++) {
            pastaRecipeData.add(new Recipe(pastaImages.getResourceId(i,0), pastaTitles[i], pastaDescriptions[i]));
        }

        pastaImages.recycle();
        pastaAdapter.notifyDataSetChanged();
    }
}
