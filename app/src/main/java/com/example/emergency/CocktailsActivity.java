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

public class CocktailsActivity extends AppCompatActivity {
    private RecyclerView cocktailsRecyclerView;
    private ArrayList<Recipe> cocktailsRecipeData;
    private RecipeAdapter cocktailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_white);

        cocktailsRecyclerView = findViewById(R.id.recycler_cocktails);
        cocktailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cocktailsRecipeData = new ArrayList<>();
        cocktailsAdapter = new RecipeAdapter(cocktailsRecipeData, this);
        cocktailsRecyclerView.setAdapter(cocktailsAdapter);
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(cocktailsRecipeData, from, to);
                cocktailsAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cocktailsRecipeData.remove(viewHolder.getAdapterPosition());
                cocktailsAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(cocktailsRecyclerView);
    }

    private void initializeData() {
        String[] cocktailsTitles = getResources().getStringArray(R.array.cocktails_titles);
        String[] cocktailsDescriptions = getResources().getStringArray(R.array.cocktails_descriptions);
        TypedArray cocktailsImages = getResources().obtainTypedArray(R.array.cocktail_images);

        cocktailsRecipeData.clear();

        for (int i = 0; i < cocktailsTitles.length; i++) {
            cocktailsRecipeData.add(new Recipe(cocktailsImages.getResourceId(i,0), cocktailsTitles[i], cocktailsDescriptions[i]));
        }

        cocktailsImages.recycle();
        cocktailsAdapter.notifyDataSetChanged();
    }
}
