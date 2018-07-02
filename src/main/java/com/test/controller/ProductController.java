package com.test.controller;

import com.test.model.Product;
import com.test.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProducts(ModelMap model) {

        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", productService.listProducts());

        return "/products";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String addProduct(ModelMap model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("print", "Name or price of the product iz not valid.");
            model.addAttribute("product", new Product());
            model.addAttribute("listProducts", productService.listProducts());
            return "/products";
        }
        try {

            if (productService.getProductByName(product.getName()) == null) {
                if (product.getID() == null) {
                    productService.addProduct(product);
                } else {
                    productService.updateProduct(product);
                }
                return "redirect:/products";
            } else {
                model.addAttribute("print", "Product with name " + product.getName() + " already exists.");
                model.addAttribute("product", new Product());
                model.addAttribute("listProducts", productService.listProducts());
                return "/products";
            }
        } catch (Exception e) {
            model.addAttribute("print", e.getMessage());
            return "/products";
        }
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeProduct(@PathVariable("id") int id) {
        productService.removeProduct(id);
        return "redirect:/products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductByID(id));
        model.addAttribute("listProducts", productService.listProducts());
        return "/products";
    }
}
