package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.databinding.FragmentNewListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewListFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainViewModel mainViewModel;
    private FragmentNewListBinding binding;
    public NewListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewListFragment newInstance(String param1, String param2) {
        NewListFragment fragment = new NewListFragment();
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
        binding = FragmentNewListBinding.inflate(inflater, container, false);

        if(mainViewModel.getDarkmode() == 1){
            binding.clNewList.setBackgroundColor(Color.DKGRAY);
            binding.editTextCreateNewList.setTextColor(Color.WHITE);
        }

        binding.floatingActionButtonCheckName.setOnClickListener(this);
        binding.buttonBackNewList.setOnClickListener(this);

        mainViewModel.setNameList(binding.editTextCreateNewList.getText().toString());

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.buttonBackNewList.getId()) {
            mainViewModel.showMainScreen();
        } else if (v.getId() == binding.floatingActionButtonCheckName.getId()) {
            String listName = binding.editTextCreateNewList.getText().toString();
            mainViewModel.setCurrentListName(listName);
            mainViewModel.showMainCategory();
        }
    }
}