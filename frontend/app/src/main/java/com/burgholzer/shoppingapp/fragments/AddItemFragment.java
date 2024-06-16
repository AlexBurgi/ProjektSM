package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.adapter.ProductAdapter;
import com.burgholzer.shoppingapp.databinding.FragmentAddItemBinding;
import com.burgholzer.shoppingapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentAddItemBinding binding;
    private MainViewModel mainViewModel;
    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentAddItemBinding.inflate(inflater, container, false);

        binding.buttonBackAddItem.setOnClickListener(this);

        if(mainViewModel.getDarkmode() == 1){
            binding.clAddItem.setBackgroundColor(Color.DKGRAY);
        }

        RecyclerView recyclerView = binding.recyclerViewAddProduct;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProductAdapter adapter = new ProductAdapter(new ArrayList<>(), mainViewModel);
        recyclerView.setAdapter(adapter);

        mainViewModel.fetchProductsBySubcategoryId();
        mainViewModel.getFruits().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d("AddItemFragment", "onChanged aufgerufen mit " + products.size() + " Produkten");
                if(products != null && !products.isEmpty()) {
                    adapter.setProducts(products);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("AddItemFragment", "Keine Produkte in der Kategorie Obst gefunden");
                }
            }
        });

        mainViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(!isLoading){
                    Log.d("Status", "fertig geladen");
                } else {
                    Log.d("Status", "Ladet noch");
                }
            }
        });

        return binding.getRoot();
    }
    @Override
    public void onClick(View v) {
        if(v.getId () == binding.buttonBackAddItem.getId()) {
            mainViewModel.showMainCategory();
        }
    }
}