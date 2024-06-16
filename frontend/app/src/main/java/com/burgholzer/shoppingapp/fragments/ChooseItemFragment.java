package com.burgholzer.shoppingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burgholzer.shoppingapp.MainViewModel;
import com.burgholzer.shoppingapp.R;
import com.burgholzer.shoppingapp.databinding.FragmentChooseItemBinding;
import com.burgholzer.shoppingapp.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseItemFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainViewModel mainViewModel;
    private FragmentChooseItemBinding binding;
    private int amount = 0;
    private Product product;

    public ChooseItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseItemFragment newInstance(String param1, String param2) {
        ChooseItemFragment fragment = new ChooseItemFragment();
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
        binding = FragmentChooseItemBinding.inflate(inflater, container, false);

        if (mainViewModel.getDarkmode() == 1) {
            binding.clChooseItem.setBackgroundColor(Color.DKGRAY);
            binding.textViewItemProductName.setTextColor(Color.WHITE);
            binding.textViewAmount.setTextColor(Color.WHITE);
            binding.textViewAmountHeadline.setTextColor(Color.WHITE);
        }

        binding.buttonBackChooseItem.setOnClickListener(this);
        binding.imageButtonMinus.setOnClickListener(this);
        binding.imageButtonPlus.setOnClickListener(this);
        binding.buttonAddToList.setOnClickListener(this);
        binding.buttonDone.setOnClickListener(this);

        String name = mainViewModel.getProductName();
        String image = mainViewModel.getImage();

        String amountText = binding.textViewAmount.getText().toString();
        if (amountText.matches("\\d+")) {
            amount = Integer.parseInt(amountText);
        } else {
            amount = 1;
        }

        if (name != null && image != null) {
            product = new Product(name, image);
            binding.imageViewItemChooseItem.setImageResource(getProduct(product));
            binding.textViewItemProductName.setText(name);
        } else {
            Log.e("ChooseItemFragment", "Produktname oder Bild ist null");
        }

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.buttonBackChooseItem.getId()) {
            mainViewModel.showMainCategory();
        }
        if (v.getId() == binding.imageButtonPlus.getId()) {
            amount++;
            binding.textViewAmount.setText(String.valueOf(amount));
        }
        if (v.getId() == binding.imageButtonMinus.getId()) {
            if (amount > 1) {
                amount--;
                binding.textViewAmount.setText(String.valueOf(amount));
            }
        }
        if (v.getId() == binding.buttonAddToList.getId()) {
            if (product != null) {
                mainViewModel.addProductToCurrentList(product, getContext());
                Log.d("subcategory: ", String.valueOf(mainViewModel.getMainCategoryIdentifier()));
                if (mainViewModel.getMainCategoryIdentifier() == 1) {
                    mainViewModel.showCookingSubCategory();
                } else if (mainViewModel.getMainCategoryIdentifier() == 2) {
                    mainViewModel.showHouseholdSubcategory();
                } else if (mainViewModel.getMainCategoryIdentifier() == 3) {
                    mainViewModel.showEntertainmentSubcategory();
                } else {
                    mainViewModel.showMainCategory();
                }
            } else {
                Log.e("ChooseItemFragment", "Produkt ist null und kann nicht hinzugefügt werden.");
            }
        }
        if (v.getId() == binding.buttonDone.getId()) {
            mainViewModel.addProductToCurrentList(product, getContext());
            mainViewModel.showMainScreen();
            mainViewModel.finishAddingProducts(getContext());
        }
    }

    public int getProduct(Product product) {
        if (product.getImage().equals("apfel.png")) {
            return R.drawable.apfel;
        }
        if (product.getImage().equals("birne.png")) {
            return R.drawable.birne;
        }
        if (product.getImage().equals("weintrauben.png")) {
            return R.drawable.weintrauben;
        }
        if (product.getImage().equals("banane.png")) {
            return R.drawable.banane;
        }
        if (product.getImage().equals("orange.png")) {
            return R.drawable.orange;
        }
        if (product.getImage().equals("tomate.png")) {
            return R.drawable.tomate;
        }
        if (product.getImage().equals("gurke.png")) {
            return R.drawable.cucumber;
        }
        if (product.getImage().equals("karotte.png")) {
            return R.drawable.carrot;
        }
        if (product.getImage().equals("spinat.png")) {
            return R.drawable.spinach;
        }
        if (product.getImage().equals("chicken.png")) {
            return R.drawable.turkey;
        }
        if (product.getImage().equals("steak.png")) {
            return R.drawable.steak;
        }
        if (product.getImage().equals("pork.png")) {
            return R.drawable.pork;
        }
        if (product.getImage().equals("milk.png")) {
            return R.drawable.milk;
        }
        if (product.getImage().equals("cheese.png")) {
            return R.drawable.cheese;
        }
        if (product.getImage().equals("yoghurt.png")) {
            return R.drawable.yoghurt;
        }
        if (product.getImage().equals("bread.png")) {
            return R.drawable.bread;
        }
        if (product.getImage().equals("rolls.png")) {
            return R.drawable.rolls;
        }
        if (product.getImage().equals("croissant.png")) {
            return R.drawable.croissant;
        }
        if (product.getImage().equals("microwave.png")) {
            return R.drawable.microwave;
        }
        if (product.getImage().equals("kettle.png")) {
            return R.drawable.kettle;
        }
        if (product.getImage().equals("toaster.png")) {
            return R.drawable.toaster;
        }
        if (product.getImage().equals("vacuumcleaner.png")) {
            return R.drawable.vacuum;
        }
        if (product.getImage().equals("washingMachine.png")) {
            return R.drawable.washingmachine;
        }
        if (product.getImage().equals("fridge.png")) {
            return R.drawable.fridge;
        }
        if (product.getImage().equals("cooking_pot.png")) {
            return R.drawable.pan;
        }
        if (product.getImage().equals("knife.png")) {
            return R.drawable.knife;
        }
        if (product.getImage().equals("scale.png")) {
            return R.drawable.scale;
        }
        if (product.getImage().equals("cuttingBoard.png")) {
            return R.drawable.cuttingboard;
        }
        if (product.getImage().equals("sieve.png")) {
            return R.drawable.sieve;
        }
        if (product.getImage().equals("bed.png")) {
            return R.drawable.bed;
        }
        if (product.getImage().equals("desk.png")) {
            return R.drawable.desk;
        }
        if (product.getImage().equals("chair.png")) {
            return R.drawable.chair;
        }
        if (product.getImage().equals("couch.png")) {
            return R.drawable.couchicon;
        }
        if (product.getImage().equals("table.png")) {
            return R.drawable.table;
        }
        if (product.getImage().equals("closet.png")) {
            return R.drawable.closet;
        }
        if (product.getImage().equals("spray.png")) {
            return R.drawable.spray;
        }
        if (product.getImage().equals("glasscleaner.png")) {
            return R.drawable.glasscleaner;
        }
        if (product.getImage().equals("bathcleaner.png")) {
            return R.drawable.bathcleaner;
        }
        if (product.getImage().equals("descaler.png")) {
            return R.drawable.descaler;
        }
        if (product.getImage().equals("rag.png")) {
            return R.drawable.rag;
        }

        if (product.getName().toUpperCase().contains("DVD")) {
            return R.drawable.dvd;
        }
        if (product.getName().toUpperCase().contains("BLU-RAY")) {
            return R.drawable.blueray;
        }
        if (product.getName().toUpperCase().contains("BUCH")) {
            return R.drawable.bookproduct;
        }
        if (product.getName().toUpperCase().contains("BRETTSPIEL")) {
            return R.drawable.game;
        }
        if (product.getName().toUpperCase().contains("VIDEOSPIEL")) {
            return R.drawable.videogameicon;
        }
        if (product.getName().toUpperCase().contains("KARTENSPIEL")) {
            return R.drawable.playingcards;
        }

        if (product.getImage().equals("football.png")) {
            return R.drawable.football;
        }
        if (product.getImage().equals("basketball.png")) {
            return R.drawable.basketball;
        }
        if (product.getImage().equals("tennis.png")) {
            return R.drawable.tennis;
        }
        if (product.getImage().equals("volleyball.png")) {
            return R.drawable.volleyball;
        }
        return 0;
    }
}
