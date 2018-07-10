package com.test.DaoImplementation;

import com.test.DaoInterfaces.ProductDAO;
import com.test.model.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class ProductDaoImpl implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addProduct(Product product) {
        em.merge(product);
        logger.info("Product successfully added. Product details: " + product);
    }

    @Override
    public Product getProductByID(int Id) {
        try {
            Query query2 = em.createQuery("from Product where ID=:productID ");
            query2.setParameter("productID", Id);
            Product product = (Product) query2.getSingleResult();
            logger.info("Product successfully loaded. Product details: " + product);
            return product;
        } catch (Exception e) {
            logger.error("Exception while trying to getProductByID. Product id is not present.");
            return null;
        }
    }

    @Override
    public Product getProductByName(String name) {
        Query query2 = em.createQuery("from Product where name=:name ");
        query2.setParameter("name", name);
        List<Product> list = (List) query2.getResultList();
        Product product = null;
        if (!(list.isEmpty())) {
            product = (Product) query2.getSingleResult();
        }
        logger.info("Product successfully loaded. Product details: " + product);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        em.merge(product);
        logger.info("Product successfully updated. Product details: " + product);
    }

    @Override
    public Product removeProduct(int id) {
        try {
            Product product = new Product();
            Query query2 = em.createQuery("from Product where ID=:productID ");
            query2.setParameter("productID", id);
            product = (Product) query2.getSingleResult();
            if (product != null) {
                em.remove(product);
            }
            logger.info("Product successfully removed. Product details: " + product);
            return product;
        } catch (Exception e) {
            logger.error("Exception while trying to remove product. Product id is not present.");
            return null;
        }
    }

    @Override
    public List<Product> listProducts() {
        Query query2 = em.createQuery("from Product");
        List<Product> products = query2.getResultList();
        for (Product product : products) {
            logger.info("List product details: " + product);
        }
        return products;
    }
}
