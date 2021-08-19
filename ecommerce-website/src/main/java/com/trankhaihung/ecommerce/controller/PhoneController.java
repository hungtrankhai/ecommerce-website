package com.trankhaihung.ecommerce.controller;

import com.trankhaihung.ecommerce.entity.Product;
import com.trankhaihung.ecommerce.service.ProductService;
import com.trankhaihung.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PhoneController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public ModelAndView index(Model model){
        ModelAndView mav = new ModelAndView("index");
        List<Product> products = productService.getProducts();
        mav.addObject("products", products);
        Integer numeberOfItemsInCart = shoppingCartService.getNumberOfItemsInCart();
        mav.addObject("numberOfItem", numeberOfItemsInCart);

        return mav;
    }
}
