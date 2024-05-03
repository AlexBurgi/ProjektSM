package com.burgholzer.shoppingapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public static int MAINSCREEN = 1;
    public static int SETTINGS = 2;
    public static int SHARELIST = 3;
    public static int LISTDETAIL = 4;
    public static int NEWLIST = 5;
    public static int MAINCATEGORY = 6;
    public static int SUBCATEGORY = 7;
    public static int CHOOSEITEM = 8;
    public static int ADDITEM = 9;

    MutableLiveData<Integer> _state = new MutableLiveData<>(MAINSCREEN);
    LiveData<Integer> state = _state;
    public void showMainScreen(){
        _state.postValue(MAINSCREEN);
    }
    public void showSettings(){
        _state.postValue(SETTINGS);
    }
    public void showShareList(){
        _state.postValue(SHARELIST);
    }
    public void showListDetail(){
        _state.postValue(LISTDETAIL);
    }
    public void showNewList(){
        _state.postValue(NEWLIST);
    }
    public void showMainCategory(){
        _state.postValue(MAINCATEGORY);
    }
    public void showSubCategory(){
        _state.postValue(SUBCATEGORY);
    }
    public void showChooseItem(){
        _state.postValue(CHOOSEITEM);
    }
    public void showAddItem(){
        _state.postValue(ADDITEM);
    }
}
