package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.adapter.ProductAdapter;
import com.burgholzer.shoppingapp.databinding.FragmentListDetailBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDetailFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainViewModel mainViewModel;
    private FragmentListDetailBinding binding;
    private ProductAdapter productAdapter;

    public ListDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDetailFragment newInstance(String param1, String param2) {
        ListDetailFragment fragment = new ListDetailFragment();
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
        binding = FragmentListDetailBinding.inflate(inflater, container, false);

        if (mainViewModel.getDarkmode() == 1) {
            binding.clListDetail.setBackgroundColor(Color.DKGRAY);
        }

        binding.buttonBackDetailFragment.setOnClickListener(this);
        binding.imageButtonDelete.setOnClickListener(this);

        binding.textViewHeadlineDetailFragment.setText(mainViewModel.getCurrentListName().getValue());

        RecyclerView recyclerView = binding.recyclerViewListDetail;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(new ArrayList<>(), mainViewModel);
        recyclerView.setAdapter(productAdapter);

        mainViewModel.getCurrentListProducts().observe(getViewLifecycleOwner(), products -> {
            productAdapter.setProducts(products);
            productAdapter.notifyDataSetChanged();
        });

        mainViewModel.setOnClickBlocker(1);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.buttonBackDetailFragment.getId()) {
            mainViewModel.showMainScreen();
            mainViewModel.setOnClickBlocker(0);
        }
        if (v.getId() == binding.imageButtonDelete.getId()) {
            mainViewModel.deleteCurrentList(getContext());
            mainViewModel.showMainScreen();
            mainViewModel.setOnClickBlocker(0);
        }
    }
}
