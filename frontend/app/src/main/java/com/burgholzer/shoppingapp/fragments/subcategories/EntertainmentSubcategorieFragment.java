package com.burgholzer.shoppingapp.fragments.subcategories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentEntertainmentSubcategorieBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntertainmentSubcategorieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntertainmentSubcategorieFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MainViewModel mainViewModel;
    private FragmentEntertainmentSubcategorieBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EntertainmentSubcategorieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntertainmentSubcategorieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntertainmentSubcategorieFragment newInstance(String param1, String param2) {
        EntertainmentSubcategorieFragment fragment = new EntertainmentSubcategorieFragment();
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
        binding = FragmentEntertainmentSubcategorieBinding.inflate(inflater,container,false);

        binding.subcategoryMovieAndBooks.setOnClickListener(this);
        binding.subcategoryGames.setOnClickListener(this);
        binding.subcategorySport.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.subcategoryMovieAndBooks.getId()){
            mainViewModel.setSubcategory(10);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategoryGames.getId()){
            mainViewModel.setSubcategory(11);
            mainViewModel.showAddItem();
        }
        if(v.getId() == binding.subcategorySport.getId()){
            mainViewModel.setSubcategory(12);
            mainViewModel.showAddItem();
        }
    }
}