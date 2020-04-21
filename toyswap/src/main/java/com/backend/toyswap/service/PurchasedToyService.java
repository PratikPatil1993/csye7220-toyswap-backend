package com.backend.toyswap.service;

import com.backend.toyswap.model.PurchasedToys;
import com.backend.toyswap.repository.PurchasedToysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchasedToyService {

    @Autowired
    PurchasedToysRepository purchasedToysRepository;


    public List<PurchasedToys> getAllPurchasedToys(long id){

        return purchasedToysRepository.findAllByUserId(id);



    }


public PurchasedToys create(PurchasedToys toy){
    return purchasedToysRepository.save(toy);
}
}
