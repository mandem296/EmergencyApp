package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BalutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balut);

        TextView balutTitle = findViewById(R.id.balut_title);
        TextView balutDescription = findViewById(R.id.balut_description);
        TextView balutRecipe = findViewById(R.id.balut_recipe);
        ImageView balutImage = findViewById(R.id.balut_image);

        String rTitle = "How to prepare " + getIntent().getStringExtra("dTitle");

        balutTitle.setText(getIntent().getStringExtra("dTitle"));
        balutDescription.setText(getIntent().getStringExtra("dDescription"));
        balutRecipe.setText(rTitle);

        Glide.with(this).load(getIntent().getIntExtra("dImage", 0)).into(balutImage);
    }
}
