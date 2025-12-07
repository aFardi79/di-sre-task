package ir.cactus.repository;


import ir.cactus.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    @Query("select i from Inventory i where i.productId=:productId")
    Optional<Inventory> findInventoryByProductId(@Param("productId") Long productId);

}
