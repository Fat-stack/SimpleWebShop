package com.classics.webshopclassics.controllers;

import com.classics.webshopclassics.entities.Customers;
import com.classics.webshopclassics.entities.Orders;
import com.classics.webshopclassics.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class OrderController {


    @Autowired
    private OrdersRepository ordersRepository;


   @GetMapping("/create-new-order")
    public String createNewOrder(HttpServletRequest request,
                                 @ModelAttribute Orders orders,
                                 RedirectAttributes redirectAttributes){
                        System.out.println("ORDERCONTROLLER / CREATE NEW ORDER");
       Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
       if (inputFlashMap != null) {
           Customers customers = (Customers) inputFlashMap.get("customers");
           System.out.println(customers.getCustomernumber());

                         // HÄMTA SISTA ORDERNUMRET OCH LÄGG PÅ ETT TILL NÄSTA ORDER
           List<Orders> lastOrderList = ordersRepository.findTop1ByOrderByOrdernumberDesc();
           Long nextOrderNumber = lastOrderList.get(0).getOrdernumber() + 1 ;
           System.out.println(nextOrderNumber);



           Date date = new Date();
           orders.setOrdernumber(nextOrderNumber);
           orders.setOrderdate(date);
           orders.setRequireddate(date);
           orders.setShippeddate(null);
           orders.setStatus("OK");
           orders.setComments("no comment ");
           orders.setCustomernumber(customers.getCustomernumber());

           ordersRepository.save(orders);

            redirectAttributes.addFlashAttribute("orders", orders);

           return "redirect:/create-new-orderdetails";
       } else {
           // FIXA EN ERROR SIDA::::
           return "redirect:/home";
       }
   }


}