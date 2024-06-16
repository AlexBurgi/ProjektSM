package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSettingsBinding binding;
    private MainViewModel mainViewModel;
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        if(mainViewModel.getDarkmode() == 1){
            binding.clSettings.setBackgroundColor(Color.DKGRAY);
            binding.switchDarkmode.setChecked(true);
        }
        else if(mainViewModel.getDarkmode() == 0){
            binding.clSettings.setBackgroundColor(Color.WHITE);
            binding.switchDarkmode.setChecked(false);
        }

        binding.buttonBackSettings.setOnClickListener(this);
        binding.switchDarkmode.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonBackSettings.getId()){
            mainViewModel.showMainScreen();
            if(binding.switchDarkmode.isChecked()){
                mainViewModel.setDarkmode(1);
            }
            else if(!binding.switchDarkmode.isChecked()){
                mainViewModel.setDarkmode(0);
            }
        }
    }
}