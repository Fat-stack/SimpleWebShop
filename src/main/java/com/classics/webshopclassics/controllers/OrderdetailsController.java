package com.classics.webshopclassics.controllers;


import com.classics.webshopclassics.entities.Customers;
import com.classics.webshopclassics.entities.Orderdetails;
import com.classics.webshopclassics.entities.Orders;
import com.classics.webshopclassics.entities.Product;
import com.classics.webshopclassics.models.ShoppingCart;
import com.classics.webshopclassics.repositories.OrderdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("shoppingCart")
public class OrderdetailsController {
    @Autowired
    private OrderdetailsRepository orderdetailsRepository;


    @GetMapping("/create-new-orderdetails")
    public String createNewOrderDetails(HttpServletRequest request,
                                        @ModelAttribute Orderdetails orderdetails,
                                        @SessionAttribute ShoppingCart shoppingCart) {
        System.out.println("ORDERDETAILS / create-new-orderdetails");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        Orders orders = (Orders) inputFlashMap.get("orders");

        System.out.println(orders.getOrdernumber());

        // ta in shoppingcart rad för rad

        List<Product> tempList = shoppingCart.getShoppingCartList();


        for (int i = 0; i < tempList.size(); i++) {
            Product cartItem = tempList.get(i);
            orderdetails.setOrdernumber(orders.getOrdernumber());
            orderdetails.setProductcode(cartItem.getProductcode());
            orderdetails.setQuantityordered((long) cartItem.getQuantity());
            orderdetails.setPriceeach(cartItem.getMsrp());
            orderdetails.setOrderlinenumber((long)i+1);



            orderdetailsRepository.save(orderdetails);
        }




        return "redirect:/orderConfirmation";
    }

    @GetMapping("/orderConfirmation")
    public String orderConfirmation() {


        System.out.println("ORDERCONFIRMATION");
        return "order-confirmation";
    }




    /************TEST MOT DB * *******************/

    @GetMapping("/test")
    public String viewFrontTop10(Model model){
        List<Orderdetails> tempList = orderdetailsRepository.findAll();
        model.addAttribute("orderdetails", tempList);
        System.out.println("TEST IN SOME CONTROLLER");
        return "test";
    }

            /************TEST MOT DB * *******************/








}
