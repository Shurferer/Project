package com.test.service;

import com.test.DaoImplementation.ProductDaoImpl;
import com.test.model.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDaoImpl productDao;

    @Transactional
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Transactional
    public Product getProductByName(String name) {
        return productDao.getProductByName(name);
    }

    @Transactional
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Transactional
    public Product removeProduct(int id) {
        return productDao.removeProduct(id);
    }

    @Transactional
    public List<Product> listProducts() {
        return productDao.listProducts();
    }

    @Transactional
    public Product getProductByID(int Id) {
        return productDao.getProductByID(Id);
    }
}
