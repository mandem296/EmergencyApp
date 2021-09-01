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

public class PizzaActivity extends AppCompatActivity {
    private RecyclerView pizzaRecyclerView;
    private ArrayList<Recipe> pizzaRecipeData;
    private RecipeAdapter pizzaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_white);

        pizzaRecyclerView = findViewById(R.id.recycler_pizza);
        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzaRecipeData = new ArrayList<>();
        pizzaAdapter = new RecipeAdapter(pizzaRecipeData, this);
        pizzaRecyclerView.setAdapter(pizzaAdapter);
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(pizzaRecipeData, from, to);
                pizzaAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pizzaRecipeData.remove(viewHolder.getAdapterPosition());
                pizzaAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(pizzaRecyclerView);
    }

    private void initializeData() {
        String[] pizzaTitles = getResources().getStringArray(R.array.pizza_titles);
        String[] pizzaDescriptions = getResources().getStringArray(R.array.pizza_descriptions);
        TypedArray pizzaImages = getResources().obtainTypedArray(R.array.pizza_images);

        pizzaRecipeData.clear();

        for (int i = 0; i < pizzaTitles.length; i++) {
            pizzaRecipeData.add(new Recipe(pizzaImages.getResourceId(i,0), pizzaTitles[i], pizzaDescriptions[i]));
        }

        pizzaImages.recycle();
        pizzaAdapter.notifyDataSetChanged();
    }
}
