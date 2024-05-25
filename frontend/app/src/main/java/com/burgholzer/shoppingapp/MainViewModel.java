package com.burgholzer.shoppingapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.burgholzer.shoppingapp.model.Product;
import com.burgholzer.shoppingapp.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    public static int MAINSCREEN = 1;
    public static int SETTINGS = 2;
    public static int SHARELIST = 3;
    public static int LISTDETAIL = 4;
    public static int NEWLIST = 5;
    public static int MAINCATEGORY = 6;
    public static int COOKING_SUBCATEGORY = 7;
    public static int HOUSEHOLD_SUBCATEGORY = 8;
    public static int ENTERTAINMENT_SUBCATEGORY = 9;
    public static int CHOOSEITEM = 10;
    public static int ADDITEM = 11;

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
    public void showCookingSubCategory(){
        _state.postValue(COOKING_SUBCATEGORY);
    }
    public void showHouseholdSubcategory(){
        _state.postValue(HOUSEHOLD_SUBCATEGORY);
    }
    public void showEntertainmentSubcategory(){
        _state.postValue(ENTERTAINMENT_SUBCATEGORY);
    }
    public void showChooseItem(){
        _state.postValue(CHOOSEITEM);
    }
    public void showAddItem(){
        _state.postValue(ADDITEM);
    }

    //Subcategory items
    //Fill items
    //cooking
    private MutableLiveData<List<Product>> fruits = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<Product>> vegetables = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<Product>> meat = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<Product>> diaryProducts = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<Product>> pastries = new MutableLiveData<>(new ArrayList<>());


    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public LiveData<List<Product>> getFruits() {
        return fruits;
    }

    public LiveData<List<Product>> getVegetables() {
        return vegetables;
    }

    public MutableLiveData<List<Product>> getMeat() {
        return meat;
    }

    public MutableLiveData<List<Product>> getDiaryProducts() {
        return diaryProducts;
    }

    public MutableLiveData<List<Product>> getPastries() {
        return pastries;
    }

    private int subcategory = 1;

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }


    public void fetchProductsBySubcategoryId(int subcategoryId) {
        setIsLoading(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService service = retrofit.create(ProductService.class);
        Call<List<Product>> call = service.getProductsBySubcategoryId(subcategory);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    Log.d("API Response Body:", response.body().toString());
                    setIsLoading(false);
                    List<Product> products = response.body();
                    Log.d("API Response:", products.toString()); // Log-Ausgabe hinzufügen

                    fruits.setValue(products);
                }
                else {
                    Log.d("API Response:", "Status Code: " + response.code() + " Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                setIsLoading(false);
                Log.d("API Failure:", t.getMessage());
            }
        });
    }
}
