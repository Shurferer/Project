package com.test.controller;

import com.test.model.Order;
import com.test.model.Product;
import com.test.model.User;
import com.test.service.OrderService;
import com.test.service.ProductService;
import com.test.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "addToOrder/{id}", method = RequestMethod.POST)
    public String addProductToOrder(@PathVariable("id") int id, ModelMap model, HttpSession session,
            @Valid Order order, BindingResult result) {
        String print;
        if (result.hasErrors()) {
            print = "Wrong number of products.";
            return "redirect:/clientPage/" + print;
        }
        ArrayList<Order> orderList = (ArrayList) session.getAttribute("orderList");
        Product product = productService.getProductByID(id);
        order.setProduct(product);
        Order order2 = null;
        for (Order order1 : orderList) {
            if (order.getProduct().getID().equals(order1.getProduct().getID())) {
                order.setNumberOfProducts(order.getNumberOfProducts() + order1.getNumberOfProducts());
                order2 = order1;
            }
        }
        if (!(order2 == null)) {
            orderList.remove(order2);
        }
        orderList.add(order);
        session.setAttribute("orderList", orderList);
        print = "Product added to your order. Please make one more or compleete your order.";
        return "redirect:/clientPage/" + print;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String saveOrder(ModelMap model, HttpSession session) {
        return "/basket";
    }

    @RequestMapping(value = "/orders/remove/{id}")
    public String removeProductFromOrder(@PathVariable("id") int id, ModelMap model, HttpSession session) {
        ArrayList<Order> orderList = (ArrayList) session.getAttribute("orderList");
        Order order1 = new Order();
        for (Order order : orderList) {
            if (order.getProduct().getID() == id) {
                order1 = order;
            }
        }
        orderList.remove(order1);
        session.setAttribute("orderList", orderList);
        return "redirect:/basket";
    }

    @RequestMapping(value = "basket/saveOrders", method = RequestMethod.POST)
    public String saveUserOrder(HttpSession session) {
        ArrayList<Order> orderList = (ArrayList) session.getAttribute("orderList");
        User user = (User) session.getAttribute("user");
        int payment = 0;
        for (Order order : orderList) {
            int id = order.getProduct().getID();
            Product product = productService.getProductByID(id);
            Integer maxOrderId= orderService.getLastOrderId();
            if (maxOrderId==null)maxOrderId=0;
            order.setId(maxOrderId+1);
            order.setProduct(product);
            order.setUser(user);
            order.setDate(new Date());
            product.getOrders().add(order);
            user.getOrders().add(order);
            userService.addUserToDataBase(user);
            payment += product.getPrice() * order.getNumberOfProducts();
        }
        return "redirect:/paymentPage/" + payment;
    }

    @RequestMapping(value = "/paymentPage/{payment}", method = RequestMethod.GET)
    public String returnToClientPage(@PathVariable("payment") int payment, ModelMap model) {
        model.addAttribute("print", payment);
        return "paymentPage";
    }

    @RequestMapping(value = "/finishOrder", method = RequestMethod.GET)
    public String finishUserOrder(HttpSession session) {
        session.invalidate();
                     String print = "Your order is compleete. Please make one more or leave the program.";
        return "redirect:/clientPage/" + print;
    }
}
