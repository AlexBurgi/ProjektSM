package com.burgholzer.shoppingapp.fragments.subcategories;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentHouseholdSubcategorieBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HouseholdSubcategorieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HouseholdSubcategorieFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainViewModel mainViewModel;
    private FragmentHouseholdSubcategorieBinding binding;

    public HouseholdSubcategorieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HouseholdSubcategorieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HouseholdSubcategorieFragment newInstance(String param1, String param2) {
        HouseholdSubcategorieFragment fragment = new HouseholdSubcategorieFragment();
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
        binding = FragmentHouseholdSubcategorieBinding.inflate(inflater, container, false);

        if(mainViewModel.getDarkmode() == 1){
            binding.clHousehold.setBackgroundColor(Color.DKGRAY);
        }

        binding.subcategoryHouseholdDevices.setOnClickListener(this);
        binding.subcategoryKitchenItems.setOnClickListener(this);
        binding.subcategoryFurniture.setOnClickListener(this);
        binding.subcateforyCleaningAgent.setOnClickListener(this);
        binding.buttonBackSubCategoryHousehold.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonBackSubCategoryHousehold.getId()){
            mainViewModel.showMainCategory();
        }
        if(v.getId() == binding.subcategoryHouseholdDevices.getId()){
            mainViewModel.setSubcategory(6);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryKitchenItems.getId()){
            mainViewModel.setSubcategory(7);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryFurniture.getId()){
            mainViewModel.setSubcategory(8);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcateforyCleaningAgent.getId()){
            mainViewModel.setSubcategory(9);
            mainViewModel.showAddItem();
        }
    }
}