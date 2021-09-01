package com.example.emergency;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {

    private ArrayList<Recipe> storeData;
    private Context myContext;

    public StoresAdapter(ArrayList<Recipe> storeData, Context myContext) {
        this.storeData = storeData;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public StoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.store_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoresAdapter.ViewHolder holder, int position) {
        Recipe currentStore = storeData.get(position);
        holder.bindTo(currentStore);
    }

    @Override
    public int getItemCount() {
        return storeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView myStoreImage;
        private TextView myStoreTitle;
        private TextView myStoreDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myStoreImage = itemView.findViewById(R.id.store_image);
            myStoreTitle = itemView.findViewById(R.id.store_name);
            myStoreDescription = itemView.findViewById(R.id.store_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int storePosition = getAdapterPosition();
                    Recipe currentStore = storeData.get(storePosition);

                    Intent balutIntent = new Intent(myContext, StoreActivity.class);
                    balutIntent.putExtra("dTitle", currentStore.getRecipeTitle());
                    balutIntent.putExtra("dImage", currentStore.getRecipeImage());
                    balutIntent.putExtra("dDescription", currentStore.getRecipeDescription());
                    myContext.startActivity(balutIntent);
                }
            });

        }

        public void bindTo(Recipe currentStore) {
            Glide.with(myContext).load(currentStore.getRecipeImage()).into(myStoreImage);
            myStoreTitle.setText(currentStore.getRecipeTitle());
            myStoreDescription.setText(currentStore.getRecipeDescription());
        }
    }
}
