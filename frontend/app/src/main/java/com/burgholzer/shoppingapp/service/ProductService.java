package com.burgholzer.shoppingapp.service;

import com.burgholzer.shoppingapp.model.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("api/products")
    Call<List<Product>> getProducts();

    @GET("api/{subcategoryId}")
    Call<List<Product>> getProductsBySubcategoryId(@Path("subcategoryId") int subcategoryId);
}