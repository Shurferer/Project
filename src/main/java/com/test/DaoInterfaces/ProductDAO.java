package com.test.DaoInterfaces;

import com.test.model.Product;
import java.util.List;

public interface ProductDAO {

    void addProduct(Product product);

    void updateProduct(Product product);

    Product removeProduct(int id);

    List<Product> listProducts();

    Product getProductByID (int Id);
    
    Product getProductByName(String name);

}
