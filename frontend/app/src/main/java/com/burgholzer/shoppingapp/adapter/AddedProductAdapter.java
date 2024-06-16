package com.burgholzer.shoppingapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.R;
import com.burgholzer.shoppingapp.model.Product;

import java.util.List;

public class AddedProductAdapter extends RecyclerView.Adapter<AddedProductAdapter.ProductViewHolder> {
    private List<String> listNames;
    private MainViewModel mainViewModel;

    public AddedProductAdapter(List<String> listNames, MainViewModel mainViewModel) {
        this.listNames = listNames;
        this.mainViewModel = mainViewModel;
    }

    public void setProducts(List<String> newListNames) {
        this.listNames = newListNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        String listName = listNames.get(position);
        holder.listNameTextView.setText(listName);
        if(mainViewModel.getDarkmode() == 1){
            holder.itemView.setBackgroundColor(Color.DKGRAY);
            holder.listNameTextView.setTextColor(Color.WHITE);
        }
        holder.itemView.setOnClickListener(v -> {
            mainViewModel.setCurrentListName(listName);
            List<Product> products = mainViewModel.getProductsForList(listName);
            mainViewModel.setCurrentListProducts(products);
            mainViewModel.showListDetail();
        });
    }

    @Override
    public int getItemCount() {
        return listNames.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView listNameTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            listNameTextView = itemView.findViewById(R.id.listNameMainScreen);
        }
    }
}

