package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentMainCategoryBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainCategoryFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentMainCategoryBinding binding;
    private MainViewModel mainViewModel;
    public MainCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainCategoryFragment newInstance(String param1, String param2) {
        MainCategoryFragment fragment = new MainCategoryFragment();
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
        binding = FragmentMainCategoryBinding.inflate(inflater, container, false);

        if(mainViewModel.getDarkmode() == 1){
            binding.clMainCategory.setBackgroundColor(Color.DKGRAY);
        }

        binding.buttonCategorieCooking.setOnClickListener(this);
        binding.buttonCategorieEntertainment.setOnClickListener(this);
        binding.buttonCategorieHousehold.setOnClickListener(this);
        binding.buttonBackMainCategory.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonBackMainCategory.getId()){
            mainViewModel.showNewList();
        }
        if(v.getId() == binding.buttonCategorieCooking.getId()){
            mainViewModel.setSubcategory(1);
            mainViewModel.setMainCategoryIdentifier(1);
            mainViewModel.showCookingSubCategory();
        }
        if(v.getId() == binding.buttonCategorieHousehold.getId()){
            mainViewModel.setSubcategory(2);
            mainViewModel.setMainCategoryIdentifier(2);
            mainViewModel.showHouseholdSubcategory();
        }
        if(v.getId() == binding.buttonCategorieEntertainment.getId()){
            mainViewModel.setSubcategory(3);
            mainViewModel.setMainCategoryIdentifier(3);
            mainViewModel.showEntertainmentSubcategory();
        }
    }
}