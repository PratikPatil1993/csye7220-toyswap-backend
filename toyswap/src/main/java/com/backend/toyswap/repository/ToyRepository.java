package com.backend.toyswap.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.toyswap.model.Toy;

/**
 * Spring repository interface for Toys.
 * Allow us to access the methods in JPA Repository
 */
@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
    List<Toy> findAllByUserId(Long userId);
    
    @Query(value ="select * from toy t where t.user_id <> ?1", nativeQuery = true)
    List<Toy> findAllForUserId(@Param("userid") Long id);

}
