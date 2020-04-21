package com.backend.toyswap.repository;

import com.backend.toyswap.model.PurchasedToys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PurchasedToysRepository extends JpaRepository<PurchasedToys,Long> {
    List<PurchasedToys> findAllByUserId (long userId);
}
