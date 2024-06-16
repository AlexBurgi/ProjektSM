package com.burgholzer.shoppingapp.fragments.subcategories;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentCookingSubCategoryBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookingSubCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookingSubCategoryFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentCookingSubCategoryBinding binding;
    private MainViewModel mainViewModel;
    public CookingSubCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CookingSubCategoryFragment newInstance(String param1, String param2) {
        CookingSubCategoryFragment fragment = new CookingSubCategoryFragment();
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
        binding = FragmentCookingSubCategoryBinding.inflate(inflater, container, false);

        if(mainViewModel.getDarkmode() == 1){
            binding.clSubcategory.setBackgroundColor(Color.DKGRAY);
        }

        binding.subcategoryFruit.setOnClickListener(this);
        binding.subcategoryVegetable.setOnClickListener(this);
        binding.subcategoryMeat.setOnClickListener(this);
        binding.subcateforyPastries.setOnClickListener(this);
        binding.subcategoryMilkProducts.setOnClickListener(this);
        binding.buttonBackSubCategoryCooking.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonBackSubCategoryCooking.getId()){
            mainViewModel.showMainCategory();
        }
        if(v.getId() == binding.subcategoryFruit.getId()){
            mainViewModel.setSubcategory(1);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryVegetable.getId()){
            mainViewModel.setSubcategory(2);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryMeat.getId()){
            mainViewModel.setSubcategory(3);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryMilkProducts.getId()){
            mainViewModel.setSubcategory(4);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcateforyPastries.getId()){
            mainViewModel.setSubcategory(5);
            mainViewModel.showAddItem();
        }
    }
}