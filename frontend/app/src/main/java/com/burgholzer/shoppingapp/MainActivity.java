package com.burgholzer.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.burgholzer.shoppingapp.fragments.AddItemFragment;
import com.burgholzer.shoppingapp.fragments.ChooseItemFragment;
import com.burgholzer.shoppingapp.fragments.ListDetailFragment;
import com.burgholzer.shoppingapp.fragments.MainCategoryFragment;
import com.burgholzer.shoppingapp.fragments.MainScreenFragment;
import com.burgholzer.shoppingapp.fragments.NewListFragment;
import com.burgholzer.shoppingapp.fragments.SettingsFragment;
import com.burgholzer.shoppingapp.fragments.ShareListFragment;
import com.burgholzer.shoppingapp.fragments.SubCategoryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.state.observe(this, state -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(state == MainViewModel.MAINSCREEN){
                fragmentTransaction.replace(R.id.clMain, MainScreenFragment.newInstance("", ""), "MAINSCREEN_TAG");
            }
            else if(state == MainViewModel.SETTINGS){
                fragmentTransaction.replace(R.id.clMain, SettingsFragment.newInstance("",""), "SETTINGS_TAG");
            }
            else if(state == MainViewModel.SHARELIST){
                fragmentTransaction.replace(R.id.clMain, ShareListFragment.newInstance("",""), "SHARELIST_TAG");
            }
            else if(state == MainViewModel.LISTDETAIL){
                fragmentTransaction.replace(R.id.clMain, ListDetailFragment.newInstance("",""), "LISTDETAIL_TAG");
            }
            else if(state == MainViewModel.NEWLIST){
                fragmentTransaction.replace(R.id.clMain, NewListFragment.newInstance("",""), "NEWLIST_TAG");
            }
            else if(state == MainViewModel.MAINCATEGORY){
                fragmentTransaction.replace(R.id.clMain, MainCategoryFragment.newInstance("",""), "MAINCATEGORY_TAG");
            }
            else if(state == MainViewModel.SUBCATEGORY){
                fragmentTransaction.replace(R.id.clMain, SubCategoryFragment.newInstance("",""), "SUBCATEGORY_TAG");
            }
            else if(state == MainViewModel.CHOOSEITEM){
                fragmentTransaction.replace(R.id.clMain, ChooseItemFragment.newInstance("",""), "CHOOSEITEM_TAG");
            }
            else if(state == MainViewModel.ADDITEM){
                fragmentTransaction.replace(R.id.clMain, AddItemFragment.newInstance("",""), "ADDITEM_TAG");
            }
            fragmentTransaction.commit();
        });
    }
}