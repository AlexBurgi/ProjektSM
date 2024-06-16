package com.burgholzer.shoppingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.burgholzer.shoppingapp.model.Product;
import com.burgholzer.shoppingapp.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    public static int MAINSCREEN = 1;
    public static int SETTINGS = 2;
    public static int LISTDETAIL = 3;
    public static int NEWLIST = 4;
    public static int MAINCATEGORY = 5;
    public static int COOKING_SUBCATEGORY = 6;
    public static int HOUSEHOLD_SUBCATEGORY = 7;
    public static int ENTERTAINMENT_SUBCATEGORY = 8;
    public static int CHOOSEITEM = 9;
    public static int ADDITEM = 10;

    MutableLiveData<Integer> _state = new MutableLiveData<>(MAINSCREEN);
    LiveData<Integer> state = _state;

    public void showMainScreen() {
        _state.postValue(MAINSCREEN);
    }

    public void showSettings() {
        _state.postValue(SETTINGS);
    }

    public void showListDetail() {
        _state.postValue(LISTDETAIL);
    }

    public void showNewList() {
        _state.postValue(NEWLIST);
    }

    public void showMainCategory() {
        _state.postValue(MAINCATEGORY);
    }

    public void showCookingSubCategory() {
        _state.postValue(COOKING_SUBCATEGORY);
    }

    public void showHouseholdSubcategory() {
        _state.postValue(HOUSEHOLD_SUBCATEGORY);
    }

    public void showEntertainmentSubcategory() {
        _state.postValue(ENTERTAINMENT_SUBCATEGORY);
    }

    public void showChooseItem() {
        _state.postValue(CHOOSEITEM);
    }

    public void showAddItem() {
        _state.postValue(ADDITEM);
    }

    //Settings
    private int darkmode = 0;

    public int getDarkmode() {
        return darkmode;
    }

    public void setDarkmode(int darkmode) {
        this.darkmode = darkmode;
    }

    //list name
    private String nameList;

    public void setNameList(String nameList) {
        this.nameList = nameList;
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

    private int subcategory = 1;

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public void fetchProductsBySubcategoryId() {
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
                if (response.isSuccessful()) {
                    Log.d("API Response Body:", response.body().toString());
                    setIsLoading(false);
                    List<Product> products = response.body();
                    Log.d("API Response:", products.toString());

                    fruits.setValue(products);
                } else {
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

    //Category
    private int mainCategoryIdentifier;

    public int getMainCategoryIdentifier() {
        return mainCategoryIdentifier;
    }

    public void setMainCategoryIdentifier(int mainCategoryIdentifier) {
        this.mainCategoryIdentifier = mainCategoryIdentifier;
    }

    //choose item
    String productName;
    String image;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //add item to list
    private MutableLiveData<List<String>> listNames = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> currentListName = new MutableLiveData<>();
    private MutableLiveData<List<Product>> currentListProducts = new MutableLiveData<>(new ArrayList<>());

    private Map<String, List<Product>> listProductsMap = new HashMap<>();

    public LiveData<List<String>> getListNames() {
        return listNames;
    }

    public LiveData<String> getCurrentListName() {
        return currentListName;
    }

    public LiveData<List<Product>> getCurrentListProducts() {
        return currentListProducts;
    }

    public void setCurrentListProducts(List<Product> products) {
        currentListProducts.setValue(products);
    }

    public void setCurrentListName(String listName) {
        currentListName.setValue(listName);
        List<Product> products = getProductsForList(listName);
        currentListProducts.setValue(products);
    }

    public void addProductToCurrentList(Product product, Context context) {
        if (product != null) {
            List<Product> products = currentListProducts.getValue();
            if (products != null) {
                products.add(product);
                currentListProducts.setValue(products);
                listProductsMap.put(currentListName.getValue(), products);
                Log.d("MainViewModel", "Produkt hinzugefügt: " + product.getName());
                saveListsToPreferences(context);
            } else {
                Log.e("MainViewModel", "Aktuelle Liste der Produkte ist null.");
            }
        } else {
            Log.e("MainViewModel", "Produkt ist null und kann nicht zur Liste hinzugefügt werden.");
        }
    }

    public List<Product> getProductsForList(String listName) {
        return listProductsMap.getOrDefault(listName, new ArrayList<>());
    }

    public void finishAddingProducts(Context context) {
        List<String> currentLists = listNames.getValue();
        String listName = currentListName.getValue();
        if (listName != null && !listName.isEmpty() && !currentLists.contains(listName)) {
            currentLists.add(listName);
            listNames.setValue(currentLists);
        }
        saveListsToPreferences(context);
        _state.postValue(MAINSCREEN);
    }

    public void deleteCurrentList(Context context) {
        String listName = currentListName.getValue();
        if (listName != null) {
            List<String> currentLists = listNames.getValue();
            currentLists.remove(listName);
            listNames.setValue(currentLists);

            listProductsMap.remove(listName);

            currentListName.setValue(null);
            currentListProducts.setValue(new ArrayList<>());
            saveListsToPreferences(context);
        }
    }

    //shared preferences
    private static final String PREFS_NAME = "shopping_app_prefs";
    private static final String LISTS_KEY = "lists_key";

    public void saveListsToPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listProductsMap);
        editor.putString(LISTS_KEY, json);
        editor.apply();
    }

    public void loadListsFromPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LISTS_KEY, "");
        if (!json.isEmpty()) {
            listProductsMap = gson.fromJson(json, new TypeToken<Map<String, List<Product>>>() {
            }.getType());
            listNames.setValue(new ArrayList<>(listProductsMap.keySet()));
        }
    }
    private int onClickBlocker = 0;

    public int getOnClickBlocker() {
        return onClickBlocker;
    }

    public void setOnClickBlocker(int onClickBlocker) {
        this.onClickBlocker = onClickBlocker;
    }
}
