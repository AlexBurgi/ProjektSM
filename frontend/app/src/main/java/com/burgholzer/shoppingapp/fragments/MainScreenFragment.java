package com.burgholzer.shoppingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.burgholzer.shoppingapp.MainActivity;
import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.R;
import com.burgholzer.shoppingapp.databinding.FragmentMainScreenBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreenFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentMainScreenBinding binding;
    private MainViewModel mainViewModel;

    public MainScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainScreenFragment newInstance(String param1, String param2) {
        MainScreenFragment fragment = new MainScreenFragment();
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
        binding = FragmentMainScreenBinding.inflate(inflater, container, false);

        mainViewModel.fetchProductsBySubcategoryId(1);

        binding.btnBurger.setOnClickListener(this);
        binding.floatingActionButtonAddList.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==binding.btnBurger.getId()){
            Log.d("MainScreenFragment", "btnBurger clicked");
            PopupMenu popupMenu = new PopupMenu(getActivity(), v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if(item.getItemId() == R.id.menu_settings){
                    Log.d("MainScreenFragment", "Settings menu item clicked");
                    mainViewModel.showSettings();
                    return true;
                }
                return false;
            });
            popupMenu.show();
        }

        if(v.getId() == binding.floatingActionButtonAddList.getId()){
            Log.d("MainScreenFragment", "floatingActionButtonAddList clicked");
            mainViewModel.showNewList();
        }
    }
}