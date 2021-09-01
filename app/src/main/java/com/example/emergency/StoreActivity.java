package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView balutTitle = findViewById(R.id.store_view_name);
        TextView balutDescription = findViewById(R.id.store_news);
        ImageView balutImage = findViewById(R.id.store_view_image);

        balutTitle.setText(getIntent().getStringExtra("dTitle"));
        balutDescription.setText(getIntent().getStringExtra("dDescription"));

        Glide.with(this).load(getIntent().getIntExtra("dImage", 0)).into(balutImage);
    }

}
