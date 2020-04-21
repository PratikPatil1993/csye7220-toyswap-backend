package com.backend.toyswap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.backend.toyswap.model.PurchasedToys;
import com.backend.toyswap.model.Toy;
import com.backend.toyswap.model.User;
import com.backend.toyswap.service.AuthService;
import com.backend.toyswap.service.PurchasedToyService;
import com.backend.toyswap.service.ToyService;
import com.backend.toyswap.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/toys")
public class ToyController {

    @Autowired
    private ToyService toyService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PurchasedToyService purchasedToyService;


    @GetMapping("")
    public List<Toy> getAllToys(@RequestHeader("authorization") String authHeader, @RequestParam(required = false) String name) {
    	String email = authService.getLoggedInUserEmail(authHeader);
        User user = userService.findUserByEmail(email);
    	if (name != null) {
            return toyService.getByName(name, user.getId());
        }
        return toyService.getAll(user.getId());
    }

    @GetMapping("/{id}")
    public Toy getByID(@PathVariable Long id) {
        return toyService.getByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public  ResponseEntity<Object> create(@RequestHeader("authorization") String authHeader,
    		@RequestParam("file") Object multipartFile ,
    		@RequestParam("toy_Name") String toyName,
    		@RequestParam("toy_Price") int toyPrice) {
    	String email = authService.getLoggedInUserEmail(authHeader);
        User user = userService.findUserByEmail(email);
        Toy newToy = new Toy(toyName, toyPrice, "default", user, (MultipartFile) multipartFile);
        newToy.setUser(user);
        Toy toy = toyService.create(newToy);
        if(toy != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Object> updateUserBalance(@PathVariable Long id, @RequestHeader("authorization") String authHeader) {
        String email = authService.getLoggedInUserEmail(authHeader);
        User user = userService.findUserByEmail(email);
        Toy toy = toyService.getByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (user.getBalance() >= toy.getToy_Price()) {

            int newBalance = user.getBalance() - toy.getToy_Price();
            user.setBalance(newBalance);
            userService.update(user);
            PurchasedToys pToy = new PurchasedToys(toy.getToy_Name(),toy.getToy_Price(),toy.getToy_Photo(),user);
            purchasedToyService.create(pToy);
            toyService.deleteById(toy.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
