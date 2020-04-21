package com.backend.toyswap.controller;
import com.backend.toyswap.model.PurchasedToys;
import com.backend.toyswap.model.User;
import com.backend.toyswap.service.AuthService;
import com.backend.toyswap.service.PurchasedToyService;
import com.backend.toyswap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchasedtoys")
public class PurchasedToyController {
    @Autowired
    private PurchasedToyService purchasedToyService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<PurchasedToys> getAllPurchasedToys(@RequestHeader("authorization") String authHeader){
        String email = authService.getLoggedInUserEmail(authHeader);
        User user = userService.findUserByEmail(email);
         return purchasedToyService.getAllPurchasedToys(user.getId());
    }



}
