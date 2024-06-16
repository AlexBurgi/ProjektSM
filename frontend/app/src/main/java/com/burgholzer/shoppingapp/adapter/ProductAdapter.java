package com.burgholzer.shoppingapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.R;
import com.burgholzer.shoppingapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private MainViewModel mainViewModel;
    public ProductAdapter(List<Product> products, MainViewModel mainViewModel) {
        this.products = products;
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.image.setImageResource(setImage(product));
        if(mainViewModel.getDarkmode() == 1){
            holder.itemView.setBackgroundColor(Color.DKGRAY);
            holder.name.setTextColor(Color.WHITE);
        }
        holder.image.setOnClickListener(v -> {
            if(mainViewModel.getOnClickBlocker() == 0){
                Product clickedProduct = products.get(position);
                mainViewModel.setProductName(clickedProduct.getName());
                mainViewModel.setImage(clickedProduct.getImage());
                mainViewModel.showChooseItem();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            image = itemView.findViewById(R.id.productImage);
        }
    }

    public int setImage(Product product){
        if(product.getImage().equals("apfel.png")){
            return R.drawable.apfel;
        }
        if(product.getImage().equals("birne.png")){
            return R.drawable.birne;
        }
        if(product.getImage().equals("weintrauben.png")){
            return R.drawable.weintrauben;
        }
        if(product.getImage().equals("banane.png")){
            return R.drawable.banane;
        }
        if(product.getImage().equals("orange.png")){
            return R.drawable.orange;
        }
        if(product.getImage().equals("tomate.png")){
            return R.drawable.tomate;
        }
        if(product.getImage().equals("gurke.png")){
            return R.drawable.cucumber;
        }
        if(product.getImage().equals("karotte.png")){
            return R.drawable.carrot;
        }
        if(product.getImage().equals("spinat.png")){
            return R.drawable.spinach;
        }
        if(product.getImage().equals("chicken.png")){
            return R.drawable.turkey;
        }
        if(product.getImage().equals("steak.png")){
            return R.drawable.steak;
        }
        if(product.getImage().equals("pork.png")){
            return R.drawable.pork;
        }
        if(product.getImage().equals("milk.png")){
            return R.drawable.milk;
        }
        if(product.getImage().equals("cheese.png")){
            return R.drawable.cheese;
        }
        if(product.getImage().equals("yoghurt.png")){
            return R.drawable.yoghurt;
        }
        if(product.getImage().equals("bread.png")){
            return R.drawable.bread;
        }
        if(product.getImage().equals("rolls.png")){
            return R.drawable.rolls;
        }
        if(product.getImage().equals("croissant.png")){
            return R.drawable.croissant;
        }
        if(product.getImage().equals("microwave.png")){
            return R.drawable.microwave;
        }
        if(product.getImage().equals("kettle.png")){
            return R.drawable.kettle;
        }
        if(product.getImage().equals("toaster.png")){
            return R.drawable.toaster;
        }
        if(product.getImage().equals("vacuumcleaner.png")){
            return R.drawable.vacuum;
        }
        if(product.getImage().equals("washingMachine.png")){
            return R.drawable.washingmachine;
        }
        if(product.getImage().equals("fridge.png")){
            return R.drawable.fridge;
        }
        if(product.getImage().equals("cooking_pot.png")){
            return R.drawable.pan;
        }
        if(product.getImage().equals("knife.png")){
            return R.drawable.knife;
        }
        if(product.getImage().equals("scale.png")){
            return R.drawable.scale;
        }
        if(product.getImage().equals("cuttingBoard.png")){
            return R.drawable.cuttingboard;
        }
        if(product.getImage().equals("sieve.png")){
            return R.drawable.sieve;
        }
        if(product.getImage().equals("bed.png")){
            return R.drawable.bed;
        }
        if(product.getImage().equals("desk.png")){
            return R.drawable.desk;
        }
        if(product.getImage().equals("chair.png")){
            return R.drawable.chair;
        }
        if(product.getImage().equals("couch.png")){
            return R.drawable.couchicon;
        }
        if(product.getImage().equals("table.png")){
            return R.drawable.table;
        }
        if(product.getImage().equals("closet.png")){
            return R.drawable.closet;
        }
        if(product.getImage().equals("spray.png")){
            return R.drawable.spray;
        }
        if(product.getImage().equals("glasscleaner.png")){
            return R.drawable.glasscleaner;
        }
        if(product.getImage().equals("bathcleaner.png")){
            return R.drawable.bathcleaner;
        }
        if(product.getImage().equals("descaler.png")){
            return R.drawable.descaler;
        }
        if(product.getImage().equals("rag.png")){
            return R.drawable.rag;
        }

        if(product.getName().toUpperCase().contains("DVD")){
            return R.drawable.dvd;
        }
        if(product.getName().toUpperCase().contains("BLU-RAY")){
            return R.drawable.blueray;
        }
        if(product.getName().toUpperCase().contains("BUCH")){
            return R.drawable.bookproduct;
        }
        if(product.getName().toUpperCase().contains("BRETTSPIEL")){
            return R.drawable.game;
        }
        if(product.getName().toUpperCase().contains("VIDEOSPIEL")){
            return R.drawable.videogameicon;
        }
        if(product.getName().toUpperCase().contains("KARTENSPIEL")){
            return R.drawable.playingcards;
        }

        if(product.getImage().equals("football.png")){
            return R.drawable.football;
        }
        if(product.getImage().equals("basketball.png")){
            return R.drawable.basketball;
        }
        if(product.getImage().equals("tennis.png")){
            return R.drawable.tennis;
        }
        if(product.getImage().equals("volleyball.png")){
            return R.drawable.volleyball;
        }
        return 0;
    }
}